package jeu;

import jeu.objets.*;
import jeu.personnages.*;

import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Jeu implements Serializable {
    private GUI gui;
    private int etatCommande;
    private int var;
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
                    case "LANC":
                    case "LANCER":
                        lancer();
                        break;
                    case "SAUV":
                    case "SAUVEGARDER":
                        sauvegarder();
                        break;
                    case "CONT":
                    case "CONTINUER":
                        continuer();
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
                    case "VERIF":
                    case "VERIFIER":
                        verifierPlaques();
                        break;
                    case "DEBUG":
                        debug();
                        break;
                    case "INS":
                    case "INSPECTER":
                        inspecter();
                        break;
                    case "DEV":
                    case "DEVEROUILLER":
                        deverrouiller();
                        break;
                    case "SAS":
                    case "SASSEOIR":
                        sAsseoir();
                        break;
                    default:
                        gui.afficher("Commande inconnue\n");
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
                        gui.afficher("Commande invalide\n");
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
                        gui.afficher("Commande invalide\n");
                        break;
                }
                break;
            case 3:
                gui.afficher("> " + commandeLue + "\n");
                if (commandeLue.equalsIgnoreCase("1") || commandeLue.equalsIgnoreCase("2") || commandeLue.equalsIgnoreCase("3")) {
                    boolean etatPlaque = poserPlaque(Integer.parseInt(commandeLue));
                    this.var = Integer.parseInt(commandeLue);
                    if (etatPlaque) {
                        gui.afficher("Il y a deja un objet sur la plaque\nRecommencez\n");
                        this.etatCommande = 0;
                    } else {
                        gui.afficher("Quel objet voulez-vous poser sur cette plaque ?\nVeuillez écrire le nom complet de l'objet\n");
                        this.etatCommande = 4;
                    }
                } else {
                    gui.afficher("Numero de plaque incorect\nRecommencez\n");
                    this.etatCommande = 0;
                }
                break;
            case 4:
                switch (commandeLue.toUpperCase()) {
                    case "PELUCHE", "PIOCHE", "LINGOT":
                        poserObjetSurPlaque(var, commandeLue.toUpperCase());
                        break;
                    default:
                        gui.afficher("Item invalide\nRecommencez\n");
                        this.etatCommande = 0;
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
        Piece nouvelle = map.getPieceCourante().obtientSortie(direction);
        if (nouvelle == null) {
            gui.afficher("\nPas de sortie " + direction);
            gui.afficher();
        } else {
            map.setPieceCourante(nouvelle);
            gui.afficher(map.getPieceCourante().descriptionLongue());
            gui.afficher();
            gui.afficheImage(map.getPieceCourante().nomImage());
        }
    }

    private void lancer() {
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans le menu")) {
            gui.afficher("Vous avez lancé le jeu\n");
            gui.afficher("Après avoir été fait prisonnier vous vous retrouvez au milieu d'un donjon\n");
            map.setPieceCourante(map.getMap()[0]);
            this.gui.afficheImage(this.map.getPieceCourante().nomImage());
//            this.gui.afficher(this.map.getPieceCourante().getNomPiece());
//            this.gui.afficher();
//            this.gui.afficher(this.map.getPieceCourante().getDescription());
//            this.gui.afficher();
            this.gui.afficher(this.map.getPieceCourante().descriptionLongue());
        } else {
            gui.afficher("Le jeu est déjà lancé");
        }
    }

    private void sauvegarder() {
        Jeu object = this;
        File fichier = new File("save.ser");

        try {
            FileOutputStream file = new FileOutputStream(fichier);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(object);
            out.close();
            file.close();
            gui.afficher("Sauvegarde réussie");
        } catch (IOException ioe) {
            System.err.println("Erreur d'E/S.");
            ioe.printStackTrace(System.err);
        }
    }

    private void continuer() {
        Jeu object1;
        String filename = "save.ser";
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            object1 = (Jeu) in.readObject();
            in.close();
            file.close();
            gui.afficher("Reprise sauvegarde\n");
            this.map = object1.map;
            this.gui.afficheImage(this.map.getPieceCourante().nomImage());
            this.gui.afficher(this.map.getPieceCourante().descriptionLongue());
            this.inventaire = object1.inventaire;
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Erreur : classe non-trouvée");
        } catch (EOFException eofe) {
            System.out.println("Fin du fichier atteinte");
        } catch (FileNotFoundException fnfe) {
            System.err.println("Erreur de fichier");
        } catch (IOException ioe) {
            System.err.println("Erreur d'E/S");
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

        } else if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la salle des gardes")) {
//            parlerGarde();
        } else {
            gui.afficher("Il n'y a personne avec qui parler");
        }
    }

//    private void parlerGarde() {
//
//        String letter = map.tireLettreAleat();
//
//        int vie = 3;
//        while (vie > 0) {
//            switch (letter) {
//                case "A":
//                    PNJ G1 = map.getPieceCourante().getListePNJ().getFirst();
//                    gui.afficher(G1.dialogue());
//                    gui.afficher(((Garde) G1).getQ1());
//                    String commandeLue = gui.lireCommande();
//                    while (!commandeLue.equals("1") || commandeLue.equals("2") || commandeLue.equals("3")) {
//                        gui.afficher("Veuillez répondre à la question posée par 1, 2 ou 3\n");
//                        if (commandeLue.equals("2") || commandeLue.equals("3")) {
//                            gui.afficher("Mauvais réponse, vous perdez une vie");
//                            vie--;
//                        } else {
//                            //gui.afficher("Je suis le deuxième garde Armando et je vais vous poser ma question : \n");
//                            //gui.afficher(garde2[0]);
//                            PNJ G2 = map.getPieceCourante().getListePNJ().get(1);
//                            gui.afficher(((Garde) G2).dialogue());
//                            gui.afficher(((Garde) G2).getQ1());
//                            if (commandeLue.equals("2") || commandeLue.equals("3")) {
//                                gui.afficher("Mauvais réponse, vous perdez une vie");
//                                vie--;
//                            } else {
//                                //gui.afficher("Je suis le troisième garde Noa et je vais vous poser ma question : \n");
//                                //gui.afficher(garde3[0]);
//                                PNJ G3 = map.getPieceCourante().getListePNJ().get(2);
//                                gui.afficher(((Garde) G3).dialogue());
//                                gui.afficher(((Garde) G3).getQ1());
//                                if (commandeLue.equals("2") || commandeLue.equals("3")) {
//                                    gui.afficher("Mauvais réponse, vous perdez une vie");
//                                    vie--;
//                                }
//                            }
//                        }
//
//                    }//fin while
//                    break;
//                case "R":
//                    //j'affiche pour les 3 gardes le contenu de l'indice 1
//                    //gui.afficher("Je suis le premier garde Rachid et je vais vous poser ma question : \n");
//                    //gui.afficher(garde1[1]);
//                    PNJ G11 = map.getPieceCourante().getListePNJ().get(0);
//                    gui.afficher(((Garde) G11).dialogue());
//                    gui.afficher(((Garde) G11).getQ2());
//                    commandeLue = gui.lireCommande();
//                    while (!commandeLue.equals("1") || commandeLue.equals("2") || commandeLue.equals("3")) {
//                        gui.afficher("Veuillez répondre à la question posée par 1, 2 ou 3\n");
//                        if (commandeLue.equals("2") || commandeLue.equals("3")) {
//                            gui.afficher("Mauvais réponse, vous perdez une vie");
//                            vie--;
//                        } else {
//                            //gui.afficher("Je suis le deuxième garde Armando et je vais vous poser ma question : \n");
//                            //gui.afficher(garde2[1]);
//                            PNJ G12 = map.getPieceCourante().getListePNJ().get(1);
//                            gui.afficher(((Garde) G12).dialogue());
//                            gui.afficher(((Garde) G12).getQ2());
//                            if (commandeLue.equals("2") || commandeLue.equals("3")) {
//                                gui.afficher("Mauvais réponse, vous perdez une vie");
//                                vie--;
//                            } else {
//                                //gui.afficher("Je suis le troisième garde Noa et je vais vous poser ma question : \n");
//                                //gui.afficher(garde3[1]);
//                                PNJ G13 = map.getPieceCourante().getListePNJ().get(2);
//                                gui.afficher(((Garde) G13).dialogue());
//                                gui.afficher(((Garde) G13).getQ2());
//                                if (commandeLue.equals("2") || commandeLue.equals("3")) {
//                                    gui.afficher("Mauvais réponse, vous perdez une vie");
//                                    vie--;
//                                }
//                            }
//                        }
//                    }//fin while
//                    break;
//                case "S":
//                    PNJ G21 = map.getPieceCourante().getListePNJ().get(0);
//                    gui.afficher(((Garde) G21).dialogue());
//                    gui.afficher(((Garde) G21).getQ3());
//                    commandeLue = gui.lireCommande();
//                    while (!commandeLue.equals("1") || commandeLue.equals("2") || commandeLue.equals("3")) {
//                        gui.afficher("Veuillez répondre à la question posée par 1, 2 ou 3\n");
//                        if (commandeLue.equals("2") || commandeLue.equals("3")) {
//                            gui.afficher("Mauvais réponse, vous perdez une vie");
//                            vie--;
//                        } else {
//                            //gui.afficher("Je suis le deuxième garde Armando et je vais vous poser ma question : \n");
//                            //gui.afficher(garde2[2]);
//                            PNJ G22 = map.getPieceCourante().getListePNJ().get(1);
//                            gui.afficher(((Garde) G22).dialogue());
//                            gui.afficher(((Garde) G22).getQ3());
//                            if (commandeLue.equals("2") || commandeLue.equals("3")) {
//                                gui.afficher("Mauvais réponse, vous perdez une vie");
//                                vie--;
//                            } else {
//                                //gui.afficher("Je suis le troisième garde Noa et je vais vous poser ma question : \n");
//                                //gui.afficher(garde3[2]);
//                                PNJ G31 = map.getPieceCourante().getListePNJ().get(2);
//                                gui.afficher(((Garde) G31).dialogue());
//                                gui.afficher(((Garde) G31).getQ3());
//                                if (commandeLue.equals("2") || commandeLue.equals("3")) {
//                                    gui.afficher("Mauvais réponse, vous perdez une vie");
//                                    vie--;
//                                }
//                            }
//                        }
//                    }//fin while
//                    break;
//                default:
//                    gui.afficher("");
//                    break;
//            }//fin switch case
//
//
//            if (vie == 0) {
//                gui.afficher("Vous avez perdu toute vos vies - - - GAME OVER\n");
//                //re direction à faire
//            } else {
//                //si tout ok : débloquer sortie vers salle du boss
//                gui.afficher("Félicitations vous avez réussi toutes les énigmes... Nous vous laissons passer" + "\n" + "Et vous souhaitons bon courage.");
//                map.getPieceCourante().ajouteSortie(Sortie.MONTER, map.getMap()[17]);
//            }
//        }
//    }


    private void donnerCle(String commandeLue) {
        if (commandeLue.equals("JAU") || commandeLue.equals("JAUNE")) {
            inventaire.ajouterInventaire(new Item("Clé Jaune", "Sert à dévérouiller le coffre jaune"));
            gui.afficher("\nLa clé Jaune a été ajouté à l'inventaire");
            for (PNJ pnj : map.getPieceCourante().getListePNJ()) {
                if (pnj.getEtat() == 0) {
                    gui.afficher(((Prisonnier) pnj).dialogueJaune());
                    pnj.setEtat(1);
                }
            }
        } else {
            inventaire.ajouterInventaire(new Item("Clé Bleue", "Sert à dévérouiller le coffre bleu"));
            gui.afficher("\nLa clé Bleue a été ajouté à l'inventaire");
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
            gui.afficher("\nQuel pot voulez fouiller : F1 F2 F3 F4 F5 F6");
            this.etatCommande = 2;
        } else if (map.getPieceCourante().estVideDObjet()) {
            gui.afficher("\nIl n'y a pas d'item à récupérer dans cette pièce");
        } else {
            Item stock = null;
            for (Item item : map.getPieceCourante().getListeItem()) {
                if (item.getClass() == Item.class) {
                    inventaire.ajouterInventaire(item);
                    gui.afficher("\n" + item.getNom() + " a été ajouté à l'inventaire");
                    stock = item;
                    break;
                }
            }
            map.getPieceCourante().retirerItem(stock);
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

        } else if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la cuisine, derrière le comptoir")) {
            inventaire.ajouterInventaire(new Item("Clé de la chambre", "Elle doit pouvoir ouvrir la grille en haut à droite"));
            gui.afficher("La clé de la chambre a été ajoutée à l'inventaire\n");
            map.getPieceCourante().setNomPiece("dans la cuisine, derrière le comptoir, le coffre est vide");
            map.getPieceCourante().setDescription("Il n'y a plus rien à faire ici");
        } else if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la cuisine, derrière le comptoir, le coffre est vide")) {
            gui.afficher("Vous avez déjà ouvert le coffre");
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
            int cptBaton = 0;
            ArrayList<Item> batons = new ArrayList<Item>();
            for (Item item : inventaire.getListeInventaire()) {
                if (item.getNom().startsWith("Baton")) {
                    cptBaton++;
                    batons.add(item);
                }
            }
            if (cptBaton == 3) {
                for (Item baton : batons) {
                    inventaire.retirerInventaire(baton);
                }
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
                    gui.afficher("Vous avez débloqué la sortie descendre\n");
                } else gui.afficher("Vous ne possédez pas d'outil pour réaliser cette action\n");
            }
        } else {
            gui.afficher("Vous ne pouvez pas utiliser cette commande ici\n");
        }
    }

    private void fouillerPot(int num) {
        Pot temp = map.getPieceCourante().getPotByNum(num);
        if (temp.getEtat() == 1) {
            inventaire.ajouterInventaire(new Item("Baton" + num, "Sert à crafter une échelle"));
            gui.afficher("Un baton a été ajouté à l'inventaire\n");
            temp.setEtat(0);
        } else {
            gui.afficher("Le pot " + num + "est vide\n");
        }
        this.etatCommande = 0;
    }

    private void poser() {
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la grotte")) {
            gui.afficher("Sur quel plaque voulez vous poser un item ? Tapez 1 2 3\n");
            this.etatCommande = 3;
        } else if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la salle des pots")) {
            Item echelle = inventaire.getItemByName("Echelle");
            if (echelle != null) {
                inventaire.retirerInventaire(echelle);
                map.getPieceCourante().ajouteSortie(Sortie.DESCENDRE, map.getMap()[23]);
                gui.afficher("\nVous avez posé l'échelle sur et pouvez maintenant descendre");
            } else {
                gui.afficher("\nVous n'avez pas d'objet vous permettant de descendre");
            }
        } else {
            gui.afficher("Vous ne pouvez pas poser d'objet dans cette pièce\n");
        }
    }

    private boolean poserPlaque(int num) {
        return map.getPieceCourante().getPlaqueByNum(num).getEtat() == 1;
    }

    private void poserObjetSurPlaque(int num, String commandeLue) {
        Item item = inventaire.getItemByName(commandeLue);
        if (item == null) {
            gui.afficher("Vous n'avez pas cet item dans l'inventaire\n");
        } else {
            map.getPieceCourante().getPlaqueByNum(num).setItem(item);
            map.getPieceCourante().getPlaqueByNum(num).setEtat(1);
            inventaire.retirerInventaire(item);
            gui.afficher("Vous avez posé " + item.getNom() + " sur la plaque " + num + "\n");
        }
        this.etatCommande = 0;
    }

    private void verifierPlaques() {
        Plaque plaque1 = map.getPieceCourante().getPlaqueByNum(1);
        Plaque plaque2 = map.getPieceCourante().getPlaqueByNum(2);
        Plaque plaque3 = map.getPieceCourante().getPlaqueByNum(3);
        if (plaque1.getEtat() == 1 && plaque2.getEtat() == 1 && plaque3.getEtat() == 1) {
            if (plaque1.getItem().getNom().equals("Peluche") && plaque2.getItem().getNom().equals("Pioche") && plaque3.getItem().getNom().equals("Lingot")) {
                map.getPieceCourante().ajouteSortie(Sortie.EST, map.getMap()[25]);
                gui.afficher("Vous avez débloqué la sortie descendre\n");
            } else {
                gui.afficher("Les objets posés ne sont pas dans le bon orde\n");
            }
        } else {
            gui.afficher("Il vous manque des objets sur les plaques\n");
        }
    }

    private void inspecter() {
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la salle à manger")) {
            map.getPieceCourante().ajouteSortie(Sortie.DESCENDRE, map.getMap()[26]);
            map.getMap()[26].ajouteSortie(Sortie.DESCENDRE, map.getMap()[15]);
            map.getPieceCourante().setNomPiece("dans la salle à manger débloquée");
            map.getPieceCourante().setDescription("");

            // changer l'affichage de la pièce
            gui.afficher("Un chemin se cachait derrière la cheminée\nVous pouvez descendre\n");
            // repaint : on arrive dans la cuisine et le perso est derrière le comptoir
//            map.getPieceCourante().setNomPiece("dans la cuisine, de l'autre côté du comptoir");
//            map.getPieceCourante().setDescription("Super, un coffre !");
        } else if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la salle à mange débloquée")) {
            gui.afficher("Vous avez déjà débloqué cette pièce\n");
        } else {
            gui.afficher("Vous ne pouvez pas utiliser cette commande ici\n");
        }
    }

    private void deverrouiller() {
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "devant la chambre de la Princesse")) {
            if (inventaire.getListeInventaire().isEmpty()) {
                gui.afficher("Vous ne possédez pas la clé pour réaliser cette action\n");
            } else {
                Item cleRDC = inventaire.getItemByName("Clé de la chambre");
                if (cleRDC != null) {
                    map.getPieceCourante().ajouteSortie(Sortie.MONTER, map.getMap()[18]);
                    gui.afficher("Vous avez débloqué la sortie monter");
                    // afficher la pièce sans la grille
                } else gui.afficher("Il vous faudrait une clé pour ouvrir cette grille\n");
            }
        }
        //si pas dans la pièce courante :
        else gui.afficher("Vous ne pouvez pas utiliser cette commande ici\n");
    }


    private void sAsseoir() {
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la salle du trône")) {
            //game over
            gui.afficher("GAME OVER    --- Vous avez perdu la partie\n");
            //gérer le GUI et les fichiers
            terminer();
        } else gui.afficher("Vous ne pouvez pas utiliser cette commande ici\n");
    }

    private void terminer() {
        gui.afficher("Au revoir...");
        gui.afficher("Fermeture du jeu dans 3 secondes");
        fermeture();
    }

    private void fermeture() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }
        gui.fermer();
    }

    private void debug() {
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la grotte")) {
            Plaque plaque = map.getPieceCourante().getPlaqueByNum(1);
            System.out.println(plaque.getNum());
        }
    }


} //fin de la classe