package jeu;

import jeu.objets.Item;
import jeu.objets.Plaque;
import jeu.objets.Pot;
import jeu.personnages.PNJ;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Cette classe vise à créer des pièces qui composeront la map
 */
public class Piece implements Serializable {
    private String nomPiece;
    private String description;
    private String nomImage;
    private final ArrayList<PNJ> listePNJ;
    private final ArrayList<Item> listeItem;
    private final HashMap<String, Piece> sorties;

    /**
     * Constructeur de la classe Piece
     *
     * @param nomPiece
     * @param description
     * @param image
     */
    public Piece(String nomPiece, String description, String image) {
        this.nomPiece = nomPiece;
        this.description = description;
        this.nomImage = image;
        this.listePNJ = new ArrayList<PNJ>();
        this.listeItem = new ArrayList<Item>();
        this.sorties = new HashMap<>();
    }

    /**
     * Getter de nomImage
     *
     * @return
     */
    public String nomImage() {
        return nomImage;
    }

    /**
     * Setter de nomImage
     *
     * @param nomImage
     */
    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }

    /**
     * Getter de nomPiece
     *
     * @return
     */
    public String getNomPiece() {
        return nomPiece;
    }

    /**
     * Setter de nomPiece
     *
     * @param nomPiece
     */
    public void setNomPiece(String nomPiece) {
        this.nomPiece = nomPiece;
    }

    /**
     * Setter de description
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return
     */
    public String toString() {
        return ("Vous êtes " + this.nomPiece + "\n" + this.description + "\n");
    }

    /**
     * Affiche les sorties possible sous forme de chaine
     *
     * @return
     */
    public String descriptionLongue() {
        return this + "\nSorties : " + sorties();
    }

    /**
     * Ajoute les sorties
     *
     * @param sortie
     * @param pieceVoisine
     */
    public void ajouteSortie(Sortie sortie, Piece pieceVoisine) {
        this.sorties.put(sortie.name(), pieceVoisine);
    }


    /**
     * Retourne les sorties sous forme de chaine
     *
     * @return
     */
    String sorties() {
        return sorties.keySet().toString();
    }

    /**
     * Retourne les sorties possibles de la piece
     *
     * @param direction
     * @return
     */
    public Piece obtientSortie(String direction) {
        return sorties.get(direction);
    }

    /**
     * Ajoute un PNJ dans liste PNJ
     *
     * @param pnj
     */
    public void ajouterPNJ(PNJ pnj) {
        this.getListePNJ().add(pnj);
    }

    /**
     * Retire un PNJ dans listePNJ
     *
     * @param pnj
     */
    public void retirerPNJ(PNJ pnj) {
        this.getListePNJ().remove(pnj);
    }

    /**
     * Renvoie listePNJ
     *
     * @return
     */
    public ArrayList<PNJ> getListePNJ() {
        return listePNJ;
    }

    /**
     * Ajoute un item dans listeItem
     *
     * @param item
     */
    public void ajouterItem(Item item) {
        this.getListeItem().add(item);
    }

    /**
     * Retire un item dans listeItem
     *
     * @param item
     */
    public void retirerItem(Item item) {
        this.getListeItem().remove(item);

    }

    /**
     * Getter de listItem
     *
     * @return
     */
    public ArrayList<Item> getListeItem() {
        return listeItem;
    }

    /**
     * Renvoie un pot selon son numéro
     *
     * @param num
     * @return
     */
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

    /**
     * Renvoie une plaque selon son numéro
     *
     * @param num
     * @return
     */
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

    /**
     * Renvoie vraie si la pièce est vide d'Item (et seulement d'objet de la classe Item pas ses classes filles)
     *
     * @return
     */
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



