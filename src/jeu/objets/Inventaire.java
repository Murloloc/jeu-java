package jeu.objets;

import java.util.ArrayList;

public class Inventaire {

    private ArrayList<Item> listeInventaire;

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
        return "Inventaire : \n";
    }

    public String afficherInventaire() {
        String chaine = toString();
        if (this.getListeInventaire().isEmpty())
            return "L'inventaire est vide";
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