package jeu.personnages;

import java.io.Serializable;

public abstract class PNJ implements Serializable {

    public String nom;

    public int etat;

    public PNJ(String nom) { //Constructeur de la classe PNJ
        this.nom = nom;
        this.etat = 0;
    }

    public String getNom() { //Getter du nom
        return nom;
    }

    public int getEtat() { //Getter de l'etat
        return etat;
    }

    public void setEtat(int etat) { //Setter de l'etat
        this.etat = etat;
    }

    @Override
    public String toString() {
        return ("Je suis " + this.nom);
    }

    public String dialogue() { //Affichage du dialogue
        return toString();
    }

}//fin de la classe
