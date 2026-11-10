package modello;

import jdk.jfr.Percentage;

public class Bici extends Veicolo{
    private int numeroMarce;
    public Bici(int codice, int batteria, Stato_veicolo stato, int numeroMarce) {
        super(codice, batteria, stato);
        this.numeroMarce = numeroMarce;
    }
    @Override
    public double calcolaCosto(int minuti){
        return minuti * 0.15;
    }
    @Override
    public double consumoBatteriaPerMinuto(){
        return 0.5;
    }

    public int getNumeroMarce() {
        return numeroMarce;
    }
}
