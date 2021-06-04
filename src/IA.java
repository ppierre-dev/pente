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
        int x = randomGenerator.nextInt(Plateau.DIMENSION);
        int y = randomGenerator.nextInt(Plateau.DIMENSION);
        position = new Position(x,y);
        while(libre == false){
            libre = this.getJeu().getPlateau().estLibre(position);
            if(libre == false){
                x = randomGenerator.nextInt(Plateau.DIMENSION);
                y = randomGenerator.nextInt(Plateau.DIMENSION);
                position = new Position(x,y);
            }
        }
        return position;
    }

    @Override
    public void poserPion(Position position){
        if(position == null){
            if(this.getJeu().getPlateau().poserPion(this.getCouleur(), this.calculerCoup())){
                this.getJeu().mettreAJour(position); 
            }
        }
        else{
            if(this.getJeu().getPlateau().poserPion(this.getCouleur(), position)){
                this.getJeu().mettreAJour(position); 
            }
        }
    }

}
