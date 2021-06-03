import java.util.HashMap;
import java.util.Scanner;

/**
 * Classe Jeu
 * Classe principale de gestion du jeu
 */
public abstract class Jeu {
    private static InterfaceGraphique interfaceGraphique;
    private static Plateau plateau = new Plateau();
    private static Couleur tourJoueur = Couleur.NOIR;
    private static HashMap<Couleur, Joueur> joueurs = new HashMap<Couleur, Joueur>();
    private static AvantDernierCoup avantDernierCoup;
    public static void main(String[] args){

        GestionnaireImages.setImage("PionBlanc", "../images/PionBlanc.png");
        GestionnaireImages.setImage("PionNoir", "../images/PionNoir.png");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Saissisez le nom du joueur 1 (Blanc) : ");
        joueurs.put(Couleur.BLANC, new Joueur(Couleur.BLANC));
        joueurs.get(Couleur.BLANC).setNom(scanner.nextLine());
        
        System.out.println("Saissisez le nom du joueur 2 (Noir) : ");
        joueurs.put(Couleur.NOIR, new Joueur(Couleur.NOIR));
        joueurs.get(Couleur.NOIR).setNom(scanner.nextLine());

        Jeu.getPlateau().ajouterPion(new Pion(Couleur.BLANC, new Position(3, 2)));

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

    public static void poserPion(Position position){
        Joueur joueur = Jeu.getJoueur(Jeu.getTourJoueur());
        if(joueur.getNombrePions() <= Joueur.MAX_PIONS){
            if(Jeu.getPlateau().estLibre(position)){
                Jeu.getPlateau().ajouterPion(new Pion(joueur.getCouleur(), position));
            }
        }
        
        Jeu.mettreAJour();
    }

    /**
     * Effectue le coup du joueur sur le plateau
     * et passe au joueur suivant
     */
    public static void mettreAJour(){
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
        Jeu.afficherInformations();
    }

    /**
     * Gère les affichages textuels du jeu
     */
    public static void afficherInformations(){
        Jeu.interfaceGraphique.getCadreInformationBlanc().setNom(Jeu.joueurs.get(Couleur.BLANC).getNom());
        Jeu.interfaceGraphique.getCadreInformationNoir().setNom(Jeu.joueurs.get(Couleur.NOIR).getNom());

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

        Jeu.interfaceGraphique.getCadreInformationBlanc().setPions(String.valueOf(Joueur.MAX_PIONS - Jeu.joueurs.get(Couleur.BLANC).getNombrePions()));
        Jeu.interfaceGraphique.getCadreInformationNoir().setPions(String.valueOf(Joueur.MAX_PIONS - Jeu.joueurs.get(Couleur.BLANC).getNombrePions()));
    }

    public static Joueur getJoueur(Couleur couleur) {
        if(Jeu.joueurs.containsKey(couleur)){
            return Jeu.joueurs.get(couleur);
        }
        else{
            return null;
        }
    }

    public static void setJoueur(Couleur couleur, Joueur joueur) {
        if(Jeu.joueurs.containsKey(couleur)){
            Jeu.joueurs.remove(couleur);
        }
        Jeu.joueurs.put(couleur, joueur);
    }

    public static HashMap<Couleur, Joueur> getJoueurs(){
        return Jeu.joueurs;
    }
}
