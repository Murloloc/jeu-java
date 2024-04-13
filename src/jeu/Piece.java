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



