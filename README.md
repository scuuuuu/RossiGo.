# RossiGo Backend

Progetto Java sviluppato per il sistema di sharing locale di monopattini e bici elettriche. Il programma gestisce la flotta, la registrazione degli utenti, i noleggi e la ricarica dei mezzi, integrando la persistenza su database SQLite e una robusta gestione delle eccezioni personalizzate.

## Struttura del Progetto
Il codice è organizzato in package con responsabilità ben distinte:
- `modello`: Contiene la gerarchia dei veicoli (`Veicolo`, `Bici`, `Monopattino`), la classe `Utente`, `Noleggio` e l'enum `Stato_veicolo`.
- `eccezioni`: Raccoglie la gerarchia delle eccezioni personalizzate (`RossiGoException` e le relative eccezioni specifiche).
- `servizio`: Contiene `FlottaService` per la gestione della logica
- `persistenza`: Gestisce la connessione al database SQLite tramite il design pattern Singleton e l'uso sicuro di `PreparedStatement` e `try-with-resources`.
- `app`: Contiene il punto di ingresso (`Main`) con la simulazione degli scenari e la stampa in ASCII art.

## Librerie Esterne Utilizzate
Nel file `pom.xml` sono state configurate e utilizzate le seguenti librerie:
1. **SQLite JDBC (`org.xerial:sqlite-jdbc`)**: Scelta obbligatoria per la persistenza locale dei dati sul file `rossigo.db`, garantendo che utenti, veicoli e crediti sopravvivano al riavvio del programma.
2. **ASCII Art - JFiglet (`com.github.lalyos:jfiglet`)**: Utilizzata per stampare in modo visivo e accattivante il titolo "RossiGo" in stile banner all'avvio dell'applicazione.
3. **Codice Fiscale (`it.kamaladafrica:codice-fiscale`)**: Utilizzata per validare formalmente il codice fiscale dell'utente in fase di registrazione, lanciando un'eccezione dedicata in caso di formato non valido.

## Come Eseguire il Progetto
Assicurati di avere installato **Java 25** e **Maven**.

1. Clona il repository o apri la cartella del progetto nel tuo terminale / IDE (es. IntelliJ IDEA).
2. Compila e scarica le dipendenze con Maven:
   ```bash
   mvn clean install
