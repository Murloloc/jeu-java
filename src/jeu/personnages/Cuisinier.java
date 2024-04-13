package jeu.personnages;

import java.io.Serializable;

public class Cuisinier extends PNJ implements Serializable {

    public Cuisinier(String nom) { //Constructeur de la classe Cuisinier
        super(nom);
    }

    public String dialogue() { //Dialogue du cuisinier
        return (this + " le cuisinier, le rez-de-chaussée communique avec 2 tours : \nune à gauche libre d’accès, et une à droite qui est verrouillée.\n" +
                "La clé pour la tour de droite se trouve certainement quelque-part à l’étage 0.\n");
    }

}//fin de la classe
