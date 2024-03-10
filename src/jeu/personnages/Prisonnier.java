package jeu.personnages;

public class Prisonnier extends PNJ {

    public Prisonnier(String nom) {
        super(nom);
    }

    public String dialogue() {
        return (this + ", je suis ici depuis des années.");
    }

}
