public class EvenementClic extends Evenement {
    
    private Position position;

    public EvenementClic(Position position){
        this.position = position;
    }

    public Position getPosition(){
        return position;
    }

}
