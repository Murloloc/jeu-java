package jeu;

import java.util.HashMap;

public class Zone {
    private final String nomPiece;
    private final String description;
    private final String nomImage;
    private final HashMap<String, Zone> sorties;

    public Zone(String nomPiece, String description, String image) {
        this.nomPiece = nomPiece;
        this.description = description;
        this.nomImage = image;
        this.sorties = new HashMap<>();
    }

    public void ajouteSortie(Sortie sortie, Zone zoneVoisine) {
        this.sorties.put(sortie.name(), zoneVoisine);
    }

    public String nomImage() {
        return nomImage;
    }

    public String toString() {
        return ("Vous êtes " + this.nomPiece +".\n" + this.description);
    }

    public String descriptionLongue() {
        return this + "\nSorties : " + sorties();
    }

    private String sorties() {
        return sorties.keySet().toString();
    }

    public Zone obtientSortie(String direction) {
        return sorties.get(direction);
    }
}

