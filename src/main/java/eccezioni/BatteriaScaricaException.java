package org.example.eccezioni;

public class BatteriaScaricaException extends RossiGoException {
    private int livelloAttuale;
    public BatteriaScaricaException(String message, int livelloAttuale) {

        super(message);
        this.livelloAttuale = livelloAttuale;
    }
    public BatteriaScaricaException(String message, int livelloAttuale, Throwable cause) {
        super(message, cause);
        this.livelloAttuale = livelloAttuale;
    }
    public int getLivelloAttuale(){
        return livelloAttuale;
    }
}
