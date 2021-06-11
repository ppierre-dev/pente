public class IABloque extends IAAntiPrise{
    private static boolean fini = false;
    public IABloque(Couleur couleur, Jeu jeu){
        super(couleur, jeu);
    }
    //Regarde si l'adversaire a 3 pion aligner et bloque
    @Override
    public Position calculerCoup(){
        Position position;
        Position positioninit;
        IABloque.fini = false;
        position = new Position(0,0);
        forBreak:
        for (int x=0; x<19;x++){
            for (int y=0; y<19;y++){
                positioninit = new Position(x,y);
                if(this.getJeu().getPlateau().estLibre(positioninit) == false && this.getJeu().getPlateau().getIntersection(positioninit) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(positioninit) != null){
                    for (int a=-1; a<=1;a++){
                        for (int b=-1; b<=1;b++){
                            position = TestBloque4(positioninit, a , b);
                            if(IABloque.fini == true){
                                break forBreak;
                            }
                            position = TestBloque3(positioninit, a , b);
                            if(IABloque.fini == true){
                                break forBreak;
                            }
                        }
                    }       
                }
            }   
        }
        if(IABloque.fini == false){
            position = super.calculerCoup();
        }
        return position;
    }
    public Position TestBloque3(Position positioninit, Integer dirX, Integer dirY){
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
                    if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(position) != null){
                        if(position.getX() > 0 && position.getX() < Plateau.DIMENSION && position.getY() > 0 && position.getY() < Plateau.DIMENSION){
                            position = new Position(
                                position.getX() + dirX,
                                position.getY() + dirY
                            );
                            if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(position) != null){
                                if(position.getX() > 0 && position.getX() < Plateau.DIMENSION && position.getY() > 0 && position.getY() < Plateau.DIMENSION){
                                    position = new Position(
                                        position.getX() + dirX,
                                        position.getY() + dirY
                                    );
                                    if(this.getJeu().getPlateau().estLibre(position) == true){
                                        IABloque.fini = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return position;
    }
    public Position TestBloque4(Position positioninit, Integer dirX, Integer dirY){
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
                    if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(position) != null){
                        if(position.getX() > 0 && position.getX() < Plateau.DIMENSION && position.getY() > 0 && position.getY() < Plateau.DIMENSION){
                            position = new Position(
                                position.getX() + dirX,
                                position.getY() + dirY
                            );
                            if(this.getJeu().getPlateau().estLibre(position) == true){
                                IABloque.fini = true;
                            }
                        }
                    }
                }
            }
        }
        return position;
    }
}