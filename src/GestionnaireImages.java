import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Classe GestionnaireImages
 * @author Lucas DOUTRELUIGNE
 * Gère les images nécessaires au bon fonctionnement du jeu
 */
public abstract class GestionnaireImages {
    
    private static HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();

    public static HashMap<String, BufferedImage> getImages(){
        return images;
    }

    public static BufferedImage getImage(String name){
        if(images.containsKey(name)){
            return images.get(name);
        }
        else{
            return null;
        }
    }

    public static void setImage(String name, BufferedImage image){
        images.putIfAbsent(name, image);
    }

    public static void setImage(String name, String url){
        try{
            BufferedImage image = ImageIO.read(new File(url));
            GestionnaireImages.setImage(name, image);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
