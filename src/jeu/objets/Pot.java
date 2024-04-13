package jeu.objets;

public class Pot extends Item {

    private final int num;
    private int etat;

    public Pot(int num, int etat) { //Constructeur de la classe Pot
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


}
