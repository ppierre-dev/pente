import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * Classe Canvas
 * Gère le dessin graphique du jeu
 */
public class Canvas extends JPanel{

    private static int DIMENSION_CASE = 30;
    private static int DIMENSION_INTERSECTION = 6;

    /**
     * Constructeur
     */
    public Canvas(){
        this.setSize(684, 684);
        this.repaint();
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

        for(int x=0; x<18; x++){
            for(int y=0; y<18; y++){
                g2d.setColor(new Color(139, 87, 58));
                g2d.fillRect(
                    (Canvas.DIMENSION_CASE / 2) + (x * Canvas.DIMENSION_CASE) + (x * Canvas.DIMENSION_INTERSECTION),
                    (Canvas.DIMENSION_CASE / 2) + (y * Canvas.DIMENSION_CASE) + (y * Canvas.DIMENSION_INTERSECTION),
                    Canvas.DIMENSION_CASE + 2 * Canvas.DIMENSION_INTERSECTION,
                    Canvas.DIMENSION_CASE + 2 * Canvas.DIMENSION_INTERSECTION
                );

                g2d.setColor(new Color(239, 193, 135));
                g2d.fillRect(
                    (Canvas.DIMENSION_CASE / 2) + Canvas.DIMENSION_INTERSECTION + (x * Canvas.DIMENSION_CASE) + (x * Canvas.DIMENSION_INTERSECTION),
                    (Canvas.DIMENSION_CASE / 2) + Canvas.DIMENSION_INTERSECTION + (y * Canvas.DIMENSION_CASE) + (y * Canvas.DIMENSION_INTERSECTION),
                    Canvas.DIMENSION_CASE,
                    Canvas.DIMENSION_CASE
                );
            }
        }

        /*
            Pour chaque joueur de la partie
         */
        for(Couleur couleur : Jeu.getJoueurs().keySet()){
            
            /*
                Pour chacun de ses pions
            */
            for(Pion pion : Jeu.getJoueur(couleur).getPions()){
                BufferedImage image = null;
                switch(couleur){
                    case BLANC:
                        image = GestionnaireImages.getImage("PionBlanc");
                    break;

                    case NOIR:
                        image = GestionnaireImages.getImage("PionNoir");
                    break;
                }
            }

        }
    }
}
