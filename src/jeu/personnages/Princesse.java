package jeu.personnages;

import java.io.Serializable;

public class Princesse extends PNJ implements Serializable {

    public Princesse(String nom) {
        super(nom);
    }

    public String dialogue() {
        return (this + ",je suis prisonnière du château depuis des années et je souhaite plus que tout pouvoir m'enfuir. Mais je suis incapable de bouger à cause d'un maléfice qui m'a été jeté.");
    }

}//fin de la classe
