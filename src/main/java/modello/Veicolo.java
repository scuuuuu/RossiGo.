package modello;

public abstract class Veicolo {
    protected int codice;
    protected int batteria;
    public Stato_veicolo stato;

    public Veicolo(int codice, int batteria,Stato_veicolo stato){
        this.codice = codice;
        this.batteria = batteria;
        this.stato = stato;
    }
    public abstract double calcolaCosto(int minuti);
    public abstract double consumoBatteriaPerMinuto();

    public int getBatteria() {
        return batteria;
    }

    public String getStato() {
        return stato.toString();
    }

    public int getCodice() {
        return codice;
    }

    public void setBatteria(int batteria) {
        this.batteria = batteria;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public void setStato(Stato_veicolo stato) {
        this.stato = stato;
    }
}
