package jeu.objets;

import java.io.Serializable;
import java.util.ArrayList;

public class Inventaire implements Serializable {

    private final ArrayList<Item> listeInventaire;

    public Inventaire() {
        this.listeInventaire = new ArrayList<Item>();
    }

    public void ajouterInventaire(Item item) {
        this.getListeInventaire().add(item);
    }

    public void retirerInventaire(Item item) {
        this.getListeInventaire().remove(item);
    }

    public ArrayList<Item> getListeInventaire() {
        return this.listeInventaire;
    }

    @Override
    public String toString() {
        return "\nInventaire : \n";
    }

    public String afficherInventaire() {
        String chaine = this.toString();
        if (this.getListeInventaire().isEmpty())
            return "L'inventaire est vide\n";
        else
            for (Item item : this.getListeInventaire())
                chaine = chaine + item.getNom() + " : " + item.getDescription() + "\n";
        return chaine;
    }

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