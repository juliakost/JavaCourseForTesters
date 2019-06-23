package pl.ua;

public class Point {

  private double x, y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance( Point point2) {
    return Math.sqrt((this.x - point2.x) * (this.x - point2.x) + (this.y - point2.y) * (this.y - point2.y));
  }
}