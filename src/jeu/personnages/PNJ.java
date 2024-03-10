package jeu.personnages;

public abstract class PNJ {

    public String nom;

    public PNJ(String nom) {
        this.nom = nom;
    }

    public PNJ() {
    }

    @Override
    public String toString() {
        return ("Je suis " + this.nom);
    }

    public String dialogue() {
        return toString();
    }

}
