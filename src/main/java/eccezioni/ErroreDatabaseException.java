package org.example.eccezioni;

public class ErroreDatabaseException extends RuntimeException {
    public ErroreDatabaseException(String message) {

        super(message);
    }
    public ErroreDatabaseException(String message, Throwable cause) {

        super(message, cause);
    }
}
