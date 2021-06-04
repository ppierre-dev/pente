import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public abstract class GestionnaireEcouteurs{

    private static ArrayList<Ecouteur> ecouteurs = new ArrayList<Ecouteur>();

    public static void enregistrerEcouteur(Ecouteur ecouteur){
        if(!ecouteurs.contains(ecouteur)){
            ecouteurs.add(ecouteur);
        }
    }

    public static void oublierEcouteur(Ecouteur ecouteur){
        if(ecouteurs.contains(ecouteur)){
            ecouteurs.remove(ecouteur);
        }
    }

    public static void emettre(Evenement evenement){
        ecouteurs.forEach(ecouteur -> {
            Method[] methodes = ecouteur.getClass().getMethods();
            for(int i=0; i<methodes.length; i++){

                if(methodes[i].isAnnotationPresent(EnEcoute.class)){
                    /*
                        Si la méthode est précédée de l'annotation
                        @EnEcoute
                    */

                    if(methodes[i].getParameterTypes().length == 1){
                        if(methodes[i].getParameterTypes()[0].equals(evenement.getClass())){

                            /*
                                Si la méthode prend un seul paramètre qui correspond
                                au type d'évènement émis
                                alors on appelle cette méthode en lui fournissant
                                l'évènement émis en paramètre
                            */

                            try {
                                methodes[i].invoke(ecouteur, evenement);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }

            }
        });
    }
    
}