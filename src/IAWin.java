/**
 * Classe IAWin
 * @author Étienne OGEZ
 * Niveau supérieur d'IA
 * 1) Cherche à terminer un alignement de cinq pions
 * (uniquement si la suite en possède déjà quatre)
 * 2) Cherche à prendre des paires de pions
 * adverses
 * 3) Cherche à bloquer les alignements de pions
 * adverses
 * 4) Cherche à bloquer les attaques prises
 * adverses (se protège)
 * 5) Analyse le plateau et cherche des alignements
 * de pions à réaliser
 */
public class IAWin extends IABloque{
    private static boolean fini;
    public IAWin(Couleur couleur, Jeu jeu){
        super(couleur, jeu);
    }
    
    /**
     * Cherche à terminer un alignement de cinq pions
     * uniquement si quatre pions sont déjà alignés
     */
    @Override
    public Position calculerCoup(){
        Position position;
        Position positioninit;
        IAWin.fini = false;
        position = new Position(0,0);
        forBreak:
        for (int x=0; x<19;x++){
            for (int y=0; y<19;y++){
                positioninit = new Position(x,y);
                if(this.getJeu().getPlateau().estLibre(positioninit) == false && this.getJeu().getPlateau().getIntersection(positioninit) == this.getCouleur()){
                    for (int a=-1; a<=1; a++){
                        for (int b=-1; b<=1; b++){
                            position = TestGagne(positioninit, a , b);
                            if(IAWin.fini == true){
                                break forBreak;
                            }
                        }
                    }       
                }
            }   
        }
        if(IAWin.fini == false){
            position = super.calculerCoup();
        }
        return position;
    }

    /**
     * Méthode de découpage du code
     */
    public Position TestGagne(Position positioninit, Integer dirX , Integer dirY){
        Position position = new Position(0, 0);
        if(positioninit.getX() > 0 && positioninit.getX() < Plateau.DIMENSION && positioninit.getY() > 0 && positioninit.getY() < Plateau.DIMENSION){
            position = new Position(
                positioninit.getX() + dirX,
                positioninit.getY() + dirY
            );
            if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) == this.getCouleur()){
                if(position.getX() > 0 && position.getX() < Plateau.DIMENSION && position.getY() > 0 && position.getY() < Plateau.DIMENSION){
                    position = new Position(
                        position.getX() + dirX,
                        position.getY() + dirY
                    );
                    if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) == this.getCouleur()){
                        if(position.getX() > 0 && position.getX() < Plateau.DIMENSION && position.getY() > 0 && position.getY() < Plateau.DIMENSION){
                            position = new Position(
                                position.getX() + dirX,
                                position.getY() + dirY
                            );
                            if(this.getJeu().getPlateau().estLibre(position) == true){
                                IAWin.fini = true;
                            }
                        }
                    }
                }
            }
        }
        return position;
    }
}