package jeu.personnages;

import java.io.Serializable;

public class Garde extends PNJ implements Serializable {

    private String question1;
    private int reponse1;
    private String question2;
    private int reponse2;
    private String question3;
    private int reponse3;


    public Garde(String nom, String q1, String q2, String q3, int r1, int r2, int r3) {
        super(nom);
        this.etat = 0;
        this.question1 = q1;
        this.question2 = q2;
        this.question3 = q3;
        this.reponse1 = r1;
        this.reponse2 = r2;
        this.reponse3 = r3;
    }

    public String dialogue(String lettre) {
        return switch (lettre) {
            case "A" -> "Bonjour je suis le garde " + this.nom + "\nVoici ma question : \n" +this.question1;
            case "R" -> "Bonjour je suis le garde " + this.nom + "\nVoici ma question : \n" +this.question2;
            case "S" -> "Bonjour je suis le garde " + this.nom + "\nVoici ma question : \n" +this.question3;
            default -> "Pas de question correspondante.";
        };

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