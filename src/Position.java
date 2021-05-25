/**
 * Classe Position
 * Représente une position quelconque sur le
 * plateau de jeu
 */
public class Position {
    
    private int x;
    private int y;

    /**
     * Constructeur de base
     * @param x Position en abscisse (entier)
     * @param y Position en ordonnée (entier)
     */
    public Position(int x, int y){
        this.setX(x);
        this.setY(y);
    }





    /**
     * Définir la position en abscisse
     * @return Un entier
     */
    public int getX() {
        return x;
    }

    /**
     * Définir la position en abscisse
     * Si la coordonnée n'est pas incluse dans [0, 19],
     * elle prend la valeur 0 par défaut
     * @param x Un entier inclu dans [0, 19]
     */
    public void setX(int x) {
        if(x >= 0 && x <= 19){
            this.x = x;
        }
        else{
            this.x = 0;
        }
    }

    /**
     * Obtenir la position en ordonnée
     * @return Un entier
     */
    public int getY() {
        return y;
    }

    /**
     * Définir la position en ordonnée
     * Si la coordonnée n'est pas incluse dans [0, 19],
     * elle prend la valeur 0 par défaut
     * @param y Un entier inclu dans [0, 19]
     */
    public void setY(int y) {
        if(y >= 0 && y <= 19){
            this.y = y;
        }
        else{
            this.y = 0;
        }
    }

}
