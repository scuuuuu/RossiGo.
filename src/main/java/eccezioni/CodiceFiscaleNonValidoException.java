package eccezioni;

public class CodiceFiscaleNonValidoException extends RuntimeException {
    public CodiceFiscaleNonValidoException(String message) {

        super(message);
    }
    public CodiceFiscaleNonValidoException(String message, Throwable cause) {

        super(message, cause);
    }
}
