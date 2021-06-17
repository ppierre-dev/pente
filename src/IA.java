import java.util.Random;

/**
 * Classe IA
 * @author Étienne OGEZ, Valentin MANTEZ
 * Classe de base de toute IA développée.
 * Elle possède une première intelligence basique,
 * mais à principalement pour but d'être étendue.
 * 
 * Le systême utilisé pour les IA est un reléguement
 * du travail à une IA inférieur
 * Ex: IAPrise relègue à IABloque si IAPrise
 * n'a rien trouvé d'intéressant
 */
public class IA extends Joueur{
    Random randomGenerator = new Random();

    public IA(Couleur couleur, Jeu jeu){
        super(couleur, jeu);
    }

    /**
     * Méthode pour dupliquer l'instance
     */
    public IA clone(){
        IA ia = new IA(this.getCouleur(), this.getJeu());
        ia.setNom(this.getNom());
        ia.setPairesPrises(this.getPairesPrises());
        return ia;
    }

    /* 
        Calcule des coordonnées aléatoires jusqu'à trouver un couple (x, y)
        vide sur le tableau
    */
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

    /**
     * Entraîne l'enchaînement des actions poser le pion
     * et mettre à jour le jeu
     */
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
