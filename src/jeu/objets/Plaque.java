package jeu.objets;

public class Plaque extends Item {
    private int num;
    private int etat;

    public Plaque(int num,int etat){
        this.num = num;
        this.etat = etat;
    }

    public int getNum() {
        return num;
    }

    public int getEtat() {
        return etat;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
}
