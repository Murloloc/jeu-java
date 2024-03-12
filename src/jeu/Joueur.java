package jeu;

import java.util.ArrayList;
import jeu.objets.Item;

public class Joueur {
    private String nom;
    private ArrayList<Item> inventaire;

    public Joueur(String nom, ArrayList<Item> inventaire){
        this.nom = nom;
        this.inventaire = inventaire;
    }

}//fin de la classe
