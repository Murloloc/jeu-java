package jeu.objets;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Cette classe vise a créer un inventaire afin de pouvoir stocker des items
 */

public class Inventaire implements Serializable {

    private final ArrayList<Item> listeInventaire;

    /**
     * Constructeur de la classe Inventaire
     */
    public Inventaire() {
        this.listeInventaire = new ArrayList<Item>();
    }

    /**
     * Ajouter un item dans l'inventaire
     *
     * @param item
     */
    public void ajouterInventaire(Item item) {
        this.getListeInventaire().add(item);
    }

    /**
     * Retirer un item dans l'inventaire
     *
     * @param item
     */
    public void retirerInventaire(Item item) {
        this.getListeInventaire().remove(item);
    }

    /**
     * Getter de listeInventaire
     *
     * @return
     */
    public ArrayList<Item> getListeInventaire() {
        return this.listeInventaire;
    }

    @Override
    public String toString() {
        return "\nInventaire : \n";
    }

    /**
     * Affichage de l'inventaire
     *
     * @return
     */
    public String afficherInventaire() {
        String chaine = this.toString();
        if (this.getListeInventaire().isEmpty())
            return "L'inventaire est vide\n";
        else
            for (Item item : this.getListeInventaire())
                chaine = chaine + item.getNom() + " : " + item.getDescription() + "\n";
        return chaine;
    }

    /**
     * Retourne un item dans l'inventaire en fonction de son nom
     *
     * @param nom
     * @return
     */
    public Item getItemByName(String nom) {
        Item var = null;
        for (Item item : this.listeInventaire) {
            if (item.getNom().equalsIgnoreCase(nom)) {
                var = item;
            }
        }
        return var;
    }

}//fin de la classe