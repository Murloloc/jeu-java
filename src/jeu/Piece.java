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

    public Piece(String nomPiece, String description, String image) { //Constructeur de la classe Piece
        this.nomPiece = nomPiece;
        this.description = description;
        this.nomImage = image;
        this.listePNJ = new ArrayList<PNJ>();
        this.listeItem = new ArrayList<Item>();
        this.sorties = new HashMap<>();
    }

    public String nomImage() { //Getter de nomImage
        return nomImage;
    }

    public void setNomImage(String nomImage) { //Setter de nomImage
        this.nomImage = nomImage;
    }

    public String getNomPiece() { //Getter de nomPiece
        return nomPiece;
    }

    public void setNomPiece(String nomPiece) { //Setter de nomPiece
        this.nomPiece = nomPiece;
    }

    public void setDescription(String description) { //Setter de description
        this.description = description;
    }

    public String toString() {
        return ("Vous êtes " + this.nomPiece + "\n" + this.description + "\n");
    }

    public String descriptionLongue() { //Affiche les sorties possible sous forme de chaine
        return this + "\nSorties : " + sorties();
    }

    public void ajouteSortie(Sortie sortie, Piece pieceVoisine) { //Ajoute les sorties
        this.sorties.put(sortie.name(), pieceVoisine);
    }



    String sorties() { //Retourne les sorties sous forme de chaine
        return sorties.keySet().toString();
    }

    public Piece obtientSortie(String direction) { //Retourne les sorties possibles de la piece
        return sorties.get(direction);
    }

    public void ajouterPNJ(PNJ pnj) { //Ajoute un PNJ dans liste PNJ
        this.getListePNJ().add(pnj);
    }

    public void retirerPNJ(PNJ pnj) { //Retire un PNJ dans listePNJ
        this.getListePNJ().remove(pnj);
    }

    public ArrayList<PNJ> getListePNJ() { //Renvoie listePNJ
        return listePNJ;
    }

    public void ajouterItem(Item item) { //Ajoute un item dans listeItem
        this.getListeItem().add(item);
    }

    public void retirerItem(Item item) { //Retire un item dans listeItem
        this.getListeItem().remove(item);

    }

    public ArrayList<Item> getListeItem() { //Getter de listItem
        return listeItem;
    }

    public Pot getPotByNum(int num) { //Renvoie un pot selon son numéro
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

    public Plaque getPlaqueByNum(int num) { //Renvoie une plaque selon son numéro
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

    public boolean estVideDObjet() { //Renvoie vraie si la pièce est vide d'Item (et seulement d'objet de la classe Item pas ses classes filles)
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



