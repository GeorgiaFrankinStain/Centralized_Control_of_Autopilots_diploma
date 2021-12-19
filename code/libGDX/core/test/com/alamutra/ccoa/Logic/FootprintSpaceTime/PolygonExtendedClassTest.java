package com.alamutra.ccoa.Logic.FootprintSpaceTime;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
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


    @Test
    void getCountPoints_fori() {
        for (int i = 0; i < 14; i++) {
            PolygonExtended polygon = new PolygonExtendedClass();
            for (int j = 0; j < i; j++) {
                polygon.addPoint(new PointClass(i, j));
            }
            assertTrue(polygon.getCountPoints() == i);
        }
    }

    @Test
    void getCountPoints_equalsSizeListPoints() {
        PolygonExtended polygon = new PolygonExtendedClass();
        List<Point> points = Arrays.asList(new Point[]{
                new PointClass(0, 0),
                new PointClass(0, 0),
                new PointClass(0, 0),
                new PointClass(0, 0),
                new PointClass(0, 0)
        });
        polygon.addAllPoint(points);
        assertTrue(polygon.getCountPoints() == points.size());

    }

    @Test
    void getPoint_equalsPointsOneObject() {
        PolygonExtended polygon = new PolygonExtendedClass();
        Point point = new PointClass(0, 0);
        polygon.addPoint(point);
        assertEquals(polygon.getPoint(0), point);
    }

    @Test
    void getPoint_equalsPointsDifferentObjects() {
        PolygonExtended polygon = new PolygonExtendedClass();
        polygon.addPoint(new PointClass(0, 0));
        assertEquals(polygon.getPoint(0), new PointClass(0, 0));
    }

    @Test
    void getPoint_editingThePassedPointsNotChangeThePolygon() {
        PolygonExtended polygon = new PolygonExtendedClass();
        Point point = new PointClass(0, 0);
        polygon.addPoint(point);
        point.setX(17);
        assertNotEquals(polygon.getPoint(0), point);
    }

    private PolygonExtended polygonWithOnePoint = createPolygonWithOnePoint();

    private PolygonExtended createPolygonWithOnePoint() {
        PolygonExtended polygon = new PolygonExtendedClass();
        polygon.addPoint(new PointClass(0, 0));
        return polygon;
    }

    @Test
    void addPoint_polygonWithOnePointGet0Point() {
        assertEquals(polygonWithOnePoint.getPoint(0), new PointClass(0, 0));
    }

    @Test
    void addPoint_polygonWithOnePointGetCountPoints() {
        assertEquals(polygonWithOnePoint.getCountPoints(), 1);
    }

    private PolygonExtended polygon5Points = createPolygonWith5Points();

    private PolygonExtended createPolygonWith5Points() {

        PolygonExtended polygon = new PolygonExtendedClass();
        List<Point> points = Arrays.asList(new Point[]{
                new PointClass(0, 0),
                new PointClass(1, 1),
                new PointClass(2, 2),
                new PointClass(3, 3),
                new PointClass(4, 4)
        });
        polygon.addAllPoint(points);
        return polygon;
    }

    @Test
    void addAllPoint_polygonWith5PointsGetCountPoints() {
        assertTrue(polygon5Points.getCountPoints() == 5);
    }

    @Test
    void addAllPoint_polygonWith5Points0Point() {
        assertEquals(polygon5Points.getPoint(0), new PointClass(0, 0));
    }

    @Test
    void addAllPoint_polygonWith5Points1Point() {
        assertEquals(polygon5Points.getPoint(1), new PointClass(1, 1));
    }

    @Test
    void addAllPoint_polygonWith5Points2Point() {
        assertEquals(polygon5Points.getPoint(2), new PointClass(2, 2));
    }

    @Test
    void addAllPoint_polygonWith5Points3Point() {
        assertEquals(polygon5Points.getPoint(3), new PointClass(3, 3));
    }

    @Test
    void addAllPoint_polygonWith5Points4Point() {
        assertEquals(polygon5Points.getPoint(4), new PointClass(4, 4));
    }

    @Test
    void insertPoint_inEmptyPolygonInStartMultipleTimes() {
        PolygonExtended polygon = new PolygonExtendedClass();
        polygon.insertPoint(0, new PointClass(0, 0));
        assertEquals(polygon.getPoint(0), new PointClass(0, 0));
        polygon.insertPoint(0, new PointClass(1, 1));
        assertEquals(polygon.getPoint(0), new PointClass(1, 1));
    }

    @Test
    void setPoint_fori() {
        PolygonExtended polygon5Points = createPolygonWith5Points();
        for (int i = 0; i < 5; i++) {
            polygon5Points.setPoint(i, new PointClass(17, 17));
            assertEquals(polygon5Points.getPoint(i), new PointClass(17, 17));
        }
    }

    @Test
    void setPoint_goingBeyondTheArrayBoundaries() {
        boolean isThrowException = false;
        try {
            PolygonExtended polygon5Points = new PolygonExtendedClass();
            polygon5Points.setPoint(17, new PointClass(17, 17));
        } catch (IndexOutOfBoundsException e) {
            isThrowException = true;
        }
        assertTrue(isThrowException);
    }

    @Test
    void rotateRelative_rotationAroundTheCenter360Degree() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(17, 17));
        square.addPoint(new PointClass(-17, 17));
        square.addPoint(new PointClass(-17, -17));
        square.addPoint(new PointClass(17, -17));

        PolygonExtended expected = square.clone();

        Point origin = new PointClass(0, 0);
        square.rotateRelative(origin, Math.PI * 2);
        assertEquals(expected, square);
    }

    @Test
    void rotateRelative_rotationAroundTheCenter180Degree() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(17, 17));
        square.addPoint(new PointClass(-17, 17));
        square.addPoint(new PointClass(-17, -17));
        square.addPoint(new PointClass(17, -17));

        Point origin = new PointClass(0, 0);
        square.rotateRelative(origin, Math.PI);

        PolygonExtended expected = new PolygonExtendedClass();
        expected.addPoint(new PointClass(-17, -17));
        expected.addPoint(new PointClass(17, -17));
        expected.addPoint(new PointClass(17, 17));
        expected.addPoint(new PointClass(-17, 17));

        assertEquals(expected, square);
    }

    /*
                  Y
                  ^
         2      17|     1
                  |
                  +------> X
                        17

         3              4


           after rotate degree 90


                  Y
                  ^
         1      17|     4
                  |
                  +------> X
                        17

         2              3
     */
    @Test
    void rotateRelative_rotationAroundTheCenter90Degree() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(17, 17));
        square.addPoint(new PointClass(-17, 17));
        square.addPoint(new PointClass(-17, -17));
        square.addPoint(new PointClass(17, -17));

        Point origin = new PointClass(0, 0);
        square.rotateRelative(origin, Math.PI / 2);

        PolygonExtended expected = new PolygonExtendedClass();
        expected.addPoint(new PointClass(-17, 17));
        expected.addPoint(new PointClass(-17, -17));
        expected.addPoint(new PointClass(17, -17));
        expected.addPoint(new PointClass(17, 17));

        assertEquals(expected, square);
    }


    /*
                          Y     4     3
                          ^
                        20|     1     2
                          |
                          +------> X
                                20

           after rotate degree 90

             3      2     Y
                          ^
             4      1   20|
                          |
                          +------> X
                                20
     */

    @Test
    void rotateRelative_rotationAroundTheCenterOnDistance90Degree() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(40, 20));
        square.addPoint(new PointClass(40, 40));
        square.addPoint(new PointClass(20, 40));

        Point origin = new PointClass(0, 0);
        square.rotateRelative(origin, Math.PI / 2);

        PolygonExtended expected = new PolygonExtendedClass();
        expected.addPoint(new PointClass(-20, 20));
        expected.addPoint(new PointClass(-20, 40));
        expected.addPoint(new PointClass(-40, 40));
        expected.addPoint(new PointClass(-40, 20));

        assertEquals(expected, square);
    }

    @Test
    void deposeOn_0Vector() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(17, 17));
        square.addPoint(new PointClass(-17, 17));
        square.addPoint(new PointClass(-17, -17));
        square.addPoint(new PointClass(17, -17));

        PolygonExtended expected = square.clone();

        Point vector = new PointClass(0, 0);
        square.deposeOn(vector);
        assertEquals(expected, square);
    }

    @Test
    void deposeOn_topRight() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(17, 17));
        square.addPoint(new PointClass(-17, 17));
        square.addPoint(new PointClass(-17, -17));
        square.addPoint(new PointClass(17, -17));


        PolygonExtended expected = new PolygonExtendedClass();
        expected.addPoint(new PointClass(27, 27));
        expected.addPoint(new PointClass(-7, 27));
        expected.addPoint(new PointClass(-7, -7));
        expected.addPoint(new PointClass(27, -7));

        Point vector = new PointClass(10, 10);
        square.deposeOn(vector);
        assertEquals(expected, square);
    }

    @Test
    void deposeOn_top() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(-20, 20));
        square.addPoint(new PointClass(-20, -20));
        square.addPoint(new PointClass(20, -20));


        PolygonExtended expected = new PolygonExtendedClass();
        expected.addPoint(new PointClass(20, 30));
        expected.addPoint(new PointClass(-20, 30));
        expected.addPoint(new PointClass(-20, -10));
        expected.addPoint(new PointClass(20, -10));

        Point vector = new PointClass(0, 10);
        square.deposeOn(vector);
        assertEquals(expected, square);
    }

    @Test
    void deposeOn_topLeft() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(-20, 20));
        square.addPoint(new PointClass(-20, -20));
        square.addPoint(new PointClass(20, -20));


        PolygonExtended expected = new PolygonExtendedClass();
        expected.addPoint(new PointClass(10, 30));
        expected.addPoint(new PointClass(-30, 30));
        expected.addPoint(new PointClass(-30, -10));
        expected.addPoint(new PointClass(10, -10));

        Point vector = new PointClass(-10, 10);
        square.deposeOn(vector);
        assertEquals(expected, square);
    }

    @Test
    void deposeOn_left() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(-20, 20));
        square.addPoint(new PointClass(-20, -20));
        square.addPoint(new PointClass(20, -20));


        PolygonExtended expected = new PolygonExtendedClass();
        expected.addPoint(new PointClass(10, 20));
        expected.addPoint(new PointClass(-30, 20));
        expected.addPoint(new PointClass(-30, -20));
        expected.addPoint(new PointClass(10, -20));

        Point vector = new PointClass(-10, 0);
        square.deposeOn(vector);
        assertEquals(expected, square);
    }

    @Test
    void deposeOn_bottomLeft() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(-20, 20));
        square.addPoint(new PointClass(-20, -20));
        square.addPoint(new PointClass(20, -20));


        PolygonExtended expected = new PolygonExtendedClass();
        expected.addPoint(new PointClass(10, 10));
        expected.addPoint(new PointClass(-30, 10));
        expected.addPoint(new PointClass(-30, -30));
        expected.addPoint(new PointClass(10, -30));

        Point vector = new PointClass(-10, -10);
        square.deposeOn(vector);
        assertEquals(expected, square);
    }

    @Test
    void deposeOn_bottom() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(-20, 20));
        square.addPoint(new PointClass(-20, -20));
        square.addPoint(new PointClass(20, -20));


        PolygonExtended expected = new PolygonExtendedClass();
        expected.addPoint(new PointClass(20, 10));
        expected.addPoint(new PointClass(-20, 10));
        expected.addPoint(new PointClass(-20, -30));
        expected.addPoint(new PointClass(20, -30));

        Point vector = new PointClass(0, -10);
        square.deposeOn(vector);
        assertEquals(expected, square);
    }

    @Test
    void deposeOn_bottomRight() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(-20, 20));
        square.addPoint(new PointClass(-20, -20));
        square.addPoint(new PointClass(20, -20));


        PolygonExtended expected = new PolygonExtendedClass();
        expected.addPoint(new PointClass(30, 10));
        expected.addPoint(new PointClass(-10, 10));
        expected.addPoint(new PointClass(-10, -30));
        expected.addPoint(new PointClass(30, -30));

        Point vector = new PointClass(10, -10);
        square.deposeOn(vector);
        assertEquals(expected, square);
    }

    @Test
    void deposeOn_right() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(-20, 20));
        square.addPoint(new PointClass(-20, -20));
        square.addPoint(new PointClass(20, -20));


        PolygonExtended expected = new PolygonExtendedClass();
        expected.addPoint(new PointClass(30, 20));
        expected.addPoint(new PointClass(-10, 20));
        expected.addPoint(new PointClass(-10, -20));
        expected.addPoint(new PointClass(30, -20));

        Point vector = new PointClass(10, 0);
        square.deposeOn(vector);
        assertEquals(expected, square);
    }

    @Test
    void testToString() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(-20, 20));
        square.addPoint(new PointClass(-20, -20));
        square.addPoint(new PointClass(20, -20));

        String actual = square.toString();
        String expected = "[(x: 20.0   y: 20.0) (x: -20.0   y: 20.0) (x: -20.0   y: -20.0) (x: 20.0   y: -20.0) ]";
        assertEquals(expected, actual);
    }

    @Test
    void testEquals_standard() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(-20, 20));
        square.addPoint(new PointClass(-20, -20));
        square.addPoint(new PointClass(20, -20));


        PolygonExtended expected = new PolygonExtendedClass();
        expected.addPoint(new PointClass(20, 20));
        expected.addPoint(new PointClass(-20, 20));
        expected.addPoint(new PointClass(-20, -20));
        expected.addPoint(new PointClass(20, -20));

        assertEquals(expected, square);
    }

    @Test
    void testEquals_differentOnePoint() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(-20, 20));
        square.addPoint(new PointClass(-20, -20));
        square.addPoint(new PointClass(20, -20));


        PolygonExtended expected = new PolygonExtendedClass();
        expected.addPoint(new PointClass(20, 20));
        expected.addPoint(new PointClass(-20, 20));
        expected.addPoint(new PointClass(-20, -20));
        expected.addPoint(new PointClass(444, -20));

        assertNotEquals(expected, square);
    }

    @Test
    void testEquals_differentCountPoints() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(-20, 20));
        square.addPoint(new PointClass(-20, -20));
        square.addPoint(new PointClass(20, -20));


        PolygonExtended expected = new PolygonExtendedClass();
        expected.addPoint(new PointClass(20, 20));
        expected.addPoint(new PointClass(-20, 20));
        expected.addPoint(new PointClass(-20, -20));

        assertNotEquals(expected, square);
    }

    @Test
    void testEquals_yourself() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(-20, 20));
        square.addPoint(new PointClass(-20, -20));
        square.addPoint(new PointClass(20, -20));

        assertEquals(square, square);
    }

    @Test
    void testEquals_empty() {
        PolygonExtended empty1 = new PolygonExtendedClass();
        PolygonExtended empty2 = new PolygonExtendedClass();
        assertEquals(empty1, empty2);
    }

    @Test
    void testHashCode_empty() {
        PolygonExtended empty1 = new PolygonExtendedClass();
        PolygonExtended empty2 = new PolygonExtendedClass();
        assertEquals(empty1.hashCode(), empty2.hashCode());
    }

    @Test
    void testHashCode_yourself() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(-20, 20));
        square.addPoint(new PointClass(-20, -20));
        square.addPoint(new PointClass(20, -20));

        assertEquals(square.hashCode(), square.hashCode());
    }

    @Test
    void testHashCode_differentCountPoints() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(-20, 20));
        square.addPoint(new PointClass(-20, -20));
        square.addPoint(new PointClass(20, -20));


        PolygonExtended expected = new PolygonExtendedClass();
        expected.addPoint(new PointClass(20, 20));
        expected.addPoint(new PointClass(-20, 20));
        expected.addPoint(new PointClass(-20, -20));

        assertNotEquals(expected.hashCode(), square.hashCode());
    }


    @Test
    void testHashCode_differentOnePoint() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(-20, 20));
        square.addPoint(new PointClass(-20, -20));
        square.addPoint(new PointClass(20, -20));


        PolygonExtended expected = new PolygonExtendedClass();
        expected.addPoint(new PointClass(20, 20));
        expected.addPoint(new PointClass(-20, 20));
        expected.addPoint(new PointClass(-20, -20));
        expected.addPoint(new PointClass(444, -20));

        assertNotEquals(expected.hashCode(), square.hashCode());
    }


    @Test
    void testHashCode_standard() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(-20, 20));
        square.addPoint(new PointClass(-20, -20));
        square.addPoint(new PointClass(20, -20));


        PolygonExtended expected = new PolygonExtendedClass();
        expected.addPoint(new PointClass(20, 20));
        expected.addPoint(new PointClass(-20, 20));
        expected.addPoint(new PointClass(-20, -20));
        expected.addPoint(new PointClass(20, -20));

        assertEquals(expected.hashCode(), square.hashCode());
    }

    @Test
    void contains_true() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(-20, 20));
        square.addPoint(new PointClass(-20, -20));
        square.addPoint(new PointClass(20, -20));

        assertTrue(square.contains(new PointClass(20, 20)));
    }

    @Test
    void contains_false() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(-20, 20));
        square.addPoint(new PointClass(-20, -20));
        square.addPoint(new PointClass(20, -20));

        assertFalse(square.contains(new PointClass(777, 777)));
    }


    @Test
    void contains_perimeterTop() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(-20, 20));
        square.addPoint(new PointClass(-20, -20));
        square.addPoint(new PointClass(20, -20));

        assertFalse(square.contains(new PointClass(0, 20)));
    }

    @Test
    void enteringPoint_vertex1() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(-20, 20));
        square.addPoint(new PointClass(-20, -20));
        square.addPoint(new PointClass(20, -20));

        assertTrue(square.contains(new PointClass(20, 20)));
    }

    @Test
    void enteringPoint_vertex2() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(-20, 20));
        square.addPoint(new PointClass(-20, -20));
        square.addPoint(new PointClass(20, -20));

        assertTrue(square.contains(new PointClass(-20, 20)));
    }

    @Test
    void enteringPoint_vertexLast() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(-20, 20));
        square.addPoint(new PointClass(-20, -20));
        square.addPoint(new PointClass(20, -20));

        assertTrue(square.contains(new PointClass(20, -20)));
    }

    @Test
    void enteringPoint_perimeterTop() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(-20, 20));
        square.addPoint(new PointClass(-20, -20));
        square.addPoint(new PointClass(20, -20));

        assertTrue(square.enteringPoint(new PointClass(0, 20)));
    }

    @Test
    void intersectionPolygon_squareNoIntersection() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(0, 0));
        square.addPoint(new PointClass(10, 0));
        square.addPoint(new PointClass(10, 10));
        square.addPoint(new PointClass(0, 10));


        PolygonExtended square2 = new PolygonExtendedClass();
        square2.addPoint(new PointClass(100, 100));
        square2.addPoint(new PointClass(110, 100));
        square2.addPoint(new PointClass(110, 110));
        square2.addPoint(new PointClass(100, 110));

        assertFalse(square.intersectionPolygon(square2)); //FIXME rename polygon
    }

    @Test
    void intersectionPolygon_oneSharedPoint() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(0, 0));
        square.addPoint(new PointClass(10, 0));
        square.addPoint(new PointClass(10, 10));
        square.addPoint(new PointClass(0, 10));

        PolygonExtended square2 = new PolygonExtendedClass();
        square2.addPoint(new PointClass(10, 10));
        square2.addPoint(new PointClass(20, 10));
        square2.addPoint(new PointClass(20, 20));
        square2.addPoint(new PointClass(10, 20));

        assertTrue(square.intersectionPolygon(square2));
    }

    @Test
    void intersectionPolygon_oneSharedLine() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(0, 0));
        square.addPoint(new PointClass(10, 0));
        square.addPoint(new PointClass(10, 10));
        square.addPoint(new PointClass(0, 10));

        PolygonExtended square2 = new PolygonExtendedClass();
        square2.addPoint(new PointClass(10, 0));
        square2.addPoint(new PointClass(10, 10));
        square2.addPoint(new PointClass(20, 10));
        square2.addPoint(new PointClass(20, 0));

        assertTrue(square.intersectionPolygon(square2));
    }

    @Test
    void intersectionPolygon_secondInsideFirst() {
        PolygonExtended first = new PolygonExtendedClass();
        first.addPoint(new PointClass(0, 0));
        first.addPoint(new PointClass(10, 0));
        first.addPoint(new PointClass(10, 10));
        first.addPoint(new PointClass(0, 10));

        PolygonExtended second = new PolygonExtendedClass();
        second.addPoint(new PointClass(5, 5));
        second.addPoint(new PointClass(15, 5));
        second.addPoint(new PointClass(15, 15));
        second.addPoint(new PointClass(5, 15));

        assertTrue(first.intersectionPolygon(second));
    }




    @Test
    public void countPoints_4() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(10, 10));
        square.addPoint(new PointClass(10, 20));
        square.addPoint(new PointClass(20, 20));
        square.addPoint(new PointClass(20, 10));

        int actual = square.getCountPoints();
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void countPoints_1() {
        PolygonExtended square = new PolygonExtendedClass();
        square.addPoint(new PointClass(10, 10));

        int actual = square.getCountPoints();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void countPoints_0() {
        PolygonExtended square = new PolygonExtendedClass();

        int actual = square.getCountPoints();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void getPoint() {
        PolygonExtended square = new PolygonExtendedClass();
        Point[] arrayPoints = {
                new PointClass(10, 10),
                new PointClass(10, 20),
                new PointClass(20, 20),
                new PointClass(20, 10),
        };
        square.addPoint(arrayPoints);

        for (int i = 0; i < arrayPoints.length; i++) {
            Point actual = square.getPoint(i);
            Point expected = arrayPoints[i];
            assertEquals(expected, actual);
        }
    }

    PolygonExtended square = new PolygonExtendedClass(Arrays.asList(new Point[]{
            new PointClass(10, 10),
            new PointClass(10, 20),
            new PointClass(20, 20),
            new PointClass(20, 10)
    }));

    @Test
    public void contains_vertexPolygon_1() {
        assertTrue(square.contains(new PointClass(10, 10)));
    }

    @Test
    public void contains_vertexPolygon_2() {
        assertTrue(square.contains(new PointClass(10, 20)));
    }

    @Test
    public void contains_vertexPolygon_3() {
        assertTrue(square.contains(new PointClass(20, 20)));
    }

    @Test
    public void contains_vertexPolygon_4() {
        assertTrue(square.contains(new PointClass(20, 10)));
    }

    @Test
    public void contains_noVertex() {
        assertFalse(square.contains(new PointClass(10.1, 10)));
    }

    @Test
    public void enteringPoint_squareCenter_15_15() {
        assertTrue(square.enteringPoint(new PointClass(15, 15)));
    }

    @Test
    public void enteringPoint_squareVertex_10_20() {
        assertTrue(square.enteringPoint(new PointClass(10, 20)));
    }

    @Test
    public void enteringPoint_squareVertex_20_10() {
        assertTrue(square.enteringPoint(new PointClass(20, 10)));
    }

    @Test
    public void enteringPoint_squareVertex_20_20() {
        assertTrue(square.enteringPoint(new PointClass(20, 20)));
    }

    @Test
    public void enteringPoint_squareVertex_10_10() {
        assertTrue(square.enteringPoint(new PointClass(10, 10)));
    }

    @Test
    public void enteringPoint_squarePointOnLine() {
        assertTrue(square.enteringPoint(new PointClass(10, 11)));
    }

    @Test
    public void enteringPoint_squareExternalPoint() {
        assertFalse(square.enteringPoint(new PointClass(15, 25)));
    }

    @Test
    public void enteringPoint_squareCenterButOppositeQuarter() {
        assertFalse(square.enteringPoint(new PointClass(-15, -15)));
    }

    @Test
    public void enteringPoint_squareCenterBut2Quarter() {
        assertFalse(square.enteringPoint(new PointClass(-15, 15)));
    }

    @Test
    public void enteringPoint_squareCenterBut4Quarter() {
        assertFalse(square.enteringPoint(new PointClass(15, -15)));
    }

    @Test
    public void enteringPoint_squareOrigin() {
        assertFalse(square.enteringPoint(new PointClass(0, 0)));
    }


    private PolygonExtended triangle = new PolygonExtendedClass(Arrays.asList(new Point[]{
            new PointClass(10, 10),
            new PointClass(20, 10),
            new PointClass(15, 20)
    }));

    @Test
    public void enteringPoint_triangle_10_10() {
        assertTrue(triangle.enteringPoint(new PointClass(10, 10)));
    }

    @Test
    public void enteringPoint_triangle_20_10() {
        assertTrue(triangle.enteringPoint(new PointClass(20, 10)));
    }

    @Test
    public void enteringPoint_triangle_15_20() {
        assertTrue(triangle.enteringPoint(new PointClass(15, 20)));
    }

    @Test
    public void enteringPoint_triangle_15_13() {
        assertTrue(triangle.enteringPoint(new PointClass(15, 13)));
    }

    @Test
    public void enteringPoint_triangle_20_16() {
        assertFalse(triangle.enteringPoint(new PointClass(20, 16)));
    }

    @Test
    public void enteringPoint_triangle_10_16() {
        assertFalse(triangle.enteringPoint(new PointClass(10, 16)));
    }

    @Test
    public void enteringPoint_triangle_15_7() {
        assertFalse(triangle.enteringPoint(new PointClass(15, 7)));
    }


    @Test
    public void enteringPoint_squareAroundOrigin() {
        PolygonExtended squareAroundOrigin = new PolygonExtendedClass();
        squareAroundOrigin.addPoint(new PointClass(-10, -10));
        squareAroundOrigin.addPoint(new PointClass(10, -10));
        squareAroundOrigin.addPoint(new PointClass(10, 10));
        squareAroundOrigin.addPoint(new PointClass(-10, 10));

        assertTrue(squareAroundOrigin.enteringPoint(new PointClass(0, 0)));
    }


    @Test
    public void enteringPoint_narrowSquare() {
        PolygonExtended narrowSquare = new PolygonExtendedClass();
        narrowSquare.addPoint(new PointClass(14, 0));
        narrowSquare.addPoint(new PointClass(16, 0));
        narrowSquare.addPoint(new PointClass(16, 30));
        narrowSquare.addPoint(new PointClass(14, 30));

        assertFalse(narrowSquare.enteringPoint(new PointClass(-10, -10)));
        assertFalse(narrowSquare.enteringPoint(new PointClass(20, 10)));
        assertFalse(narrowSquare.enteringPoint(new PointClass(10, 20)));
    }


    PolygonExtended glassesPolygon = new PolygonExtendedClass(Arrays.asList(new Point[]{
            new PointClass(0, 15),
            new PointClass(15, 15),
            new PointClass(55, 56),
            new PointClass(52, 15),
            new PointClass(85, 15),
            new PointClass(96, 21),
            new PointClass(100, 85),
            new PointClass(85, 85),
            new PointClass(80, 73),
            new PointClass(71, 53),
            new PointClass(15, 85),
            new PointClass(0, 85)
    }));

    @Test
    public void enteringPoint_glasses_19_46() {
        assertTrue(glassesPolygon.enteringPoint(new PointClass(19, 46)));
    }

    @Test
    public void enteringPoint_glasses_79_32() {
        assertTrue(glassesPolygon.enteringPoint(new PointClass(79, 32)));
    }

    @Test
    public void enteringPoint_glasses_58_22() {
        assertTrue(glassesPolygon.enteringPoint(new PointClass(58, 22)));
    }

    @Test
    public void enteringPoint_glasses_22_6() {
        assertFalse(glassesPolygon.enteringPoint(new PointClass(22, 6)));
    }

    @Test
    public void enteringPoint_glasses_59_82() {
        assertFalse(glassesPolygon.enteringPoint(new PointClass(59, 82)));
        //FIXME добавить тестирование полигона "полумесяц" и лабиринт для алгоритма зональной декстры
    }


    @Test
    public void intersectionPolygon_squarePlus5() {
        PolygonExtended squarePlus5 = new PolygonExtendedClass();
        squarePlus5.addPoint(new PointClass(15, 15));
        squarePlus5.addPoint(new PointClass(15, 25));
        squarePlus5.addPoint(new PointClass(25, 25));
        squarePlus5.addPoint(new PointClass(25, 15));


        assertTrue(square.intersectionPolygon(squarePlus5));
    }

    @Test
    public void intersectionPolygon_squarePlus10() {
        PolygonExtended squarePlus10 = new PolygonExtendedClass();
        squarePlus10.addPoint(new PointClass(20, 20));
        squarePlus10.addPoint(new PointClass(20, 30));
        squarePlus10.addPoint(new PointClass(30, 30));
        squarePlus10.addPoint(new PointClass(30, 20));


        assertTrue(square.intersectionPolygon(squarePlus10));
    }

    @Test
    public void intersectionPolygon_squareBig() {
        PolygonExtended squareBig = new PolygonExtendedClass();
        squareBig.addPoint(new PointClass(15, 0));
        squareBig.addPoint(new PointClass(15, 30));
        squareBig.addPoint(new PointClass(30, 30));
        squareBig.addPoint(new PointClass(30, 0));


        assertTrue(square.intersectionPolygon(squareBig));
    }

    @Test
    public void intersectionPolygon_squareSmall() {
        PolygonExtended squareSmall = new PolygonExtendedClass();
        squareSmall.addPoint(new PointClass(14, 0));
        squareSmall.addPoint(new PointClass(16, 0));
        squareSmall.addPoint(new PointClass(16, 30));
        squareSmall.addPoint(new PointClass(14, 30));


        assertTrue(square.intersectionPolygon(squareSmall));
    }

    @Test
    public void intersectionPolygon_squarePlus15() {
        PolygonExtended squarePlus15 = new PolygonExtendedClass();
        squarePlus15.addPoint(new PointClass(25, 25));
        squarePlus15.addPoint(new PointClass(25, 35));
        squarePlus15.addPoint(new PointClass(35, 35));
        squarePlus15.addPoint(new PointClass(35, 25));


        assertFalse(square.intersectionPolygon(squarePlus15));
    }

    @Test
    public void intersecionLine_BigDiagonale() {
        assertTrue(square.intersectionLine(new LineCutClass(new PointClass(0, 0), new PointClass(40, 40))));
    }

    @Test
    public void intersecionLine_onlyOneSharedPoint() {
        assertTrue(square.intersectionLine(new LineCutClass(new PointClass(0, 0), new PointClass(10, 10))));
    }

    @Test
    public void intersecionLine_noIntersection() {
        assertFalse(square.intersectionLine(new LineCutClass(new PointClass(0, -4), new PointClass(0, 4))));
    }

    @Test
    public void intersecionLine_noIntersectionHorizontal() {
        assertFalse(square.intersectionLine(new LineCutClass(new PointClass(0, 0), new PointClass(0, 4))));
    }

    @Test
    public void getCenterAverage_square_firstQuarter() {
        PolygonExtended polygonExtended = new PolygonExtendedClass();
        polygonExtended.addPoint(new PointClass(0, 0));
        polygonExtended.addPoint(new PointClass(100, 0));
        polygonExtended.addPoint(new PointClass(100, 100));
        polygonExtended.addPoint(new PointClass(0, 100));

        Point actualAverageCenter = polygonExtended.getCenterAverage();
        Point expectedAverageCenter = new PointClass(50, 50);

        assertEquals(actualAverageCenter, expectedAverageCenter);
    }

    @Test
    public void getCenterAverage_0_0() {
        PolygonExtended polygonExtended = new PolygonExtendedClass();
        polygonExtended.addPoint(new PointClass(0, 0));
        polygonExtended.addPoint(new PointClass(-100, 0));
        polygonExtended.addPoint(new PointClass(-100, -100));
        polygonExtended.addPoint(new PointClass(0, -100));

        Point actualAverageCenter = polygonExtended.getCenterAverage();
        Point expectedAverageCenter = new PointClass(-50, -50);

        assertEquals(actualAverageCenter, expectedAverageCenter);
    }

    @Test
    public void getCenterAverage_allPointsZeros() {
        PolygonExtended polygonExtended = new PolygonExtendedClass();
        polygonExtended.addPoint(new PointClass(0, 0));
        polygonExtended.addPoint(new PointClass(0, 0));
        polygonExtended.addPoint(new PointClass(0, 0));
        polygonExtended.addPoint(new PointClass(0, 0));

        Point actualAverageCenter = polygonExtended.getCenterAverage();
        Point expectedAverageCenter = new PointClass(0, 0);

        assertEquals(actualAverageCenter, expectedAverageCenter);

    }

    @Test
    public void getFormatDoubleArray() {
        PolygonExtended polygonExtended = new PolygonExtendedClass();
        polygonExtended.addPoint(new PointClass(0, 0));
        polygonExtended.addPoint(new PointClass(10, 0));
        polygonExtended.addPoint(new PointClass(10, 10));
        polygonExtended.addPoint(new PointClass(0, 10));

        Double[] actualArray = polygonExtended.getFormatDoubleArray();
        Double[] expectedArray = {0.0, 0.0, 10.0, 0.0, 10.0, 10.0, 0.0, 10.0};

        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void rotateRelative() {
        PolygonExtended actualRectangle = new PolygonExtendedClass();
        actualRectangle.addPoint(new PointClass(20, 20));
        actualRectangle.addPoint(new PointClass(-20, 20));
        actualRectangle.addPoint(new PointClass(-20, -20));
        actualRectangle.addPoint(new PointClass(20, -20));


        actualRectangle.rotateRelative(
                new PointClass(0, 0),
                Math.PI / 2
        );


        PolygonExtended expectedRectangle = new PolygonExtendedClass();
        expectedRectangle.addPoint(new PointClass(-20, 20));
        expectedRectangle.addPoint(new PointClass(-20, -20));
        expectedRectangle.addPoint(new PointClass(20, -20));
        expectedRectangle.addPoint(new PointClass(20, 20));

        assertEquals(expectedRectangle, actualRectangle);
    }

    @Test
    public void deposeOn() {
        PolygonExtended actualRectangle = new PolygonExtendedClass();
        actualRectangle.addPoint(new PointClass(20, 20));
        actualRectangle.addPoint(new PointClass(-20, 20));
        actualRectangle.addPoint(new PointClass(-20, -20));
        actualRectangle.addPoint(new PointClass(20, -20));

        Point vector = new PointClass(20, 20);

        actualRectangle.deposeOn(vector);

        PolygonExtended expectedRectangle = new PolygonExtendedClass();
        expectedRectangle.addPoint(new PointClass(40, 40));
        expectedRectangle.addPoint(new PointClass(0, 40));
        expectedRectangle.addPoint(new PointClass(0, 0));
        expectedRectangle.addPoint(new PointClass(40, 0));

        assertEquals(expectedRectangle, actualRectangle);
    }


    @org.junit.jupiter.api.Test
    void isLiesInsideThe_squareInsideRound() {
        Round round = new RoundClass(new PointClass(0, 0), 10);
        PolygonExtended square = new PolygonExtendedClass(Arrays.asList(new Point[]{
                new PointClass(1, 1),
                new PointClass(-1, 1),
                new PointClass(-1, -1),
                new PointClass(1, -1)
        }));

        assertTrue(square.isLiesInsideThe(round));
    }

    @org.junit.jupiter.api.Test
    void isLiesInsideThe_squareOutsideRound() {
        Round round = new RoundClass(new PointClass(0, 0), 10);
        PolygonExtended square = new PolygonExtendedClass(Arrays.asList(new Point[]{
                new PointClass(100, 100),
                new PointClass(99, 100),
                new PointClass(99, 99),
                new PointClass(100, 99)
        }));

        assertFalse(square.isLiesInsideThe(round));
    }

    @org.junit.jupiter.api.Test
    void isLiesInsideThe_squareOutsideRoundOneSharedPoint() {
        Round round = new RoundClass(new PointClass(0, 0), 10);
        PolygonExtended square = new PolygonExtendedClass(Arrays.asList(new Point[]{
                new PointClass(10, 0),
                new PointClass(10, 10),
                new PointClass(20, 10),
                new PointClass(20, 0)
        }));

        assertFalse(square.isLiesInsideThe(round));
    }

    @org.junit.jupiter.api.Test
    void isLiesInsideThe_squareInsideRoundSharedPoints() {
        Round round = new RoundClass(new PointClass(0, 0), 10);
        PolygonExtended square = new PolygonExtendedClass(Arrays.asList(new Point[]{
                new PointClass(10, 0),
                new PointClass(0, -10),
                new PointClass(-10, 0),
                new PointClass(0, 10)
        }));

        assertTrue(square.isLiesInsideThe(round));
    }

    @org.junit.jupiter.api.Test
    void isLiesInsideThe_squareNearBoarder() {
        Round round = new RoundClass(new PointClass(0, 0), 10);
        PolygonExtended square = new PolygonExtendedClass(Arrays.asList(new Point[]{
                new PointClass(11, 0),
                new PointClass(11, 11),
                new PointClass(20, 11),
                new PointClass(20, 0)
        }));

        assertFalse(square.isLiesInsideThe(round));
    }

    @org.junit.jupiter.api.Test
    void isLiesInsideThe_squareNearBoarderNegative() {
        Round round = new RoundClass(new PointClass(-100, -100), 10);
        PolygonExtended square = new PolygonExtendedClass(Arrays.asList(new Point[]{
                new PointClass(-90, -100),
                new PointClass(-100, -110),
                new PointClass(-110, -100),
                new PointClass(-100, -90)
        }));

        assertTrue(square.isLiesInsideThe(round));
    }
}