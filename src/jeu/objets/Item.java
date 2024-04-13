package jeu.objets;

import java.io.Serializable;

public class Item implements Serializable {

    private String nom;
    private String description;


    public Item(String nom, String description) { // Constructeur de la classe Item
        this.nom = nom;
        this.description = description;
    }

    public Item() { // Constructeur vide de la classe Item
    }

    public String getNom() { //Getter du nom
        return nom;
    }

    public String getDescription() { // Getter de la description
        return description;
    }

    @Override
    public String toString() {
        return this.nom;
    }

}//fin de la classe
