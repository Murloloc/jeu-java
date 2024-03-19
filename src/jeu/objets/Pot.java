package jeu.objets;

import java.util.ArrayList;
import java.util.Random;

import jeu.Map;

public class Pot extends Item {

    private int num;
    private int etat;


    public Pot(int num, int etat) {
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



}
