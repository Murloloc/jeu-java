package jeu.objets;

public class Plaque extends Item {
    private final int num;
    private int etat;
    private Item item;

    public Plaque(int num, int etat) {
        this.num = num;
        this.etat = etat;
    }

    public int getNum() {
        return num;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return this.item;
    }
}
