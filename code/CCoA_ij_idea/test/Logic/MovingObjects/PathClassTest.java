package Logic.MovingObjects;

import Logic.FootprintSpaceTime.Point;
import Logic.FootprintSpaceTime.PointClass;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PathClassTest {

    @Test
    void getSize_constructor1() {
        List<Point> points = new ArrayList<>();
        points.add(new PointClass(0, 0));
        points.add(new PointClass(1, 1));

        Path path = new PathClass(points);
        Point expected = new PointClass(0, 0);
        assertEquals(expected, path.getPoint(0));
    }

    @Test
    void getSize_constructor2() {
        List<Point> points = new ArrayList<>();
        points.add(new PointClass(0, 0));
        points.add(new PointClass(1, 1));

        Path path = new PathClass(points);
        Point expected = new PointClass(1, 1);
        assertEquals(expected, path.getPoint(1));
    }

    @Test
    void getPoint_1() {
        Path path = new PathClass();
        path.addPoint(new PointClass(1, 1));
        Point expected = new PointClass(1, 1);
        assertEquals(expected, path.getPoint(0));
    }

    @Test
    void getPoint_protectionFromEditingSourceListPoints() {
        List<Point> points = new ArrayList<>();
        points.add(new PointClass(0, 0));
        points.add(new PointClass(1, 1));

        Path path = new PathClass(points);

        points.remove(1);

        Point expected = new PointClass(1, 1);
        assertEquals(expected, path.getPoint(1));
    }

    @Test
    void getPoint_protectionFromEditingSourcePoint() {
        Path path = new PathClass();
        Point sourcePoint = new PointClass(1, 1);
        path.addPoint(sourcePoint);
        sourcePoint.setX(13);
        sourcePoint.setY(13);
        Point expected = new PointClass(1, 1);
        assertEquals(expected, path.getPoint(0));
    }

    @Test
    void addPoint() {
        Path path = new PathClass();
        path.addPoint(new PointClass(1, 1));
        Point expected = new PointClass(1, 1);
        assertEquals(expected, path.getPoint(0));
    }

    @Test
    void deposeOn_1st() {
        Path path = new PathClass();
        path.addPoint(new PointClass(0, 0));
        path.addPoint(new PointClass(1, 1));
        Point vector = new PointClass(1, 1);
        path.deposeOn(vector);

        assertEquals(new PointClass(1, 1), path.getPoint(0));
    }

    @Test
    void deposeOn_2nd() {
        Path path = new PathClass();
        path.addPoint(new PointClass(0, 0));
        path.addPoint(new PointClass(1, 1));
        Point vector = new PointClass(1, 1);
        path.deposeOn(vector);

        assertEquals(new PointClass(2, 2), path.getPoint(1));
    }

    @Test
    void testEquals_empty() {
        Path first = new PathClass();
        Path second = new PathClass();

        assertEquals(first, second);
    }

    @Test
    void testEquals_1And1() {
        Path first = new PathClass();
        first.addPoint(new PointClass(1, 1));
        Path second = new PathClass();
        second.addPoint(new PointClass(1, 1));

        assertEquals(first, second);
    }

    @Test
    void testEquals_notEquals_order() {
        Path first = new PathClass();
        first.addPoint(new PointClass(0, 0));
        first.addPoint(new PointClass(1, 1));

        Path second = new PathClass();
        second.addPoint(new PointClass(0, 0));
        second.addPoint(new PointClass(1, 1));

        assertEquals(first, second);
    }

    @Test
    void testEquals_notEquals_0And1() {
        Path first = new PathClass();
        first.addPoint(new PointClass(0, 0));
        Path second = new PathClass();
        second.addPoint(new PointClass(1, 1));

        assertNotEquals(first, second);
    }

    @Test
    void testEquals_reverseOrder() {
        Path first = new PathClass();
        first.addPoint(new PointClass(0, 0));
        first.addPoint(new PointClass(1, 1));

        Path second = new PathClass();
        second.addPoint(new PointClass(1, 1));
        second.addPoint(new PointClass(0, 0));

        assertNotEquals(first, second);
    }

    @Test
    void testEquals_notEquals_emptyAndOnePoint() {
        Path first = new PathClass();
        Path second = new PathClass();
        second.addPoint(new PointClass(1, 1));

        assertNotEquals(first, second);
    }

    @Test
    void testEquals_yourself() {
        Path first = new PathClass();

        assertEquals(first, first);
    }

    @Test
    void testToString_0Points() {
        Path first = new PathClass();
        String actual = first.toString();
        String expected = "";

        assertEquals(expected, actual);
    }

    @Test
    void testToString_1() {
        Path first = new PathClass();
        first.addPoint(new PointClass(1, 1));

        String actual = first.toString();
        String expected = "(x: 1.0   y: 1.0) ";

        assertEquals(expected, actual);
    }

    @Test
    void testToString_2Points() {
        Path first = new PathClass();
        first.addPoint(new PointClass(1, 1));
        first.addPoint(new PointClass(2, 2));

        String actual = first.toString();
        String expected = "(x: 1.0   y: 1.0) (x: 2.0   y: 2.0) ";

        assertEquals(expected, actual);
    }
}