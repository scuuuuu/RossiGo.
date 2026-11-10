package modello;

public class Monopattino extends Veicolo{
    private int velocitaMassima;

    public Monopattino(String codice, int batteria, Stato_veicolo stato, int velocitaMassima) {
        super(codice, batteria, stato);
        this.velocitaMassima = velocitaMassima;
    }

    @Override
    public double calcolaCosto(int minuti){
        return minuti *0.25;
    }
    @Override
    public double consumoBatteriaPerMinuto(){
        return 1.1;
    }
    public boolean isLimitatoInZonaPedonale() {
        return true;
    }

    public int getVelocitaMassima() {
        return velocitaMassima;
    }
}
