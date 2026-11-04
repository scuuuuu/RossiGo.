package org.example.eccezioni;

public class VeicoloNonDisponibileException extends RuntimeException {
    public VeicoloNonDisponibileException(String message) {

        super(message);
    }
    public VeicoloNonDisponibileException(String message, Throwable cause){
        super(message, cause);
    }
}
