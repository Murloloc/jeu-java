package jeu;

import jeu.objets.Item;
import jeu.objets.Plaque;
import jeu.objets.Pot;
import jeu.personnages.Cuisinier;
import jeu.personnages.Prisonnier;
import jeu.personnages.Servante;

import java.util.Random;

public class Map {

    private Piece[] map;

    private Piece pieceCourante;


    public Map(){
        this.map = new Piece[25];
    }



    public void creerCarte() {

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
        map[21].ajouteSortie(Sortie.EST,map[20]);
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

        Plaque plaque1 = new Plaque(1,0);
        Plaque plaque2 = new Plaque(2,0);
        Plaque plaque3 = new Plaque(3,0);


        initialiserPot();

        map[23].ajouterItem(plaque1);
        map[23].ajouterItem(plaque2);
        map[23].ajouterItem(plaque3);


        map[3].ajouterItem(baton);

        map[10].ajouterItem(epee);

        map[23].ajouterItem(lingot);
        map[23].ajouterItem(peluche);

        this.pieceCourante = map[22];
    }


    public  void initialiserPot() {
        int[] full = new int[6];

        for (int i = 0; i < 6; i++) {
            full[i] = 0;
        }

        Random random = new Random();

        int a;
        int b;
        int c;

        while ((a = random.nextInt(5)) == (b = random.nextInt(5)) || (a) == (c = random.nextInt(5)) || (b) == (c)) {
        }

        full[a] = 1;
        full[b] = 1;
        full[c] = 1;

        for (int i = 0; i < 6; i++) {
            map[22].ajouterItem(new Pot(i + 1, full[i]));
        }
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
}
