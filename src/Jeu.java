import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Classe Jeu
 * Classe principale de gestion du jeu
 */
public class Jeu{

    private static Jeu instancePrincipale = new Jeu();

    private InterfaceGraphique interfaceGraphique;
    private Plateau plateau;
    private Couleur tourJoueur;
    private HashMap<Couleur, Joueur> joueurs;
    private ArrayList<EtatJeu> historiqueEtats;
    private boolean etatPartie;
    public static void main(String[] args){

        GestionnaireImages.setImage("PionBlanc", "../images/PionBlanc.png");
        GestionnaireImages.setImage("PionNoir", "../images/PionNoir.png");

    }





    public Jeu(){

        this.plateau = new Plateau(this);
        this.joueurs = new HashMap<Couleur, Joueur>();
        this.setHistoriqueEtats(new ArrayList<EtatJeu>());
        this.etatPartie = true;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Saissisez le nom du joueur 1 (Blanc) : ");
        joueurs.put(Couleur.BLANC, new IA4(Couleur.BLANC, this));
        joueurs.get(Couleur.BLANC).setNom(scanner.nextLine());
        
        System.out.println("Saissisez le nom du joueur 2 (Noir) : ");
        joueurs.put(Couleur.NOIR, new Joueur(Couleur.NOIR, this));
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
            this.interfaceGraphique.getCadreInformationBlanc().afficherMessageJouer();
            this.interfaceGraphique.getCadreInformationNoir().afficherMessageAnnuler();
        }
        else{
            this.interfaceGraphique.getCadreInformationBlanc().afficherMessageAnnuler();
            this.interfaceGraphique.getCadreInformationNoir().afficherMessageJouer();
        }

        this.interfaceGraphique.getCadreInformationBlanc().setPions(String.valueOf(Joueur.MAX_PIONS - this.joueurs.get(Couleur.BLANC).getNombrePions()));
        this.interfaceGraphique.getCadreInformationNoir().setPions(String.valueOf(Joueur.MAX_PIONS - this.joueurs.get(Couleur.NOIR).getNombrePions()));
    
        this.interfaceGraphique.getCadreInformationBlanc().setPrises(String.valueOf(this.joueurs.get(Couleur.BLANC).getPairesPrises()));
        this.interfaceGraphique.getCadreInformationNoir().setPrises(String.valueOf(this.joueurs.get(Couleur.NOIR).getPairesPrises()));
    }



    public void enregistrerEtat(){
        this.getHistoriqueEtats().add(new EtatJeu(this));
        System.out.println("enregistr\u00e9s: " + this.getHistoriqueEtats().size());
    }

    public void revenirEtatPrecedent(){
        if(this.getHistoriqueEtats().size() > 0){
            EtatJeu etatJeu = this.getHistoriqueEtats().get(this.getHistoriqueEtats().size() - 1);

            this.getPlateau().setIntersections(etatJeu.getPlateau().getIntersections());;
            this.setJoueurs(etatJeu.getJoueurs());
            this.setTourJoueur(etatJeu.getTourJoueur());
            this.setEtatPartie(etatJeu.getEtatPartie());

            this.getHistoriqueEtats().remove(this.getHistoriqueEtats().size() - 1);
            System.out.println("Coup annul\u00e9: " + this.getHistoriqueEtats().size());

            if(this.getJoueur(this.getTourJoueur()) instanceof IA){
                IA ia = (IA) this.getJoueur(this.getTourJoueur());
                ia.poserPion(ia.calculerCoup());
            }
        }
        else{
            System.out.println("Pas de coup pr\u00e9c\u00e9dent");
        }
    }




    /**
     * Effectue le coup du joueur sur le plateau
     * et passe au joueur suivant
     */
    public void mettreAJour(Position position){

        if(!this.getEtatPartie()){
            return;
        }

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
                Vérification des alignements
                de pierre
            */

            this.testerAlignement(position, couleur, -1, 0); /* Horizontale gauche */
            this.testerAlignement(position, couleur, 1, 0);  /* Horizontale droite */
            this.testerAlignement(position, couleur, 0, -1); /* Verticale haut */
            this.testerAlignement(position, couleur, 0, 1); /* Verticale bas */
            this.testerAlignement(position, couleur, -1, -1); /* Diagonale haut-gauche */
            this.testerAlignement(position, couleur, 1, -1); /* Diagonale haut-droite */
            this.testerAlignement(position, couleur, -1, 1); /* Diagonale bas-gauche */
            this.testerAlignement(position, couleur, 1, 1); /* Diagonale bas-droite */

            
            /*
                Vérification des prises
                de pierre
            */

            this.testerPrise(position, couleur, -1, 0); /* Horizontale gauche */
            this.testerPrise(position, couleur, 1, 0);  /* Horizontale droite */
            this.testerPrise(position, couleur, 0, -1); /* Verticale haut */
            this.testerPrise(position, couleur, 0, 1); /* Verticale bas */
            this.testerPrise(position, couleur, -1, -1); /* Diagonale haut-gauche */
            this.testerPrise(position, couleur, 1, -1); /* Diagonale haut-droite */
            this.testerPrise(position, couleur, -1, 1); /* Diagonale bas-gauche */
            this.testerPrise(position, couleur, 1, 1); /* Diagonale bas-droite */

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

        this.afficherInformations();

        if(this.getJoueur(this.getTourJoueur()) instanceof IA){
            IA ia = (IA) this.getJoueur(this.getTourJoueur());
            ia.poserPion(ia.calculerCoup());
        }
    }


    public void testerAlignement(Position positionInitiale, Couleur couleur,  int dirX, int dirY){

        int pierres = 0;
        for(int k=1; k<=4; k++){
            Position pos = new Position(
                positionInitiale.getX() + (k * dirX),
                positionInitiale.getY() + (k * dirY)
            );
            if(!plateau.estLibre(pos) && plateau.getIntersection(pos) != null && plateau.getIntersection(pos).equals(couleur)){
                pierres++;
            }
        }
        if(pierres >= 4){
            this.terminerPartie(couleur);
        }
    }

    public void testerPrise(Position positionInitiale, Couleur couleurPreneur, int dirX, int dirY){
        Position p1 = new Position(positionInitiale.getX() + dirX, positionInitiale.getY() + dirY);
        Couleur c1 = this.getPlateau().getIntersection(p1);
            if(c1 != null && c1 != couleurPreneur){
                /* Il y a un pion d'un adversaire */
                Position p2 = new Position(positionInitiale.getX() + 2 * dirX, positionInitiale.getY() + 2 * dirY);
                Couleur c2 = this.getPlateau().getIntersection(p2);
                if(c2 != null && c2 == c1){
                    /* Il y a deux pions du même adversaire */
                    Position p3 = new Position(positionInitiale.getX() + 3 * dirX, positionInitiale.getY() + 3 * dirY);
                    Couleur c3 = this.getPlateau().getIntersection(p3);
                    if(c3 != null && c3 == couleurPreneur){
                        /* Le joueur a capturée une paire adverse */
                        this.getPlateau().retirerPion(p1);
                        this.getPlateau().retirerPion(p2);
                        this.getJoueur(couleurPreneur).incrementerPairesPrises();

                        if(this.getJoueur(couleurPreneur).getPairesPrises() >= 5){
                            this.terminerPartie(couleurPreneur);
                        }
                    }
                }
            }
    }




    public void terminerPartie(Couleur couleur){
        System.out.println("Partie termin\u00e9e");
        this.setEtatPartie(false);
        System.out.println(couleur);
        if(couleur.equals(Couleur.NOIR)){
            getInterfaceGraphique().getCadreInformationBlanc().getLabelNomJoueur().setVisible(false);
            getInterfaceGraphique().getCadreInformationBlanc().getLabelJouerJoueur().setVisible(false);
            getInterfaceGraphique().getCadreInformationNoir().getLabelJouerJoueur().setVisible(false);
            getInterfaceGraphique().getCadreInformationBlanc().getLabelCouleurJoueur().setVisible(false);
            getInterfaceGraphique().getCadreInformationBlanc().getLabelPionsJoueur().setVisible(false);
            getInterfaceGraphique().getCadreInformationBlanc().getLabelPrisesJoueur().setVisible(false);
            getInterfaceGraphique().getCadreInformationNoir().getLabelWin().setVisible(true);
        }else{
            getInterfaceGraphique().getCadreInformationNoir().getLabelNomJoueur().setVisible(false);
            getInterfaceGraphique().getCadreInformationNoir().getLabelJouerJoueur().setVisible(false);
            getInterfaceGraphique().getCadreInformationBlanc().getLabelJouerJoueur().setVisible(false);
            getInterfaceGraphique().getCadreInformationNoir().getLabelCouleurJoueur().setVisible(false);
            getInterfaceGraphique().getCadreInformationNoir().getLabelPionsJoueur().setVisible(false);
            getInterfaceGraphique().getCadreInformationNoir().getLabelPrisesJoueur().setVisible(false);
            getInterfaceGraphique().getCadreInformationBlanc().getLabelWin().setVisible(true);
        }   
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

    public void setJoueurs(HashMap<Couleur, Joueur> joueurs){
        this.joueurs = joueurs;
    }

    public HashMap<Couleur, Joueur> getJoueurs(){
        return this.joueurs;
    }

    public void setEtatPartie(boolean bool){
        this.etatPartie = bool;
    }

    public boolean getEtatPartie(){
        return this.etatPartie;
    }

    public ArrayList<EtatJeu> getHistoriqueEtats() {
        return historiqueEtats;
    }

    public void setHistoriqueEtats(ArrayList<EtatJeu> historiqueEtats) {
        this.historiqueEtats = historiqueEtats;
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

    public static Jeu getInstancePrincipale(){
        return Jeu.instancePrincipale;
    }
}
