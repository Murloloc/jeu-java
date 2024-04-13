package jeu.objets;

import java.io.Serializable;

/**
 * Cette classe vise à créer des items afin de les utiliser pour résoudre des énigmes et avancer dans le jeu
 */
public class Item implements Serializable {

    private String nom;
    private String description;

    /**
     * Constructeur de la classe Item
     *
     * @param nom
     * @param description
     */
    public Item(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    /**
     * Constructeur vide de la classe Item
     */
    public Item() {
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
     * Getter de la description
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return this.nom;
    }

}//fin de la classe
