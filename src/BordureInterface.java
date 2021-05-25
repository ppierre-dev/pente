import javax.swing.*;
import java.awt.*;

public class BordureInterface extends JPanel{
    @Override
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
