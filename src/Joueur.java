import java.util.ArrayList;
import java.util.UUID;

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

    /**
     * Obtenir le nombre de pions du joueur
     * @return
     */
    public final int getNombrePions(){
        return this.getJeu().getPlateau().getNombrePionsCouleur(this.getCouleur());
    }

    /**
     * Obtenir le nom du joueur
     * @return Le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définir le nom du joueur
     * @param nom Le nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtenir la couleur du joueur
     * @return
     */
    public Couleur getCouleur(){
        return this.couleur;
    }

    /**
     * Définir en interne la couleur du joueur
     * @param couleur Une couleur
     */
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
