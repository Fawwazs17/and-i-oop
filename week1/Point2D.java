public class Point2D {
    private int x;
    private int y;

    public Point2D(){
        x = 0;
        y = 0;
    }

    public Point2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "x: " + x + ", y: " + y;
    }

    //getters
    public int getX(){
        return this.x;
    }

    public int getY() {
        return y;
    }

    //setters
    public void setX(int x){
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
