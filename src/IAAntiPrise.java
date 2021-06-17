/**
 * Classe IAAligne
 * @author Étienne OGEZ, Valentin MANTEZ
 * Niveau supérieur d'IA
 * Analyse le plateau et cherche des alignements
 * de pions à réaliser
 * Cherche à bloquer les attaques prises
 * adverses (se protège)
 */
public class IAAntiPrise extends IAPrise{
    private static boolean fini;
    public IAAntiPrise(Couleur couleur, Jeu jeu){
        super(couleur, jeu);
    }

    /**
     * Nouvelle version de la méthode
     */
    @Override
    public Position calculerCoup(){
        Position position;
        Position positioninit;
        IAAntiPrise.fini = false;
        position = new Position(0,0);
        forBreak:
        for (int x=0; x<19;x++){
            for (int y=0; y<19;y++){
                positioninit = new Position(x,y);
                if(this.getJeu().getPlateau().estLibre(positioninit) == false && this.getJeu().getPlateau().getIntersection(positioninit) == this.getCouleur() && this.getJeu().getPlateau().getIntersection(positioninit) != null){
                    for (int a=-1; a<=1; a++){
                        for (int b=-1; b<=1; b++){
                            position = TestAntiPrise(positioninit, a , b);
                            if(IAAntiPrise.fini == true){
                                break forBreak;
                            }
                        }
                    }       
                }
            }   
        }
        if(IAAntiPrise.fini == false){
            position = super.calculerCoup();
        }
        return position;
    }

    /**
     * Interprète l'état du jeu pour chercher un endroit
     * où faire une défense anti-prise de ses pions
     */
    public Position TestAntiPrise(Position positioninit, Integer dirX , Integer dirY){
        Position position = new Position(0, 0);
        if(positioninit.getX() > 0 && positioninit.getX() < Plateau.DIMENSION && positioninit.getY() > 0 && positioninit.getY() < Plateau.DIMENSION){
            position = new Position(
                positioninit.getX() + dirX,
                positioninit.getY() + dirY
            );
            if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) == this.getCouleur() && this.getJeu().getPlateau().getIntersection(position) != null){
                if(position.getX() > 0 && position.getX() < Plateau.DIMENSION && position.getY() > 0 && position.getY() < Plateau.DIMENSION){
                    position = new Position(
                        position.getX() + dirX,
                        position.getY() + dirY
                    );
                    if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(position) != null){
                        if(position.getX() > 0 && position.getX() < Plateau.DIMENSION && position.getY() > 0 && position.getY() < Plateau.DIMENSION){
                            position = new Position(
                                positioninit.getX() - dirX,
                                positioninit.getY() - dirY
                            );
                            if(this.getJeu().getPlateau().estLibre(position) == true){
                                IAAntiPrise.fini = true;
                            }
                        }
                    }
                }
            }
        }
        return position;
    }
}