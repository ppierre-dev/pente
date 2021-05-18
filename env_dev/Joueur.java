package fr.groupe1tpd;

import java.util.ArrayList;

public class Joueur {
    
    private ArrayList<Pion> pions;

    /**
     * Constructeur par défaut
     */
    public Joueur(){
        this.pions = new ArrayList<Pion>();
    }

    /**
     * Obtenir la liste des pions du joueur
     */
    public ArrayList<Pion> getPions(){
        return this.pions;
    }

    /**
     * Obtenir le nombre de pions du joueur
     * @return
     */
    public int getNombrePions(){
        return this.pions.size();
    }

    /**
     * Définir la liste des pions du joueur
     * @param pions
     */
    public void setPions(ArrayList<Pion> pions){
        this.pions = pions;
    }

}
