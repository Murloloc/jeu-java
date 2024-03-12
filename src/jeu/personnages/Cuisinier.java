package jeu.personnages;

public class Cuisinier extends PNJ{

    public Cuisinier(String nom) {
        super(nom);
    }
    public String dialogue() {
        return (this + ", le rez-de-chaussée communique avec 2 tours : une à gauche libre d’accès, et une à droite qui est verrouillée.\n" +
                "La clé pour la tour de droite se trouve certainement quelque-part à l’étage 0.\n");
    }

}//fin de la classe
