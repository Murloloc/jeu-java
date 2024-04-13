package jeu.objets;

/**
 * Cette classe vise à créer des pots pouvant contenir des batons afin de résoudre une énigme
 */

public class Pot extends Item {

    private final int num;
    private int etat;

    /**
     * Constructeur de la classe Pot
     *
     * @param num
     * @param etat
     */
    public Pot(int num, int etat) {
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


}
