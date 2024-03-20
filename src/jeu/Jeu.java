package jeu;

import jeu.objets.*;
import jeu.personnages.*;

import java.util.Objects;

public class Jeu {
    private GUI gui;
    private int etatCommande;
    Inventaire inventaire = new Inventaire();
    Joueur joueur = new Joueur("Tyrion", inventaire.getListeInventaire());
    Map map = new Map();

    public Jeu() {
        map.creerCarte();
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

    private void afficherLocalisation() {
        gui.afficher(map.getPieceCourante().descriptionLongue());
        gui.afficher();
    }

    private void afficherMessageDeBienvenue() {
        gui.afficher("Bienvenue !");
        gui.afficher();
        gui.afficher("Tapez '?' pour obtenir de l'aide");
        gui.afficher();
        gui.afficher("Après avoir été fait prisonnier vous vous retrouvez au milieu d'un donjon\n");
        afficherLocalisation();
        gui.afficheImage(map.getPieceCourante().nomImage());
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
                    case "PO":
                    case "POSER":
                        poser();
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
                break;
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
            case 3:
                gui.afficher("> " + commandeLue + "\n");
                if (commandeLue.equalsIgnoreCase("1") || commandeLue.equalsIgnoreCase("2") || commandeLue.equalsIgnoreCase("3")) {
                    boolean etatPlaque = poserPlaque(Integer.parseInt(commandeLue));
                    if (etatPlaque) {
                        gui.afficher("\nIl y a deja un objet sur la plaque");
                    } else {
                        gui.afficher("\nQuel objet voulez vous poser sur cette plaque");
                        String itemLue = gui.lireCommande();
                        poserObjetSurPlaque(inventaire.getItemByName(itemLue), Integer.parseInt(commandeLue));
                    }
                } else {
                    gui.afficher("\nNumero de plaque incorect");
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
        Piece nouvelle = map.getPieceCourante().obtientSortie(direction);
        if (nouvelle == null) {
            gui.afficher("Pas de sortie " + direction);
            gui.afficher();
        } else {
            map.setPieceCourante(nouvelle);
            gui.afficher(map.getPieceCourante().descriptionLongue());
            gui.afficher();
            gui.afficheImage(map.getPieceCourante().nomImage());
        }
    }

    private void parler() {
        if (!map.getPieceCourante().getListePNJ().isEmpty()) {
            for (PNJ pnj : map.getPieceCourante().getListePNJ()) {
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
            for (PNJ pnj : map.getPieceCourante().getListePNJ()) {
                if (pnj.getEtat() == 0) {
                    gui.afficher(((Prisonnier) pnj).dialogueJaune());
                    pnj.setEtat(1);
                }
            }
        } else {
            inventaire.ajouterInventaire(new Item("Clé Bleue", "Sert à dévérouiller le coffre bleu"));
            gui.afficher("La clé Bleue a été ajouté à l'inventaire");
            for (PNJ pnj : map.getPieceCourante().getListePNJ()) {
                if (pnj.getEtat() == 0) {
                    gui.afficher(((Prisonnier) pnj).dialogueBleu());
                    pnj.setEtat(1);
                }
            }
        }
        this.etatCommande = 0;
    }

    private void fouiller() {
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la salle des pots")) {
            gui.afficher("Quel pot voulez fouiller : F1 F2 F3 F4 F5 F6");
            this.etatCommande = 2;
        } else if (map.getPieceCourante().getListeItem().isEmpty()) {
            gui.afficher("Il n'y a pas d'item à récupérer dans cette pièce");
        } else {
            for (Item item : map.getPieceCourante().getListeItem()) {
                inventaire.ajouterInventaire(item);
                gui.afficher(item.getNom() + " a été ajouté à l'inventaire");
                map.getPieceCourante().retirerItem(item);
                if (map.getPieceCourante().getListeItem().isEmpty()) //Merci à l'illustre Philippe Ramadour Andreoletti pour les traveux
                    break;
            }
        }
    }

    private void consulterInventaire() {
        gui.afficher(inventaire.afficherInventaire());
    }

    private void ouvrirCoffre() {
        int etat = 0;
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la salle des coffres")) {
            if (inventaire.getListeInventaire().isEmpty()) {
                gui.afficher("Il vous faudrait une clé afin de pouvoir ouvrir un de ces coffres \n");
            } else {
                Item tempj = inventaire.getItemByName("Clé Jaune");
                if (tempj != null) {
                    inventaire.retirerInventaire(tempj);
                    inventaire.ajouterInventaire(new Item("Tête de pioche", "combiné avec un baton je peux peut être en faire une pioche afin de casser les rochers"));
                    gui.afficher("Le coffre jaune a été ouvert\n");
                    gui.afficher("La tête de pioche a été ajouté à l'inventaire\n");
                    map.getPieceCourante().setNomPiece("dans la salle des coffres (ouverts)");
                    map.getPieceCourante().setDescription("Il n'y a plus rien à faire ici");
                    etat = 1;
                }
                Item tempb = inventaire.getItemByName("Clé Bleue");
                if (tempb != null) {
                    inventaire.retirerInventaire(tempb);
                    inventaire.ajouterInventaire(new Item("Charbon", "combiné avec un baton je peux peut être en faire une torche afin d'éclaire l'escalier"));
                    gui.afficher("Le coffre bleu a été ouvert\n");
                    gui.afficher("Du charbon a été ajouté à l'inventaire\n");
                    map.getPieceCourante().setNomPiece("dans la salle des coffres (ouverts)");
                    map.getPieceCourante().setDescription("Il n'y a plus rien à faire ici");
                    etat = 1;
                }
                if (etat == 0) {
                    gui.afficher("Il vous faudrait une clé afin de pouvoir ouvrir un de ces coffres \n");
                }
            }
        } else if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la salle des coffres (ouverts)")) {
            gui.afficher("Vous avez déjà ouvert un coffre");
        } else {
            gui.afficher("Il n'y a rien à ouvrir ici");
        }
    }

    private void crafter() {
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans le couloir")) {
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
        } else if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la salle des pots")) {
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
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "à l'escalier Nord")) {
            if (inventaire.getListeInventaire().isEmpty()) {
                gui.afficher("Vous ne possédez pas d'outil pour réaliser cette action\n");
            } else {
                Item torchePresent = inventaire.getItemByName("Torche");
                if (torchePresent != null) {
                    map.getPieceCourante().setNomImage("EscalierEclaire.jpg");
                    gui.afficheImage("EscalierEclaire.jpg");
                    map.getPieceCourante().ajouteSortie(Sortie.MONTER, map.getMap()[5]);
                    gui.afficher("Vous avez débloqué la sortie monter");
                } else gui.afficher("Vous ne possédez pas la ressource pour réaliser cette action\n");
            }
        } else {
            gui.afficher("Vous ne pouvez pas utiliser cette commande ici");
        }
    }

    private void casser() {
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la prison")) {
            if (inventaire.getListeInventaire().isEmpty()) {
                gui.afficher("Vous ne possédez pas d'outil pour réaliser cette action\n");
            } else {
                Item piochePresent = inventaire.getItemByName("Pioche");
                if (piochePresent != null) {
                    map.getPieceCourante().setNomImage("PrisonCassee.jpg");
                    gui.afficheImage("PrisonCassee.jpg");
                    map.getPieceCourante().ajouteSortie(Sortie.DESCENDRE, map.getMap()[19]);
                    gui.afficher("Vous avez débloqué la sortie descendre");
                } else gui.afficher("Vous ne possédez pas d'outil pour réaliser cette action\n");
            }
        } else {
            gui.afficher("Vous ne pouvez pas utiliser cette commande ici");
        }
    }

    private void fouillerPot(int num) {
        Pot temp = map.getPieceCourante().getPotByNum(num);
        if (temp.getEtat() == 1) {
            inventaire.ajouterInventaire(new Item("Baton" + num, "Sert à crafter une échelle"));
            gui.afficher("Un baton a été ajouté à l'inventaire");
            temp.setEtat(0);
        } else {
            gui.afficher("Le pot " + num + "est vide");
        }
        this.etatCommande = 0;
    }

    private void poser() {
        if (map.getPieceCourante().getNomPiece() == "dans la grotte") {
            gui.afficher("Sur quel plaque voulez vous poser un item ? Tapez 1 2 3");
            this.etatCommande = 3;
        } else {
            gui.afficher("\nVous ne pouvez pas poser d'objet dans cette pièce");
        }
    }

    private boolean poserPlaque(int num) {
        return map.getPieceCourante().getPlaqueByNum(num).getEtat() == 1;
    }

    private void poserObjetSurPlaque(Item item, int num) {
        if (item == null) {
            gui.afficher("Vous n'avez pas cet item dans l'inventaire");
        } else {

        }

    }

//    private void inspecter() {
//        if (map.getPieceCourante().getNomPiece() == ("dans la salle à manger")) {
//            // débloquer la sortie du passage secret vers cuisine
//            map[15].ajouteSortie(Sortie.DESCENDRE, map[11]);
//            // changer l'affichage de la pièce
//            // repaint : on arrive dans la cuisine et le perso est derrière le comptoir
//            map.getPieceCourante().setNomPiece("dans la cuisine, de l'autre côté du comptoir");
//            map.getPieceCourante().setDescription("Super, un coffre !");
//        }
//    }

//    private void déverrouiller() {
//        if (map.getPieceCourante().getNomPiece() == ("devant la chambre de la Princesse")) {
//            if (inventaire.getListeInventaire().isEmpty()) {
//                gui.afficher("Vous ne possédez pas la clé pour réaliser cette action\n");
//            } else {
//                Item cleRDC = inventaire.getItemByName("Clé de la chambre");
//                if (cleRDC != null) {
//                    map.getPieceCourante().ajouteSortie(Sortie.MONTER, map[18]);
//                    gui.afficher("Vous avez débloqué la sortie monter");
//                    // afficher la pièce sans la grille
//                } else gui.afficher("Vous ne possédez pas la ressource pour réaliser cette action\n");
//            }
//        }
//        //si pas dans la pièce courante :
//        else gui.afficher("Vous ne pouvez pas utiliser cette commande ici\n");
//    }


    private void sAsseoir() {
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la salle du trône")) {
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