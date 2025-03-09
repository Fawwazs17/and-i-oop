//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class PointApp {
    public static void main(String[] args) {

        Point2D point = new Point2D();
        Point2D point2 = new Point2D(5,10);

        System.out.println("Point [before]: " + point);
        point.setX(15);
        point.setY(20);
        System.out.println("Point: " + point);
        System.out.println("Point2: " + point2);
        System.out.println("Adding y from Point1 and Point2: " + (point.getY() + point2.getY()));
    }
}