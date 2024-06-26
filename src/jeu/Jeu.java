package jeu;

import jeu.objets.*;
import jeu.personnages.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Cette classe définit toutes les règles du jeu et permet d'y jouer
 */
public class Jeu implements Serializable {
    private GUI gui;
    private int etatCommande;
    private int var;
    private int vie;
    private Inventaire inventaire = new Inventaire();
    private Map map = new Map();

    /**
     * Constructeur de la classe Jeu
     */
    public Jeu() {
        map.creerCarte();
        this.gui = null;
        this.etatCommande = 8;
        this.vie = 3;
    }

    /**
     * Setter de GUI
     *
     * @param g
     */
    public void setGUI(GUI g) {
        gui = g;
        afficherMessageDeBienvenue();
    }

    /**
     * Affiche la localisation actuelle
     */
    private void afficherLocalisation() {
        gui.afficher(map.getPieceCourante().descriptionLongue());
        gui.afficher();
    }

    /**
     * Affiche le message de bienvenue
     */
    private void afficherMessageDeBienvenue() {
        gui.afficher("Bienvenue !\n");
        gui.afficher("Tapez '?' pour obtenir de l'aide\n");
        afficherLocalisation();
        gui.afficheImage(map.getPieceCourante().nomImage());
    }

    /**
     * Traite la commande lue
     *
     * @param commandeLue
     */
    public void traiterCommande(String commandeLue) {
        switch (etatCommande) {
            case 0: //Commande de base
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
                    case "SAUV":
                    case "SAUVEGARDER":
                        sauvegarder();
                        break;
                    case "CONT":
                    case "CONTINUER":
                        continuer();
                        break;
                    case "PAUSE":
                        pause();
                        break;
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
                    case "L":
                    case "LIRE":
                        lire();
                        break;
                    case "PO":
                    case "POSER":
                        poser();
                        break;
                    case "VERIF":
                    case "VERIFIER":
                        verifierPlaques();
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
            case 1: //Reponse au prisonnier
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
            case 2: //Fouiller les pots
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
            case 3: //Choisir une plaque
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
            case 4: //Poser un objet sur une plaque
                gui.afficher("> " + commandeLue + "\n");
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
            case 5: //Depause
                gui.afficher("> " + commandeLue + "\n");
                if (commandeLue.equalsIgnoreCase("DEPAUSE")) {
                    depause();
                }
                break;
            case 6: //Repondre à un garde ou donner la potion
                gui.afficher("> " + commandeLue + "\n");
                switch (commandeLue.toUpperCase()) {
                    case "1":
                    case "2":
                    case "3":
                        if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la salle des gardes")) {
                            parlerGarde(commandeLue);
                        } else {
                            donnerPotion(commandeLue);
                        }
                        break;
                    default:
                        gui.afficher("Commande invalide\n");
                        break;
                }
                break;
            case 7: //Combat contre le boss
                gui.afficher("> " + commandeLue + "\n");
                switch (commandeLue.toUpperCase()) {
                    case "COM":
                    case "COMBATTRE":
                        combattre();
                        break;
                    case "FU":
                    case "FUIR":
                        fuir();
                        break;
                    default:
                        gui.afficher("Commande invalide\n");
                        break;
                }
                break;
            case 8: //Menu
                gui.afficher("> " + commandeLue + "\n");
                switch (commandeLue.toUpperCase()) {
                    case "CONT":
                    case "CONTINUER":
                        continuer();
                        break;
                    case "LANCER":
                    case "LANC":
                        lancer();
                        break;
                    case "QUITTER":
                        terminer();
                        break;
                    default:
                        gui.afficher("Commande invalide\n");
                        break;
                }
                break;
            case 9: //Victoire
                gui.afficher("> " + commandeLue + "\n");
                if (commandeLue.equalsIgnoreCase("QUITTER")) {
                    terminer();
                } else {
                    gui.afficher("Commande invalide\n");
                }
                break;
            case 10: //Defaite
                gui.afficher("> " + commandeLue + "\n");
                switch (commandeLue.toUpperCase()) {
                    case "CONT":
                    case "CONTINUER":
                        continuer();
                        break;
                    case "QUITTER":
                        terminer();
                        break;
                    default:
                        gui.afficher("Commande invalide\n");
                        break;
                }
                break;
        }
    }

    /**
     * Affiche la liste des commandes
     */
    private void afficherAide() {
        gui.afficher("Etes-vous perdu ?\n");
        gui.afficher("Les commandes autorisées sont :\n");
        gui.afficher(Commande.toutesLesDescriptions().toString());
        gui.afficher("\n\nVous pouvez utiliser le nom ou l'abréviation\n");
    }

    /**
     * Traite le déplacement du joueur
     *
     * @param direction
     */
    private void allerEn(String direction) {
        Piece nouvelle = map.getPieceCourante().obtientSortie(direction);
        if (nouvelle == null) {
            if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la room 1") || Objects.equals(map.getPieceCourante().getNomPiece(), "dans la room 2") || Objects.equals(map.getPieceCourante().getNomPiece(), "dans la room 3") || Objects.equals(map.getPieceCourante().getNomPiece(), "dans la room 4") || Objects.equals(map.getPieceCourante().getNomPiece(), "dans la room 5")) {
                this.vie--;
                gui.afficher("\nPas de sortie " + direction + "\nVous perdez une vie");
                if (this.vie > 1) {
                    gui.afficher("\nIl vous reste " + this.vie + " vies");
                } else {
                    gui.afficher("Il vous reste " + this.vie + " vie");
                }
                gui.afficher();
                checkVie();
            } else {
                gui.afficher("\nPas de sortie " + direction);
                gui.afficher();
            }
        } else {
            map.setPieceCourante(nouvelle);
            if (Objects.equals(map.getPieceCourante().getNomPiece(), "sur l'écran de victoire")) {
                gagner();
            } else if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la room 1") || Objects.equals(map.getPieceCourante().getNomPiece(), "dans la room 2") || Objects.equals(map.getPieceCourante().getNomPiece(), "dans la room 3") || Objects.equals(map.getPieceCourante().getNomPiece(), "dans la room 4") || Objects.equals(map.getPieceCourante().getNomPiece(), "dans la room 5")) {
                gui.afficher("Vous êtes " + map.getPieceCourante().getNomPiece());
                gui.afficheImage(map.getPieceCourante().nomImage());
            } else {
                gui.afficher(map.getPieceCourante().descriptionLongue());
                gui.afficher();
                gui.afficheImage(map.getPieceCourante().nomImage());
                if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la salle du Boss")) {
                    this.etatCommande = 7;
                }
                if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la salle du boss (mort)")) {
                    this.etatCommande = 0;
                }
            }
        }
    }

    /**
     * Lance la partie
     */
    private void lancer() {
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans le menu")) {
            gui.afficher("Vous avez lancé le jeu\n");
            gui.afficher("Après avoir été fait prisonnier vous vous retrouvez au milieu d'un donjon\n");
            map.setPieceCourante(map.getMap()[0]);
            this.gui.afficheImage(this.map.getPieceCourante().nomImage());
            this.gui.afficher(this.map.getPieceCourante().descriptionLongue());
            this.etatCommande = 0;
        } else {
            gui.afficher("Le jeu est déjà lancé");
        }
    }

    /**
     * Sauvegarde la partie
     */
    private void sauvegarder() {

        if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la room 1") || Objects.equals(map.getPieceCourante().getNomPiece(), "dans la room 2") || Objects.equals(map.getPieceCourante().getNomPiece(), "dans la room 3") || Objects.equals(map.getPieceCourante().getNomPiece(), "dans la room 4") || Objects.equals(map.getPieceCourante().getNomPiece(), "dans la room 5")) {
            gui.afficher("Sauvegarde impossible dans le labyrinthe");
        } else {
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
    }

    /**
     * Reprend la partie à la dernière sauvegarde
     */
    private void continuer() {
        Jeu object1;
        String filename = "save.ser";
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            object1 = (Jeu) in.readObject();
            in.close();
            file.close();
            gui.afficher("Reprise de la sauvegarde\n");
            this.map = object1.map;
            this.gui.afficheImage(this.map.getPieceCourante().nomImage());
            this.gui.afficher(this.map.getPieceCourante().descriptionLongue());
            this.inventaire = object1.inventaire;
            this.etatCommande = object1.etatCommande;
            this.vie = object1.vie;
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

    /**
     * Met le jeu en pause
     */
    private void pause() {
        this.etatCommande = 5;
        gui.afficher("Le jeu est en pause. Entrez depause pour continuer\n");
        this.etatCommande = 5;
    }

    /**
     * Retire la pause et reprend le jeu
     */
    private void depause() {
        this.etatCommande = 0;
        gui.afficher("Le jeu reprend\n");
    }

    /**
     * Declenche le dialogue du premier PNJ de la pièce courante
     */
    private void parler() {
        if (!map.getPieceCourante().getListePNJ().isEmpty()) {
            if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la salle des gardes")) {
                parlerGarde();
            } else {
                for (PNJ pnj : map.getPieceCourante().getListePNJ()) {
                    gui.afficher(pnj.dialogue());
                    if (pnj instanceof Prisonnier && pnj.getEtat() == 0) {
                        gui.afficher("JAU pour la clé Jaune\nBLE pour la clé Bleue");
                        this.etatCommande = 1;
                    }
                    if (pnj instanceof Princesse) {
                        gui.afficher("\nQuelle potion voulez vous donner à la Princesse ?\nTapez 1 2 ou 3\n");
                        this.etatCommande = 6;
                    }
                }
            }
        } else {
            gui.afficher("Il n'y a personne avec qui parler");
        }
    }

    /**
     * Declenche le dialogue avec le premier garde de la pièce courante
     */
    private void parlerGarde() {
        Garde garde = (Garde) map.getPieceCourante().getListePNJ().get(0);
        gui.afficher(garde.dialogue(map.getLettreAleat()));
        this.etatCommande = 6;
    }

    /**
     * Reponse à la question du garde
     *
     * @param commandeLue
     */
    private void parlerGarde(String commandeLue) {
        Garde garde = (Garde) map.getPieceCourante().getListePNJ().get(0);
        if (Objects.equals(commandeLue, garde.getReponse(map.getLettreAleat()))) {
            gui.afficher("\nBonne réponse\n");
            map.getPieceCourante().retirerPNJ(garde);
            gui.afficher("\nVous avez réussi à passer le garde\nLe garde " + garde.getNom() + " est parti\n");
            if (!map.getPieceCourante().getListePNJ().isEmpty()) {
                parler();
            } else {
                gui.afficher("\nVous avez répondu corectement à toutes les questions\n");
                gui.afficher("\nVous pouvez monter les escaliers\n");
                map.getPieceCourante().ajouteSortie(Sortie.MONTER, map.getMap()[17]);
                map.getPieceCourante().setDescription("Il n'y a plus de garde");
//                map.getPieceCourante().setNomImage("SalleDesGardesSansGarde.jpg");
//                gui.afficheImage(map.getPieceCourante().nomImage());
                this.etatCommande = 0;
            }
        } else {
            gui.afficher("\nMauvaise réponse\n");
            this.vie--;
            gui.afficher("Vous perdez une vie\n");
            if (this.vie > 1) {
                gui.afficher("Il vous reste " + this.vie + " vies\n");
            } else {
                gui.afficher("Il vous reste " + this.vie + " vie\n");
            }
            if (!checkVie()) {
                parler();
            }
        }
    }


    /**
     * Donne la cle choisie par le joueur
     *
     * @param commandeLue
     */
    private void donnerCle(String commandeLue) {
        if (commandeLue.equals("JAU") || commandeLue.equals("JAUNE")) {
            inventaire.ajouterInventaire(new Item("Clé Jaune", "Sert à dévérouiller le coffre jaune"));
            for (PNJ pnj : map.getPieceCourante().getListePNJ()) {
                if (pnj.getEtat() == 0) {
                    gui.afficher(((Prisonnier) pnj).dialogueJaune());
                    pnj.setEtat(1);
                }
            }
            gui.afficher("\nLa clé Jaune a été ajouté à l'inventaire");
        } else {
            inventaire.ajouterInventaire(new Item("Clé Bleue", "Sert à dévérouiller le coffre bleu"));
            for (PNJ pnj : map.getPieceCourante().getListePNJ()) {
                if (pnj.getEtat() == 0) {
                    gui.afficher(((Prisonnier) pnj).dialogueBleu());
                    pnj.setEtat(1);
                }
            }
            gui.afficher("\nLa clé Bleue a été ajouté à l'inventaire");
        }
        this.etatCommande = 0;
    }

    /**
     * Fouille la piece
     */
    private void fouiller() {
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la salle des pots")) {
            gui.afficher("\nQuel pot voulez fouiller : F1 F2 F3 F4 F5 F6");
            this.etatCommande = 2;
        } else if (map.getPieceCourante().estVideDObjet()) {
            gui.afficher("\nIl n'y a pas d'item à récupérer dans cette pièce\n");
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
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans le couloir") && map.getPieceCourante().estVideDObjet()) {
            map.getPieceCourante().setNomImage("CouloirFouille.jpg");
            gui.afficheImage(map.getPieceCourante().nomImage());
        }
    }

    /**
     * Affiche l'inventaire
     */
    private void consulterInventaire() {
        gui.afficher(inventaire.afficherInventaire());
    }

    /**
     * Ouvre un coffre
     */
    private void ouvrirCoffre() {
        int etat = 0;
        switch (map.getPieceCourante().getNomPiece()) {
            case "dans la salle des coffres" -> {
                if (inventaire.getListeInventaire().isEmpty()) {
                    gui.afficher("Il vous faudrait une clé afin de pouvoir ouvrir un de ces coffres \n");
                } else {
                    Item tempj = inventaire.getItemByName("Clé Jaune");
                    if (tempj != null) {
                        inventaire.retirerInventaire(tempj);
                        inventaire.ajouterInventaire(new Item("Tête de pioche", "combinée avec un baton je peux peut être en faire une pioche"));
                        gui.afficher("Le coffre jaune a été ouvert\n");
                        gui.afficher("La tête de pioche a été ajouté à l'inventaire\n");
                        map.getPieceCourante().setNomPiece("dans la salle des coffres (ouverts)");
                        map.getPieceCourante().setDescription("Il n'y a plus rien à faire ici");
                        map.getPieceCourante().setNomImage("SalleDesCoffresSansJaune.jpg");
                        gui.afficheImage(map.getPieceCourante().nomImage());
                        etat = 1;
                    }
                    Item tempb = inventaire.getItemByName("Clé Bleue");
                    if (tempb != null) {
                        inventaire.retirerInventaire(tempb);
                        inventaire.ajouterInventaire(new Item("Charbon", "combiné avec un baton je peux peut être en faire une torche"));
                        gui.afficher("Le coffre bleu a été ouvert\n");
                        gui.afficher("Du charbon a été ajouté à l'inventaire\n");
                        map.getPieceCourante().setNomPiece("dans la salle des coffres (ouverts)");
                        map.getPieceCourante().setDescription("Il n'y a plus rien à faire ici");
                        map.getPieceCourante().setNomImage("SalleDesCoffresSansBleu.jpg");
                        gui.afficheImage(map.getPieceCourante().nomImage());
                        etat = 1;
                    }
                    if (etat == 0) {
                        gui.afficher("Il vous faudrait une clé afin de pouvoir ouvrir un de ces coffres \n");
                    }
                }
            }
            case "dans la salle des coffres (ouverts)" -> gui.afficher("Vous avez déjà ouvert un coffre");
            case "dans la cuisine, derrière le comptoir" -> {
                inventaire.ajouterInventaire(new Item("Clé de la chambre", "Elle doit pouvoir ouvrir la grille en haut à droite"));
                gui.afficher("La clé de la chambre a été ajoutée à l'inventaire\n");
                map.getPieceCourante().setNomPiece("dans la cuisine, derrière le comptoir, le coffre est vide");
                map.getPieceCourante().setDescription("Il n'y a plus rien à faire ici");
            }
            case "dans la cuisine, derrière le comptoir, le coffre est vide" ->
                    gui.afficher("Vous avez déjà ouvert le coffre");
            default -> gui.afficher("Il n'y a rien à ouvrir ici");
        }
    }

    /**
     * Crafter un objet
     */
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

    /**
     * Eclaire la piece
     */
    private void eclairer() {
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "à l'escalier Nord")) {
            if (inventaire.getListeInventaire().isEmpty()) {
                gui.afficher("Vous ne possédez pas d'outil pour réaliser cette action\n");
            } else {
                Item torchePresent = inventaire.getItemByName("Torche");
                if (torchePresent != null) {
                    map.getPieceCourante().setNomImage("EscalierEclaire.jpg");
                    gui.afficheImage(map.getPieceCourante().nomImage());
                    map.getPieceCourante().ajouteSortie(Sortie.MONTER, map.getMap()[5]);
                    gui.afficher("Vous posez la torche sur le porte-torche\nVous avez débloqué la sortie monter\n");
                    inventaire.retirerInventaire(torchePresent);
                } else gui.afficher("Vous ne possédez pas la ressource pour réaliser cette action\n");
            }
        } else {
            gui.afficher("Vous ne pouvez pas utiliser cette commande ici");
        }
    }

    /**
     * Casse les rochers de la piece
     */
    private void casser() {
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la prison")) {
            if (inventaire.getListeInventaire().isEmpty()) {
                gui.afficher("Vous ne possédez pas d'outil pour réaliser cette action\n");
            } else {
                Item piochePresent = inventaire.getItemByName("Pioche");
                if (piochePresent != null) {
                    map.getPieceCourante().setNomImage("PrisonCassee.jpg");
                    gui.afficheImage(map.getPieceCourante().nomImage());
                    map.getPieceCourante().ajouteSortie(Sortie.DESCENDRE, map.getMap()[19]);
                    gui.afficher("Vous avez débloqué la sortie descendre\n");
                } else gui.afficher("Vous ne possédez pas d'outil pour réaliser cette action\n");
            }
        } else {
            gui.afficher("Vous ne pouvez pas utiliser cette commande ici\n");
        }
    }

    /**
     * Lit les indications de la piece
     */
    private void lire() {
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans les catacombes")) {
            gui.afficher("Vous distinguez des inscriptions au mur...\n");
            gui.afficher("Vous vous approchez et lisez : \n");
            gui.afficher(map.getInscriptionMur());
        } else if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la bibliothèque")) {
            gui.afficher("Vous lisez le parchemin\nLa réponse commence par : " + map.getLettreAleat() + "\nLa potion : " + map.getNumAleat());
            gui.afficher("\nQu'est ce que cela peut bien signifier ?\n");

        } else gui.afficher("Vous ne pouvez pas utiliser cette commande ici");
    }

    /**
     * Fouille le pot du numéro choisi
     *
     * @param num
     */
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

    /**
     * Pose un item
     */
    private void poser() {
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la grotte")) {
            gui.afficher("Sur quel plaque voulez vous poser un item ? Tapez 1 2 3\n");
            this.etatCommande = 3;
        } else if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la salle des pots")) {
            Item echelle = inventaire.getItemByName("Echelle");
            if (echelle != null) {
                inventaire.retirerInventaire(echelle);
                map.getPieceCourante().ajouteSortie(Sortie.DESCENDRE, map.getMap()[23]);
                map.getPieceCourante().setNomImage("SalleDesPotsEchelle.jpg");
                gui.afficheImage(map.getPieceCourante().nomImage());
                gui.afficher("\nVous avez posé l'échelle et pouvez maintenant descendre");
            } else {
                gui.afficher("\nVous n'avez pas d'objet vous permettant de descendre");
            }
        } else {
            gui.afficher("Vous ne pouvez pas poser d'objet dans cette pièce\n");
        }
    }

    /**
     * Choisie la plaque sur laquelle poser un item
     *
     * @param num
     * @return
     */
    private boolean poserPlaque(int num) {
        return map.getPieceCourante().getPlaqueByNum(num).getEtat() == 1;
    }

    /**
     * Inspecte la piece pour reveler un passage secret
     *
     * @param num
     * @param commandeLue
     */
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

    /**
     * Inspecte la piece pour reveler un passage secret
     */
    private void verifierPlaques() {
        Plaque plaque1 = map.getPieceCourante().getPlaqueByNum(1);
        Plaque plaque2 = map.getPieceCourante().getPlaqueByNum(2);
        Plaque plaque3 = map.getPieceCourante().getPlaqueByNum(3);
        if (plaque1.getEtat() == 1 && plaque2.getEtat() == 1 && plaque3.getEtat() == 1) {
            if (plaque1.getItem().getNom().equals("Peluche") && plaque2.getItem().getNom().equals("Pioche") && plaque3.getItem().getNom().equals("Lingot")) {
                map.getPieceCourante().ajouteSortie(Sortie.EST, map.getMap()[25]);
                gui.afficher("Vous avez débloqué la sortie descendre\n");
                map.getPieceCourante().setNomImage("GrotteOuverte.jpg");
                gui.afficheImage(map.getPieceCourante().nomImage());
                map.getPieceCourante().ajouteSortie(Sortie.DESCENDRE, map.getMap()[25]);
            } else {
                gui.afficher("Les objets posés ne sont pas dans le bon ordre\n");
                plaque1.setEtat(0);
                plaque2.setEtat(0);
                plaque3.setEtat(0);
                inventaire.ajouterInventaire(plaque1.getItem());
                inventaire.ajouterInventaire(plaque2.getItem());
                inventaire.ajouterInventaire(plaque3.getItem());
                gui.afficher("Les objets ont été remis dans votre inventaire\n");

            }
        } else {
            gui.afficher("Il vous manque des objets sur les plaques\n");
        }
    }

    /**
     * Inspecte la piece pour reveler un passage secret
     */
    private void inspecter() {
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la salle à manger")) {
            map.getPieceCourante().ajouteSortie(Sortie.DESCENDRE, map.getMap()[26]);
            map.getMap()[26].ajouteSortie(Sortie.DESCENDRE, map.getMap()[15]);
            map.getPieceCourante().setNomPiece("dans la salle à manger débloquée");
            map.getPieceCourante().setDescription("");
            gui.afficher("Un chemin se cachait derrière la cheminée\nVous pouvez descendre\n");
        } else if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la salle à manger débloquée")) {
            gui.afficher("Vous avez déjà débloqué cette pièce\n");
        } else {
            gui.afficher("Vous ne pouvez pas utiliser cette commande ici\n");
        }
    }

    /**
     * Deverouille une grille fermée
     */
    private void deverrouiller() {
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "devant la chambre de la Princesse")) {
            if (inventaire.getListeInventaire().isEmpty()) {
                gui.afficher("Vous ne possédez pas la clé pour réaliser cette action\n");
            } else {
                Item cleRDC = inventaire.getItemByName("Clé de la chambre");
                if (cleRDC != null) {
                    map.getPieceCourante().ajouteSortie(Sortie.MONTER, map.getMap()[18]);
                    gui.afficher("Vous avez débloqué la sortie monter\n");
                    map.getPieceCourante().setNomImage("EntreeChambrePrincesseOuverte.jpg");
                    gui.afficheImage(map.getPieceCourante().nomImage());
                    inventaire.retirerInventaire(cleRDC);
                } else gui.afficher("Il vous faudrait une clé pour ouvrir cette grille\n");
            }
        } else if (Objects.equals(map.getPieceCourante().getNomPiece(), "à l'entrée du château")) {
            if (inventaire.getListeInventaire().isEmpty()) {
                gui.afficher("Vous ne possédez pas la clé pour réaliser cette action\n");
            } else {
                Item cleChateau = inventaire.getItemByName("Clé du château");
                if (cleChateau != null) {
                    map.getPieceCourante().ajouteSortie(Sortie.DESCENDRE, map.getMap()[25]);
                    gui.afficher("Vous avez débloqué la sortie descendre\n");
                    map.getPieceCourante().setNomImage("EntreeChateauOuverte.jpg");
                    gui.afficheImage(map.getPieceCourante().nomImage());
                    inventaire.retirerInventaire(cleChateau);
                } else gui.afficher("Il vous faudrait une clé pour ouvrir cette grille\n");
            }
        } else gui.afficher("Vous ne pouvez pas utiliser cette commande ici\n");
    }


    /**
     * S'asseoir sur le trone
     */
    private void sAsseoir() {
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la salle du trône")) {
            gui.afficher("Vous vous êtes assis sur le trône et des gardes vous ont repéré et renvoyé au donjon\n");
            perdre();
        } else gui.afficher("Vous ne pouvez pas utiliser cette commande ici\n");
    }

    /**
     * Fuir le combat du boss
     */
    private void fuir() {
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la salle du Boss")) {
            Item epeePresent = inventaire.getItemByName("Epée");
            if (epeePresent != null) {
                gui.afficher("Vous décidez de fuir...\nVous prenez vos jambes à votre cou et courrez le plus vite possible.\nDans votre élan, vous faites tomber votre épée et trébuchez dessus !\nLe monstre vous attrape et vous dévore\n");
                perdre();
            } else {
                gui.afficher("Vous avez fui et êtes de retour dans la salle des gardes\n");
                allerEn("DESCENDRE");
                this.etatCommande = 0;
            }
        } else {
            gui.afficher("Vous ne pouvez pas utiliser cette commande ici\n");
        }
    }

    /**
     * Combattre le boss
     */
    private void combattre() {
        if (Objects.equals(map.getPieceCourante().getNomPiece(), "dans la salle du Boss")) {
            Item epeePresent = inventaire.getItemByName("Epée");
            if (epeePresent != null) {
                gui.afficher("Vous avez décidé de combattre le boss\n");
                gui.afficher("Vous lui assénez un coup d'épee, il semble affaibli !\n");
                gui.afficher("Le monstre contre-attaque, il vous blesse à la jambe\n");
                gui.afficher("En vous relevant vous envoyez un autre coup d'épée, le Boss tombe au sol\n");
                gui.afficher("Vous foncez vers lui et le poignardez de toutes vos forces. Vous avez réussi !\n");
                gui.afficher("Vous appercevez une clé au fond de la pièce\n");
                map.getPieceCourante().setDescription("Le Boss était le gardien de la clé\n");
                map.getPieceCourante().setNomPiece("dans la salle du boss (mort)");
                map.getPieceCourante().setNomImage("SalleDuBossMort.jpg");
                gui.afficheImage(map.getPieceCourante().nomImage());
                this.etatCommande = 0;
            } else {
                gui.afficher("Vous tentez de combattre le Boss à sans armes, il vous tue en un seul coup\n");
                perdre();
            }
        } else {
            gui.afficher("Vous ne pouvez pas utiliser cette commande ici\n");
        }
    }

    /**
     * Donne la potion choisie à la Princesse
     *
     * @param commandeLue
     */
    private void donnerPotion(String commandeLue) {
        if (commandeLue.equals(String.valueOf(map.getNumAleat()))) {
            gui.afficher("\nVous avez choisi la bonne potion, la Princesse est désormais libre\nVous vous enfuyez du château par son balcon\n");
            map.setPieceCourante(map.getMap()[25]);
            gagner();
        } else {
            gui.afficher("\nVous avez choisi la mauvaise potion, la Princesse est morte\nVous buvez les deux autres potions et mourrez avec elle\n");
            perdre();
        }
    }

    /**
     * Verifie le nombre de vie du joueur
     *
     * @return
     */
    private boolean checkVie() {
        if (this.vie == 0) {
            gui.afficher("Vous avez perdu toute vos vies\n");
            perdre();
            return true;
        }
        return false;
    }

    /**
     * Gagner le partie
     */
    private void gagner() {
        gui.afficher("\nVous avez gagné la partie\nFéliciations !!!\n");
        gui.afficher(map.getPieceCourante().toString());
        gui.afficheImage(map.getPieceCourante().nomImage());
        this.etatCommande = 9;
    }

    /**
     * Perdre la partie
     */
    private void perdre() {
        gui.afficher("\nVous avez perdu la partie - - - GAME OVER\n");
        map.setPieceCourante(map.getMap()[28]);
        gui.afficher(map.getPieceCourante().descriptionLongue());
        gui.afficheImage(map.getPieceCourante().nomImage());
        this.etatCommande = 10;
    }

    /**
     * Quitter le jeu
     */
    private void terminer() {
        gui.afficher("Au revoir...\n");
        gui.afficher("Fermeture du jeu dans 3 secondes");

        int delai = 3000; //millisecondes
        ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                gui.fermer();
            }
        };
        new Timer(delai, action).start();
    }
} //fin de la classe