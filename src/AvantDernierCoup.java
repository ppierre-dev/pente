public class AvantDernierCoup {
    private Couleur couleurJoueur;
    private Plateau plateau;

    /**
     * Constructeur uniquee
     * @param couleurJoueur La couleur du joueur
     * @param plateau Le plateau avant de jouer le coup
     */
    public AvantDernierCoup(Couleur couleurJoueur, Plateau plateau){
        this.setCouleurJoueur(couleurJoueur);
        this.setPlateau(plateau);
    }

    /**
     * Définir en interne la couleur du joueur
     * @param couleurJoueur La couleur
     */
    private void setCouleurJoueur(Couleur couleurJoueur){
        this.couleurJoueur = couleurJoueur;
    }

    /**
     * Obtenir la couleur du joueur
     * @return
     */
    public Couleur getCouleurJoueur(){
        return this.couleurJoueur;
    }

    /**
     * Définir en interne le plateau avant de jouer le coup
     * @param plateau
     */
    private void setPlateau(Plateau plateau){
        this.plateau = plateau;
    }

    /**
     * Obtenir le plateau avant de jouer le coup
     * @return
     */
    public Plateau getPlateau(){
        return this.plateau;
    }
}