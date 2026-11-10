package modello;

import eccezioni.*;
import it.kamaladafrica.codicefiscale.CodiceFiscale;
import persistenza.Database;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class FlottaService {
    static private HashMap<Integer,Utente> utenti = new HashMap<Integer, Utente>();
    static private HashMap<Integer,Veicolo> veicoli = new HashMap<Integer, Veicolo>();
    static private ArrayList<Noleggio> noleggi = new ArrayList<Noleggio>();
    static private Database database = null;

    public static void RegistraUtente(Utente utente) throws RossiGoException, SQLException {
        database = Database.getInstance();
        if(utenti.containsKey(utente.getId())){
            throw new RossiGoException("Id gia usato");
        }
        if(!CodiceFiscale.isFormatValid(utente.getCodiceFiscale())){
            throw new CodiceFiscaleNonValidoException("Codice fiscale non valido");
        }
        utenti.put(utente.getId(), utente);
        database.insertUtente(utente.getId(),utente.getNome(),utente.getEmail(),utente.getCodiceFiscale(),utente.getCredito());
    }
    public static void RegistraVeicolo(Veicolo v) throws RossiGoException,SQLException {
        if(veicoli.containsKey(v.getCodice())){
            throw new RossiGoException("Id gia presente");
        }
        database = Database.getInstance();
        veicoli.put(v.getCodice(), v);
        if(v instanceof Bici b){
            database.insertVeicolo(v.getCodice(), v.getBatteria(), v.getStato(), "bici");
        }
        else{
            database.insertVeicolo(v.getCodice(), v.getBatteria(), v.getStato(), "monopattino");
        }
    }
    public static void SbloccaVeicolo(int codice, int utente) throws RossiGoException,SQLException {
        if(!veicoli.containsKey(codice) || !veicoli.containsKey(utente)){
            throw new RossiGoException("Nessun veicolo trovato");
        }
        if(Objects.equals(veicoli.get(codice), "IN_USO")){
            throw new VeicoloNonDisponibileException("Veicolo gia in uso");
        }
        if(veicoli.get(codice).getBatteria()<15){
            throw new BatteriaScaricaException("Batteria scarica", veicoli.get(codice).getBatteria());
        }
        if(utenti.get(utente).getCredito() <1){
            throw new CreditoInsuficcienteException("Credito insufficente");
        }
        database = Database.getInstance();
        noleggi.add(new Noleggio(utenti.get(utente), veicoli.get(codice), LocalDateTime.now()));
        Noleggio noleggio = noleggi.getLast();
        database.insertNoleggio(noleggio.getIdUtente(),noleggio.getCodiceVeicolo(),noleggio.getOra_inizio(),null);
    }
    public static void mettiInRicarica(int codice) throws RossiGoException {
        Veicolo v = veicoli.get(codice);
        if(!veicoli.containsKey(codice)){
            throw new RossiGoException("Veicolo non trovato");
        }
        v.setStato(Stato_veicolo.In_ricarica);
        database.AggiornaStatoVeicolo(codice,v.getStato(),v.getBatteria());
    }
    public static void terminaNol(int min, int codice) throws SQLException {
        Noleggio trovato = null;
        if(min <= 0){
            throw new RossiGoException("Tempo insufficente");

        }
        for(Noleggio noleggio : noleggi){
            if(noleggio.getCodiceVeicolo() == codice){
                trovato = noleggio;
                break;
            }
        }
        if(trovato == null){
            throw new RossiGoException("Errore!");
        }
        Veicolo v =veicoli.get(codice);
        Utente u = utenti.get(trovato.getCodiceVeicolo());
        double togli = 1+u.getCredito();
        if(u.getCredito()-togli <= 0){
            u.setCredito(0);
        }
        else{
            u.setCredito(u.getCredito()-togli);
        }
        v.setStato(Stato_veicolo.Libero);
        v.setBatteria((int)(v.getBatteria()-v.consumoBatteriaPerMinuto()*min));
        database=Database.getInstance();
        database.AggiornaStatoVeicolo(codice,v.getStato(),v.getBatteria());
        database.AggiornaNoleggio(trovato.getIdUtente(),codice,trovato.getOra_fine());
        database.AggiornaCreditoUtente(u.getId(),u.getCredito());
    }
}
