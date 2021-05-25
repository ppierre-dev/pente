import java.awt.*;
import javax.swing.*;

/**
 * Classe CadreInformation
 * Représente un cadre d'information d'un joueur
 */
public class CadreInformation extends JPanel{

    private JLabel labelNomJoueur;
    private JLabel labelCouleurJoueur;

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
        this.getLabelCouleurJoueur().setLocation(0, 30);
        this.getLabelCouleurJoueur().validate();
        this.getLabelCouleurJoueur().repaint();
        this.getLabelCouleurJoueur().setFont(new Font("Yu Gothic", Font.BOLD, 15));

        this.setVisible(true);
    }

    /**
     * Obtenir le label du nom du joueur
     * @return Une instance de JLabel
     */
    private JLabel getLabelNomJoueur() {
        return this.labelNomJoueur;
    }

    /**
     * Définir le label du nom du joueur
     * @param labelNomJoueur Une instance de JLabel
     */
    private void setLabelNomJoueur(JLabel labelNomJoueur) {
        this.labelNomJoueur = labelNomJoueur;
    }

    /**
     * Obtenir le label de la couleur du joueur
     * @return Une instance de JLabel
     */
    private JLabel getLabelCouleurJoueur() {
        return this.labelCouleurJoueur;
    }

    /**
     * Définir le label de la couleur du joueur
     * @param labelCouleurJoueur Une instance de JLabel
     */
    private void setLabelCouleurJoueur(JLabel labelCouleurJoueur) {
        this.labelCouleurJoueur = labelCouleurJoueur;
    }

    public String getNom(){
        return this.getLabelNomJoueur().getText();
    }

    public void setNom(String nom){
        this.getLabelNomJoueur().setText(nom);
    }

    
}
