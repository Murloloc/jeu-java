package jeu;

import java.util.ArrayList;
import java.util.List;

public enum Commande {
    AIDE("?", "? (aide)"),
    SAUVEGARDER("SAUV", "SAUV (sauvegarder la partie)"),
    CONTINUER("CONT", "CONT (continuer la partie)"),
    QUITTER("QUITTER", "QUITTER (quitter la partie)"),
    NORD("N", "N (aller à la sortie nord)"),
    SUD("S", "S (aller à la sortie sud)"),
    EST("E", "E (aller à la sortie est)"),
    OUEST("O", "O (aller à la sortie ouest)"),
    MONTER("M", "M (monter vers la pièce du dessus)"),
    DESCENDRE("D", "D (descendre vers la pièce du dessous)"),
    FOUILLER("F", "F (fouiller la pièce)"),
    INVENTAIRE("I", "I (consulter l'inventaire"),
    PARLER("P", "P (parler avec un personnage)"),
    CRAFTER("CR", "CR (crafter un item)"),
    OUVRIR("OUV", "OUV (ouvrir le coffre)"),
    CASSER("CA", "CA (casser l'obstacle)"),
    ECLAIRER("ECL", "ECL (éclaire la pièce)"),
    LIRE("L", "L (lire le texte écrit)"),
    POSER("PO", "PO (poser un objet)"),
    INSPECTER("INS", "INS (inspecter la pièce)"),
    ASSEOIR("SAS", "SAS (s'asseoir sur le trône)"),
    LANCER("LANC", "LANC (lancer la partie)"),

    ;

    private final String abreviation;
    private final String description;

    Commande(String c, String d) {
        abreviation = c;
        description = d;
    }

    @Override
    public String toString() {
        return name();
    }

    public static List<String> toutesLesDescriptions() {
        ArrayList<String> resultat = new ArrayList<String>();
        for (Commande c : values()) {
            resultat.add("\n" + c.name() + " : " + c.description);
        }
        return resultat;
    }

}//fin de l'enum
