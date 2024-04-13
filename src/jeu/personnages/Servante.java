package jeu.personnages;

import java.io.Serializable;

/**
 * Cette classe sert à créer un PNJ étant une servante
 */

public class Servante extends PNJ implements Serializable {

    /**
     * Constructeur de la classe Servante
     *
     * @param nom
     */
    public Servante(String nom) {
        super(nom);
    }

    /**
     * Dialogue de la servante
     *
     * @return
     */
    public String dialogue() {
        return this + " la servante, à ta place je n'irai pas dans cette tour sans être armé";
    }
}//fin de la classe
