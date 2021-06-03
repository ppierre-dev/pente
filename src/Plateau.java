/**
 * Classe Plateau
 * Représente un plateau de jeu
 */
public class Plateau{
    public static int DIMENSION = 19;
    private Pion[][] intersections;

    /**
     * Constructeur par défaut
     * Initialise le tableau de intersections
     * par des valeurs null indiquant
     * l'absence de pions
     */
    public Plateau(){
        Pion[][] intersections_ = new Pion[Plateau.DIMENSION][Plateau.DIMENSION];
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
    public Pion[][] getIntersections(){
        return this.intersections;
    }

    /**
     * Définir les intersections du plateau
     * @param intersections
     */
    public void setIntersections(Pion[][] intersections){
        this.intersections = intersections;
    }

    public boolean estLibre(Position position){
        return this.getIntersections()[position.getX()][position.getY()] == null;
    }

    public void ajouterPion(Pion pion, Position position){
        if(this.getNombrePionsCouleur(pion.getCouleur()) < 60){
            if(this.estLibre(position)){
                this.getIntersections()[position.getX()][position.getY()] = pion;
            }
        }
    }

    public Pion getIntersection(Position position){
        if(this.estLibre(position)){
            return null;
        }
        else{
            return this.getIntersections()[position.getX()][position.getY()];
        }
    }

    public int getNombrePionsCouleur(Couleur couleur){
        int compteur = 0;
        for(int x=0; x<Plateau.DIMENSION; x++){
            for(int y=0; y<Plateau.DIMENSION; y++){

                if(this.getIntersections()[x][y] != null){
                    if(this.getIntersections()[x][y].getCouleur().equals(couleur)){
                        compteur++;
                    }
                }

            }
        }
        return compteur;
    }
}