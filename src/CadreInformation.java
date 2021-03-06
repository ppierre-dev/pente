import java.awt.*;
import javax.swing.*;

/**
 * Classe CadreInformation
 * @author Lucas DOUTRELUIGNE, Jérémy Catez
 * Représente un cadre d'information d'un joueur
 */
public class CadreInformation extends JPanel{

    private JLabel labelNomJoueur;
    private JLabel labelCouleurJoueur;
    private JLabel pions;
    private JLabel prises;
    private JLabel jouer;
    private JLabel win;

    /**
     * Constructeur
     */
    public CadreInformation(){
        this.setSize(490, 320);
        this.setLayout(null);
        JLabel nom = new JLabel();
        this.setLabelNomJoueur(nom);
        this.add(this.getLabelNomJoueur());
        this.getLabelNomJoueur().setSize(500, 50);
        this.getLabelNomJoueur().setLocation(0, 0);
        this.getLabelNomJoueur().validate();
        this.getLabelNomJoueur().repaint();
        this.getLabelNomJoueur().setFont(new Font("Yu Gothic", Font.BOLD, 25));
        this.getLabelNomJoueur().setHorizontalAlignment(SwingConstants.CENTER);


        JLabel couleur = new JLabel();
        this.setLabelCouleurJoueur(couleur);
        this.add(this.getLabelCouleurJoueur());
        this.getLabelCouleurJoueur().setSize(500, 50);
        this.getLabelCouleurJoueur().setLocation(0, 40);
        this.getLabelCouleurJoueur().validate();
        this.getLabelCouleurJoueur().repaint();
        this.getLabelCouleurJoueur().setFont(new Font("Yu Gothic", Font.BOLD, 18));
        this.getLabelCouleurJoueur().setForeground(Color.GRAY);

        JLabel pions = new JLabel();
        this.setLabelPionsJoueur(pions);
        this.add(this.getLabelPionsJoueur());
        this.getLabelPionsJoueur().setSize(500, 50);
        this.getLabelPionsJoueur().setLocation(0, 80);
        this.getLabelPionsJoueur().validate();
        this.getLabelPionsJoueur().repaint();
        this.getLabelPionsJoueur().setFont(new Font("Yu Gothic", Font.BOLD, 18));
        this.getLabelPionsJoueur().setForeground(Color.GRAY);

        JLabel prises = new JLabel();
        this.setLabelPrisesJoueur(prises);
        this.add(this.getLabelPrisesJoueur());
        this.getLabelPrisesJoueur().setSize(500, 50);
        this.getLabelPrisesJoueur().setLocation(0, 120);
        this.getLabelPrisesJoueur().validate();
        this.getLabelPrisesJoueur().repaint();
        this.getLabelPrisesJoueur().setFont(new Font("Yu Gothic", Font.BOLD, 18));
        this.getLabelPrisesJoueur().setForeground(Color.GRAY);

        JLabel jouer = new JLabel();
        this.setLabelJouerJoueur(jouer);
        this.add(this.getLabelJouerJoueur());
        this.getLabelJouerJoueur().setSize(500, 50);
        this.getLabelJouerJoueur().setLocation(0, 280);
        this.getLabelJouerJoueur().validate();
        this.getLabelJouerJoueur().repaint();
        this.getLabelJouerJoueur().setFont(new Font("Yu Gothic", Font.BOLD, 18));
        this.getLabelJouerJoueur().setHorizontalAlignment(SwingConstants.CENTER);
        this.getLabelJouerJoueur().setVisible(true);


        JLabel win = new JLabel();
        this.setLabelWin(win);
        this.add(this.getLabelWin());
        this.getLabelWin().setSize(500, 50);
        this.getLabelWin().setLocation(0, 280);
        this.getLabelWin().validate();
        this.getLabelWin().repaint();
        this.getLabelWin().setFont(new Font("Yu Gothic", Font.BOLD, 18));
        this.getLabelWin().setHorizontalAlignment(SwingConstants.CENTER);
        this.getLabelWin().setVisible(false);


        this.afficherMessageJouer();
        this.afficherMessageWin();

        this.setVisible(true);
    }

    /*
        Méthodes pour modifier les messages
        affichés
    */

    public void afficherMessageJouer(){
        this.getLabelJouerJoueur().setForeground(Color.GREEN);
        this.getLabelJouerJoueur().setText("C'est \u00e0 vous de jouer !");
    }

    public void afficherMessageAnnuler(){
        this.getLabelJouerJoueur().setForeground(Color.RED);
        this.getLabelJouerJoueur().setText("Annuler via clique droit sur le plateau");
    }

    public void afficherMessageWin(){
        this.getLabelWin().setForeground(Color.RED);
        this.getLabelWin().setText("VAINQUEUR");
    }

    public void setPrises(String prises){
        this.getLabelPrisesJoueur().setText(prises + " prise(s) effectu\u00e9e(s)");
    }

    public void setCouleur(Couleur couleur){
        this.getLabelCouleurJoueur().setText("Equipe " + couleur.toString());
    }

    public void setPions(String pions){
        this.getLabelPionsJoueur().setText(pions + " pion(s) restant(s)");
    }


    


    public JLabel getLabelNomJoueur() {
        return this.labelNomJoueur;
    }

    public void setLabelNomJoueur(JLabel labelNomJoueur) {
        this.labelNomJoueur = labelNomJoueur;
    }

    public JLabel getLabelCouleurJoueur() {
        return this.labelCouleurJoueur;
    }

    public void setLabelCouleurJoueur(JLabel labelCouleurJoueur) {
        this.labelCouleurJoueur = labelCouleurJoueur;
    }

    public void setLabelPionsJoueur(JLabel pions) {
        this.pions = pions;
    }

    public JLabel getLabelPionsJoueur() {
        return this.pions;
    }

    public void setLabelPrisesJoueur(JLabel prises) {
        this.prises = prises;
    }

    public JLabel getLabelPrisesJoueur() {
        return this.prises;
    }

    public void setLabelJouerJoueur(JLabel jouer) {
        this.jouer = jouer;
    }

    public JLabel getLabelJouerJoueur() {
        return this.jouer;
    }

    public String getNom(){
        return this.getLabelNomJoueur().getText();
    }

    public void setNom(String nom){
        this.getLabelNomJoueur().setText(nom);
    }

    public String getPions(){
        return this.getLabelPionsJoueur().getText();
    }

    public String getPrises(){
        return this.getLabelPrisesJoueur().getText();
    }

    public void setLabelWin(JLabel win) {
        this.win = win;
    }

    public JLabel getLabelWin() {
        return this.win;
    }

}
