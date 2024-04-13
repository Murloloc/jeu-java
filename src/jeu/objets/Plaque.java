package jeu.objets;

public class Plaque extends Item {
    private final int num;
    private int etat;
    private Item item;

    public Plaque(int num, int etat) { //Constructeur de la classe Plaque
        this.num = num;
        this.etat = etat;
    }

    public int getNum() { //Getter du num
        return num;
    }

    public int getEtat() { //Getter de l'etat
        return etat;
    }

    public void setEtat(int etat) { //Setter de l'etat
        this.etat = etat;
    }

    public void setItem(Item item) { //Setter de l'item
        this.item = item;
    }

    public Item getItem() { //Getter de l'item
        return this.item;
    }
}
