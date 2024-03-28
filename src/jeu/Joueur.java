package jeu;

import java.io.Serializable;
import java.util.ArrayList;
import jeu.objets.Item;

public class Joueur implements Serializable {
    private String nom;
    private ArrayList<Item> inventaire;

    public Joueur(String nom, ArrayList<Item> inventaire){
        this.nom = nom;
        this.inventaire = inventaire;
    }

}//fin de la classe