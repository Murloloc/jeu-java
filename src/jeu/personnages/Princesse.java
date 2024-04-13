package jeu.personnages;

import java.io.Serializable;

public class Princesse extends PNJ implements Serializable {

    public Princesse(String nom) { //Constructeur de la classe Princesse
        super(nom);
    }

    public String dialogue() { //Dialogue de la princesse
        return (this + " la princesse, je suis prisonnière du château depuis des années et je souhaite plus que tout\npouvoir m'enfuir. Mais je suis incapable de bouger à cause d'un maléfice qui m'a été jeté.\nIl y a trois potions sur cette table qui pourraient m'aider à me libérer.\nJe ne sais pas laquelle choisir. Peux-tu m'aider ?\n");
    }

}//fin de la classe
