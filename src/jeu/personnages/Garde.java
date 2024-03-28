package jeu.personnages;

import java.io.Serializable;

public class Garde extends PNJ implements Serializable {

    private String question1;
    private String question2;
    private String question3;


    public Garde(String nom, String q1, String q2, String q3) {
        super(nom);
        this.etat = 0;
        this.question1=q1;
        this.question2=q2;
        this.question3=q3;
    }

    public String dialogue() {
        String diag;
        diag = "Je suis le garde" + this.nom + "et je vais vous poser ma question : ";
        return diag;
    }

    public String getQ1() {
        return this.question1;
    }

    public String getQ2() {
        return this.question2;
    }

    public String getQ3() {
        return this.question3;
    }
}//fin de la classe