/**
 * Classe IAAligne
 * @author Étienne OGEZ
 * Niveau supérieur d'IA
 * 1) Cherche à prendre des paires de pions
 * adverses
 * 2) Cherche à bloquer les alignements de pions
 * adverses
 * 3) Cherche à bloquer les attaques prises
 * adverses (se protège)
 * 4) Analyse le plateau et cherche des alignements
 * de pions à réaliser
 */
public class IAPrise extends IAAligne{
    private static boolean fini;
    public IAPrise(Couleur couleur, Jeu jeu){
        super(couleur, jeu);
    }
    
    /**
     * Nouvelle version de la méthode
     * Cherche à prendre des paires de pions adverses
     */
    @Override
    public Position calculerCoup(){
        Position position;
        Position positioninit;
        IAPrise.fini = false;
        position = new Position(0,0);
        forBreak:
        for (int x=0; x<19;x++){
            for (int y=0; y<19;y++){
                positioninit = new Position(x,y);
                if(this.getJeu().getPlateau().estLibre(positioninit) == false && this.getJeu().getPlateau().getIntersection(positioninit) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(positioninit) != null){
                    for (int a=-1; a<=1; a++){
                        for (int b=-1; b<=1; b++){
                            position = TestPrise(positioninit, a , b);
                            if(IAPrise.fini == true){
                                break forBreak;
                            }
                        }
                    }       
                }
            }   
        }
        if(IAPrise.fini == false){
            position = super.calculerCoup();
        }
        return position;
    }

    /**
     * Cherche des paires adverses à capturer
     */
    public Position TestPrise(Position positioninit, Integer dirX , Integer dirY){
        Position position = new Position(0, 0);
        if(positioninit.getX() > 0 && positioninit.getX() < Plateau.DIMENSION && positioninit.getY() > 0 && positioninit.getY() < Plateau.DIMENSION){
            position = new Position(
                positioninit.getX() + dirX,
                positioninit.getY() + dirY
            );
            if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(position) != null){
                if(position.getX() > 0 && position.getX() < Plateau.DIMENSION && position.getY() > 0 && position.getY() < Plateau.DIMENSION){
                    position = new Position(
                        position.getX() + dirX,
                        position.getY() + dirY
                    );
                    if(this.getJeu().getPlateau().estLibre(position) == true){
                        IAPrise.fini = true;
                    }
                }
            }
        }
        return position;
    }
}