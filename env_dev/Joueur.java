import java.util.ArrayList;

public class Joueur {
    
    private ArrayList<Pion> pions;
    private Couleur couleur;

    /**
     * Constructeur par défaut
     */
    public Joueur(Couleur couleur){
        this.setPions(new ArrayList<Pion>());
        this.setCouleur(couleur);
    }

    /**
     * Obtenir la liste des pions du joueur
     */
    public ArrayList<Pion> getPions(){
        return this.pions;
    }

    /**
     * Obtenir le nombre de pions du joueur
     * @return
     */
    public int getNombrePions(){
        return this.pions.size();
    }

    /**
     * Définir la liste des pions du joueur
     * @param pions
     */
    public void setPions(ArrayList<Pion> pions){
        this.pions = pions;
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
