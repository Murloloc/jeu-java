package jeu.objets;

/**
 * Cette classe vise à créer des plaques sur lesquelles un item peut être posé afin de résoudre une énigme
 */

public class Plaque extends Item {
    private final int num;
    private int etat;
    private Item item;

    /**
     * Constructeur de la classe Plaque
     *
     * @param num
     * @param etat
     */
    public Plaque(int num, int etat) {
        this.num = num;
        this.etat = etat;
    }

    /**
     * Getter du num
     *
     * @return
     */
    public int getNum() {
        return num;
    }

    /**
     * Getter de l'etat
     *
     * @return
     */
    public int getEtat() {
        return etat;
    }

    /**
     * Setter de l'etat
     *
     * @param etat
     */
    public void setEtat(int etat) {
        this.etat = etat;
    }

    /**
     * Setter de l'item
     *
     * @param item
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Getter de l'item
     *
     * @return
     */
    public Item getItem() {
        return this.item;
    }
}
