package jeu;

import jeu.objets.Item;
import jeu.objets.Plaque;
import jeu.objets.Pot;
import jeu.personnages.Cuisinier;
import jeu.personnages.Garde;
import jeu.personnages.Prisonnier;
import jeu.personnages.Servante;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Map implements Serializable {

    private Piece[] map;

    private Piece pieceCourante;

    private String lettreAleat;

    private int numAleat;

    private String parchemin;


    public Map() {
        this.map = new Piece[31];
    }

    public void creerCarte() {

        map[27] = new Piece("dans le menu", "LANCER pour commencer une nouvelle partie\nCONTINUER pour reprendre une partie sauvegardée\nQUITTER pour quitter le jeu", "Menu.jpg");
        map[28] = new Piece("sur l'écran de victoire","Merci d'avoir joué au jeu\nQUITTER pour fermer le jeu","Victoire.jpg");
        map[29] = new Piece("sur l'écran de défaite","Dommage :(\nCONTINUER pour relancer le jeu à votre dernière sauvegarde\nQUITTER pour fermer le jeu","Defaite.jpg");

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
        map[26] = new Piece("dans la cuisine, derrière le comptoir", "Vous êtes face à un coffre", "Comptoir.jpg");
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
        Labyrinthe labyrinthe = new Labyrinthe("dans le labyrinthe", "Il fait tout noir ! Et le chemin est très étroit" + "\n" + "Il ne faudrait pas tomber dans le vide", "Labyrinthe.jpg", this);


        map[21] = new Piece("à l'escalier", "Deux directions semblent se distinguer... Nord ou Sud ?", "EscalierCatacombes.jpg");
        map[22] = new Piece("dans la salle des pots", "On doit forcément trouver des ressources parmi tous ces pots...", "SalleDesPots.jpg");

        // étage -3
        map[23] = new Piece("dans la grotte", "Il y a trois plaques de pression" + "\n" + "Si on les regarde de gauche à droite, elles sont de plus en plus épaisses" + "\n" + "Essayez-donc de poser des objets de plus en plus lourds dans ce même ordre\nTapez VERIF quand vous pensez avoir fini\n", "Grotte.jpg");

        //sortie gagnante

        map[25] = new Piece("dans la foret", "Vous avez réussi à vous échapper du château, félicitations !", "Foret.jpg");

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
        map[19].ajouteSortie(Sortie.NORD, labyrinthe.getPiecesLab()[0]);

        map[21].ajouteSortie(Sortie.MONTER, map[9]);
        map[21].ajouteSortie(Sortie.DESCENDRE, labyrinthe.getPiecesLab()[4]);
        map[21].ajouteSortie(Sortie.SUD, map[22]);

        map[22].ajouteSortie(Sortie.NORD, map[21]);

        // étage -2

        map[23].ajouteSortie(Sortie.MONTER, map[22]);

        //ajout des personnages

        Prisonnier prisonnier = new Prisonnier("Jacques");
        Cuisinier cuisinier = new Cuisinier("Jacquelin");
        Servante servante = new Servante("Jacqueline");
        Garde garde1 = new Garde("Rachid", "Quel est le deuxième plus grand Océan du monde ?\n1) Atlantique\n2) Pacifique\n3) Indien\n", "Dans le jeu Pokémon Version ''Noir et Blanc'', comment se nomme le Pokémon à l'apparence d'un écureuil ?\n1) Ratentif\n2) Ecurieux\n3) Squirwel\n", "Quel célèbre championnat sportif concerne le Football Américain ?\n1) Superbowl\n2) Top 14\n3) Ligue 1\n");
        Garde garde2 = new Garde("Armando", "Parmi les catégories suivantes, laquelle a le plus petit taux d'espèces venimeuses/vénéneuses ?\n1) Araignées\n2) Serpents\n3) Champignons", "Lequel de ces 3 sports se pratique dans l'eau ?\n1) Rafting\n2) Roundnet\n3) Ringuette", "Quelle actrice joue la Black Widow dans le film éponyme sorti en 2021 ?\n 1) Scarlett Johanson\n2) Jennifer Lawrence\n3)Julian Moore\n");
        Garde garde3 = new Garde("Noa", "Quelle plante appelle-t-on ''l'arbre à fraises'' ?\n1)Arbousier,  2)Néflier ou 3)Prunier", "Parmi les 6 personnages principaux de la série télévisée Friends, on retrouve un des trois personnages suivants : 1)Ross, 2)Stanley ou 3)Barney", "Quel personnage de jeu vidéo représente un hérisson ? 1)Sonic, 2)Yoshi ou 3)Donkey Kong");
        /*

        Princesse princesse = new Princesse();
        Boss boss = new Boss();
        */

        map[1].ajouterPNJ(prisonnier);
        map[6].ajouterPNJ(cuisinier);
        map[14].ajouterPNJ(servante);
        map[16].ajouterPNJ(garde1);
        map[16].ajouterPNJ(garde2);
        map[16].ajouterPNJ(garde3);

        //ajout des objets

        Item baton = new Item("Baton", "sert à construire un objet");
        Item epee = new Item("Epée", "sert à vous défendre");
        Item lingot = new Item("Lingot", "est très lourd");
        Item peluche = new Item("Peluche", "est très léger");

        Plaque plaque1 = new Plaque(1, 0);
        Plaque plaque2 = new Plaque(2, 0);
        Plaque plaque3 = new Plaque(3, 0);

        initialiserPot();

        map[23].ajouterItem(lingot);
        map[23].ajouterItem(peluche);

        map[23].ajouterItem(plaque1);
        map[23].ajouterItem(plaque2);
        map[23].ajouterItem(plaque3);

        map[3].ajouterItem(baton);
        map[10].ajouterItem(epee);

        this.pieceCourante = map[27];
    }


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

    public void aleat() {
        String alphabet = "asr";
        Random rand = new Random();
        int randomIndex = rand.nextInt(alphabet.length());
        this.lettreAleat = String.valueOf(alphabet.charAt(randomIndex));

        this.numAleat = rand.nextInt(3) + 1;
    }

    public Piece[] getMap() {
        return map;
    }

    public void setMap(Piece[] map) {
        this.map = map;
    }

    public Piece getPieceCourante() {
        return pieceCourante;
    }

    public void setPieceCourante(Piece pieceCourante) {
        this.pieceCourante = pieceCourante;
    }

    public String getParchemin() {
        return parchemin;
    }

    public void setParchemin(String parchemin) {
        this.parchemin = parchemin;
    }

    public String getLettreAleat() {
        return lettreAleat;
    }

    public int getNumAleat() {
        return numAleat;
    }
}
