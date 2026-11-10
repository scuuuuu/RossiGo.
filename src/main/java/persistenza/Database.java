package persistenza;

import eccezioni.ErroreDatabaseException;
import eccezioni.RossiGoException;

import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Database {
   private Connection connection = null;
   private static Database istance;
   private Database() throws SQLException {
       connection = DriverManager.getConnection("jdbc:sqlite:database.db");
   }

    public static Database getInstance() throws SQLException {
       if(istance == null){
           istance = new Database();
       }
       return istance;
    }

    public void insertUtente(int id,String nome,String email,String codiceFiscale,double credito) throws ErroreDatabaseException {
        if(!testConnection()){
            throw new ErroreDatabaseException("Connessione al db fallita");
        }
        String query = "INSERT INTO Utenti (IdUtente, Nome, Email, CodiceFiscale, Credito) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.setString(2,nome);
            statement.setString(3,email);
            statement.setString(4,codiceFiscale);
            statement.setDouble(5,credito);
            statement.execute();
        } catch (SQLException e) {
            throw new ErroreDatabaseException("Insert fallito " + e.getMessage());
        }
    }

    public void insertVeicolo(int codice,int batteria,String stato,String tipo) throws ErroreDatabaseException {
        if(!testConnection()){
            throw new ErroreDatabaseException("Impossibile connettersi al db");
        }
        String query = "INSERT INTO Veicoli (Codice, Batteria, Stato, Tipo) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(query);
            statement.setInt(1,codice);
            statement.setInt(2,batteria);
            statement.setString(3,stato);
            statement.setString(4,tipo);
            statement.execute();
        } catch (SQLException e) {
            throw new ErroreDatabaseException("Inserrt fallito: " + e.getMessage());
        }
    }


    public void insertNoleggio(int idUtente, int codiceVeicolo, LocalDateTime ora_inizio, LocalDateTime ora_fine) throws ErroreDatabaseException {
        if(!testConnection()){
            throw new ErroreDatabaseException("Connessione al db fallita");
        }
        String query = "INSERT INTO Noleggi (IdUtente, CodiceVeicolo, OraInizio, OraFine) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(query);
            statement.setInt(1,idUtente);
            statement.setInt(2,codiceVeicolo);
            statement.setObject(3,ora_inizio);
            statement.setObject(4,ora_fine);
            statement.execute();
        } catch (SQLException e) {
            throw new ErroreDatabaseException("Insert fallito: " +e.getMessage());
        }
    }

    public void AggiornaCreditoUtente(int idUtente,double credito) throws ErroreDatabaseException {
        if(!testConnection()){
            throw new ErroreDatabaseException("Connessione al db fallita");
        }
        String query = "UPDATE Utenti SET Credito = ? WHERE IdUtente = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setDouble(1,credito);
            statement.setInt(2,idUtente);
            statement.execute();
        } catch (SQLException e) {
            throw new ErroreDatabaseException("Update fallito: " + e.getMessage());
        }

    }

    public void AggiornaStatoVeicolo(int CodVeicolo,String stato,int livello) throws ErroreDatabaseException {
        if(!testConnection()){
            throw new ErroreDatabaseException("Connessione al db fallita");
        }
        String query = "UPDATE Veicoli SET Stato = ?,Batteria = ? WHERE Codice = ? ";
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(query);
            statement.setString(1,stato);
            statement.setInt(2,livello);
            statement.setInt(3,CodVeicolo);
            statement.execute();
        } catch (SQLException e) {
            throw new ErroreDatabaseException("Update fallito: " + e.getMessage());
        }
    }

    public void AggiornaNoleggio(int CodVeicolo,int IdUtente, LocalDateTime ora_fine) throws ErroreDatabaseException {
        if(!testConnection()){
            throw new ErroreDatabaseException("Impossbile collegarsi");
        }
        String query="UPDATE Noleggi SET OraFine = ? WHERE CodiceVeicolo = ? AND IdUtente = ?";
        PreparedStatement statement= null;
        try{
            statement=connection.prepareStatement(query);
            statement.setObject(1,ora_fine);
            statement.setInt(2,CodVeicolo);
            statement.setInt(3,IdUtente);
        }catch(SQLException e){
            throw new ErroreDatabaseException("Insert fallito:"+ e.getMessage());
        }
    }


    private boolean testConnection() {
        try {
            return connection.isValid(5);
        } catch (SQLException e) {
            return false;
        }
    }
}