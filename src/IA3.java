import java.util.Random;


public class IA3 extends IA2{
    Random randomGenerator = new Random();

    public IA3(Couleur couleur, Jeu jeu){
        super(couleur, jeu);
    }
    @Override
    public Position calculerCoup(){
        Position position;
        Position positioninit;
        boolean fini;
        fini = false;
        position = new Position(0,0);
        for (int x=0; x<19;x++){
            for (int y=0; y<19;y++){
                positioninit = new Position(x,y);
                if(this.getJeu().getPlateau().estLibre(positioninit) == false && this.getJeu().getPlateau().getIntersection(positioninit) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(positioninit) != null){
                    if(positioninit.getX() < Plateau.DIMENSION){
                        position = new Position(
                        positioninit.getX() + 1,
                        positioninit.getY()
                        );
                        if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(position) != null){
                            if(position.getX() < Plateau.DIMENSION){
                                position = new Position(
                                position.getX() + 1,
                                position.getY()
                                );
                                if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(position) != null){
                                    if(position.getX() < Plateau.DIMENSION){
                                        position = new Position(
                                        position.getX() + 1,
                                        position.getY()
                                        );
                                        if(this.getJeu().getPlateau().estLibre(position) == true){
                                            fini = true;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if(positioninit.getX() > 0){
                        position = new Position(
                        positioninit.getX() - 1,
                        positioninit.getY()
                        );
                        if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(position) != null){
                            if(position.getX() > 0){
                                position = new Position(
                                position.getX() - 1,
                                position.getY()
                                );
                                if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(position) != null){
                                    if(position.getX() > 0){
                                        position = new Position(
                                        position.getX() - 1,
                                        position.getY()
                                        );
                                        if(this.getJeu().getPlateau().estLibre(position) == true){
                                            fini = true;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if(positioninit.getY() < Plateau.DIMENSION){
                        position = new Position(
                        positioninit.getX(),
                        positioninit.getY() + 1
                        );
                        if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(position) != null){
                            if(positioninit.getY() < Plateau.DIMENSION){
                                position = new Position(
                                position.getX(),
                                position.getY() + 1
                                );
                                if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(position) != null){
                                    if(positioninit.getY() < Plateau.DIMENSION){
                                        position = new Position(
                                        position.getX(),
                                        position.getY() + 1
                                        );
                                        if(this.getJeu().getPlateau().estLibre(position) == true){
                                            fini = true;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    
                    if(positioninit.getY() > 0){
                        position = new Position(
                        positioninit.getX(),
                        positioninit.getY() - 1
                        );
                        if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(position) != null){
                            if(positioninit.getY() > 0){
                                position = new Position(
                                position.getX(),
                                position.getY() - 1
                                );
                                if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(position) != null){
                                    if(positioninit.getY() > 0){
                                        position = new Position(
                                        position.getX(),
                                        position.getY() - 1
                                        );
                                        if(this.getJeu().getPlateau().estLibre(position) == true){
                                            fini = true;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if(positioninit.getX() > 0 && positioninit.getY() > 0){
                        position = new Position(
                        positioninit.getX() - 1,
                        positioninit.getY() - 1
                        );
                        if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(position) != null){
                            if(positioninit.getX() > 0 && positioninit.getY() > 0){
                                position = new Position(
                                position.getX() - 1,
                                position.getY() - 1
                                );
                                if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(position) != null){
                                    if(positioninit.getX() > 0 && positioninit.getY() > 0){
                                        position = new Position(
                                        position.getX() - 1,
                                        position.getY() - 1
                                        );
                                        if(this.getJeu().getPlateau().estLibre(position) == true){
                                            fini = true;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if(positioninit.getX() < Plateau.DIMENSION && positioninit.getY() < Plateau.DIMENSION){
                        position = new Position(
                        positioninit.getX() + 1,
                        positioninit.getY() + 1
                        );
                        if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(position) != null){
                            if(positioninit.getX() < Plateau.DIMENSION && positioninit.getY() < Plateau.DIMENSION){
                                position = new Position(
                                position.getX() + 1,
                                position.getY() + 1
                                );
                                if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(position) != null){
                                    if(positioninit.getX() < Plateau.DIMENSION && positioninit.getY() < Plateau.DIMENSION){
                                        position = new Position(
                                        position.getX() + 1,
                                        position.getY() + 1
                                        );
                                        if(this.getJeu().getPlateau().estLibre(position) == true){
                                            fini = true;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if(positioninit.getX() > 0 && positioninit.getY() < Plateau.DIMENSION){
                        position = new Position(
                        positioninit.getX() - 1,
                        positioninit.getY() + 1
                        );
                        if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(position) != null){
                            if(positioninit.getX() > 0 && positioninit.getY() < Plateau.DIMENSION){
                                position = new Position(
                                position.getX() - 1,
                                position.getY() + 1
                                );
                                if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(position) != null){
                                    if(positioninit.getX() > 0 && positioninit.getY() < Plateau.DIMENSION){
                                        position = new Position(
                                        position.getX() - 1,
                                        position.getY() + 1
                                        );
                                        if(this.getJeu().getPlateau().estLibre(position) == true){
                                            fini = true;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    
                    if(positioninit.getX() < Plateau.DIMENSION && positioninit.getY() > 0){
                        position = new Position(
                        positioninit.getX() + 1,
                        positioninit.getY() - 1
                        );
                        if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(position) != null){
                            if(positioninit.getX() < Plateau.DIMENSION && positioninit.getY() > 0){
                                position = new Position(
                                position.getX() + 1,
                                position.getY() - 1
                                );
                                if(this.getJeu().getPlateau().estLibre(position) == false && this.getJeu().getPlateau().getIntersection(position) != this.getCouleur() && this.getJeu().getPlateau().getIntersection(position) != null){
                                    if(positioninit.getX() < Plateau.DIMENSION && positioninit.getY() > 0){
                                        position = new Position(
                                        position.getX() + 1,
                                        position.getY() - 1
                                        );
                                        if(this.getJeu().getPlateau().estLibre(position) == true){
                                            fini = true;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        if(fini == true){
            break;
        }
        }
        if(fini == false){
            position = super.calculerCoup();
        }
        return position;
    }
}