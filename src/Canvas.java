import javax.swing.*;
import java.awt.*;
/**
 * Classe Canvas
 * Gère le dessin graphique du jeu
 */
public class Canvas extends JPanel{

    /**
     * Constructeur
     */
    public Canvas(){
        this.setSize(690, 690);
    }

    /**
     * Dessiner les éléments du jeu
     */
    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;

        g2d.clearRect(0, 0, this.getWidth(), this.getHeight());

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
