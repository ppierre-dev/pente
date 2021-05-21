import java.awt.*;
import javax.swing.*;

/**
 * Classe CadreInformation
 * Représente un cadre d'information d'un joueur
 */
public class CadreInformation extends JPanel{

    private JLabel labelNomJoueur;

    /**
     * Constructeur
     */
    public CadreInformation(){
        this.setSize(490, 320);
        //this.setLayout(null);
        JLabel nom = new JLabel("Joueur 1");
        nom.setText("Joueur 1");
        this.add(nom);
        this.setLabelNomJoueur(nom);
        //this.getLabelNomJoueur().setFont(new Font("Yu Gothic", Font.BOLD, 25));
        this.getLabelNomJoueur().setOpaque(false);
        //this.getLabelNomJoueur().setForeground(Color.BLACK);
        this.getLabelNomJoueur().setLocation(0, 50);
        //this.getLabelNomJoueur().setHorizontalAlignment(SwingConstants.LEFT);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(0, 0, 50, 100);
        this.getLabelNomJoueur().repaint();
    }

    /**
     * Obtenir le nom du joueur
     * @return Le nom
     */
    public JLabel getLabelNomJoueur() {
        return labelNomJoueur;
    }

    /**
     * Définir le nom du joueur
     * @param labelNomJoueur Le nom
     */
    public void setLabelNomJoueur(JLabel labelNomJoueur) {
        this.labelNomJoueur = labelNomJoueur;
    }

    
}
