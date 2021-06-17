import java.util.ArrayList;
import java.util.UUID;

/**
 * Classe Joueur
 * @author Lucas DOUTRELUIGNE, Pierre PREVOST
 * Représente un joueur
 * La Classe IA étend cette classe
 * pour des raisons pratiques
 */
public class Joueur{
    
    public static int MAX_PIONS = 60;

    private Jeu jeu;
    private Couleur couleur;
    private String nom;
    private int pairesPrises = 0;

    /**
     * Constructeur par défaut
     */
    public Joueur(Couleur couleur, Jeu jeu){
        this.setCouleur(couleur);
        this.setNom(this.getCouleur().toString());
        this.setJeu(jeu);
    }

    public void poserPion(Position position){
        if(this.getJeu().getPlateau().poserPion(this.getCouleur(), position)){
           this.getJeu().mettreAJour(position); 
        }
    }

    public int getPairesPrises(){
        return this.pairesPrises;
    }

    public void incrementerPairesPrises(){
        this.pairesPrises++;
    }

    public void setPairesPrises(int prises){
        this.pairesPrises = prises;
    }

    public Joueur clone(){
        Joueur joueur = new Joueur(this.getCouleur(), this.getJeu());
        joueur.setNom(this.getNom());
        joueur.setPairesPrises(this.getPairesPrises());
        return joueur;
    }

    public final int getNombrePions(){
        return this.getJeu().getPlateau().getNombrePionsCouleur(this.getCouleur());
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Couleur getCouleur(){
        return this.couleur;
    }

    private void setCouleur(Couleur couleur){
        this.couleur = couleur;
    }

    public Jeu getJeu(){
        return this.jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

}
