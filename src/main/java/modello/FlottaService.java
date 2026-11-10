package modello;

import eccezioni.RossiGoException;
import persistenza.Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class FlottaService {
    static private HashMap<Integer,Utente> utenti = new HashMap<Integer, Utente>();
    static private HashMap<Integer,Veicolo> veicoli = new HashMap<Integer, Veicolo>();
    static private ArrayList<Noleggio> noleggi = new ArrayList<Noleggio>();
    static private Database database = null;

    public static void RegistraUtente(Utente utente) throws RossiGoException, SQLException {
        database = Database.getInstance();
    }
}
