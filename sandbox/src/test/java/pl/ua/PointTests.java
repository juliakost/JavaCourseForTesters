package pl.ua;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testPoint() {
    Point p = new Point(0, 5);
    Assert.assertEquals(p.distance(new Point(0, 13)), 8.0);
  }

  @Test
  public void testPoint1() {
    Point p = new Point(0, 0);
    Assert.assertEquals(p.distance(new Point(0, 0)), 0.0);
  }

  @Test
  public void testPoint2() {
    Point p = new Point(-5, 0);
    Assert.assertEquals(p.distance(new Point(-13, 0)), 7.0);
  }
}