package pl.ua;

public class Task2Distance {
  public static void main(String[] args) {

    Point p1 = new Point(8, 2);
    Point p2 = new Point(2, 0);

    System.out.println("The distance between points is: " + p1.distance(p2));
  }
}

//  public static double distance(Point p1, Point p2) {
//    return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
//  }