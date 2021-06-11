import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.SwingUtilities;

/**
 * Classe Jeu
 * Classe principale de gestion du jeu
 */
public class Jeu{

    private static Jeu instancePrincipale = new Jeu();

    private InterfaceGraphique interfaceGraphique;
    private Plateau plateau;
    private Couleur tourJoueur;
    private BoucleDessin boucleDessin;
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
        Boolean continuer = false;
        while(!continuer) {
            System.out.println("Tapez :");
            System.out.println("1 --> IA VS IA");
            System.out.println("2 --> Joueur VS Joueur");
            System.out.println("3 --> Joueur VS IA");
            String reponse = scanner.nextLine();
            if (reponse.equals("1")) {
                joueurs.put(Couleur.NOIR, new IAWin(Couleur.NOIR, this));
                joueurs.get(Couleur.NOIR).setNom("IA 2");

                joueurs.put(Couleur.BLANC, new IAWin(Couleur.BLANC, this));
                joueurs.get(Couleur.BLANC).setNom("IA 1");
                continuer = true;
            } else if (reponse.equals("2")) {
                System.out.println("Saissisez le nom du joueur 1 (Blanc) : ");
                joueurs.put(Couleur.BLANC, new Joueur(Couleur.BLANC, this));
                joueurs.get(Couleur.BLANC).setNom(scanner.nextLine());

                System.out.println("Saissisez le nom du joueur 2 (Noir) : ");
                joueurs.put(Couleur.NOIR, new Joueur(Couleur.NOIR, this));
                joueurs.get(Couleur.NOIR).setNom(scanner.nextLine());

                continuer = true;
            } else if (reponse.equals("3")) {
                System.out.println("Saissisez le nom du joueur 1 (Blanc) : ");
                joueurs.put(Couleur.BLANC, new Joueur(Couleur.BLANC, this));
                joueurs.get(Couleur.BLANC).setNom(scanner.nextLine());

                joueurs.put(Couleur.NOIR, new IAWin(Couleur.NOIR, this));
                joueurs.get(Couleur.NOIR).setNom("IA");
                continuer = true;
            } else {
                System.out.println("Mauvaise saisie, veuillez recommencer");
            }
        }

        double random = Math.random();
        if(random < 0.5){
            this.setTourJoueur(Couleur.BLANC);
        }
        else{
            this.setTourJoueur(Couleur.NOIR);
        }

        this.setInterfaceGraphique(new InterfaceGraphique(this));
        this.getInterfaceGraphique().setVisible(true);

        this.setBoucleDessin(new BoucleDessin(this.getInterfaceGraphique().getCanvas()));

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

        this.getBoucleDessin().run();

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
    }

    public void revenirEtatPrecedent(){
        if(this.getHistoriqueEtats().size() > 0 && this.getEtatPartie()){
            EtatJeu etatJeu = this.getHistoriqueEtats().get(this.getHistoriqueEtats().size() - 1);

            this.getPlateau().setIntersections(etatJeu.getPlateau().getIntersections());;
            this.setJoueurs(etatJeu.getJoueurs());
            this.setTourJoueur(etatJeu.getTourJoueur());
            this.setEtatPartie(etatJeu.getEtatPartie());

            this.getHistoriqueEtats().remove(this.getHistoriqueEtats().size() - 1);
            System.out.println("Coup annul\u00e9: " + this.getHistoriqueEtats().size());

            if(this.getJoueur(this.getTourJoueur()) instanceof IA){
                Jeu that = this;
                Thread threadIA = new Thread(){
                    @Override
                    public void run(){

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        IA ia = (IA) that.getJoueur(that.getTourJoueur());
                        ia.poserPion(ia.calculerCoup());
                    }
                };
                threadIA.start();
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

        if(this.getJoueur(this.getTourJoueur()).getNombrePions() >= 60){
            if(this.getEtatPartie() == true){
                /*
                    Dans le cas où le joueur devant jouer
                    n'a plus de pions, il perd
                */
                if(this.getTourJoueur().equals(Couleur.BLANC)){
                    this.terminerPartie(Couleur.NOIR);
                }
                else{
                    this.terminerPartie(Couleur.BLANC);
                }
            }
        }

        this.afficherInformations();

        if(this.getJoueur(this.getTourJoueur()) instanceof IA){

            Jeu that = this;
            Thread threadIA = new Thread(){
                @Override
                public void run(){

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    IA ia = (IA) that.getJoueur(that.getTourJoueur());
                    ia.poserPion(ia.calculerCoup());
                }
            };
            threadIA.start();
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

    public BoucleDessin getBoucleDessin() {
        return boucleDessin;
    }

    public void setBoucleDessin(BoucleDessin boucleDessin) {
        this.boucleDessin = boucleDessin;
    }

}
