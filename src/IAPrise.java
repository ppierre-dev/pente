public class IAPrise extends IAGagne{
    private static boolean fini;
    public IAPrise(Couleur couleur, Jeu jeu){
        super(couleur, jeu);
    }
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
                    System.out.println(IAPrise.fini);
                    if(this.getJeu().getPlateau().estLibre(position) == true){
                        IAPrise.fini = true;
                    }
                }
            }
        }
        return position;
    }
}