package jeu.personnages;

import java.io.Serializable;

public class Prisonnier extends PNJ implements Serializable {

    public Prisonnier(String nom) {
        super(nom);
        this.etat = 0;

    }

    public String dialogue() {
        if (this.etat == 0) {
            return (this + ", je suis ici depuis des années.\nJ'ai essayé de m'échapper sans jamais avoir réussi.\nDans mes tentatives j'ai trouvé une clé bleue et une clé jaune mais\naucune d'elle ne dévérouille ma cellule\nJe vais t'en donner une, essaye de trouver ce qu'elle dévérouille.\n");
        } else {
            return ("\nAlors tu as trouvé à quoi servait la clé ?");
        }
    }

    public String dialogueJaune() {
        return "\nTiens voici la clé Jaune";

    }

    public String dialogueBleu() {
        return "\nTiens voici la clé Bleue";
    }

}//fin de la classe
