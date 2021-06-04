import java.util.HashMap;
import java.util.Scanner;

/**
 * Classe Jeu
 * Classe principale de gestion du jeu
 */
public class Jeu implements Ecouteur{

    private static Jeu instancePrincipale = new Jeu();

    private InterfaceGraphique interfaceGraphique;
    private Plateau plateau = new Plateau(this);
    private Couleur tourJoueur = Couleur.NOIR;
    private HashMap<Couleur, Joueur> joueurs = new HashMap<Couleur, Joueur>();
    private AvantDernierCoup avantDernierCoup;
    public static void main(String[] args){

        GestionnaireEcouteurs.enregistrerEcouteur(Jeu.getInstancePrincipale());
        /*GestionnaireEcouteurs.enregistrerEcouteur(Jeu.getInstancePrincipale().getPlateau());
        GestionnaireEcouteurs.enregistrerEcouteur(Jeu.getInstancePrincipale().getInterfaceGraphique().getCanvas());*/
        GestionnaireImages.setImage("PionBlanc", "../images/PionBlanc.png");
        GestionnaireImages.setImage("PionNoir", "../images/PionNoir.png");

    }




    public Jeu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Saissisez le nom du joueur 1 (Blanc) : ");
        joueurs.put(Couleur.BLANC, new Joueur(Couleur.BLANC, this));
        joueurs.get(Couleur.BLANC).setNom(scanner.nextLine());
        
        //System.out.println("Saissisez le nom du joueur 2 (Noir) : ");
        joueurs.put(Couleur.NOIR, new IA(Couleur.NOIR, this));
        joueurs.get(Couleur.NOIR).setNom(scanner.nextLine());

        double random = Math.random();
        if(random < 0.5){
            this.setTourJoueur(Couleur.BLANC);
        }
        else{
            this.setTourJoueur(Couleur.NOIR);
        }

        this.setInterfaceGraphique(new InterfaceGraphique(this));
        this.getInterfaceGraphique().setVisible(true);

        this.afficherInformations();

        this.getJoueur(this.getTourJoueur()).poserPion(new Position(
            Math.abs(this.getPlateau().DIMENSION / 2),
            Math.abs(this.getPlateau().DIMENSION / 2)
        ));
    }




    /**
     * Gère les affichages textuels du jeu
     */
    public void afficherInformations(){
        this.interfaceGraphique.getCadreInformationBlanc().setNom(this.joueurs.get(Couleur.BLANC).getNom());
        this.interfaceGraphique.getCadreInformationNoir().setNom(this.joueurs.get(Couleur.NOIR).getNom());

        this.interfaceGraphique.getCadreInformationBlanc().setCouleur(Couleur.BLANC);
        this.interfaceGraphique.getCadreInformationNoir().setCouleur(Couleur.NOIR);

        if(this.tourJoueur.equals(Couleur.BLANC)){
            this.interfaceGraphique.getCadreInformationBlanc().getLabelJouerJoueur().setVisible(true);
            this.interfaceGraphique.getCadreInformationNoir().getLabelJouerJoueur().setVisible(false);
        }
        else{
            this.interfaceGraphique.getCadreInformationBlanc().getLabelJouerJoueur().setVisible(false);
            this.interfaceGraphique.getCadreInformationNoir().getLabelJouerJoueur().setVisible(true);
        }

        this.interfaceGraphique.getCadreInformationBlanc().setPions(String.valueOf(Joueur.MAX_PIONS - this.joueurs.get(Couleur.BLANC).getNombrePions()));
        this.interfaceGraphique.getCadreInformationNoir().setPions(String.valueOf(Joueur.MAX_PIONS - this.joueurs.get(Couleur.NOIR).getNombrePions()));
    }






    /**
     * Obtenir le plateau du jeu
     * @return Une instance de Plateau
     */
    public Plateau getPlateau(){
        return this.plateau;
    }

    /**
     * Définir le plateau du jeu
     * @param plateau Une instance de Plateau
     */
    public void setPlateau(Plateau plateau){
        this.plateau = plateau;
    }

    /**
     * Obtenir l'interface graphique du jeu
     * @return Une instance de InterfaceGraphique
     */
    public InterfaceGraphique getInterfaceGraphique(){
        return this.interfaceGraphique;
    }

    /**
     * Définir l'interface graphique du jeu
     * @param plateau Une instance de InterfaceGraphique
     */
    public void setInterfaceGraphique(InterfaceGraphique interfaceGraphique){
        this.interfaceGraphique = interfaceGraphique;
    }

    /**
     * Obtenir la couleur du joueur qui doit jouer
     * @return
     */
    public Couleur getTourJoueur(){
        return tourJoueur;
    }

    /**
     * Définir en interne la couleur du joueur qui doit jouer
     * @return
     */
    private void setTourJoueur(Couleur tourJoueur){
        this.tourJoueur = tourJoueur;
    }

    /**
     * Effectue le coup du joueur sur le plateau
     * et passe au joueur suivant
     */
    public void mettreAJour(Position position){

        for(Couleur couleur : Couleur.values()){
            Plateau plateau = this.getPlateau();
            if(plateau.estLibre(position) || !plateau.getIntersection(position).equals(couleur)){
                /* Ce n'est pas cette couleur là qui a joué le coup */
                continue;
            }

            /*
                Ici on saît qu'on travaille avec
                la bonne couleur
            */        



            /*
                Vérifications des alignements
                de pierre
            */

            int pierres = 0;
            /* 
                Test alignement horizontal
            */
            pierres = 0;
            /*
                gauche
            */  
            for(int x = position.getX() - 1; x >= position.getX() - 4; x--){
                Position pos = new Position(x, position.getY());
                if(!plateau.estLibre(pos) && plateau.getIntersection(pos).equals(couleur)){
                    pierres++;
                }
                else{
                    break;
                }
            }
            /* 
                droite
            */
            for(int x = position.getX() + 1; x <= position.getX() + 4; x++){
                Position pos = new Position(x, position.getY());
                if(!plateau.estLibre(pos) && plateau.getIntersection(pos).equals(couleur)){
                    pierres++;
                }
                else{
                    break;
                }
            }
            if(pierres >= 4){
                this.terminerPartie();
                this.getInterfaceGraphique().getBordure1().setVisible(false);
            }



            /* 
                Test alignement vertical
            */
            pierres = 0;
            /*
                haut
            */
            for(int y = position.getY() + 1; y <= position.getY() + 4; y++){
                Position pos = new Position(position.getX(), y);
                if(!plateau.estLibre(pos) && plateau.getIntersection(pos).equals(couleur)){
                    pierres++;
                }
                else{
                    break;
                }
            }
            /* 
                bas
            */
            for(int y = position.getY() - 1; y >= position.getY() - 4; y--){
                Position pos = new Position(position.getX(), y);
                if(!plateau.estLibre(pos) && plateau.getIntersection(pos).equals(couleur)){
                    pierres++;
                }
                else{
                    break;
                }
            }
            if(pierres >= 4){
                this.terminerPartie();
            }






            /* 
                Test alignement diagonale en "\"
            */
            pierres = 0;
            /*
                haut à gauche
            */
            for(int k = 1; k <= 4; k++){
                Position pos = new Position(position.getX() - k, position.getY() - k);
                if(!plateau.estLibre(pos) && plateau.getIntersection(pos).equals(couleur)){
                    pierres++;
                }
                else{
                    break;
                }
            }
            /*
                bas à droite
            */
            for(int k = 1; k <= 4; k++){
                Position pos = new Position(position.getX() + k, position.getY() + k);
                if(!plateau.estLibre(pos) && plateau.getIntersection(pos).equals(couleur)){
                    pierres++;
                }
                else{
                    break;
                }
            }
            if(pierres >= 4){
                this.terminerPartie();
            }




            /* 
                Test alignement diagonale en "/"
            */
            pierres = 0;
            /*
                haut à droite
            */
            for(int k = 1; k <= 4; k++){
                Position pos = new Position(position.getX() + k, position.getY() - k);
                if(!plateau.estLibre(pos) && plateau.getIntersection(pos).equals(couleur)){
                    pierres++;
                }
                else{
                    break;
                }
            }
            /*
                bas à gauche
            */
            for(int k = 1; k <= 4; k++){
                Position pos = new Position(position.getX() - k, position.getY() + k);
                if(!plateau.estLibre(pos) && plateau.getIntersection(pos).equals(couleur)){
                    pierres++;
                }
                else{
                    break;
                }
            }
            if(pierres >= 4){
                this.terminerPartie();
            }
        }


        /*
            Passer au joueur suivant
        */
        if(this.getTourJoueur().equals(Couleur.BLANC)){
            this.setTourJoueur(Couleur.NOIR);
        }
        else{
            this.setTourJoueur(Couleur.BLANC);
        }

        if(this.getJoueur(this.getTourJoueur()) instanceof IA){
            IA ia = (IA) this.getJoueur(this.getTourJoueur());
            ia.poserPion(ia.calculerCoup());
        }
        this.afficherInformations();
    }





    public void terminerPartie(){
        System.out.println("Partie terminée");
        getInterfaceGraphique().getBordure1().setVisible(false);
        getInterfaceGraphique().getBordure2().setVisible(false);   
    }






    public Joueur getJoueur(Couleur couleur) {
        if(this.joueurs.containsKey(couleur)){
            return this.joueurs.get(couleur);
        }
        else{
            return null;
        }
    }




    public void setJoueur(Couleur couleur, Joueur joueur) {
        if(this.joueurs.containsKey(couleur)){
            this.joueurs.remove(couleur);
        }
        this.joueurs.put(couleur, joueur);
    }

    public HashMap<Couleur, Joueur> getJoueurs(){
        return this.joueurs;
    }

    public static Jeu getInstancePrincipale(){
        return Jeu.instancePrincipale;
    }
}
