/**
 * Classe Plateau
 * Représente un plateau de jeu
 */
public class Plateau{
    private Pion[][] cases;

    /**
     * Constructeur par défaut
     * Initialise le tableau de cases
     * par des valeurs null indiquant
     * l'absence de pions
     */
    public Plateau(){
        Pion[][] cases_ = new Pion[20][20];
        for(int x=0; x<20; x++){
            for(int y=0; y<20; y++){
                cases_[x][y] = null;
            }
        }
        this.setCases(cases_);
    }

    /**
     * Obtenir les cases du plateau
     * @return
     */
    public Pion[][] getCases(){
        return this.cases;
    }

    /**
     * Définir les cases du plateau
     * @param cases
     */
    public void setCases(Pion[][] cases){
        this.cases = cases;
    }
}