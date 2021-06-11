public class BoucleDessin implements Runnable {

    private Canvas canvas;
    public BoucleDessin(Canvas canvas){
        this.setCanvas(canvas);
    }

    @Override
    public void run() {
        this.getCanvas().repaint();
    }
    
    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

}
