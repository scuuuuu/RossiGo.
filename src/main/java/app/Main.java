import com.github.lalyos.jfiglet.FigletFont;
import modello.*;
import eccezioni.*;

import java.sql.SQLException;

import static java.lang.IO.println;

void main() throws SQLException {
    try {
        println(FigletFont.convertOneLine("RossiGo"));
    } catch (Exception e) {
        println("     ROSSIGO     ");
    }

    Utente u = new Utente(1, "Utente1", "User@gmail.com", "SCDTMS08L22C743W", 7.0);
    Bici b = new Bici(15, 65, Stato_veicolo.Libero, 6);
    Monopattino m = new Monopattino(20, 70, Stato_veicolo.Libero, 20);
    FlottaService.RegistraUtente(u);
    FlottaService.RegistraVeicolo(b);
    FlottaService.RegistraVeicolo(m);

    println("\n1-Sblocco con credito a 0.50€");
    try {
        Utente prova = new Utente(2, "Utenet2", "AKA12@gmail.com", "SCDTMS08L22C743W", 0.50);
        FlottaService.RegistraUtente(prova);
        FlottaService.SbloccaVeicolo(15, 2);
    } catch (CreditoInsuficcienteException e) {
        println("Credito non sufficente, ricarica");
    }

    println("\n2- Utente con codice fiscale sbagliato");
    try {
        Utente bottas = new Utente(3, "Vallteri Bottas", "vb@caddilac.com", "BOTTASVALL", 6.7);
        FlottaService.RegistraUtente(bottas);
    } catch (CodiceFiscaleNonValidoException e) {
        println("Codice fiscale errato");
    }

    println("\n3- Sblocco bici" );
    try {
        FlottaService.SbloccaVeicolo(15, 1);
        println("Sblocato");
        Utente leclerc = new Utente(4, "Charles Leclerc", "CL16@ferrari.com", "SCDTMS08L22C743W", 16.0);
        FlottaService.RegistraUtente(leclerc);
        FlottaService.SbloccaVeicolo(15, 4);
    } catch (RossiGoException e) {
        println("Veicolo già in uso");
    }

    println("\n4- Monopattino scarico");
    try {
        Monopattino lh = new Monopattino(44, 10, Stato_veicolo.Libero, 44);
        FlottaService.RegistraVeicolo(lh);
        FlottaService.SbloccaVeicolo(44, 1);
    } catch (BatteriaScaricaException e) {
        println("Batteria al " + e.getLivelloAttuale() + "% quindi scarica");
    }

    println("\n5- FIne noleggio");
    FlottaService.terminaNol(15, 15);
    println("Fine nol");

}