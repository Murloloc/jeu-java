package jeu.personnages;

public class Garde extends PNJ {

    public Garde(String nom) {
        super(nom);
    }

    public String dialogue() {
        return (this + ", .");
    }
}//fin de la classe

