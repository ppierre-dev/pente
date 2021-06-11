import java.util.Random;

public class IA extends Joueur{
    Random randomGenerator = new Random();

    public IA(Couleur couleur, Jeu jeu){
        super(couleur, jeu);
    }

    public IA clone(){
        IA ia = new IA(this.getCouleur(), this.getJeu());
        ia.setNom(this.getNom());
        ia.setPairesPrises(this.getPairesPrises());
        return ia;
    }

    //Methode crée par Peepoodoo et Tchoupi (Valentin et Etienne)
    //Cet classe va prend des coordonnées aleatoire et regarde si la case et libre pour savoir si elle peut jouer cet intersection
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
