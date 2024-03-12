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
    QUITTER("Q", "Q (quitter)"),
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
    PELUCHE("PEL", "PEL (poser la peluche)"),
    PIOCHE("PIO", "PIO (poser la pioche)"),
    LINGOT("LIN", "LIN (er le lingot d'or)"),
    ASSEOIR("AS", "AS (s'asseoir sur le trône)"),
    COMBATTRE("COM", "COM (engager le combat)"),
    FUIR("FU", "FU (prendre la fuite)"),
    OUI("OUI", "(confirmer le choix)"),
    NON("NON", "(annuler le choix)"),
    F1("F1", "(fouiller le pot 1)"),
    F2("F2", "(fouiller le pot 2)"),
    F3("F3", "(fouiller le pot 3)"),
    F4("F4", "(fouiller le pot 4)"),
    F5("F5", "(fouiller le pot 5)"),
    F6("F6", "(fouiller le pot 6)"),
    F7("F7", "(fouiller le pot 7)"),
    A("A", "(choix A)"),
    B("B", "(choix B)"),
    C("C", "(choix C)"),
    D("D", "(choix D)"),
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
