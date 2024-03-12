package jeu;

import jeu.objets.*;
import jeu.personnages.*;

import java.util.ArrayList;


public class Jeu {

    private GUI gui;
    private Piece pieceCourante;

    public Jeu() {
        creerCarte();
        gui = null;
    }

    public GUI getGUI() {
        return gui;
    }

    public void setGUI(GUI g) {
        gui = g;
        afficherMessageDeBienvenue();
    }


    Inventaire inventaire = new Inventaire();
    Joueur joueur = new Joueur("Tyrion", inventaire.getListeInventaire());


    private void creerCarte() {
        //création des pièces
        Piece[] pieces = new Piece[20];
        // piece sans PNJ et sans item : pieces[] = new Piece("nom","description",".jpg");
        // piece avec PNJ et/ou item : pieces[] = new Piece("nom","description",".jpg","etat")

        //étage -1
        pieces[0] = new Piece("au centre du donjon", "C'est l'endroit où vous vous êtes réveillé", "Donjon.jpg");
        pieces[1] = new Piece("dans la prison", "Des rochers vous bloquent ce qui vous semble être un chemin, peut être que le prisonnier\nsait ce qu'il se cache derrière", "Prison.jpg");
        pieces[2] = new Piece("à l'escalier nord", "Il fait trop noir pour monter ces escaliers", "EscalierNord.jpg");
        pieces[3] = new Piece("dans le couloir", "Peut être que quelque chose se cache dans ces vieux pots", "Couloir.jpg");
        pieces[4] = new Piece("dans la salle des coffres", "Un coffre jaune et un coffre bleu, une clé serait nécessaire pour les dévérouiller", "SalleDesCoffres.jpg");

        //RDC
        pieces[5] = new Piece("Escalier de gauche", "Vous appercevez l'entrée du château", "EscalierGauche.jpg");
        pieces[6] = new Piece("Entrée du château", "C'est l'entrée du château, comment s'en échapper ? ", "EntreeChateau.jpg");
        pieces[7] = new Piece("Bibliothèque", "Peut être que des informations sur le château sont écrites ici", "Bibliotheque.jpg");
        pieces[8] = new Piece("Salle du trône", "On apperçoit le trône du Roi, il vaudrait mieux ne pas s'asseoir dessus", "SalleDuTrone.jpg");
        pieces[9] = new Piece("Escalier de droite", "Vous appercevez l'entrée du château", "EscalierDroite.jpg");
        pieces[10] = new Piece("Armurerie", "Il y a des armes partout", "Armurerie.jpg");
        pieces[11] = new Piece("Cuisine", "Il semblerait que quelque chose se cache derrière ce comptoir", "Cuisine.jpg");
        pieces[12] = new Piece("Entrée chambre de la Princesse", "Cette entrée est bloquée", "EntreeChambrePrincesse.jpg");
        pieces[13] = new Piece("Galerie", "", "Galerie.jpg");
        pieces[14] = new Piece("Entrée tour de gauche", "Cette entrée est bloquée", "EntreeTourGauche.jpg");
        pieces[15] = new Piece("Salle à manger", "", "SalleAManger.jpg");

        //ajout des sorties
        // étage - 1
        pieces[0].ajouteSortie(Sortie.EST, pieces[1]);
        pieces[0].ajouteSortie(Sortie.NORD, pieces[2]);
        pieces[0].ajouteSortie(Sortie.OUEST, pieces[3]);
        pieces[1].ajouteSortie(Sortie.OUEST, pieces[0]);
        //to do : la version de la pièce sans rocher qui débloque la sortir descendre
        //zones[1].ajouteSortie(Sortie.DESCENDRE, zones[?]);
        pieces[2].ajouteSortie(Sortie.MONTER, pieces[5]);
        pieces[2].ajouteSortie(Sortie.SUD, pieces[0]);
        pieces[3].ajouteSortie(Sortie.EST, pieces[0]);
        pieces[3].ajouteSortie(Sortie.SUD, pieces[4]);
        pieces[4].ajouteSortie(Sortie.OUEST, pieces[3]);

        //rdc
        pieces[5].ajouteSortie(Sortie.DESCENDRE, pieces[2]);
        pieces[5].ajouteSortie(Sortie.EST, pieces[6]);
        pieces[5].ajouteSortie(Sortie.NORD, pieces[7]);

        pieces[6].ajouteSortie(Sortie.OUEST, pieces[5]);
        pieces[6].ajouteSortie(Sortie.NORD, pieces[8]);
        pieces[6].ajouteSortie(Sortie.EST, pieces[9]);

        pieces[7].ajouteSortie(Sortie.SUD, pieces[5]);
        pieces[7].ajouteSortie(Sortie.NORD, pieces[15]);

        pieces[8].ajouteSortie(Sortie.SUD, pieces[6]);

        //zones[9].ajouteSortie(Sortie.DESCENDRE, zones[?]);
        pieces[9].ajouteSortie(Sortie.OUEST, pieces[6]);
        pieces[9].ajouteSortie(Sortie.NORD, pieces[10]);

        pieces[10].ajouteSortie(Sortie.SUD, pieces[9]);
        pieces[10].ajouteSortie(Sortie.NORD, pieces[11]);

        //to do la liaison 15 11
        //zones[11].ajouteSortie(Sortie., zones[15]);
        pieces[11].ajouteSortie(Sortie.SUD, pieces[10]);
        pieces[11].ajouteSortie(Sortie.NORD, pieces[12]);

        pieces[12].ajouteSortie(Sortie.SUD, pieces[11]);
        pieces[12].ajouteSortie(Sortie.OUEST, pieces[13]);
        //zones[12].ajouteSortie(Sortie.MONTER, zones[?]);

        pieces[13].ajouteSortie(Sortie.EST, pieces[12]);
        pieces[13].ajouteSortie(Sortie.OUEST, pieces[14]);

        pieces[14].ajouteSortie(Sortie.SUD, pieces[15]);
        pieces[14].ajouteSortie(Sortie.EST, pieces[13]);
        //zones[14].ajouteSortie(Sortie.MONTER, zones[,]);

        pieces[15].ajouteSortie(Sortie.SUD, pieces[7]);
        pieces[15].ajouteSortie(Sortie.NORD, pieces[14]);
        //to do pareil que la cusine
        //zones[14].ajouteSortie(Sortie., zones[11]);

        //ajout des personnages

        Prisonnier prisonnier = new Prisonnier("Jacques");
        Cuisinier cuisinier = new Cuisinier("Jacquelin");
        Servante servante = new Servante("Jacqueline");
        /*
        Garde garde1 = new Garde();
        Garde garde2 = new Garde();
        Garde garde3 = new Garde();
        Princesse princesse = new Princesse();
        Boss boss = new Boss();
        */

        pieces[1].ajouterPNJ(prisonnier);
        pieces[6].ajouterPNJ(cuisinier);
        pieces[14].ajouterPNJ(servante);


        //ajout des objets

        Item baton = new Item("baton", "sert à construire un objet");
        Item teteDePioche = new Item("Tete de pioche", "sert à construire un objet");

        pieces[3].ajouterItem(baton);
        pieces[3].ajouterItem(teteDePioche);

        this.pieceCourante = pieces[0];
    }

    private void afficherLocalisation() {
        gui.afficher(pieceCourante.descriptionLongue());
        gui.afficher();
    }

    private void afficherMessageDeBienvenue() {
        gui.afficher("Bienvenue !");
        gui.afficher();
        gui.afficher("Tapez '?' pour obtenir de l'aide");
        gui.afficher();
        gui.afficher("Après avoir été fait prisonnier vous vous retrouvez au milieu d'un donjon\n");
        afficherLocalisation();
        gui.afficheImage(pieceCourante.nomImage());
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
            case "P":
            case "PARLER":
                parler();
                break;
            case "F":
            case "FOUILLER":
                fouiller();
                break;
            case "I":
            case "INVENTAIRE":
                consulterInventaire();
                break;
//            case "CR":
//            case "CRAFTER":
//                crafter();
            default:
                gui.afficher("Commande inconnue");
                break;
        }
    }

    private void afficherAide() {
        gui.afficher("Etes-vous perdu ?\n");
        gui.afficher("Les commandes autorisées sont :\n");
        gui.afficher(Commande.toutesLesDescriptions().toString());
        gui.afficher("\nVous pouvez utiliser le nom ou l'abréviation");
    }

    private void allerEn(String direction) {
        Piece nouvelle = pieceCourante.obtientSortie(direction);
        if (nouvelle == null) {
            gui.afficher("Pas de sortie " + direction);
            gui.afficher();
        } else {
            pieceCourante = nouvelle;
            gui.afficher(pieceCourante.descriptionLongue());
            gui.afficher();
            gui.afficheImage(pieceCourante.nomImage());
        }
    }

    private void parler() {
        if (!pieceCourante.getListePNJ().isEmpty()) {
            for (PNJ pnj : pieceCourante.getListePNJ()) {
                gui.afficher(pnj.dialogue());
            }
        } else {
            gui.afficher("Il n'y a personne avec qui parler");
        }
    }

    private void fouiller() {
        if (pieceCourante.getListeItem().isEmpty()) {
            gui.afficher("Il n'y a pas d'item à récupérer dans cette pièce");
        } else {
            for (Item item : pieceCourante.getListeItem()) {
                inventaire.ajouterInventaire(item);
                gui.afficher(item.getNom() + " a été ajouté à l'inventaire");
                pieceCourante.retirerItem(item);
            }
        }
    }

    private void consulterInventaire() {
        gui.afficher(inventaire.afficherInventaire());
    }

//    private void crafter() {
//        String obj1 = "baton";
//        String obj2 = "TeteDePioche";
//        boolean obj1present = false;
//        boolean obj2present = false;
//
//        String obj3 = "charbon";
//        boolean obj3present = false;
//        int a = 3;
//        if (a != 3) {
//            gui.afficher("Il n'y a pas d'établi dans cette pièce");
//        } else {
//            for (Item item : inventaire.getListeInventaire()) {
//                if (item.getNom().equals(obj1)) {
//                    obj1present = true;
//                }
//                if (item.getNom().equals(obj2)) {
//                    obj2present = true;
//                }
//                if (obj1present && obj2present) {
//                    break;
//                }
//            }
//            for (Item item : inventaire.getListeInventaire()) {
//                if (item.getNom().equals(obj1)) {
//                    obj1present = true;
//                }
//                if (item.getNom().equals(obj3)) {
//                    obj3present = true;
//                }
//                if (obj1present && obj3present) {
//                    break;
//                }
//            }
//        }
//        if (obj1present && obj2present) {
//            for (Item item : inventaire.getListeInventaire()) {
//                if (item.getNom().equals("baton")) {
//                    inventaire.retirerInventaire(item);
//                }
//                if (item.getNom().equals("TeteDePioche")) {
//                    inventaire.retirerInventaire(item);
//                }
//            }
//
//        } else if (obj1present && obj3present) {
//            for (Item item : inventaire.getListeInventaire()) {
//                if (item.getNom().equals("baton")) {
//                    inventaire.retirerInventaire(item);
//                }
//                if (item.getNom().equals("charbon")) {
//                    inventaire.retirerInventaire(item);
//                }
//            }
//        } else {
//            gui.afficher("Vous ne pouvez pas crafter d'objet, car il vous manque des objets");
//        }
//    }
//
//
//    public boolean equals(Object obj) {
//        if ((obj == null) || (this.getClass() != obj.getClass()))
//            return (false);
//        else {
//            if (this == obj) {
//                return (true);
//            } else {
//                Jeu inventaire = (Jeu) obj;
//                return (this.inventaire == inventaire.inventaire);
//            }
//        }
//    }

    private void terminer() {
        gui.afficher("Au revoir...");
        gui.enable(false);
    }
}//fin de la classe


