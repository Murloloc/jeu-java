package jeu.personnages;

import java.io.Serializable;

public abstract class PNJ implements Serializable {

    public String nom;

    public int etat;

    public PNJ(String nom) {
        this.nom = nom;
        this.etat = 0;
    }

    public PNJ() {
    }

    public String getNom() {
        return nom;
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

}//fin de la classe
