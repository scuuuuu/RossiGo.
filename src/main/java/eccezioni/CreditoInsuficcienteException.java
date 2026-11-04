package org.example.eccezioni;

public class CreditoInsuficcienteException extends RuntimeException {
    public CreditoInsuficcienteException(String message) {

        super(message);
    }
    public CreditoInsuficcienteException(String message, Throwable cause) {

        super(message,cause);
    }

}
