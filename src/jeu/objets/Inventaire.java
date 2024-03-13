package jeu.objets;

import java.util.ArrayList;

public class Inventaire {

    private ArrayList<Item> listeInventaire;

    public Inventaire(){
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

    public boolean presenceItem(String nom){
        for(Item item : listeInventaire){
            if(item.getNom().equals(nom));{
                return true;
            }
        }
        return false;
    }



}//fin de la classe
