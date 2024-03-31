package jeu;

import jeu.objets.Item;
import jeu.objets.Plaque;
import jeu.objets.Pot;
import jeu.personnages.PNJ;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Piece implements Serializable {
    private String nomPiece;
    private String description;
    private String nomImage;
    private final ArrayList<PNJ> listePNJ;
    private final ArrayList<Item> listeItem;
    private final HashMap<String, Piece> sorties;

    public Piece(String nomPiece, String description, String image) {
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

    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
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
        return ("Vous êtes " + this.nomPiece + "\n" + this.description + "\n");
    }

    public String descriptionLongue() {
        return this + "\nSorties : " + sorties();
    }

    public void ajouteSortie(Sortie sortie, Piece pieceVoisine) {
        this.sorties.put(sortie.name(), pieceVoisine);
    }



    String sorties() {
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

    public Pot getPotByNum(int num) {
        Pot var = null;
        for (Item item : this.listeItem) {
            if (item instanceof Pot) {
                if (((Pot) item).getNum() == num) {
                    var = ((Pot) item);
                }
            }
        }
        return var;
    }

    public Plaque getPlaqueByNum(int num) {
        Plaque var = null;
        for (Item item : this.listeItem) {
            if (item instanceof Plaque) {
                if (((Plaque) item).getNum() == num) {
                    var = ((Plaque) item);

                }
            }
        }
        return var;
    }

    public boolean estVideDObjet() {
        boolean a = true;
        for (Item item : this.listeItem) {
            if (item.getClass() == Item.class) {
                a = false;
                break;
            }
        }
        return a;
    }

}//class



