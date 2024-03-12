package jeu.personnages;

import jeu.GUI;
import jeu.Jeu;
import jeu.objets.Inventaire;

public class Prisonnier extends PNJ {


    public Prisonnier(String nom) {
        super(nom);
        this.etat = 0;

    }


    public String dialogue() {
        if (this.etat == 0) {
            return (this + ", je suis ici depuis des années.\nJ'ai essayé de m'échapper sans jamais avoir réussi.\nDans mes tentatives j'ai trouvé une clé bleue et une clé jaune mais aucune d'elle ne dévérouille ma cellule\nJe vais t'en donner une, essaye de trouver ce qu'elle dévérouille");
        } else {
            return ("Alors tu as trouvé à quoi servait la clé ?");
        }
    }

    public String dialogueJaune() {
        return "Tiens voici la clé Jaune";

    }

    public String dialogueBleu() {
        return "Tiens voici la clé Bleue";
    }

}//fin de la classe
