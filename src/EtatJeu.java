import java.util.ArrayList;
import java.util.HashMap;

public class EtatJeu {

    private Plateau plateau;
    private Couleur tourJoueur;
    private HashMap<Couleur, Joueur> joueurs;
    private boolean etatPartie;

    public EtatJeu(Jeu jeu){
        Plateau plateau = new Plateau(jeu);
        plateau.copy(jeu.getPlateau());

        this.setPlateau(plateau);
        this.tourJoueur = jeu.getTourJoueur();

        HashMap<Couleur, Joueur> joueurs = new HashMap<Couleur, Joueur>();
        jeu.getJoueurs().forEach((couleur, joueur) -> {
            joueurs.put(couleur, joueur);
        });
        this.joueurs = joueurs;
        this.etatPartie = jeu.getEtatPartie();
    }

    public boolean getEtatPartie() {
        return etatPartie;
    }

    public void setEtatPartie(boolean etatPartie) {
        this.etatPartie = etatPartie;
    }

    public HashMap<Couleur, Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(HashMap<Couleur, Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    public Couleur getTourJoueur() {
        return tourJoueur;
    }

    public void setTourJoueur(Couleur tourJoueur) {
        this.tourJoueur = tourJoueur;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

}
