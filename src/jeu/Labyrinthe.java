package jeu;

import java.util.Random;

public class Labyrinthe extends Piece {
    private static final String[] Directions = {"NORD", "EST", "SUD", "OUEST"};

    private Piece[] piecesLab = new Piece[5];

    public Labyrinthe(String nomPiece, String description, String image) {
        super(nomPiece, description, image);
    }

    public void genererLabyrinthe(int nbPieces) {
        Random rand = new Random();
        for (int i = 0; i < nbPieces-1; i++) {
            piecesLab[i] = new Piece("Room " + (i+1), "", "Labyrinthe.jpg");
            this.ajouteSortie(Sortie.valueOf(Directions[rand.nextInt(4)].toUpperCase()), piecesLab[i+1]);
        }
        piecesLab[nbPieces-1] = new Piece("Room " + nbPieces, "", "Labyrinthe.jpg"); // Add the last room
    }
}
