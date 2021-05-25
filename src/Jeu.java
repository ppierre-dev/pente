import java.util.Scanner;

/**
 * Classe Jeu
 * Classe principale de gestion du jeu
 */
public abstract class Jeu {
    private static InterfaceGraphique interfaceGraphique;
    private static Plateau plateau = new Plateau();
    private static Couleur tourJoueur = Couleur.NOIR;
    private static Joueur joueurBlanc = new Joueur(Couleur.BLANC);
    private static Joueur joueurNoir = new Joueur(Couleur.NOIR);
    private static AvantDernierCoup avantDernierCoup;
    public static void main(String[] args){ 

        Scanner scanner = new Scanner(System.in);
        System.out.println("Saissisez le nom du joueur 1 (Blanc) : ");
        joueurBlanc.setNom(scanner.nextLine());
        System.out.println("Saissisez le nom du joueur 2 (Noir) : ");
        joueurNoir.setNom(scanner.nextLine());

        double random = Math.random();
        if(random < 0.5){
            Jeu.setTourJoueur(Couleur.BLANC);
        }
        else{
            Jeu.setTourJoueur(Couleur.NOIR);
        }
        Jeu.interfaceGraphique = new InterfaceGraphique();
        Jeu.afficherInformations();
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

    /**
     * Gère les affichages textuels du jeu
     */
    public static void afficherInformations(){
        Jeu.interfaceGraphique.getCadreInformationBlanc().setNom(Jeu.joueurBlanc.getNom());
        Jeu.interfaceGraphique.getCadreInformationNoir().setNom(Jeu.joueurNoir.getNom());

        Jeu.interfaceGraphique.getCadreInformationBlanc().setCouleur(Couleur.BLANC);
        Jeu.interfaceGraphique.getCadreInformationNoir().setCouleur(Couleur.NOIR);

        if(Jeu.tourJoueur.equals(Couleur.BLANC)){
            Jeu.interfaceGraphique.getCadreInformationBlanc().getLabelJouerJoueur().setVisible(true);
            Jeu.interfaceGraphique.getCadreInformationNoir().getLabelJouerJoueur().setVisible(false);
        }
        else{
            Jeu.interfaceGraphique.getCadreInformationBlanc().getLabelJouerJoueur().setVisible(false);
            Jeu.interfaceGraphique.getCadreInformationNoir().getLabelJouerJoueur().setVisible(true);
        }

        Jeu.interfaceGraphique.getCadreInformationBlanc().setPions(String.valueOf(Joueur.MAX_PIONS - joueurBlanc.getNombrePions()));
        Jeu.interfaceGraphique.getCadreInformationNoir().setPions(String.valueOf(Joueur.MAX_PIONS - joueurNoir.getNombrePions()));
    }

    public static Joueur getJoueurBlanc() {
        return joueurBlanc;
    }

    public static void setJoueurBlanc(Joueur joueurBlanc) {
        Jeu.joueurBlanc = joueurBlanc;
    }

    public static Joueur getJoueurNoir() {
        return joueurNoir;
    }

    public static void setJoueurNoir(Joueur joueurNoir) {
        Jeu.joueurNoir = joueurNoir;
    }
}
