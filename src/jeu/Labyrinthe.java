package jeu;

import java.util.Random;

/**
 * Cette classe vise à créer un labyrinthe généré aléatoirement afin de l'ajouter dans la map
 */

public class Labyrinthe extends Piece {

    private final Piece[] piecesLab;
    private final Map map;

    /**
     * Constructeur de la classe Labyrinthe
     *
     * @param nomPiece
     * @param description
     * @param image
     * @param map
     */
    public Labyrinthe(String nomPiece, String description, String image, Map map) {
        super(nomPiece, description, image);
        this.piecesLab = new Piece[5];
        this.map = map;

    }

    /**
     * Genere le labyrinthe
     */
    public void genererLabyrinthe() {

        piecesLab[0] = new Piece("dans la room 1", "", "Labyrinthe.jpg");
        piecesLab[1] = new Piece("dans la room 2", "", "Labyrinthe.jpg");
        piecesLab[2] = new Piece("dans la room 3", "", "Labyrinthe.jpg");
        piecesLab[3] = new Piece("dans la room 4", "", "Labyrinthe.jpg");
        piecesLab[4] = new Piece("dans la room 5", "", "Labyrinthe.jpg");


        piecesLab[0].ajouteSortie(Sortie.SUD, map.getMap()[19]);


        // Link Room 2 to Room 1 from a random direction except south
        Sortie randomSortie = getRandomSortieExcept(Sortie.SUD);
        String mur = "Entrez dans le labyrinthe puis : \n" + randomSortie;
        piecesLab[0].ajouteSortie(randomSortie, piecesLab[1]);
        piecesLab[1].ajouteSortie(getOppositeSortie(randomSortie), piecesLab[0]);


        randomSortie = getRandomSortieExcept(getOppositeSortie(randomSortie));
        mur += "\n" + randomSortie;
        piecesLab[1].ajouteSortie(randomSortie, piecesLab[2]);
        piecesLab[2].ajouteSortie(getOppositeSortie(randomSortie), piecesLab[1]);

        randomSortie = getRandomSortieExcept(getOppositeSortie(randomSortie));
        mur += "\n" + randomSortie;
        piecesLab[2].ajouteSortie(randomSortie, piecesLab[3]);
        piecesLab[3].ajouteSortie(getOppositeSortie(randomSortie), piecesLab[2]);

        randomSortie = getRandomSortieExcept(getOppositeSortie(randomSortie));
        mur += "\n" + randomSortie;
        piecesLab[3].ajouteSortie(randomSortie, piecesLab[4]);
        piecesLab[4].ajouteSortie(getOppositeSortie(randomSortie), piecesLab[3]);

        piecesLab[4].ajouteSortie(Sortie.MONTER, map.getMap()[21]);
        mur += "\n" + "MONTER";

        map.setInscriptionMur(mur);
    }

    /**
     * Renvoie les sorties de chaque piece (generees aleatoirement)
     *
     * @param exceptSortie
     * @return
     */
    public Sortie getRandomSortieExcept(Sortie exceptSortie) {
        Sortie[] allSorties = {Sortie.NORD, Sortie.SUD, Sortie.EST, Sortie.OUEST};
        Sortie randomSortie;
        do {
            randomSortie = allSorties[new Random().nextInt(allSorties.length)];
        } while (randomSortie == exceptSortie);
        return randomSortie;
    }

    /**
     * Renvoie la direction opposee a chaque sortie du labyrinthe
     *
     * @param sortie
     * @return
     */
    public Sortie getOppositeSortie(Sortie sortie) {
        return switch (sortie) {
            case NORD -> Sortie.SUD;
            case SUD -> Sortie.NORD;
            case EST -> Sortie.OUEST;
            case OUEST -> Sortie.EST;
            default -> throw new IllegalArgumentException("Invalid direction: " + sortie);
        };
    }

    /**
     * Getter de piecesLab
     *
     * @return
     */
    public Piece[] getPiecesLab() {
        return piecesLab;
    }

    /**
     * Redefinition afin de ne pas montrer les sorties possibles dans le labyrinthe
     *
     * @return
     */
    public String descriptionLongue() {
        return this + "\nSorties : ????";
    }


}
