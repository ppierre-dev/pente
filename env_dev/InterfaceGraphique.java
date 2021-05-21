import javax.swing.*;
import java.awt.*;

/**
 * Classe InterfaceGraphique
 * Représente l'interface graphique du jeu
 * par laquelle toutes les interactions entre
 * le joueur et le jeu passeront
 */
public class InterfaceGraphique extends JFrame{

    private Canvas canvas;
    
    /**
     * Constructeur
     */
    public InterfaceGraphique(){
        this.setTitle("Jeu de pente");
        this.setSize(new Dimension(1300, 800));
        this.setLayout(null);
        this.setResizable(false);

        Canvas canvas = new Canvas();
        canvas.setSize(690, 690);
        canvas.setLocation(30, 30);

        this.setCanvas(canvas);
        this.add(this.getCanvas());

        BordureInterface bordure1 = new BordureInterface();
        bordure1.setSize(1, 690);
        bordure1.setLocation(750, 30);
        bordure1.repaint();
        this.add(bordure1);

        BordureInterface bordure2 = new BordureInterface();
        bordure2.setSize(490, 1);
        bordure2.setLocation(780, 370);
        bordure2.repaint();
        this.add(bordure2);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Obtenir le canvas de l'interface graphique
     * @param canvas
     */
    public void setCanvas(Canvas canvas){
        this.canvas = canvas;
    }

    /**
     * Définir le canvas de l'interface graphique
     * @return
     */
    public Canvas getCanvas(){
        return this.canvas;
    }
}
