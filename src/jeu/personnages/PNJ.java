package jeu.personnages;

public abstract class PNJ {

    public String nom;

    public int etat;

    public PNJ(String nom) {
        this.nom = nom;
        this.etat = 0;
    }

    public PNJ() {
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    @Override
    public String toString() {
        return ("Je suis " + this.nom);
    }

    public String dialogue() {
        return toString();
    }

}
