package jeu;

import jeu.objets.Item;
import jeu.objets.Plaque;
import jeu.objets.Pot;
import jeu.personnages.*;

import java.io.Serializable;
import java.util.Random;

/**
 * Cette classe vise à créer la map du jeu
 */
public class Map implements Serializable {

    private final Piece[] map;

    private Piece pieceCourante;

    private String lettreAleat;

    private int numAleat;

    private String inscriptionMur;


    public Map() {
        this.map = new Piece[31];
    }

    /**
     * Creer la carte
     */
    public void creerCarte() {

        map[27] = new Piece("dans le menu", "LANCER pour commencer une nouvelle partie\nCONTINUER pour reprendre une partie sauvegardée\nQUITTER pour quitter le jeu", "Menu.jpg");
        map[25] = new Piece("sur l'écran de victoire", "Merci d'avoir joué au jeu\nQUITTER pour fermer le jeu", "Victoire.jpg");
        map[28] = new Piece("sur l'écran de défaite", "Dommage :(\nCONTINUER pour relancer le jeu à votre dernière sauvegarde\nQUITTER pour fermer le jeu", "Defaite.jpg");

        //étage -1
        map[0] = new Piece("au centre du donjon", "C'est l'endroit où vous vous êtes réveillé, sans souvenirs", "Donjon.jpg");
        map[1] = new Piece("dans la prison", "Des rochers vous bloquent ce qui semble être un chemin, peut être que le prisonnier" + "\n" + "sait ce qu'il se cache derrière", "Prison.jpg");
        map[2] = new Piece("à l'escalier Nord", "Il fait trop noir pour monter ces escaliers", "EscalierNord.jpg");
        map[3] = new Piece("dans le couloir", "Peut-être que quelque-chose se cache dans ces vieux pots...\nIl y a une table de craft dans la pièce", "Couloir.jpg");
        map[4] = new Piece("dans la salle des coffres", "Vous voyez un coffre jaune et un coffre bleu vérouillés.\nUne clé doit sûrement se cacher quelque-part...", "SalleDesCoffres.jpg");

        // RDC
        map[5] = new Piece("à l'escalier de gauche", "\nLa grille de l'escalier est fermée\nIl n'est plus possible de descendre\nVous apercevez l'entrée du château", "EscalierGauche.jpg");
        map[6] = new Piece("à l'entrée du château", "Comment faire pour s'échapper ?", "EntreeChateau.jpg");
        map[7] = new Piece("dans la bibliothèque", "Peut-être que des informations utiles sont gardées ici", "Bibliotheque.jpg");
        map[8] = new Piece("dans la salle du trône", "On aperçoit le trône du Roi, cela serait osé de s'asseoir dessus en son absence...", "SalleDuTrone.jpg");
        map[9] = new Piece("à l'escalier de droite", "\nLa grille de l'escalier est fermée\nIl n'est plus possible de descendre\nVous apercevez l'entrée du château", "EscalierDroite.jpg");
        map[10] = new Piece("dans l'armurerie", "Il y a des armes partout ! Et personne ne les surveille...", "Armurerie.jpg");
        map[11] = new Piece("dans la cuisine", "Il semblerait que quelque-chose se cache derrière ce comptoir...", "Cuisine.jpg");
        map[26] = new Piece("dans la cuisine, derrière le comptoir", "Vous êtes face à un coffre", "Comptoir.jpg");
        map[12] = new Piece("devant la chambre de la Princesse", "Cette entrée est bloquée", "EntreeChambrePrincesse.jpg");
        map[13] = new Piece("dans la galerie", "", "Galerie.jpg");
        map[14] = new Piece("devant la tour de gauche", "Cette entrée est bloquée", "EntreeTourGauche.jpg");
        map[15] = new Piece("dans la salle à manger", "La cheminée est étrangement propre", "SalleAManger.jpg");
        map[24] = new Piece("dans la cuisine, derrière le comptoir", "Vous êtes face à un coffre", "Comptoir.jpg");

        // tour 1G
        map[16] = new Piece("dans la salle des gardes", "Les gardes ont l'air féroces, on dirait qu'ils bloquent l'accès Nord", "SalleDesGardes.jpg");
        map[17] = new Piece("dans la salle du Boss", "Le monstre du château se dresse devant-vous" + "\n" + "Qu'allez-vous faire... Fuir ou combattre ?", "SalleDuBoss.jpg");

        // tour 1D
        map[18] = new Piece("dans la chambre de la Princesse", "Ce qui semblait être un vieux grenier à l'air de faire office de chambre à coucher", "ChambrePrincesse.jpg");

        // étage -2
        map[19] = new Piece("dans les catacombes", "Le mur est en piteux état, on dirait que quelqu'un a écrit dessus", "Catacombes.jpg");
        Labyrinthe labyrinthe = new Labyrinthe("dans le labyrinthe", "Il fait tout noir ! Et le chemin est très étroit" + "\n" + "Il ne faudrait pas tomber dans le vide", "Labyrinthe.jpg", this);


        map[21] = new Piece("à l'escalier", "Deux directions semblent se distinguer...", "EscalierCatacombes.jpg");
        map[22] = new Piece("dans la salle des pots", "On doit forcément trouver des ressources parmi tous ces pots...\nIl y a une table de craft dans la pièce", "SalleDesPots.jpg");

        // étage -3
        map[23] = new Piece("dans la grotte", "Il y a trois plaques de pression\nSi on les regarde de gauche à droite, elles sont de plus en plus épaisses\nEssayez-donc de poser des objets de plus en plus lourds dans ce même ordre\nTapez VERIF quand vous pensez avoir fini", "Grotte.jpg");


        //labyrinthe
        labyrinthe.genererLabyrinthe();
        map[20] = labyrinthe;


        //tirage des variables aléatoires pour la biblio
        aleat();

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
        map[16].ajouteSortie(Sortie.DESCENDRE, map[14]);

        map[17].ajouteSortie(Sortie.DESCENDRE, map[16]);

        //tour 1D
        map[18].ajouteSortie(Sortie.DESCENDRE, map[12]);

        // étage -1
        map[19].ajouteSortie(Sortie.MONTER, map[1]);
        map[19].ajouteSortie(Sortie.NORD, labyrinthe.getPiecesLab()[0]);

        map[21].ajouteSortie(Sortie.MONTER, map[9]);
        map[21].ajouteSortie(Sortie.DESCENDRE, labyrinthe.getPiecesLab()[4]);
        map[21].ajouteSortie(Sortie.SUD, map[22]);

        map[22].ajouteSortie(Sortie.NORD, map[21]);

        // étage -2
        map[23].ajouteSortie(Sortie.MONTER, map[22]);

        //ajout des personnages
        Prisonnier prisonnier = new Prisonnier("Marc");
        Cuisinier cuisinier = new Cuisinier("Marcel");
        Servante servante = new Servante("Marie");
        Garde garde1 = new Garde("Armando", "Quel est le deuxième plus grand Océan du monde ?\n1) Atlantique\n2) Pacifique\n3) Indien\n", "Dans le jeu Pokémon Version ''Noir et Blanc'', comment se nomme le Pokémon à l'apparence d'un écureuil ?\n1) Ratentif\n2) Ecurieux\n3) Squirwel\n", "Quel célèbre championnat sportif concerne le Football Américain ?\n1) Ligue 1\n2) Top 14\n3) Superbowl\n", "1", "1", "3");
        Garde garde2 = new Garde("Rachid", "Parmi les catégories suivantes, laquelle a le plus petit taux d'espèces venimeuses/vénéneuses ?\n1) Serpents\n2) Araignées\n3) Champignons", "Lequel de ces 3 sports se pratique dans l'eau ?\n1) Rafting\n2) Roundnet\n3) Ringuette\n", "Quelle actrice joue la Black Widow dans le film éponyme sorti en 2021 ?\n1) Jennifer Lawrence\n2) Scarlett Johanson\n3) Julian Moore\n", "2", "1", "2");
        Garde garde3 = new Garde("Noa", "Quelle plante appelle-t-on ''l'arbre à fraises'' ?\n1) Prunier \n2) Néflier \n3) Arbousier", "Parmi les 6 personnages principaux de la série télévisée Friends, on retrouve un des trois personnages suivants :\n1) Stanley\n2) Ross\n3) Barney\n", "Quel personnage de jeu vidéo représente un hérisson ?\n1) Donkey Kong\n2) Yoshi\n3) Sonic\n", "3", "2", "3");
        Princesse princesse = new Princesse("Marion");

        map[1].ajouterPNJ(prisonnier);
        map[6].ajouterPNJ(cuisinier);
        map[14].ajouterPNJ(servante);
        map[16].ajouterPNJ(garde1);
        map[16].ajouterPNJ(garde2);
        map[16].ajouterPNJ(garde3);
        map[18].ajouterPNJ(princesse);

        //ajout des objets
        Item baton = new Item("Baton", "sert à construire un objet");
        Item epee = new Item("Epée", "sert à vous défendre");
        Item lingot = new Item("Lingot", "est très lourd");
        Item peluche = new Item("Peluche", "est très léger");
        Item cleDuChateau = new Item("Clé du château", "sert à ouvrir la porte du château");

        Plaque plaque1 = new Plaque(1, 0);
        Plaque plaque2 = new Plaque(2, 0);
        Plaque plaque3 = new Plaque(3, 0);

        map[3].ajouterItem(baton);
        map[10].ajouterItem(epee);
        map[17].ajouterItem(cleDuChateau);

        initialiserPot();

        map[23].ajouterItem(lingot);
        map[23].ajouterItem(peluche);

        map[23].ajouterItem(plaque1);
        map[23].ajouterItem(plaque2);
        map[23].ajouterItem(plaque3);

        this.pieceCourante = map[27];
    }

    /**
     * Initialisation des pots de manière aléatoire
     */
    public void initialiserPot() {
        int[] full = new int[6];

        for (int i = 0; i < 6; i++) {
            full[i] = 0;
        }
        Random rand = new Random();
        int a, b, c;
        do {
            a = rand.nextInt(6);
            b = rand.nextInt(6);
            c = rand.nextInt(6);
        } while (a == b || a == c || b == c);

        full[a] = 1;
        full[b] = 1;
        full[c] = 1;

        for (int i = 0; i < 6; i++) {
            map[22].ajouterItem(new Pot(i + 1, full[i]));
        }
    }

    /**
     * Tirer de manière aléatoire une lettre et un chiffre
     */
    public void aleat() {
        String alphabet = "asr";
        Random rand = new Random();
        int randomIndex = rand.nextInt(alphabet.length());
        this.lettreAleat = String.valueOf(alphabet.charAt(randomIndex));

        this.numAleat = rand.nextInt(3) + 1;
    }

    /**
     * Getter de map
     *
     * @return
     */
    public Piece[] getMap() {
        return map;
    }

    /**
     * Getter de pieceCourante
     *
     * @return
     */
    public Piece getPieceCourante() {
        return pieceCourante;
    }

    /**
     * Setter de pieceCourante
     *
     * @param pieceCourante
     */
    public void setPieceCourante(Piece pieceCourante) {
        this.pieceCourante = pieceCourante;
    }

    /**
     * Getter de inscriptionMur
     *
     * @return
     */
    public String getInscriptionMur() {
        return inscriptionMur;
    }

    /**
     * Setter de inscriptionMur
     *
     * @param inscriptionMur
     */
    public void setInscriptionMur(String inscriptionMur) {
        this.inscriptionMur = inscriptionMur;
    }

    /**
     * Getter de lettreAleat
     *
     * @return
     */
    public String getLettreAleat() {
        return lettreAleat;
    }

    /**
     * Getter de numAleat
     *
     * @return
     */
    public int getNumAleat() {
        return numAleat;
    }
}
