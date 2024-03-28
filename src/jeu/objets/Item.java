package jeu.objets;

import java.io.Serializable;

public class Item implements Serializable {

    private String nom;
    private String description;


    public Item(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public Item() {
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.nom;
    }

}//fin de la classe
