package jeu;

import jeu.objets.Item;
import jeu.personnages.PNJ;

import java.util.ArrayList;
import java.util.HashMap;

public class Piece {
    private String nomPiece;
    private String description;
    private String nomImage;
    private ArrayList<PNJ> listePNJ;
    private ArrayList<Item> listeItem;
    private HashMap<String, Piece> sorties;
    private int etat; //1=que un ou des pnj //2 = que un ou des item //3=un/des items ET un/des pnj

    public Piece(String nomPiece, String description, String image) { //constructeur sans PNJ et sans Item
        this.nomPiece = nomPiece;
        this.description = description;
        this.nomImage = image;
        this.listePNJ = new ArrayList<PNJ>();
        this.listeItem = new ArrayList<Item>();
        this.sorties = new HashMap<>();
    }

    public String nomImage() {
        return nomImage;
    }


    public String getNomPiece() {
        return nomPiece;
    }

    public void setNomPiece(String nomPiece) {
        this.nomPiece = nomPiece;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return ("Vous êtes " + this.nomPiece + ".\n" + this.description);
    }

    public String descriptionLongue() {
        return this + "\nSorties : " + sorties();
    }

    public void ajouteSortie(Sortie sortie, Piece pieceVoisine) {
        this.sorties.put(sortie.name(), pieceVoisine);
    }

    private String sorties() {
        return sorties.keySet().toString();
    }

    public Piece obtientSortie(String direction) {
        return sorties.get(direction);
    }

    public void ajouterPNJ(PNJ pnj) {
        this.getListePNJ().add(pnj);
    }

    public void retirerPNJ(PNJ pnj) {
        this.getListePNJ().remove(pnj);
    }

    public ArrayList<PNJ> getListePNJ() {
        return listePNJ;
    }

    public void ajouterItem(Item item) {
        this.getListeItem().add(item);
    }

    public void retirerItem(Item item) {
        this.getListeItem().remove(item);
    }

    public ArrayList<Item> getListeItem() {
        return listeItem;
    }

}



