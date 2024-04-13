package jeu.personnages;

import java.io.Serializable;

/**
 * Cette classe abstraite sert à modéliser les propriétes et méthodes d'un PNJ
 */

public abstract class PNJ implements Serializable {

    public String nom;

    public int etat;

    /**
     * Constructeur de la classe PNJ
     *
     * @param nom
     */
    public PNJ(String nom) {
        this.nom = nom;
        this.etat = 0;
    }

    /**
     * Getter du nom
     *
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter de l'etat
     *
     * @return
     */
    public int getEtat() {
        return etat;
    }

    /**
     * Setter de l'etat
     *
     * @param etat
     */
    public void setEtat(int etat) {
        this.etat = etat;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return ("Je suis " + this.nom);
    }

    /**
     * Affichage du dialogue
     *
     * @return
     */
    public String dialogue() {
        return toString();
    }

}//fin de la classe
