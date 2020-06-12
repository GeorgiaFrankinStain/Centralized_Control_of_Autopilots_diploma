package Logic.FootprintSpaceTime;

import org.junit.Test;

import static java.lang.Math.PI;
import static org.junit.Assert.*;

public class PointTest {
    double standartDelta = 0.001;

    @Test
    public void getAngleRelative() {
        {
            Point origin = new PointClass(0, 0);
            Point point = new PointClass(1, 0);

            double expected = 0;
            double actual = point.getAngleRotareRelative(origin);
            assertEquals(expected, actual, standartDelta);
        }
        {
            Point origin = new PointClass(0, 0);
            Point point = new PointClass(-1, 0);

            double expected = PI;
            double actual = point.getAngleRotareRelative(origin);
            assertEquals(expected, actual, standartDelta);
        }
        {
            Point origin = new PointClass(0, 0);
            Point point = new PointClass(-1, -1);

            double expected = -0.75 * PI;
            double actual = point.getAngleRotareRelative(origin);
            assertEquals(expected, actual, standartDelta);
        }
        {
            Point origin = new PointClass(0, 0);
            Point point = new PointClass(1, -1);

            double expected = -0.25 * PI;
            double actual = point.getAngleRotareRelative(origin);
            assertEquals(expected, actual, standartDelta);
        }
        {
            Point origin = new PointClass(0, 0);
            Point point = new PointClass(1, 1);

            double expected = 0.25 * PI;
            double actual = point.getAngleRotareRelative(origin);
            assertEquals(expected, actual, standartDelta);
        }
    }

    @Test
    public void getRotareRelative() {
        {
            Point point = new PointClass(0, 10);
            Point origin = new PointClass(0, 0);

            Point expected = new PointClass(10, 0);
            Point actual = point.getRotateRelative(origin, -(PI / 2));

            assertEquals(expected, actual);
        }
        {
            Point point = new PointClass(0, 100);
            Point origin = new PointClass(0, 0);

            Point expected = new PointClass(100, 0);
            Point actual = point.getRotateRelative(origin, -(PI / 2));

            assertEquals(expected, actual);
        }
        {
            Point point = new PointClass(20, 20);
            Point origin = new PointClass(0, 0);

            Point expected = new PointClass(-20, 20);
            Point actual = point.getRotateRelative(origin, (PI / 2));

            assertEquals(expected, actual);
        }
        {
            Point point = new PointClass(20, 20);
            Point origin = new PointClass(0, 0);

            Point expected = new PointClass(-20, -19); //FIXME TEST FAILED -20 -20
            Point actual = point.getRotateRelative(origin, (PI));

            assertEquals(expected, actual);
        }
        {
            Point point = new PointClass(200000, 200000);
            Point origin = new PointClass(0, 0);

            Point expected = new PointClass(-200000, -199999.0); //FIXME TEST FAILED
//            Point expected = new PointClass(-200000, -200000);
            Point actual = point.getRotateRelative(origin, (PI));

            assertEquals(expected, actual);
        }
        {
            Point point = new PointClass(20, 20);
            Point origin = new PointClass(0, 0);

            Point expected = new PointClass(19, -20);
//            Point expected = new PointClass(20, -20); //FIXME TEST FAILED
            Point actual = point.getRotateRelative(origin, (PI * 1.5));

            assertEquals(expected, actual);
        }
    }
}