import java.awt.*;
import javax.swing.*;

/**
 * Classe CadreInformation
 * Représente un cadre d'information d'un joueur
 */
public class CadreInformation extends JPanel{

    private JLabel labelNomJoueur;
    private JLabel labelCouleurJoueur;
    private JLabel pions;
    private JLabel prises;
    private JLabel jouer;
    private JButton annuler;

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

        this.afficherMessageJouer();

        this.setVisible(true);
    }

    public void afficherMessageJouer(){
        this.getLabelJouerJoueur().setForeground(Color.GREEN);
        this.getLabelJouerJoueur().setText("C'est \u00e0 vous de jouer !");
    }

    public void afficherMessageAnnuler(){
        this.getLabelJouerJoueur().setForeground(Color.RED);
        this.getLabelJouerJoueur().setText("Annuler via clique droit sur le plateau");
    }

    /**
     * Obtenir le label du nom du joueur
     * @return Une instance de JLabel
     */
    public JLabel getLabelNomJoueur() {
        return this.labelNomJoueur;
    }

    /**
     * Définir le label du nom du joueur
     * @param labelNomJoueur Une instance de JLabel
     */
    public void setLabelNomJoueur(JLabel labelNomJoueur) {
        this.labelNomJoueur = labelNomJoueur;
    }

    /**
     * Obtenir le label de la couleur du joueur
     * @return Une instance de JLabel
     */
    public JLabel getLabelCouleurJoueur() {
        return this.labelCouleurJoueur;
    }

    /**
     * Définir le label de la couleur du joueur
     * @param labelCouleurJoueur Une instance de JLabel
     */
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

    public JButton getButtonAnnuler(){
        return this.annuler;
    }

    public void setButtonAnnuler(JButton annuler){
        this.annuler = annuler;
    }

    public String getNom(){
        return this.getLabelNomJoueur().getText();
    }

    public void setNom(String nom){
        this.getLabelNomJoueur().setText(nom);
    }

    public void setCouleur(Couleur couleur){
        this.getLabelCouleurJoueur().setText("Equipe " + couleur.toString());
    }

    public void setPions(String pions){
        this.getLabelPionsJoueur().setText(pions + " pion(s) restant(s)");
    }

    public String getPions(){
        return this.getLabelPionsJoueur().getText();
    }

    public void setPrises(String prises){
        this.getLabelPrisesJoueur().setText(prises + " prise(s) effectuée(s)");
    }

    public String getPrises(){
        return this.getLabelPrisesJoueur().getText();
    }

}
