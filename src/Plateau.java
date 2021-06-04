import java.lang.annotation.Annotation;

/**
 * Classe Plateau
 * Représente un plateau de jeu
 */
public final class Plateau{
    public static int DIMENSION = 19;
    private Couleur[][] intersections;
    private Jeu jeu;

    /**
     * Constructeur par défaut
     * Initialise le tableau de intersections
     * par des valeurs null indiquant
     * l'absence de Couleurs
     */
    public Plateau(Jeu jeu){
        this.setJeu(jeu);
        Couleur[][] intersections_ = new Couleur[Plateau.DIMENSION][Plateau.DIMENSION];
        for(int x=0; x<Plateau.DIMENSION; x++){
            for(int y=0; y<Plateau.DIMENSION; y++){
                intersections_[x][y] = null;
            }
        }
        this.setIntersections(intersections_);
    }

    /**
     * Obtenir les intersections du plateau
     * @return
     */
    public Couleur[][] getIntersections(){
        return this.intersections;
    }

    /**
     * Définir les intersections du plateau
     * @param intersections
     */
    public void setIntersections(Couleur[][] intersections){
        this.intersections = intersections;
    }

    public boolean estLibre(Position position){
        if(position.getX() >= 0 && position.getX() < Plateau.DIMENSION){
            if(position.getY() >= 0 && position.getY() < Plateau.DIMENSION){
                return this.getIntersections()[position.getX()][position.getY()] == null;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    public boolean retirerPion(Position position){
        if(!this.getJeu().getEtatPartie()){
            return false;
        }

        if(!this.estLibre(position)){
            this.getIntersections()[position.getX()][position.getY()] = null;
            return true;
        }
        else{
            return false;
        }
    }

    public boolean poserPion(Couleur couleur, Position position){
        if(!this.getJeu().getEtatPartie()){
            return false;
        }

        if(!(this.getJeu().getTourJoueur().equals(couleur))){
            return false;
        }

        if(this.getNombrePionsCouleur(couleur) < Joueur.MAX_PIONS){
            if(this.estLibre(position)){
                this.getJeu().enregistrerEtat();
                this.getIntersections()[position.getX()][position.getY()] = couleur;
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    public Couleur getIntersection(Position position){
        if(this.estLibre(position)){
            return null;
        }
        else{
            if(position.getX() >= 0 && position.getX() < Plateau.DIMENSION){
                if(position.getY() >= 0 && position.getY() < Plateau.DIMENSION){
                    return this.getIntersections()[position.getX()][position.getY()];
                }
                else{
                    return null;
                }
            }
            else{
                return null;
            }
        }
    }

    public int getNombrePionsCouleur(Couleur couleur){
        int compteur = 0;
        for(int x=0; x<Plateau.DIMENSION; x++){
            for(int y=0; y<Plateau.DIMENSION; y++){

                if(this.getIntersections()[x][y] != null){
                    if(this.getIntersections()[x][y].equals(couleur)){
                        compteur++;
                    }
                }

            }
        }
        return compteur;
    }

    public void copy(Plateau p){
        for(int x=0; x<Plateau.DIMENSION; x++){
            for(int y=0; y<Plateau.DIMENSION; y++){
                this.intersections[x][y] = p.getIntersection(new Position(x, y));
            }
        }
    }

    private Jeu getJeu(){
        return this.jeu;
    }

    private void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }
}