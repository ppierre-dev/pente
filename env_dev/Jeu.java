/**
 * Classe Jeu
 * Classe principale de gestion du jeu 
 */
public abstract class Jeu {
    private static InterfaceGraphique interfaceGraphique = new InterfaceGraphique();
    private static Plateau plateau = new Plateau();
    private static Couleur tourJoueur = Couleur.NOIR;
    private static Joueur joueurBlanc = new Joueur(Couleur.BLANC);
    private static Joueur joueurNoir = new Joueur(Couleur.NOIR);
    private static AvantDernierCoup avantDernierCoup;
    public static void main(String[] args){ 
        double random = Math.random();
        if(random < 0.5){
            Jeu.setTourJoueur(Couleur.BLANC);
        }
        else{
            Jeu.setTourJoueur(Couleur.NOIR);
        }
    }

    /**
     * Obtenir le plateau du jeu
     * @return Une instance de Plateau
     */
    public static Plateau getPlateau(){
        return Jeu.plateau;
    }

    /**
     * Définir le plateau du jeu
     * @param plateau Une instance de Plateau
     */
    public static void setPlateau(Plateau plateau){
        Jeu.plateau = plateau;
    }

    /**
     * Obtenir la couleur du joueur qui doit jouer
     * @return
     */
    public static Couleur getTourJoueur(){
        return tourJoueur;
    }

    /**
     * Définir en interne la couleur du joueur qui doit jouer
     * @return
     */
    private static void setTourJoueur(Couleur tourJoueur){
        Jeu.tourJoueur = tourJoueur;
    }

    /**
     * Effectue le coup du joueur sur le plateau
     * et passe au joueur suivant
     */
    public static void jouerCoup(){
        /*
            Traitement du plateau
            à venir
        */

        /*
            Passer au joueur suivant
         */
        if(Jeu.getTourJoueur().equals(Couleur.BLANC)){
            Jeu.setTourJoueur(Couleur.NOIR);
        }
        else{
            Jeu.setTourJoueur(Couleur.BLANC);
        }
    }
}
