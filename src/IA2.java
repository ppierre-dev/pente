import java.util.Random;


public class IA2 extends IA{
    Random randomGenerator = new Random();

    public IA2(Couleur couleur, Jeu jeu){
        super(couleur, jeu);
    }
    @Override
    public Position calculerCoup(){
        Position position;
        Position positiontest;
        Integer suite;
        suite = 1;
        int place;
        place = 0;
        position = new Position(0,0);
        boolean libre;
        for (int x=0; x<19;x++) {
            for (int y=0; y<19;y++) {
                position = new Position(x,y);
                libre = Jeu.getInstancePrincipale().getPlateau().estLibre(position);
                if(libre == false && Jeu.getInstancePrincipale().getPlateau().getIntersection(position) != this.getCouleur()){
                    for(int a=x; a< a + 3; a++){
                        positiontest = new Position(a,y);
                        if(Jeu.getInstancePrincipale().getPlateau().estLibre(positiontest) == false && Jeu.getInstancePrincipale().getPlateau().getIntersection(position) != this.getCouleur()){
                            suite++;
                            if(suite == 4){
                                place = 1;
                            }
                        }
                    }
                    suite = 1;
                    for(int a=x; a > a - 3; a--){
                        positiontest = new Position(a,y);
                        if(Jeu.getInstancePrincipale().getPlateau().estLibre(positiontest) == false && Jeu.getInstancePrincipale().getPlateau().getIntersection(position) != this.getCouleur()){
                            suite = suite + 1;
                            if(suite == 4){
                                place = 2;
                            }
                        }
                    }
                    suite = 1;
                    for(int a=y; a< a + 3; a++){
                        positiontest = new Position(a,y);
                        if(Jeu.getInstancePrincipale().getPlateau().estLibre(positiontest) == false && Jeu.getInstancePrincipale().getPlateau().getIntersection(position) != this.getCouleur()){
                            suite++;
                            if(suite == 4){
                                place = 3;
                            }
                        }
                    }
                    suite = 1;
                    for(int a=x; a > a - 3; a--){
                        positiontest = new Position(a,y);
                        if(Jeu.getInstancePrincipale().getPlateau().estLibre(positiontest) == false && Jeu.getInstancePrincipale().getPlateau().getIntersection(position) != this.getCouleur()){
                            suite = suite + 1;
                            if(suite == 4){
                                place = 4;
                            }
                        }
                    }
                    
                    if(place == 1){
                        position = new Position(x+4,y);
                    }
                    else if(place == 2){
                        position = new Position(x-4,y);
                    }
                    else if(place == 3){
                        position = new Position(x,y+4);
                    }
                    else if(place == 4){
                        position = new Position(x,y-4);
                    }
                    else{
                        position = calculerCoup();
                    }

                }
            }
        }
        
        return position;
    }

}