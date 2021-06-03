import java.util.ArrayList;
import java.util.UUID;

public class Joueur {
    
    public static int MAX_PIONS = 60;

    private ArrayList<Pion> pions;
    private Couleur couleur;
    private String nom;

    /**
     * Constructeur par défaut
     */
    public Joueur(Couleur couleur){
        this.setCouleur(couleur);
        this.setNom(this.getCouleur().toString());
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
     * Obtenir le nombre de pions du joueur
     * @return
     */
    public int getNombrePions(){
        return Jeu.getPlateau().getNombrePionsCouleur(this.getCouleur());
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

}
