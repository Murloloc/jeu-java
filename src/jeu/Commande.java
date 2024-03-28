package jeu;

import javax.swing.plaf.OptionPaneUI;
import java.util.ArrayList;
import java.util.List;

public enum Commande {
    AIDE("?", "? (aide)"),
    NORD("N", "N (aller à la sortie nord)"),
    SUD("S", "S (aller à la sortie sud)"),
    EST("E", "E (aller à la sortie est)"),
    OUEST("O", "O (aller à la sortie ouest)"),
    MONTER("M", "M (monter vers la pièce du dessus)"),
    DESCENDRE("D", "D (descendre vers la pièce du dessous)"),
    FOUILLER("F", "F (fouiller la pièce)"),
    INVENTAIRE("I", "I (consulter l'inventaire"),
    PARLER("P", "P (parler avec un personnage)"),
    JAUNE("JAU", "JAU (choisir la clé jaune)"),
    BLEUE("BLE", "BLE (choisir la clé bleue"),
    CRAFTER("CR", "CR (crafter un item)"),
    OUVRIR("OUV", "OUV (ouvrir le coffre"),
    CASSER("CA", "CA (casser l'obstacle)"),
    ECLAIRER("ECL", "ECL (éclaire la pièce)"),
    LIRE("L", "L (lire le texte écrit)"),
    POSER("PO", "PO (poser un objet)"),
    VERIFIER("VER", "VER (vérifier les plaques de pression)"),
    INSPECTER("INS", "INS (inspecter la pièce)"),
    ASSEOIR("AS", "AS (s'asseoir sur le trône)"),
    COMBATTRE("COM", "COM (engager le combat)"),
    FUIR("FU", "FU (prendre la fuite)"),
    LANCER ("LANC", "LANC (lancer la partie)"),
    SAUVEGARDER("SAUV", "SAUV (sauvegarder la partie)"),
    CONTINUER ("CONT", "CONT (continuer la partie)"),
    QUITTER ("QUITTER", "QUITTER (quitter la partie)"),

    ;

    private String abreviation;
    private String description;

    private Commande(String c, String d) {
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

    public static List<String> toutesLesAbreviations() {
        ArrayList<String> resultat = new ArrayList<String>();
        for (Commande c : values()) {
            resultat.add(c.abreviation);
        }
        return resultat;
    }

    public static List<String> tousLesNoms() {
        ArrayList<String> resultat = new ArrayList<String>();
        for (Commande c : values()) {
            resultat.add(c.name());
        }
        return resultat;
    }
}//fin de l'enum
