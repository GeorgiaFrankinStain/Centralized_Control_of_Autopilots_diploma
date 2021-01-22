package Logic.FootprintSpaceTime;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PolygonExtendedClassTest {

    @Test
    void constructorTextFormat_standart() {
        String draw =
                "1 2\n" +
                "   \n" +
                "4 3\n";
        PolygonExtended actualPolygon = new PolygonExtendedClass(draw);

        List<Point> points = new ArrayList<>();
        points.add(new PointClass(0, 0));
        points.add(new PointClass(2, 0));
        points.add(new PointClass(2, 2));
        points.add(new PointClass(0, 2));
        PolygonExtended expeactedPolygon = new PolygonExtendedClass(points);

        assertEquals(expeactedPolygon, actualPolygon);
    }
    @Test
    void constructorTextFormat_reverse() {
        String draw =
                "4 3\n" +
                "   \n" +
                "1 2\n";
        PolygonExtended actualPolygon = new PolygonExtendedClass(draw);

        List<Point> points = new ArrayList<>();
        points.add(new PointClass(0, 2));
        points.add(new PointClass(2, 2));
        points.add(new PointClass(2, 0));
        points.add(new PointClass(0, 0));
        PolygonExtended expeactedPolygon = new PolygonExtendedClass(points);

        assertEquals(expeactedPolygon, actualPolygon);
    }
    @Test
    void constructorTextFormat_origin() {
        String draw =
                "1 2\n" +
                " + \n" +
                "4 3\n";
        PolygonExtended actualPolygon = new PolygonExtendedClass(draw);

        List<Point> points = new ArrayList<>();
        points.add(new PointClass(-1, -1));
        points.add(new PointClass(1, -1));
        points.add(new PointClass(1, 1));
        points.add(new PointClass(-1, 1));
        PolygonExtended expeactedPolygon = new PolygonExtendedClass(points);

        assertEquals(expeactedPolygon, actualPolygon);
    }

    @Test
    void testClone_0Points() {
        PolygonExtended original = new PolygonExtendedClass();
        PolygonExtended clone = original.clone();
        assertEquals(original, clone);
    }
    @Test
    void testClone_1Point() {
        PolygonExtended original = new PolygonExtendedClass();
        original.addPoint(new PointClass(1, 1));
        PolygonExtended clone = original.clone();
        assertEquals(original, clone);
    }
    @Test
    void testClone_2Point() {
        PolygonExtended original = new PolygonExtendedClass();
        original.addPoint(new PointClass(1, 1));
        original.addPoint(new PointClass(2, 2));
        PolygonExtended clone = original.clone();
        assertEquals(original, clone);
    }
    @Test
    void testClone_changeOriginalAfterCloning() {
        PolygonExtended original = new PolygonExtendedClass();
        original.addPoint(new PointClass(1, 1));
        original.addPoint(new PointClass(2, 2));
        PolygonExtended clone = original.clone();

        original.addPoint(new PointClass(-1, -1));
        assertNotEquals(clone, null);
        assertNotEquals(original, clone);
    }
    @Test
    void testClone_changeItemOfOriginalAfterCloning() {
        PolygonExtended original = new PolygonExtendedClass();
        original.addPoint(new PointClass(1, 1));
        original.addPoint(new PointClass(2, 2));
        PolygonExtended clone = original.clone();

        original.deposeOn(new PointClass(10, 10));
        assertNotEquals(clone, null);
        assertNotEquals(original, clone);
    }
}