public class BoucleDessin implements Runnable {

    public static int COMPTEUR = 0;

    private Canvas canvas;
    public BoucleDessin(Canvas canvas){
        this.setCanvas(canvas);
    }

    @Override
    public void run() {
        this.getCanvas().repaint();
        BoucleDessin.COMPTEUR++;
    }
    
    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

}
