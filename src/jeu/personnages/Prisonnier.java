package jeu.personnages;

public class Prisonnier extends PNJ {

    public Prisonnier(String nom) {
        super(nom);
    }

    public String dialogue() {
        return (this + ", je suis ici depuis des années.\nJ'ai essayé de m'échapper sans jamais avoir réussi.\nDans mes tentatives j'ai trouvé une clé bleue et une clé jaune mais aucune d'elle ne dévérouille ma cellule\n Je vais t'en donner une, essaye de trouver ce qu'elle dévérouille");
    }

}
