package jeu;

public class Jeu {

    private GUI gui;
    private Zone zoneCourante;

    public Jeu() {
        creerCarte();
        gui = null;
    }

    public void setGUI(GUI g) {
        gui = g;
        afficherMessageDeBienvenue();
    }

    private void creerCarte() {
        Zone[] zones = new Zone[20];
        // zones[] = new Zone("", ".jpg");

        //étage -1
        zones[0] = new Zone("Donjon centre", "Donjon.jpg");
        zones[1] = new Zone("La prison", "Prison.jpg");
        zones[2] = new Zone("Escalier nord", "EscalierNord.jpg");
        zones[3] = new Zone("Couloir", "Couloir.jpg");
        zones[4] = new Zone("Salle des coffres", "SalleDesCoffres.jpg");

        //RDC
        zones[5] = new Zone("Escalier de gauche", "EscalierGauche.jpg");
        zones[6] = new Zone("Entrée du château", "EntreeChateau.jpg");
        zones[7] = new Zone("Bibliothèque", "Bibliotheque.jpg");
        zones[8] = new Zone("Salle du trône", "SalleDuTrone.jpg");
        zones[9] = new Zone("Escalier de droite", "EscalierDroite.jpg");
        zones[10] = new Zone("Armurerie", "Armurerie.jpg");
        zones[11] = new Zone("Cuisine", "Cuisine.jpg");
        zones[12] = new Zone("Entrée chambre de la Princesse", "EntreeChambrePrincesse.jpg");
        zones[13] = new Zone("Galerie", "Galerie.jpg");
        zones[14] = new Zone("Entrée tour de gauche", "EntreeTourGauche.jpg");
        zones[15] = new Zone("Salle à manger", "SalleAManger.jpg");

        //étage - 1
        zones[0].ajouteSortie(Sortie.EST, zones[1]);
        zones[0].ajouteSortie(Sortie.NORD, zones[2]);
        zones[0].ajouteSortie(Sortie.OUEST, zones[3]);
        zones[1].ajouteSortie(Sortie.OUEST, zones[0]);
        //to do : la version de la pièce sans rocher qui débloque la sortir descendre
        //zones[1].ajouteSortie(Sortie.DESCENDRE, zones[?]);
        zones[2].ajouteSortie(Sortie.MONTER, zones[5]);
        zones[2].ajouteSortie(Sortie.SUD, zones[0]);
        zones[3].ajouteSortie(Sortie.EST, zones[0]);
        zones[3].ajouteSortie(Sortie.SUD, zones[4]);
        zones[4].ajouteSortie(Sortie.OUEST, zones[3]);

        //rdc
        zones[5].ajouteSortie(Sortie.DESCENDRE, zones[2]);
        zones[5].ajouteSortie(Sortie.EST, zones[6]);
        zones[5].ajouteSortie(Sortie.NORD, zones[7]);

        zones[6].ajouteSortie(Sortie.OUEST, zones[5]);
        zones[6].ajouteSortie(Sortie.NORD, zones[8]);
        zones[6].ajouteSortie(Sortie.EST, zones[9]);

        zones[7].ajouteSortie(Sortie.SUD, zones[5]);
        zones[7].ajouteSortie(Sortie.NORD, zones[15]);

        zones[8].ajouteSortie(Sortie.SUD, zones[6]);

        //zones[9].ajouteSortie(Sortie.DESCENDRE, zones[?]);
        zones[9].ajouteSortie(Sortie.OUEST, zones[6]);
        zones[9].ajouteSortie(Sortie.NORD, zones[10]);

        zones[10].ajouteSortie(Sortie.SUD, zones[9]);
        zones[10].ajouteSortie(Sortie.NORD, zones[11]);

        //to do la liaison 15 11
        //zones[11].ajouteSortie(Sortie., zones[15]);
        zones[11].ajouteSortie(Sortie.SUD, zones[10]);
        zones[11].ajouteSortie(Sortie.NORD, zones[12]);

        zones[12].ajouteSortie(Sortie.SUD, zones[11]);
        zones[12].ajouteSortie(Sortie.OUEST, zones[13]);
        //zones[12].ajouteSortie(Sortie.MONTER, zones[?]);

        zones[13].ajouteSortie(Sortie.EST, zones[12]);
        zones[13].ajouteSortie(Sortie.OUEST, zones[14]);

        zones[14].ajouteSortie(Sortie.SUD, zones[15]);
        zones[14].ajouteSortie(Sortie.EST, zones[13]);
        //zones[14].ajouteSortie(Sortie.MONTER, zones[,]);

        zones[15].ajouteSortie(Sortie.SUD, zones[7]);
        zones[15].ajouteSortie(Sortie.NORD, zones[14]);
        //to do pareil que la cusine
        //zones[14].ajouteSortie(Sortie., zones[11]);


        this.zoneCourante = zones[0];
    }

    private void afficherLocalisation() {
        gui.afficher(zoneCourante.descriptionLongue());
        gui.afficher();
    }

    private void afficherMessageDeBienvenue() {
        gui.afficher("Bienvenue !");
        gui.afficher();
        gui.afficher("Tapez '?' pour obtenir de l'aide.");
        gui.afficher();
        afficherLocalisation();
        gui.afficheImage(zoneCourante.nomImage());
    }

    public void traiterCommande(String commandeLue) {
        gui.afficher("> " + commandeLue + "\n");
        switch (commandeLue.toUpperCase()) {
            case "?":
            case "AIDE":
                afficherAide();
                break;
            case "N":
            case "NORD":
                allerEn("NORD");
                break;
            case "S":
            case "SUD":
                allerEn("SUD");
                break;
            case "E":
            case "EST":
                allerEn("EST");
                break;
            case "O":
            case "OUEST":
                allerEn("OUEST");
                break;
            case "M":
            case "MONTER":
                allerEn("MONTER");
                break;
            case "D":
            case "DESCENDRE":
                allerEn("DESCENDRE");
                break;
            case "Q":
            case "QUITTER":
                terminer();
                break;
            default:
                gui.afficher("Commande inconnue");
                break;
        }
    }

    private void afficherAide() {
        gui.afficher("Etes-vous perdu ?");
        gui.afficher();
        gui.afficher("Les commandes autorisées sont :");
        gui.afficher();
        gui.afficher(Commande.toutesLesDescriptions().toString());
        gui.afficher();
    }

    private void allerEn(String direction) {
        Zone nouvelle = zoneCourante.obtientSortie(direction);
        if (nouvelle == null) {
            gui.afficher("Pas de sortie " + direction);
            gui.afficher();
        } else {
            zoneCourante = nouvelle;
            gui.afficher(zoneCourante.descriptionLongue());
            gui.afficher();
            gui.afficheImage(zoneCourante.nomImage());
        }
    }

    private void terminer() {
        gui.afficher("Au revoir...");
        gui.enable(false);
    }
}
