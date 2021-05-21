/**
 * Classe Pion
 * Représente une pièce placée sur un plateau de jeu
 */
public class Pion {
    private Position position;
    private Couleur couleur;

    /**
     * Obtenir la position du pion
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * Définir la position du pion
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Obtenir la couleur du pion
     * @return
     */
    public Couleur getCouleur(){
        return this.couleur;
    }

    /**
     * Définir en interne la couleur du pion
     * @param couleur Une couleur
     */
    private void setCouleur(Couleur couleur){
        this.couleur = couleur;
    }

}
