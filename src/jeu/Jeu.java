package jeu;

import jeu.objets.*;
import jeu.personnages.*;

import java.util.ArrayList;


public class Jeu {

    private GUI gui;
    private Piece pieceCourante;
    private Piece[] map;
    private int etatCommande;

    public Jeu() {
        creerCarte();
        this.gui = null;
        this.etatCommande = 0;
    }

    public GUI getGUI() {
        return gui;
    }

    public void setGUI(GUI g) {
        gui = g;
        afficherMessageDeBienvenue();
    }

    public Piece getPieceCourante() {
        return pieceCourante;
    }

    Inventaire inventaire = new Inventaire();
    Joueur joueur = new Joueur("Tyrion", inventaire.getListeInventaire());


    private void creerCarte() {

        this.map = new Piece[25];

        //étage -1
        map[0] = new Piece("au centre du donjon", "C'est l'endroit où vous vous êtes réveillé, sans souvenirs", "Donjon.jpg"); //centre
        map[1] = new Piece("dans la prison", "Des rochers vous bloquent ce qui semble être un chemin, peut être que le prisonnier" + "\n" + "sait ce qu'il se cache derrière", "Prison.jpg"); //est
        map[2] = new Piece("à l'escalier Nord", "Il fait trop noir pour monter ces escaliers", "EscalierNord.jpg"); //nord
        map[3] = new Piece("dans le couloir", "Peut-être que quelque-chose se cache dans ces vieux pots...", "Couloir.jpg"); //ouest
        map[4] = new Piece("dans la salle des coffres", "Vous voyez un coffre jaune et un coffre bleu vérouillés, une clé doit sûrement se cacher" + "\n" + "quelque-part...", "SalleDesCoffres.jpg"); //sud

        // RDC
        map[5] = new Piece("à l'escalier de gauche", "Vous apercevez l'entrée du château", "EscalierGauche.jpg");
        map[6] = new Piece("à l'entrée du château", "C'est l'entrée du château ! Comment faire pour s'échapper...", "EntreeChateau.jpg");
        map[7] = new Piece("dans la bibliothèque", "Peut-être que des informations utiles sont gardées ici", "Bibliotheque.jpg");
        map[8] = new Piece("dans la salle du trône", "On aperçoit le trône du Roi, cela serait osé de s'asseoir dessus en son absence...", "SalleDuTrone.jpg");
        map[9] = new Piece("à l'escalier de droite", "Vous apercevez l'entrée du château", "EscalierDroite.jpg");
        map[10] = new Piece("dans l'armurerie", "Il y a des armes partout ! Et personne ne les surveille...", "Armurerie.jpg");
        map[11] = new Piece("dans la cuisine", "Il semblerait que quelque-chose se cache derrière ce comptoir...", "Cuisine.jpg");
        map[12] = new Piece("devant la chambre de la Princesse", "Cette entrée est bloquée", "EntreeChambrePrincesse.jpg");
        map[13] = new Piece("dans la galerie", "", "Galerie.jpg");
        map[14] = new Piece("devant la tour de gauche", "Cette entrée est bloquée", "EntreeTourGauche.jpg");
        map[15] = new Piece("dans la salle à manger", "La cheminée est étrangement propre", "SalleAManger.jpg");
        map[24] = new Piece("dans la cuisine, derrière le comptoir", "Vous êtes face à un coffre", "");

        // tour 1G
        map[16] = new Piece("dans la salle des gardes", "Les gardes ont l'air féroces, on dirait qu'ils bloquent l'accès Nord", "SalleDesGardes.jpg");
        map[17] = new Piece("dans la salle du Boss", "Le monstre du château se dresse devant-vous" + "\n" + "Qu'allez-vous faire... Fuir ou combattre ?", "SalleDuBoss.jpg");

        // tour 1D
        map[18] = new Piece("dans la chambre de la Princesse", "Ce qui semblait être un vieux grenier à l'air de faire office de chambre à coucher", "ChambrePrincesse.jpg");

        // étage -2
        map[19] = new Piece("dans les catacombes", "Le mur est en piteux état, on dirait que quelqu'un a écrit dessus", "Catacombes.jpg");
        map[20] = new Piece("dans le labyrinthe", "Il fait tout noir ! Et le chemin est très étroit" + "\n" + "Il ne faudrait pas tomber dans le vide", "Labyrinthe.jpg");
        map[21] = new Piece("à l'escalier", "Deux directions semblent se distinguer... Nord ou Sud ?", "EscalierCatacombes.jpg");
        map[22] = new Piece("dans la salle des pots", "On doit forcément trouver des ressources parmi tous ces pots...", "SalleDesPots.jpg");

        // étage -3
        map[23] = new Piece("dans la grotte", "Il y a trois plaques de pression" + "\n" + "Si on les regarde de gauche à droite, elles sont de plus en plus épaisses" + "\n" + "Essayez-donc de poser des objets de plus en plus lourds dans ce même ordre", "Grotte.jpg");

        //ajout des sorties
        // étage - 1
        map[0].ajouteSortie(Sortie.EST, map[1]);
        map[0].ajouteSortie(Sortie.NORD, map[2]);
        map[0].ajouteSortie(Sortie.OUEST, map[3]);
        map[1].ajouteSortie(Sortie.OUEST, map[0]);
        map[2].ajouteSortie(Sortie.SUD, map[0]);
        map[3].ajouteSortie(Sortie.EST, map[0]);
        map[3].ajouteSortie(Sortie.SUD, map[4]);
        map[4].ajouteSortie(Sortie.OUEST, map[3]);

        //rdc
        map[5].ajouteSortie(Sortie.DESCENDRE, map[2]);
        map[5].ajouteSortie(Sortie.EST, map[6]);
        map[5].ajouteSortie(Sortie.NORD, map[7]);

        map[6].ajouteSortie(Sortie.OUEST, map[5]);
        map[6].ajouteSortie(Sortie.NORD, map[8]);
        map[6].ajouteSortie(Sortie.EST, map[9]);

        map[7].ajouteSortie(Sortie.SUD, map[5]);
        map[7].ajouteSortie(Sortie.NORD, map[15]);

        map[8].ajouteSortie(Sortie.SUD, map[6]);

        map[9].ajouteSortie(Sortie.OUEST, map[6]);
        map[9].ajouteSortie(Sortie.NORD, map[10]);

        map[10].ajouteSortie(Sortie.SUD, map[9]);
        map[10].ajouteSortie(Sortie.NORD, map[11]);

        map[11].ajouteSortie(Sortie.SUD, map[10]);
        map[11].ajouteSortie(Sortie.NORD, map[12]);

        map[12].ajouteSortie(Sortie.SUD, map[11]);
        map[12].ajouteSortie(Sortie.OUEST, map[13]);


        map[13].ajouteSortie(Sortie.EST, map[12]);
        map[13].ajouteSortie(Sortie.OUEST, map[14]);

        map[14].ajouteSortie(Sortie.SUD, map[15]);
        map[14].ajouteSortie(Sortie.EST, map[13]);
        map[14].ajouteSortie(Sortie.MONTER, map[16]);

        map[15].ajouteSortie(Sortie.SUD, map[7]);
        map[15].ajouteSortie(Sortie.NORD, map[14]);

        //tour 1G
        map[16].ajouteSortie(Sortie.SUD, map[14]);
        map[16].ajouteSortie(Sortie.NORD, map[17]);

        map[17].ajouteSortie(Sortie.SUD, map[16]);

        //tour 1D

        map[18].ajouteSortie(Sortie.DESCENDRE, map[12]);

        // étage -1

        map[19].ajouteSortie(Sortie.MONTER, map[1]);
        map[19].ajouteSortie(Sortie.NORD, map[20]);

        map[20].ajouteSortie(Sortie.SUD, map[19]);
        map[20].ajouteSortie(Sortie.OUEST, map[21]);

        map[21].ajouteSortie(Sortie.MONTER, map[9]);
        map[21].ajouteSortie(Sortie.SUD, map[22]);

        map[22].ajouteSortie(Sortie.NORD, map[21]);
        map[22].ajouteSortie(Sortie.DESCENDRE, map[23]);

        // étage -2

        map[23].ajouteSortie(Sortie.MONTER, map[22]);

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

        map[1].ajouterPNJ(prisonnier);
        map[6].ajouterPNJ(cuisinier);
        map[14].ajouterPNJ(servante);

        //ajout des objets

        Item baton = new Item("Baton", "sert à construire un objet");
        Item epee = new Item("Epée", "sert à vous défendre");
        Item lingot = new Item("Lingot", "est très lourd");
        Item peluche = new Item("Peluche", "est très léger");


        Pot.initialiserPot();


        map[3].ajouterItem(baton);

        map[10].ajouterItem(epee);

        map[23].ajouterItem(lingot);
        map[23].ajouterItem(peluche);

//        map[22].ajouterItem(pot1);
//        map[22].ajouterItem(pot2);
//        map[22].ajouterItem(pot3);
//        map[22].ajouterItem(pot4);
//        map[22].ajouterItem(pot5);
//        map[22].ajouterItem(pot6);

        this.pieceCourante = map[22];
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
        switch (etatCommande) {
            case 0:
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
                    case "OUV":
                    case "OUVRIR":
                        ouvrirCoffre();
                        break;
                    case "CR":
                    case "CRAFTER":
                        crafter();
                        break;
                    case "ECL":
                    case "ECLAIRER":
                        eclairer();
                        break;
                    case "CA":
                    case "CASSER":
                        casser();
                        break;
                    default:
                        gui.afficher("Commande inconnue");
                        break;
                }
                break;
            case 1:
                gui.afficher("> " + commandeLue + "\n");
                switch (commandeLue.toUpperCase()) {
                    case "JAU":
                    case "JAUNE":
                    case "BLE":
                    case "BLEUE":
                        donnerCle(commandeLue.toUpperCase());
                        break;
                    default:
                        gui.afficher("Commande invalide");
                        break;
                }
            case 2:
                gui.afficher("> " + commandeLue + "\n");
                switch (commandeLue.toUpperCase()) {
                    case "F1":
                        fouillerPot(1);
                        break;
                    case "F2":
                        fouillerPot(2);
                        break;
                    case "F3":
                        fouillerPot(3);
                        break;
                    case "F4":
                        fouillerPot(4);
                        break;
                    case "F5":
                        fouillerPot(5);
                        break;
                    case "F6":
                        fouillerPot(6);
                        break;
                    default:
                        gui.afficher("Commande invalide");
                        break;
                }
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
                if (pnj instanceof Prisonnier && pnj.getEtat() == 0) {
                    gui.afficher("JAU pour la clé Jaune\nBLE pour la clé Bleue");
                    etatCommande = 1;
                }
            }
        } else {
            gui.afficher("Il n'y a personne avec qui parler");
        }
    }


    private void donnerCle(String commandeLue) {
        if (commandeLue.equals("JAU") || commandeLue.equals("JAUNE")) {
            inventaire.ajouterInventaire(new Item("Clé Jaune", "Sert à dévérouiller le coffre jaune"));
            gui.afficher("La clé Jaune a été ajouté à l'inventaire");
            for (PNJ pnj : pieceCourante.getListePNJ()) {
                if (pnj.getEtat() == 0) {
                    gui.afficher(((Prisonnier) pnj).dialogueJaune());
                    pnj.setEtat(1);
                }
            }
        } else {
            inventaire.ajouterInventaire(new Item("Clé Bleue", "Sert à dévérouiller le coffre bleu"));
            gui.afficher("La clé Bleue a été ajouté à l'inventaire");
            for (PNJ pnj : pieceCourante.getListePNJ()) {
                if (pnj.getEtat() == 0) {
                    gui.afficher(((Prisonnier) pnj).dialogueBleu());
                    pnj.setEtat(1);
                }
            }
        }
        this.etatCommande = 0;
    }

    private void fouiller() {
        if (pieceCourante.getNomPiece() == "dans la salle des pots") {
            gui.afficher("Quel pot voulez fouiller : F1 F2 F3 F4 F5 F6");
            this.etatCommande = 2;
        } else if (pieceCourante.getListeItem().isEmpty()) {
            gui.afficher("Il n'y a pas d'item à récupérer dans cette pièce");
        } else {
            for (Item item : pieceCourante.getListeItem()) {
                inventaire.ajouterInventaire(item);
                gui.afficher(item.getNom() + " a été ajouté à l'inventaire");
                pieceCourante.retirerItem(item);
                if (pieceCourante.getListeItem().isEmpty()) //Merci à l'illustre Philippe Ramadour Andreoletti pour les traveux
                    break;
            }
        }
    }

    private void consulterInventaire() {
        gui.afficher(inventaire.afficherInventaire());
    }

    private void ouvrirCoffre() {
        int etat = 0;
        if (pieceCourante.getNomPiece() == ("dans la salle des coffres")) {
            if (inventaire.getListeInventaire().isEmpty()) {
                gui.afficher("Il vous faudrait une clé afin de pouvoir ouvrir un de ces coffres \n");
            } else {
                Item tempj = inventaire.getItemByName("Clé Jaune");
                if (tempj != null) {
                    inventaire.retirerInventaire(tempj);
                    inventaire.ajouterInventaire(new Item("Tête de pioche", "combiné avec un baton je peux peut être en faire une pioche afin de casser les rochers"));
                    gui.afficher("Le coffre jaune a été ouvert\n");
                    gui.afficher("La tête de pioche a été ajouté à l'inventaire\n");
                    pieceCourante.setNomPiece("dans la salle des coffres (ouverts)");
                    pieceCourante.setDescription("Il n'y a plus rien à faire ici");
                    etat = 1;
                }
                Item tempb = inventaire.getItemByName("Clé Bleue");
                if (tempb != null) {
                    inventaire.retirerInventaire(tempb);
                    inventaire.ajouterInventaire(new Item("Charbon", "combiné avec un baton je peux peut être en faire une torche afin d'éclaire l'escalier"));
                    gui.afficher("Le coffre bleu a été ouvert\n");
                    gui.afficher("Du charbon a été ajouté à l'inventaire\n");
                    pieceCourante.setNomPiece("dans la salle des coffres (ouverts)");
                    pieceCourante.setDescription("Il n'y a plus rien à faire ici");
                    etat = 1;
                }
                if (etat == 0) {
                    gui.afficher("Il vous faudrait une clé afin de pouvoir ouvrir un de ces coffres \n");
                }
            }
        } else if (pieceCourante.getNomPiece() == "dans la salle des coffres (ouverts)") {
            gui.afficher("Vous avez déjà ouvert un coffre");
        } else {
            gui.afficher("Il n'y a rien à ouvrir ici");
        }
    }

    private void crafter() {

        if (pieceCourante.getNomPiece() == "dans le couloir") {
            Item batonPresent = inventaire.getItemByName("Baton");
            Item teteDePiochePresent = inventaire.getItemByName("Tête de pioche");
            Item charbonPresent = inventaire.getItemByName("Charbon");

            if (batonPresent != null && teteDePiochePresent != null) {
                inventaire.retirerInventaire(batonPresent);
                inventaire.retirerInventaire(teteDePiochePresent);
                inventaire.ajouterInventaire(new Item("Pioche", "sert à casser les rochers"));
                gui.afficher("La pioche a été ajouté à l'inventaire");

            } else if (batonPresent != null && charbonPresent != null) {
                inventaire.retirerInventaire(batonPresent);
                inventaire.retirerInventaire(charbonPresent);
                inventaire.ajouterInventaire(new Item("Torche", "sert à éclairer l'escalier"));
                gui.afficher("La torche a été ajouté à l'inventaire");
            } else {
                gui.afficher("Vous ne pouvez pas crafter d'objet, car il vous manque des objets");
            }
        } else if (pieceCourante.getNomPiece() == "dans la salle des pots") {
            Item baton1Present = inventaire.getItemByName("Baton4");
            Item baton2Present = inventaire.getItemByName("Baton5");
            Item baton3Present = inventaire.getItemByName("Baton6");

            if (baton1Present != null && baton2Present != null && baton3Present != null) {
                inventaire.retirerInventaire(baton1Present);
                inventaire.retirerInventaire(baton2Present);
                inventaire.retirerInventaire(baton3Present);
                inventaire.ajouterInventaire(new Item("Echelle", "sert à descendre vers la grotte"));
                gui.afficher("L'échelle a été ajouté à l'inventaire");
            } else {
                gui.afficher("Vous ne pouvez pas crafter d'objet, car il vous manque des objets");
            }
        } else {
            gui.afficher("Il n'y a pas d'établi dans cette pièce");
        }
    }

    private void eclairer() {
        if (pieceCourante.getNomPiece() == ("à l'escalier Nord")) {
            if (inventaire.getListeInventaire().isEmpty()) {
                gui.afficher("Vous ne possédez pas d'outil pour réaliser cette action\n");
            } else {
                Item torchePresent = inventaire.getItemByName("Torche");
                if (torchePresent != null) {
                    // gui.afficheImage(pieceCourante.nomImage("à compléter"));
                    pieceCourante.setNomImage("EscalierEclaire.jpg");
                    pieceCourante.ajouteSortie(Sortie.MONTER, map[5]);
                    gui.afficher("Vous avez débloqué la sortie monter");
                } else gui.afficher("Vous ne possédez pas la ressource pour réaliser cette action\n");
            }
        } else {
            gui.afficher("Vous ne pouvez pas utiliser cette commande ici");
        }
    }

    private void casser() {
        if (pieceCourante.getNomPiece() == ("dans la prison")) {
            if (inventaire.getListeInventaire().isEmpty()) {
                gui.afficher("Vous ne possédez pas d'outil pour réaliser cette action\n");
            } else {
                Item piochePresent = inventaire.getItemByName("Pioche");
                if (piochePresent != null) {
                    pieceCourante.setNomImage("PrisonCassee.jpg");

                    pieceCourante.ajouteSortie(Sortie.DESCENDRE, map[19]);
                    gui.afficher("Vous avez débloqué la sortie descendre");
                } else gui.afficher("Vous ne possédez pas la ressource pour réaliser cette action\n");
            }
            //inventaire non vide mais pas de Pioche
        } else {
            gui.afficher("Vous ne pouvez pas utiliser cette commande ici");
        }
    }

    private void fouillerPot(int num) {
        Pot temp = (Pot) pieceCourante.getPotByNum(num);
        if (temp.getEtat() == 1) {
            inventaire.ajouterInventaire(new Item("Baton" + num, "Sert à crafter une échelle"));
            gui.afficher("Un baton a été ajouté à l'inventaire");
            temp.setEtat(0);
        } else {
            gui.afficher("Le pot " + num + "est vide");
        }
        this.etatCommande = 0;
    }

    // private void poser(){

    //}

    private void inspecter() {
        if (pieceCourante.getNomPiece() == ("dans la salle à manger")) {
            // débloquer la sortie du passage secret vers cuisine
            map[15].ajouteSortie(Sortie.DESCENDRE, map[11]);
            // changer l'affichage de la pièce
            // repaint : on arrive dans la cuisine et le perso est derrière le comptoir
            pieceCourante.setNomPiece("dans la cuisine, de l'autre côté du comptoir");
            pieceCourante.setDescription("Super, un coffre !");
        }
    }

    private void déverrouiller() {
        if (pieceCourante.getNomPiece() == ("devant la chambre de la Princesse")) {
            if (inventaire.getListeInventaire().isEmpty()) {
                gui.afficher("Vous ne possédez pas la clé pour réaliser cette action\n");
            } else {
                Item cleRDC = inventaire.getItemByName("Clé de la chambre");
                if (cleRDC != null) {
                    pieceCourante.ajouteSortie(Sortie.MONTER, map[18]);
                    gui.afficher("Vous avez débloqué la sortie monter");
                    // afficher la pièce sans la grille
                } else gui.afficher("Vous ne possédez pas la ressource pour réaliser cette action\n");
            }
        }
        //si pas dans la pièce courante :
        else gui.afficher("Vous ne pouvez pas utiliser cette commande ici\n");
    }


    private void sAsseoir() {
        if (pieceCourante.getNomPiece() == ("dans la salle du trône")) {
            //game over
            gui.afficher("GAME OVER    --- Vous avez perdu la partie\n");
            //gérer le GUI et les fichiers
        } else gui.afficher("Vous ne pouvez pas utiliser cette commande ici\n");
    }

    private void terminer() {
        gui.afficher("Au revoir...");
        gui.enable(false);
    }


} //fin de la classe