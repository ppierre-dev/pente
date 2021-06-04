import java.util.Random;

public class IA extends Joueur{
    Random randomGenerator = new Random();

    public IA(Couleur couleur, Jeu jeu){
        super(couleur, jeu);
    }

    public Position calculerCoup(){
        boolean libre;
        Position position;
        libre = false;
        int x = randomGenerator.nextInt(19);
        int y = randomGenerator.nextInt(19);
        position = new Position(x,y);
        while(libre == false){
            libre = Jeu.getInstancePrincipale().getPlateau().estLibre(position);
            if(libre == false){
                x = randomGenerator.nextInt(19);
                y = randomGenerator.nextInt(19);
                position = new Position(x,y);
            }
        }
        return position;
    }
}
