package jeu.personnages;

public class Servante extends PNJ {

    public Servante(String nom) {
        super(nom);
    }

    public String dialogue() {
        return this + " à ta place je n'irai pas dans cette tour sans être armé";
    }
}//fin de la classe
