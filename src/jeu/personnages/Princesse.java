package jeu.personnages;

import java.io.Serializable;

/**
 * Cette classe sert à créer un PNJ étant une princesse
 */
public class Princesse extends PNJ implements Serializable {

    /**
     * Constructeur de la classe Princesse
     *
     * @param nom
     */
    public Princesse(String nom) {
        super(nom);
    }

    /**
     * Dialogue de la princesse
     *
     * @return
     */
    public String dialogue() {
        return (this + " la princesse, je suis prisonnière du château depuis des années et je souhaite plus que tout\npouvoir m'enfuir. Mais je suis incapable de bouger à cause d'un maléfice qui m'a été jeté.\nIl y a trois potions sur cette table qui pourraient m'aider à me libérer.\nJe ne sais pas laquelle choisir. Peux-tu m'aider ?\n");
    }

}//fin de la classe
