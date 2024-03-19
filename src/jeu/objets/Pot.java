package jeu.objets;

import java.util.ArrayList;
import java.util.Random;

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

    public static void initialiserPot() {
        int[] full = new int[6];

        for (int i = 0; i < 6; i++) {
            full[i] = 0;
        }

        Random random = new Random();

        int a;
        int b;
        int c;

        while ((a = random.nextInt(5)) == (b = random.nextInt(5)) || (a = random.nextInt(5)) == (c = random.nextInt(5)) || (b = random.nextInt(5)) == (c = random.nextInt(5))) {
        }

        full[a] = 1;
        full[b] = 1;
        full[c] = 1;

        for (int i = 0; i < 6; i++) {
            new Pot(i, full[i]);
            i++;

        }
    }
}
