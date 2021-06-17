import javax.swing.*;
import java.awt.*;

/**
 * Classe BordureInterface
 * @author Lucas DOUTRELUIGNE
 * Il s'agit uniquement d'un cosmétique d'interface
 */
public class BordureInterface extends JPanel{
    /**
     * Méthode de rafraîchissement d'affichage
     */
    @Override
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
