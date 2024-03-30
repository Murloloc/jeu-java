package jeu.personnages;

import java.io.Serializable;

public class Servante extends PNJ implements Serializable {

    public Servante(String nom) {
        super(nom);
    }

    public String dialogue() {
        return this + " la servante, à ta place je n'irai pas dans cette tour sans être armé";
    }
}//fin de la classe
