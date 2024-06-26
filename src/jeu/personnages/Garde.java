package jeu.personnages;

import java.io.Serializable;

/**
 * Cette classe sert à créer des PNJ étant des gardes
 */

public class Garde extends PNJ implements Serializable {

    private final String question1;
    private final String reponse1;
    private final String question2;
    private final String reponse2;
    private final String question3;
    private final String reponse3;


    /**
     * Constructeur de la classe Garde
     *
     * @param nom
     * @param q1
     * @param q2
     * @param q3
     * @param r1
     * @param r2
     * @param r3
     */
    public Garde(String nom, String q1, String q2, String q3, String r1, String r2, String r3) {
        super(nom);
        this.etat = 0;
        this.question1 = q1;
        this.question2 = q2;
        this.question3 = q3;
        this.reponse1 = r1;
        this.reponse2 = r2;
        this.reponse3 = r3;
    }

    /**
     * Dialogue du garde
     *
     * @param lettre
     * @return
     */
    public String dialogue(String lettre) {
        return switch (lettre.toUpperCase()) {
            case "A" -> "\nBonjour je suis le garde " + this.nom + "\nVoici ma question : \n" + this.question1;
            case "R" -> "\nBonjour je suis le garde " + this.nom + "\nVoici ma question : \n" + this.question2;
            case "S" -> "\nBonjour je suis le garde " + this.nom + "\nVoici ma question : \n" + this.question3;
            default -> "\nPas de question correspondante.";
        };
    }

    /**
     * Getter de la reponse du garde
     *
     * @param lettre
     * @return
     */
    public String getReponse(String lettre) {
        return switch (lettre.toUpperCase()) {
            case "A" -> this.reponse1;
            case "R" -> this.reponse2;
            case "S" -> this.reponse3;
            default -> "\nPas de réponse correspondante.";
        };
    }

}//fin de la classe