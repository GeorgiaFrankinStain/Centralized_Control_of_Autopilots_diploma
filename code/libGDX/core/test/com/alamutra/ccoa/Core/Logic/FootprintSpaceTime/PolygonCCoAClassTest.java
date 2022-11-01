package com.alamutra.ccoa.Core.Logic.FootprintSpaceTime;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PolygonCCoAClassTest {

    @Test
    void constructorTextFormat_standart() {
        String draw =
                "1 2\n" +
                        "   \n" +
                        "4 3\n";
        PolygonCCoA actualPolygon = new PolygonCCoAClass(draw);

        List<PointCCoA> pointCCoAS = new ArrayList<>();
        pointCCoAS.add(new PointCCoAClass(0, 0));
        pointCCoAS.add(new PointCCoAClass(2, 0));
        pointCCoAS.add(new PointCCoAClass(2, 2));
        pointCCoAS.add(new PointCCoAClass(0, 2));
        PolygonCCoA expeactedPolygon = new PolygonCCoAClass(pointCCoAS);

        assertEquals(expeactedPolygon, actualPolygon);
    }

    @Test
    void constructorTextFormat_reverse() {
        String draw =
                "4 3\n" +
                        "   \n" +
                        "1 2\n";
        PolygonCCoA actualPolygon = new PolygonCCoAClass(draw);

        List<PointCCoA> pointCCoAS = new ArrayList<>();
        pointCCoAS.add(new PointCCoAClass(0, 2));
        pointCCoAS.add(new PointCCoAClass(2, 2));
        pointCCoAS.add(new PointCCoAClass(2, 0));
        pointCCoAS.add(new PointCCoAClass(0, 0));
        PolygonCCoA expeactedPolygon = new PolygonCCoAClass(pointCCoAS);

        assertEquals(expeactedPolygon, actualPolygon);
    }

    @Test
    void constructorTextFormat_origin() {
        String draw =
                "1 2\n" +
                        " + \n" +
                        "4 3\n";
        PolygonCCoA actualPolygon = new PolygonCCoAClass(draw);

        List<PointCCoA> pointCCoAS = new ArrayList<>();
        pointCCoAS.add(new PointCCoAClass(-1, -1));
        pointCCoAS.add(new PointCCoAClass(1, -1));
        pointCCoAS.add(new PointCCoAClass(1, 1));
        pointCCoAS.add(new PointCCoAClass(-1, 1));
        PolygonCCoA expeactedPolygon = new PolygonCCoAClass(pointCCoAS);

        assertEquals(expeactedPolygon, actualPolygon);
    }

    @Test
    void testClone_0Points() {
        PolygonCCoA original = new PolygonCCoAClass();
        PolygonCCoA clone = original.clone();
        assertEquals(original, clone);
    }

    @Test
    void testClone_1Point() {
        PolygonCCoA original = new PolygonCCoAClass();
        original.addPoint(new PointCCoAClass(1, 1));
        PolygonCCoA clone = original.clone();
        assertEquals(original, clone);
    }

    @Test
    void testClone_2Point() {
        PolygonCCoA original = new PolygonCCoAClass();
        original.addPoint(new PointCCoAClass(1, 1));
        original.addPoint(new PointCCoAClass(2, 2));
        PolygonCCoA clone = original.clone();
        assertEquals(original, clone);
    }

    @Test
    void testClone_changeOriginalAfterCloning() {
        PolygonCCoA original = new PolygonCCoAClass();
        original.addPoint(new PointCCoAClass(1, 1));
        original.addPoint(new PointCCoAClass(2, 2));
        PolygonCCoA clone = original.clone();

        original.addPoint(new PointCCoAClass(-1, -1));
        assertNotEquals(clone, null);
        assertNotEquals(original, clone);
    }

    @Test
    void testClone_changeItemOfOriginalAfterCloning() {
        PolygonCCoA original = new PolygonCCoAClass();
        original.addPoint(new PointCCoAClass(1, 1));
        original.addPoint(new PointCCoAClass(2, 2));
        PolygonCCoA clone = original.clone();

        original.deposeOn(new PointCCoAClass(10, 10));
        assertNotEquals(clone, null);
        assertNotEquals(original, clone);
    }


    @Test
    void getCountPoints_fori() {
        for (int i = 0; i < 14; i++) {
            PolygonCCoA polygon = new PolygonCCoAClass();
            for (int j = 0; j < i; j++) {
                polygon.addPoint(new PointCCoAClass(i, j));
            }
            assertTrue(polygon.getCountPoints() == i);
        }
    }

    @Test
    void getCountPoints_equalsSizeListPoints() {
        PolygonCCoA polygon = new PolygonCCoAClass();
        List<PointCCoA> pointCCoAS = Arrays.asList(new PointCCoA[]{
                new PointCCoAClass(0, 0),
                new PointCCoAClass(0, 0),
                new PointCCoAClass(0, 0),
                new PointCCoAClass(0, 0),
                new PointCCoAClass(0, 0)
        });
        polygon.addAllPoint(pointCCoAS);
        assertTrue(polygon.getCountPoints() == pointCCoAS.size());

    }

    @Test
    void getPoint_equalsPointsOneObject() {
        PolygonCCoA polygon = new PolygonCCoAClass();
        PointCCoA pointCCoA = new PointCCoAClass(0, 0);
        polygon.addPoint(pointCCoA);
        assertEquals(polygon.getPoint(0), pointCCoA);
    }

    @Test
    void getPoint_equalsPointsDifferentObjects() {
        PolygonCCoA polygon = new PolygonCCoAClass();
        polygon.addPoint(new PointCCoAClass(0, 0));
        assertEquals(polygon.getPoint(0), new PointCCoAClass(0, 0));
    }

    @Test
    void getPoint_editingThePassedPointsNotChangeThePolygon() {
        PolygonCCoA polygon = new PolygonCCoAClass();
        PointCCoA pointCCoA = new PointCCoAClass(0, 0);
        polygon.addPoint(pointCCoA);
        pointCCoA.setX(17);
        assertNotEquals(polygon.getPoint(0), pointCCoA);
    }

    private PolygonCCoA polygonWithOnePoint = createPolygonWithOnePoint();

    private PolygonCCoA createPolygonWithOnePoint() {
        PolygonCCoA polygon = new PolygonCCoAClass();
        polygon.addPoint(new PointCCoAClass(0, 0));
        return polygon;
    }

    @Test
    void addPoint_polygonWithOnePointGet0Point() {
        assertEquals(polygonWithOnePoint.getPoint(0), new PointCCoAClass(0, 0));
    }

    @Test
    void addPoint_polygonWithOnePointGetCountPoints() {
        assertEquals(polygonWithOnePoint.getCountPoints(), 1);
    }

    private PolygonCCoA polygon5Points = createPolygonWith5Points();

    private PolygonCCoA createPolygonWith5Points() {

        PolygonCCoA polygon = new PolygonCCoAClass();
        List<PointCCoA> pointCCoAS = Arrays.asList(new PointCCoA[]{
                new PointCCoAClass(0, 0),
                new PointCCoAClass(1, 1),
                new PointCCoAClass(2, 2),
                new PointCCoAClass(3, 3),
                new PointCCoAClass(4, 4)
        });
        polygon.addAllPoint(pointCCoAS);
        return polygon;
    }

    @Test
    void addAllPoint_polygonWith5PointsGetCountPoints() {
        assertTrue(polygon5Points.getCountPoints() == 5);
    }

    @Test
    void addAllPoint_polygonWith5Points0Point() {
        assertEquals(polygon5Points.getPoint(0), new PointCCoAClass(0, 0));
    }

    @Test
    void addAllPoint_polygonWith5Points1Point() {
        assertEquals(polygon5Points.getPoint(1), new PointCCoAClass(1, 1));
    }

    @Test
    void addAllPoint_polygonWith5Points2Point() {
        assertEquals(polygon5Points.getPoint(2), new PointCCoAClass(2, 2));
    }

    @Test
    void addAllPoint_polygonWith5Points3Point() {
        assertEquals(polygon5Points.getPoint(3), new PointCCoAClass(3, 3));
    }

    @Test
    void addAllPoint_polygonWith5Points4Point() {
        assertEquals(polygon5Points.getPoint(4), new PointCCoAClass(4, 4));
    }

    @Test
    void insertPoint_inEmptyPolygonInStartMultipleTimes() {
        PolygonCCoA polygon = new PolygonCCoAClass();
        polygon.insertPoint(0, new PointCCoAClass(0, 0));
        assertEquals(polygon.getPoint(0), new PointCCoAClass(0, 0));
        polygon.insertPoint(0, new PointCCoAClass(1, 1));
        assertEquals(polygon.getPoint(0), new PointCCoAClass(1, 1));
    }

    @Test
    void setPoint_fori() {
        PolygonCCoA polygon5Points = createPolygonWith5Points();
        for (int i = 0; i < 5; i++) {
            polygon5Points.setPoint(i, new PointCCoAClass(17, 17));
            assertEquals(polygon5Points.getPoint(i), new PointCCoAClass(17, 17));
        }
    }

    @Test
    void setPoint_goingBeyondTheArrayBoundaries() {
        boolean isThrowException = false;
        try {
            PolygonCCoA polygon5Points = new PolygonCCoAClass();
            polygon5Points.setPoint(17, new PointCCoAClass(17, 17));
        } catch (IndexOutOfBoundsException e) {
            isThrowException = true;
        }
        assertTrue(isThrowException);
    }

    @Test
    void rotateRelative_rotationAroundTheCenter360Degree() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(17, 17));
        square.addPoint(new PointCCoAClass(-17, 17));
        square.addPoint(new PointCCoAClass(-17, -17));
        square.addPoint(new PointCCoAClass(17, -17));

        PolygonCCoA expected = square.clone();

        PointCCoA origin = new PointCCoAClass(0, 0);
        square.rotateRelative(origin, Math.PI * 2);
        assertEquals(expected, square);
    }

    @Test
    void rotateRelative_rotationAroundTheCenter180Degree() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(17, 17));
        square.addPoint(new PointCCoAClass(-17, 17));
        square.addPoint(new PointCCoAClass(-17, -17));
        square.addPoint(new PointCCoAClass(17, -17));

        PointCCoA origin = new PointCCoAClass(0, 0);
        square.rotateRelative(origin, Math.PI);

        PolygonCCoA expected = new PolygonCCoAClass();
        expected.addPoint(new PointCCoAClass(-17, -17));
        expected.addPoint(new PointCCoAClass(17, -17));
        expected.addPoint(new PointCCoAClass(17, 17));
        expected.addPoint(new PointCCoAClass(-17, 17));

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
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(17, 17));
        square.addPoint(new PointCCoAClass(-17, 17));
        square.addPoint(new PointCCoAClass(-17, -17));
        square.addPoint(new PointCCoAClass(17, -17));

        PointCCoA origin = new PointCCoAClass(0, 0);
        square.rotateRelative(origin, Math.PI / 2);

        PolygonCCoA expected = new PolygonCCoAClass();
        expected.addPoint(new PointCCoAClass(-17, 17));
        expected.addPoint(new PointCCoAClass(-17, -17));
        expected.addPoint(new PointCCoAClass(17, -17));
        expected.addPoint(new PointCCoAClass(17, 17));

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
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(40, 20));
        square.addPoint(new PointCCoAClass(40, 40));
        square.addPoint(new PointCCoAClass(20, 40));

        PointCCoA origin = new PointCCoAClass(0, 0);
        square.rotateRelative(origin, Math.PI / 2);

        PolygonCCoA expected = new PolygonCCoAClass();
        expected.addPoint(new PointCCoAClass(-20, 20));
        expected.addPoint(new PointCCoAClass(-20, 40));
        expected.addPoint(new PointCCoAClass(-40, 40));
        expected.addPoint(new PointCCoAClass(-40, 20));

        assertEquals(expected, square);
    }

    @Test
    void deposeOn_0Vector() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(17, 17));
        square.addPoint(new PointCCoAClass(-17, 17));
        square.addPoint(new PointCCoAClass(-17, -17));
        square.addPoint(new PointCCoAClass(17, -17));

        PolygonCCoA expected = square.clone();

        PointCCoA vector = new PointCCoAClass(0, 0);
        square.deposeOn(vector);
        assertEquals(expected, square);
    }

    @Test
    void deposeOn_topRight() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(17, 17));
        square.addPoint(new PointCCoAClass(-17, 17));
        square.addPoint(new PointCCoAClass(-17, -17));
        square.addPoint(new PointCCoAClass(17, -17));


        PolygonCCoA expected = new PolygonCCoAClass();
        expected.addPoint(new PointCCoAClass(27, 27));
        expected.addPoint(new PointCCoAClass(-7, 27));
        expected.addPoint(new PointCCoAClass(-7, -7));
        expected.addPoint(new PointCCoAClass(27, -7));

        PointCCoA vector = new PointCCoAClass(10, 10);
        square.deposeOn(vector);
        assertEquals(expected, square);
    }

    @Test
    void deposeOn_top() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(-20, 20));
        square.addPoint(new PointCCoAClass(-20, -20));
        square.addPoint(new PointCCoAClass(20, -20));


        PolygonCCoA expected = new PolygonCCoAClass();
        expected.addPoint(new PointCCoAClass(20, 30));
        expected.addPoint(new PointCCoAClass(-20, 30));
        expected.addPoint(new PointCCoAClass(-20, -10));
        expected.addPoint(new PointCCoAClass(20, -10));

        PointCCoA vector = new PointCCoAClass(0, 10);
        square.deposeOn(vector);
        assertEquals(expected, square);
    }

    @Test
    void deposeOn_topLeft() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(-20, 20));
        square.addPoint(new PointCCoAClass(-20, -20));
        square.addPoint(new PointCCoAClass(20, -20));


        PolygonCCoA expected = new PolygonCCoAClass();
        expected.addPoint(new PointCCoAClass(10, 30));
        expected.addPoint(new PointCCoAClass(-30, 30));
        expected.addPoint(new PointCCoAClass(-30, -10));
        expected.addPoint(new PointCCoAClass(10, -10));

        PointCCoA vector = new PointCCoAClass(-10, 10);
        square.deposeOn(vector);
        assertEquals(expected, square);
    }

    @Test
    void deposeOn_left() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(-20, 20));
        square.addPoint(new PointCCoAClass(-20, -20));
        square.addPoint(new PointCCoAClass(20, -20));


        PolygonCCoA expected = new PolygonCCoAClass();
        expected.addPoint(new PointCCoAClass(10, 20));
        expected.addPoint(new PointCCoAClass(-30, 20));
        expected.addPoint(new PointCCoAClass(-30, -20));
        expected.addPoint(new PointCCoAClass(10, -20));

        PointCCoA vector = new PointCCoAClass(-10, 0);
        square.deposeOn(vector);
        assertEquals(expected, square);
    }

    @Test
    void deposeOn_bottomLeft() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(-20, 20));
        square.addPoint(new PointCCoAClass(-20, -20));
        square.addPoint(new PointCCoAClass(20, -20));


        PolygonCCoA expected = new PolygonCCoAClass();
        expected.addPoint(new PointCCoAClass(10, 10));
        expected.addPoint(new PointCCoAClass(-30, 10));
        expected.addPoint(new PointCCoAClass(-30, -30));
        expected.addPoint(new PointCCoAClass(10, -30));

        PointCCoA vector = new PointCCoAClass(-10, -10);
        square.deposeOn(vector);
        assertEquals(expected, square);
    }

    @Test
    void deposeOn_bottom() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(-20, 20));
        square.addPoint(new PointCCoAClass(-20, -20));
        square.addPoint(new PointCCoAClass(20, -20));


        PolygonCCoA expected = new PolygonCCoAClass();
        expected.addPoint(new PointCCoAClass(20, 10));
        expected.addPoint(new PointCCoAClass(-20, 10));
        expected.addPoint(new PointCCoAClass(-20, -30));
        expected.addPoint(new PointCCoAClass(20, -30));

        PointCCoA vector = new PointCCoAClass(0, -10);
        square.deposeOn(vector);
        assertEquals(expected, square);
    }

    @Test
    void deposeOn_bottomRight() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(-20, 20));
        square.addPoint(new PointCCoAClass(-20, -20));
        square.addPoint(new PointCCoAClass(20, -20));


        PolygonCCoA expected = new PolygonCCoAClass();
        expected.addPoint(new PointCCoAClass(30, 10));
        expected.addPoint(new PointCCoAClass(-10, 10));
        expected.addPoint(new PointCCoAClass(-10, -30));
        expected.addPoint(new PointCCoAClass(30, -30));

        PointCCoA vector = new PointCCoAClass(10, -10);
        square.deposeOn(vector);
        assertEquals(expected, square);
    }

    @Test
    void deposeOn_right() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(-20, 20));
        square.addPoint(new PointCCoAClass(-20, -20));
        square.addPoint(new PointCCoAClass(20, -20));


        PolygonCCoA expected = new PolygonCCoAClass();
        expected.addPoint(new PointCCoAClass(30, 20));
        expected.addPoint(new PointCCoAClass(-10, 20));
        expected.addPoint(new PointCCoAClass(-10, -20));
        expected.addPoint(new PointCCoAClass(30, -20));

        PointCCoA vector = new PointCCoAClass(10, 0);
        square.deposeOn(vector);
        assertEquals(expected, square);
    }

    @Test
    void testToString() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(-20, 20));
        square.addPoint(new PointCCoAClass(-20, -20));
        square.addPoint(new PointCCoAClass(20, -20));

        String actual = square.toString();
        String expected = "Polygon [(x: 20.0   y: 20.0) (x: -20.0   y: 20.0) (x: -20.0   y: -20.0) (x: 20.0   y: -20.0) ]";
        assertEquals(expected, actual);
    }

    @Test
    void testEquals_standard() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(-20, 20));
        square.addPoint(new PointCCoAClass(-20, -20));
        square.addPoint(new PointCCoAClass(20, -20));


        PolygonCCoA expected = new PolygonCCoAClass();
        expected.addPoint(new PointCCoAClass(20, 20));
        expected.addPoint(new PointCCoAClass(-20, 20));
        expected.addPoint(new PointCCoAClass(-20, -20));
        expected.addPoint(new PointCCoAClass(20, -20));

        assertEquals(expected, square);
    }

    @Test
    void testEquals_differentOnePoint() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(-20, 20));
        square.addPoint(new PointCCoAClass(-20, -20));
        square.addPoint(new PointCCoAClass(20, -20));


        PolygonCCoA expected = new PolygonCCoAClass();
        expected.addPoint(new PointCCoAClass(20, 20));
        expected.addPoint(new PointCCoAClass(-20, 20));
        expected.addPoint(new PointCCoAClass(-20, -20));
        expected.addPoint(new PointCCoAClass(444, -20));

        assertNotEquals(expected, square);
    }

    @Test
    void testEquals_differentCountPoints() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(-20, 20));
        square.addPoint(new PointCCoAClass(-20, -20));
        square.addPoint(new PointCCoAClass(20, -20));


        PolygonCCoA expected = new PolygonCCoAClass();
        expected.addPoint(new PointCCoAClass(20, 20));
        expected.addPoint(new PointCCoAClass(-20, 20));
        expected.addPoint(new PointCCoAClass(-20, -20));

        assertNotEquals(expected, square);
    }

    @Test
    void testEquals_yourself() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(-20, 20));
        square.addPoint(new PointCCoAClass(-20, -20));
        square.addPoint(new PointCCoAClass(20, -20));

        assertEquals(square, square);
    }

    @Test
    void testEquals_empty() {
        PolygonCCoA empty1 = new PolygonCCoAClass();
        PolygonCCoA empty2 = new PolygonCCoAClass();
        assertEquals(empty1, empty2);
    }

    @Test
    void testHashCode_empty() {
        PolygonCCoA empty1 = new PolygonCCoAClass();
        PolygonCCoA empty2 = new PolygonCCoAClass();
        assertEquals(empty1.hashCode(), empty2.hashCode());
    }

    @Test
    void testHashCode_yourself() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(-20, 20));
        square.addPoint(new PointCCoAClass(-20, -20));
        square.addPoint(new PointCCoAClass(20, -20));

        assertEquals(square.hashCode(), square.hashCode());
    }

    @Test
    void testHashCode_differentCountPoints() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(-20, 20));
        square.addPoint(new PointCCoAClass(-20, -20));
        square.addPoint(new PointCCoAClass(20, -20));


        PolygonCCoA expected = new PolygonCCoAClass();
        expected.addPoint(new PointCCoAClass(20, 20));
        expected.addPoint(new PointCCoAClass(-20, 20));
        expected.addPoint(new PointCCoAClass(-20, -20));

        assertNotEquals(expected.hashCode(), square.hashCode());
    }


    @Test
    void testHashCode_differentOnePoint() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(-20, 20));
        square.addPoint(new PointCCoAClass(-20, -20));
        square.addPoint(new PointCCoAClass(20, -20));


        PolygonCCoA expected = new PolygonCCoAClass();
        expected.addPoint(new PointCCoAClass(20, 20));
        expected.addPoint(new PointCCoAClass(-20, 20));
        expected.addPoint(new PointCCoAClass(-20, -20));
        expected.addPoint(new PointCCoAClass(444, -20));

        assertNotEquals(expected.hashCode(), square.hashCode());
    }


    @Test
    void testHashCode_standard() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(-20, 20));
        square.addPoint(new PointCCoAClass(-20, -20));
        square.addPoint(new PointCCoAClass(20, -20));


        PolygonCCoA expected = new PolygonCCoAClass();
        expected.addPoint(new PointCCoAClass(20, 20));
        expected.addPoint(new PointCCoAClass(-20, 20));
        expected.addPoint(new PointCCoAClass(-20, -20));
        expected.addPoint(new PointCCoAClass(20, -20));

        assertEquals(expected.hashCode(), square.hashCode());
    }

    @Test
    void contains_true() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(-20, 20));
        square.addPoint(new PointCCoAClass(-20, -20));
        square.addPoint(new PointCCoAClass(20, -20));

        assertTrue(square.contains(new PointCCoAClass(20, 20)));
    }

    @Test
    void contains_false() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(-20, 20));
        square.addPoint(new PointCCoAClass(-20, -20));
        square.addPoint(new PointCCoAClass(20, -20));

        assertFalse(square.contains(new PointCCoAClass(777, 777)));
    }


    @Test
    void contains_perimeterTop() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(-20, 20));
        square.addPoint(new PointCCoAClass(-20, -20));
        square.addPoint(new PointCCoAClass(20, -20));

        assertFalse(square.contains(new PointCCoAClass(0, 20)));
    }

    @Test
    void enteringPoint_vertex1() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(-20, 20));
        square.addPoint(new PointCCoAClass(-20, -20));
        square.addPoint(new PointCCoAClass(20, -20));

        assertTrue(square.contains(new PointCCoAClass(20, 20)));
    }

    @Test
    void enteringPoint_vertex2() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(-20, 20));
        square.addPoint(new PointCCoAClass(-20, -20));
        square.addPoint(new PointCCoAClass(20, -20));

        assertTrue(square.contains(new PointCCoAClass(-20, 20)));
    }

    @Test
    void enteringPoint_vertexLast() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(-20, 20));
        square.addPoint(new PointCCoAClass(-20, -20));
        square.addPoint(new PointCCoAClass(20, -20));

        assertTrue(square.contains(new PointCCoAClass(20, -20)));
    }

    @Test
    void enteringPoint_perimeterTop() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(-20, 20));
        square.addPoint(new PointCCoAClass(-20, -20));
        square.addPoint(new PointCCoAClass(20, -20));

        assertTrue(square.enteringPoint(new PointCCoAClass(0, 20)));
    }

    /*
     * Y
     * ^
     * |               +-------+
     * |               |       |
     * |               |       |
     * |               +-------+
     * |
     * |
     * |
     * |     +-------+
     * |     |       |
     * |     |       |
     * |     +-------+
     * +--------------------------------------> X
     */
    @Test
    void intersectionPolygon_squareNoIntersection() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(0, 0));
        square.addPoint(new PointCCoAClass(10, 0));
        square.addPoint(new PointCCoAClass(10, 10));
        square.addPoint(new PointCCoAClass(0, 10));


        PolygonCCoA square2 = new PolygonCCoAClass();
        square2.addPoint(new PointCCoAClass(100, 100));
        square2.addPoint(new PointCCoAClass(110, 100));
        square2.addPoint(new PointCCoAClass(110, 110));
        square2.addPoint(new PointCCoAClass(100, 110));

        assertFalse(square.intersectionPolygon(square2)); //FIXME rename polygon
    }


    /*
     * Y
     * ^
     * |
     * |
     * |
     * |
     * |             +-------+
     * |             |       |
     * |             |       |
     * |     +-------+-------+
     * |     |       |
     * |     |       |
     * |     +-------+
     * +--------------------------------------> X
     */
    @Test
    void intersectionPolygon_oneSharedPoint() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(0, 0));
        square.addPoint(new PointCCoAClass(10, 0));
        square.addPoint(new PointCCoAClass(10, 10));
        square.addPoint(new PointCCoAClass(0, 10));

        PolygonCCoA square2 = new PolygonCCoAClass();
        square2.addPoint(new PointCCoAClass(10, 10));
        square2.addPoint(new PointCCoAClass(20, 10));
        square2.addPoint(new PointCCoAClass(20, 20));
        square2.addPoint(new PointCCoAClass(10, 20));

        assertTrue(square.intersectionPolygon(square2));
    }


    /*
     * Y
     * ^
     * |
     * |
     * |
     * |
     * |
     * |
     * |
     * |     +-------+-------+
     * |     |       |       |
     * |     |       |       |
     * |     +-------+-------+
     * +--------------------------------------> X
     */
    @Test
    void intersectionPolygon_oneSharedLineCut() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(0, 0));
        square.addPoint(new PointCCoAClass(10, 0));
        square.addPoint(new PointCCoAClass(10, 10));
        square.addPoint(new PointCCoAClass(0, 10));

        PolygonCCoA square2 = new PolygonCCoAClass();
        square2.addPoint(new PointCCoAClass(10, 0));
        square2.addPoint(new PointCCoAClass(10, 10));
        square2.addPoint(new PointCCoAClass(20, 10));
        square2.addPoint(new PointCCoAClass(20, 0));

        assertTrue(square.intersectionPolygon(square2));
    }

    /*
     *                                   Y
     *                                   ^
     *                                   |
     *                                   |
     *                                   |
     *                                   |
     *                                   |
     *                  +------------------------------+
     *                  |                |             |
     *                  |                |     +-------+
     *                  |                |     |       |
     *                  |                |     |       |
     *                  |                |     +-------+
     * -----------------|----------------+--------------------------------------> X
     *                  |                |             |
     *                  |                |             |
     *                  |                |             |
     *                  |                |             |
     *                  |                |             |
     *                  +------------------------------+
     *                                   |
     *                                   |
     *                                   |
     *                                   |
     */
    @Test
    void intersectionPolygon_oneSharedLineWithoutSharedVertex() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(0, 0));
        square.addPoint(new PointCCoAClass(10, 0));
        square.addPoint(new PointCCoAClass(10, 10));
        square.addPoint(new PointCCoAClass(0, 10));

        PolygonCCoA square2 = new PolygonCCoAClass();
        square2.addPoint(new PointCCoAClass(-100, -100));
        square2.addPoint(new PointCCoAClass(10, -100));
        square2.addPoint(new PointCCoAClass(10, 100));
        square2.addPoint(new PointCCoAClass(-100, 100));

        assertTrue(square.intersectionPolygon(square2));
    }

    /*
     * Y
     * ^
     * |
     * |
     * |
     * |
     * |
     * |  +----------------+
     * |  |                |
     * |  |  +-------+     |
     * |  |  |       |     |
     * |  |  |       |     |
     * |  |  +-------+     |
     * |  +----------------+
     * +--------------------------------------> X
     */
    @Test
    void intersectionPolygon_secondInsideFirst() {
        PolygonCCoA first = new PolygonCCoAClass();
        first.addPoint(new PointCCoAClass(0, 0));
        first.addPoint(new PointCCoAClass(10, 0));
        first.addPoint(new PointCCoAClass(10, 10));
        first.addPoint(new PointCCoAClass(0, 10));

        PolygonCCoA second = new PolygonCCoAClass();
        second.addPoint(new PointCCoAClass(5, 5));
        second.addPoint(new PointCCoAClass(15, 5));
        second.addPoint(new PointCCoAClass(15, 15));
        second.addPoint(new PointCCoAClass(5, 15));

        assertTrue(first.intersectionPolygon(second));
    }


    @Test
    public void countPoints_4() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(10, 10));
        square.addPoint(new PointCCoAClass(10, 20));
        square.addPoint(new PointCCoAClass(20, 20));
        square.addPoint(new PointCCoAClass(20, 10));

        int actual = square.getCountPoints();
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void countPoints_1() {
        PolygonCCoA square = new PolygonCCoAClass();
        square.addPoint(new PointCCoAClass(10, 10));

        int actual = square.getCountPoints();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void countPoints_0() {
        PolygonCCoA square = new PolygonCCoAClass();

        int actual = square.getCountPoints();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void getPoint() {
        PolygonCCoA square = new PolygonCCoAClass();
        PointCCoA[] arrayPointCCoAS = {
                new PointCCoAClass(10, 10),
                new PointCCoAClass(10, 20),
                new PointCCoAClass(20, 20),
                new PointCCoAClass(20, 10),
        };
        square.addPoint(arrayPointCCoAS);

        for (int i = 0; i < arrayPointCCoAS.length; i++) {
            PointCCoA actual = square.getPoint(i);
            PointCCoA expected = arrayPointCCoAS[i];
            assertEquals(expected, actual);
        }
    }

    PolygonCCoA square = new PolygonCCoAClass(Arrays.<PointCCoA>asList(new PointCCoAClass[]{
            new PointCCoAClass(10, 10),
            new PointCCoAClass(10, 20),
            new PointCCoAClass(20, 20),
            new PointCCoAClass(20, 10)
    }));

    @Test
    public void contains_vertexPolygon_1() {
        assertTrue(square.contains(new PointCCoAClass(10, 10)));
    }

    @Test
    public void contains_vertexPolygon_2() {
        assertTrue(square.contains(new PointCCoAClass(10, 20)));
    }

    @Test
    public void contains_vertexPolygon_3() {
        assertTrue(square.contains(new PointCCoAClass(20, 20)));
    }

    @Test
    public void contains_vertexPolygon_4() {
        assertTrue(square.contains(new PointCCoAClass(20, 10)));
    }

    @Test
    public void contains_noVertex() {
        assertFalse(square.contains(new PointCCoAClass(10.1, 10)));
    }

    @Test
    public void enteringPoint_squareCenter_15_15() {
        assertTrue(square.enteringPoint(new PointCCoAClass(15, 15)));
    }

    @Test
    public void enteringPoint_squareVertex_10_20() {
        assertTrue(square.enteringPoint(new PointCCoAClass(10, 20)));
    }

    @Test
    public void enteringPoint_squareVertex_20_10() {
        assertTrue(square.enteringPoint(new PointCCoAClass(20, 10)));
    }

    @Test
    public void enteringPoint_squareVertex_20_20() {
        assertTrue(square.enteringPoint(new PointCCoAClass(20, 20)));
    }

    @Test
    public void enteringPoint_squareVertex_10_10() {
        assertTrue(square.enteringPoint(new PointCCoAClass(10, 10)));
    }

    @Test
    public void enteringPoint_squarePointOnLine() {
        assertTrue(square.enteringPoint(new PointCCoAClass(10, 11)));
    }

    @Test
    public void enteringPoint_squareExternalPoint() {
        assertFalse(square.enteringPoint(new PointCCoAClass(15, 25)));
    }

    @Test
    public void enteringPoint_squareCenterButOppositeQuarter() {
        assertFalse(square.enteringPoint(new PointCCoAClass(-15, -15)));
    }

    @Test
    public void enteringPoint_squareCenterBut2Quarter() {
        assertFalse(square.enteringPoint(new PointCCoAClass(-15, 15)));
    }

    @Test
    public void enteringPoint_squareCenterBut4Quarter() {
        assertFalse(square.enteringPoint(new PointCCoAClass(15, -15)));
    }

    @Test
    public void enteringPoint_squareOrigin() {
        assertFalse(square.enteringPoint(new PointCCoAClass(0, 0)));
    }


    private PolygonCCoA triangle = new PolygonCCoAClass(Arrays.<PointCCoA>asList(new PointCCoAClass[]{
            new PointCCoAClass(10, 10),
            new PointCCoAClass(20, 10),
            new PointCCoAClass(15, 20)
    }));

    @Test
    public void enteringPoint_triangle_10_10() {
        assertTrue(triangle.enteringPoint(new PointCCoAClass(10, 10)));
    }

    @Test
    public void enteringPoint_triangle_20_10() {
        assertTrue(triangle.enteringPoint(new PointCCoAClass(20, 10)));
    }

    @Test
    public void enteringPoint_triangle_15_20() {
        assertTrue(triangle.enteringPoint(new PointCCoAClass(15, 20)));
    }

    @Test
    public void enteringPoint_triangle_15_13() {
        assertTrue(triangle.enteringPoint(new PointCCoAClass(15, 13)));
    }

    @Test
    public void enteringPoint_triangle_20_16() {
        assertFalse(triangle.enteringPoint(new PointCCoAClass(20, 16)));
    }

    @Test
    public void enteringPoint_triangle_10_16() {
        assertFalse(triangle.enteringPoint(new PointCCoAClass(10, 16)));
    }

    @Test
    public void enteringPoint_triangle_15_7() {
        assertFalse(triangle.enteringPoint(new PointCCoAClass(15, 7)));
    }


    @Test
    public void enteringPoint_squareAroundOrigin() {
        PolygonCCoA squareAroundOrigin = new PolygonCCoAClass();
        squareAroundOrigin.addPoint(new PointCCoAClass(-10, -10));
        squareAroundOrigin.addPoint(new PointCCoAClass(10, -10));
        squareAroundOrigin.addPoint(new PointCCoAClass(10, 10));
        squareAroundOrigin.addPoint(new PointCCoAClass(-10, 10));

        assertTrue(squareAroundOrigin.enteringPoint(new PointCCoAClass(0, 0)));
    }


    @Test
    public void enteringPoint_narrowSquare() {
        PolygonCCoA narrowSquare = new PolygonCCoAClass();
        narrowSquare.addPoint(new PointCCoAClass(14, 0));
        narrowSquare.addPoint(new PointCCoAClass(16, 0));
        narrowSquare.addPoint(new PointCCoAClass(16, 30));
        narrowSquare.addPoint(new PointCCoAClass(14, 30));

        assertFalse(narrowSquare.enteringPoint(new PointCCoAClass(-10, -10)));
        assertFalse(narrowSquare.enteringPoint(new PointCCoAClass(20, 10)));
        assertFalse(narrowSquare.enteringPoint(new PointCCoAClass(10, 20)));
    }


    PolygonCCoA glassesPolygon = new PolygonCCoAClass(Arrays.<PointCCoA>asList(new PointCCoAClass[]{
            new PointCCoAClass(0, 15),
            new PointCCoAClass(15, 15),
            new PointCCoAClass(55, 56),
            new PointCCoAClass(52, 15),
            new PointCCoAClass(85, 15),
            new PointCCoAClass(96, 21),
            new PointCCoAClass(100, 85),
            new PointCCoAClass(85, 85),
            new PointCCoAClass(80, 73),
            new PointCCoAClass(71, 53),
            new PointCCoAClass(15, 85),
            new PointCCoAClass(0, 85)
    }));

    @Test
    public void enteringPoint_glasses_19_46() {
        assertTrue(glassesPolygon.enteringPoint(new PointCCoAClass(19, 46)));
    }

    @Test
    public void enteringPoint_glasses_79_32() {
        assertTrue(glassesPolygon.enteringPoint(new PointCCoAClass(79, 32)));
    }

    @Test
    public void enteringPoint_glasses_58_22() {
        assertTrue(glassesPolygon.enteringPoint(new PointCCoAClass(58, 22)));
    }

    @Test
    public void enteringPoint_glasses_22_6() {
        assertFalse(glassesPolygon.enteringPoint(new PointCCoAClass(22, 6)));
    }

    @Test
    public void enteringPoint_glasses_59_82() {
        assertFalse(glassesPolygon.enteringPoint(new PointCCoAClass(59, 82)));
        //FIXME добавить тестирование полигона "полумесяц" и лабиринт для алгоритма зональной декстры
    }


    @Test
    public void intersectionPolygon_squarePlus5() {
        PolygonCCoA squarePlus5 = new PolygonCCoAClass();
        squarePlus5.addPoint(new PointCCoAClass(15, 15));
        squarePlus5.addPoint(new PointCCoAClass(15, 25));
        squarePlus5.addPoint(new PointCCoAClass(25, 25));
        squarePlus5.addPoint(new PointCCoAClass(25, 15));


        assertTrue(square.intersectionPolygon(squarePlus5));
    }

    @Test
    public void intersectionPolygon_squarePlus10() {
        PolygonCCoA squarePlus10 = new PolygonCCoAClass();
        squarePlus10.addPoint(new PointCCoAClass(20, 20));
        squarePlus10.addPoint(new PointCCoAClass(20, 30));
        squarePlus10.addPoint(new PointCCoAClass(30, 30));
        squarePlus10.addPoint(new PointCCoAClass(30, 20));


        assertTrue(square.intersectionPolygon(squarePlus10));
    }

    @Test
    public void intersectionPolygon_squareBig() {
        PolygonCCoA squareBig = new PolygonCCoAClass();
        squareBig.addPoint(new PointCCoAClass(15, 0));
        squareBig.addPoint(new PointCCoAClass(15, 30));
        squareBig.addPoint(new PointCCoAClass(30, 30));
        squareBig.addPoint(new PointCCoAClass(30, 0));


        assertTrue(square.intersectionPolygon(squareBig));
    }

    @Test
    public void intersectionPolygon_squareSmall() {
        PolygonCCoA squareSmall = new PolygonCCoAClass();
        squareSmall.addPoint(new PointCCoAClass(14, 0));
        squareSmall.addPoint(new PointCCoAClass(16, 0));
        squareSmall.addPoint(new PointCCoAClass(16, 30));
        squareSmall.addPoint(new PointCCoAClass(14, 30));


        assertTrue(square.intersectionPolygon(squareSmall));
    }

    @Test
    public void intersectionPolygon_squarePlus15() {
        PolygonCCoA squarePlus15 = new PolygonCCoAClass();
        squarePlus15.addPoint(new PointCCoAClass(25, 25));
        squarePlus15.addPoint(new PointCCoAClass(25, 35));
        squarePlus15.addPoint(new PointCCoAClass(35, 35));
        squarePlus15.addPoint(new PointCCoAClass(35, 25));


        assertFalse(square.intersectionPolygon(squarePlus15));
    }

    @Test
    public void intersecionLine_BigDiagonale() {
        assertTrue(square.intersectionLine(new LineCutClass(new PointCCoAClass(0, 0), new PointCCoAClass(40, 40))));
    }

    @Test
    public void intersecionLine_onlyOneSharedPoint() {
        assertTrue(square.intersectionLine(new LineCutClass(new PointCCoAClass(0, 0), new PointCCoAClass(10, 10))));
    }

    @Test
    public void intersecionLine_noIntersection() {
        assertFalse(square.intersectionLine(new LineCutClass(new PointCCoAClass(0, -4), new PointCCoAClass(0, 4))));
    }

    @Test
    public void intersecionLine_noIntersectionHorizontal() {
        assertFalse(square.intersectionLine(new LineCutClass(new PointCCoAClass(0, 0), new PointCCoAClass(0, 4))));
    }

    @Test
    public void getCenterAverage_square_firstQuarter() {
        PolygonCCoA polygonCCoA = new PolygonCCoAClass();
        polygonCCoA.addPoint(new PointCCoAClass(0, 0));
        polygonCCoA.addPoint(new PointCCoAClass(100, 0));
        polygonCCoA.addPoint(new PointCCoAClass(100, 100));
        polygonCCoA.addPoint(new PointCCoAClass(0, 100));

        PointCCoA actualAverageCenter = polygonCCoA.getCenterAverage();
        PointCCoA expectedAverageCenter = new PointCCoAClass(50, 50);

        assertEquals(actualAverageCenter, expectedAverageCenter);
    }

    @Test
    public void getCenterAverage_0_0() {
        PolygonCCoA polygonCCoA = new PolygonCCoAClass();
        polygonCCoA.addPoint(new PointCCoAClass(0, 0));
        polygonCCoA.addPoint(new PointCCoAClass(-100, 0));
        polygonCCoA.addPoint(new PointCCoAClass(-100, -100));
        polygonCCoA.addPoint(new PointCCoAClass(0, -100));

        PointCCoA actualAverageCenter = polygonCCoA.getCenterAverage();
        PointCCoA expectedAverageCenter = new PointCCoAClass(-50, -50);

        assertEquals(actualAverageCenter, expectedAverageCenter);
    }

    @Test
    public void getCenterAverage_allPointsZeros() {
        PolygonCCoA polygonCCoA = new PolygonCCoAClass();
        polygonCCoA.addPoint(new PointCCoAClass(0, 0));
        polygonCCoA.addPoint(new PointCCoAClass(0, 0));
        polygonCCoA.addPoint(new PointCCoAClass(0, 0));
        polygonCCoA.addPoint(new PointCCoAClass(0, 0));

        PointCCoA actualAverageCenter = polygonCCoA.getCenterAverage();
        PointCCoA expectedAverageCenter = new PointCCoAClass(0, 0);

        assertEquals(actualAverageCenter, expectedAverageCenter);

    }

    @Test
    public void getFormatDoubleArray() {
        PolygonCCoA polygonCCoA = new PolygonCCoAClass();
        polygonCCoA.addPoint(new PointCCoAClass(0, 0));
        polygonCCoA.addPoint(new PointCCoAClass(10, 0));
        polygonCCoA.addPoint(new PointCCoAClass(10, 10));
        polygonCCoA.addPoint(new PointCCoAClass(0, 10));

        Double[] actualArray = polygonCCoA.getFormatDoubleArray();
        Double[] expectedArray = {0.0, 0.0, 10.0, 0.0, 10.0, 10.0, 0.0, 10.0};

        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void rotateRelative() {
        PolygonCCoA actualRectangle = new PolygonCCoAClass();
        actualRectangle.addPoint(new PointCCoAClass(20, 20));
        actualRectangle.addPoint(new PointCCoAClass(-20, 20));
        actualRectangle.addPoint(new PointCCoAClass(-20, -20));
        actualRectangle.addPoint(new PointCCoAClass(20, -20));


        actualRectangle.rotateRelative(
                new PointCCoAClass(0, 0),
                Math.PI / 2
        );


        PolygonCCoA expectedRectangle = new PolygonCCoAClass();
        expectedRectangle.addPoint(new PointCCoAClass(-20, 20));
        expectedRectangle.addPoint(new PointCCoAClass(-20, -20));
        expectedRectangle.addPoint(new PointCCoAClass(20, -20));
        expectedRectangle.addPoint(new PointCCoAClass(20, 20));

        assertEquals(expectedRectangle, actualRectangle);
    }

    @Test
    public void deposeOn() {
        PolygonCCoA actualRectangle = new PolygonCCoAClass();
        actualRectangle.addPoint(new PointCCoAClass(20, 20));
        actualRectangle.addPoint(new PointCCoAClass(-20, 20));
        actualRectangle.addPoint(new PointCCoAClass(-20, -20));
        actualRectangle.addPoint(new PointCCoAClass(20, -20));

        PointCCoA vector = new PointCCoAClass(20, 20);

        actualRectangle.deposeOn(vector);

        PolygonCCoA expectedRectangle = new PolygonCCoAClass();
        expectedRectangle.addPoint(new PointCCoAClass(40, 40));
        expectedRectangle.addPoint(new PointCCoAClass(0, 40));
        expectedRectangle.addPoint(new PointCCoAClass(0, 0));
        expectedRectangle.addPoint(new PointCCoAClass(40, 0));

        assertEquals(expectedRectangle, actualRectangle);
    }


    @org.junit.jupiter.api.Test
    void isLiesInsideThe_squareInsideRound() {
        Round round = new RoundClass(new PointCCoAClass(0, 0), 10);
        PolygonCCoA square = new PolygonCCoAClass(Arrays.<PointCCoA>asList(new PointCCoAClass[]{
                new PointCCoAClass(1, 1),
                new PointCCoAClass(-1, 1),
                new PointCCoAClass(-1, -1),
                new PointCCoAClass(1, -1)
        }));

        assertTrue(square.isLiesInsideThe(round));
    }

    @org.junit.jupiter.api.Test
    void isLiesInsideThe_squareOutsideRound() {
        Round round = new RoundClass(new PointCCoAClass(0, 0), 10);
        PolygonCCoA square = new PolygonCCoAClass(Arrays.<PointCCoA>asList(new PointCCoAClass[]{
                new PointCCoAClass(100, 100),
                new PointCCoAClass(99, 100),
                new PointCCoAClass(99, 99),
                new PointCCoAClass(100, 99)
        }));

        assertFalse(square.isLiesInsideThe(round));
    }

    @org.junit.jupiter.api.Test
    void isLiesInsideThe_squareOutsideRoundOneSharedPoint() {
        Round round = new RoundClass(new PointCCoAClass(0, 0), 10);
        PolygonCCoA square = new PolygonCCoAClass(Arrays.<PointCCoA>asList(new PointCCoAClass[]{
                new PointCCoAClass(10, 0),
                new PointCCoAClass(10, 10),
                new PointCCoAClass(20, 10),
                new PointCCoAClass(20, 0)
        }));

        assertFalse(square.isLiesInsideThe(round));
    }

    @org.junit.jupiter.api.Test
    void isLiesInsideThe_squareInsideRoundSharedPoints() {
        Round round = new RoundClass(new PointCCoAClass(0, 0), 10);
        PolygonCCoA square = new PolygonCCoAClass(Arrays.<PointCCoA>asList(new PointCCoAClass[]{
                new PointCCoAClass(10, 0),
                new PointCCoAClass(0, -10),
                new PointCCoAClass(-10, 0),
                new PointCCoAClass(0, 10)
        }));

        assertTrue(square.isLiesInsideThe(round));
    }

    @org.junit.jupiter.api.Test
    void isLiesInsideThe_squareNearBoarder() {
        Round round = new RoundClass(new PointCCoAClass(0, 0), 10);
        PolygonCCoA square = new PolygonCCoAClass(Arrays.<PointCCoA>asList(new PointCCoAClass[]{
                new PointCCoAClass(11, 0),
                new PointCCoAClass(11, 11),
                new PointCCoAClass(20, 11),
                new PointCCoAClass(20, 0)
        }));

        assertFalse(square.isLiesInsideThe(round));
    }

    @org.junit.jupiter.api.Test
    void isLiesInsideThe_squareNearBoarderNegative() {
        Round round = new RoundClass(new PointCCoAClass(-100, -100), 10);
        PolygonCCoA square = new PolygonCCoAClass(Arrays.<PointCCoA>asList(new PointCCoAClass[]{
                new PointCCoAClass(-90, -100),
                new PointCCoAClass(-100, -110),
                new PointCCoAClass(-110, -100),
                new PointCCoAClass(-100, -90)
        }));

        assertTrue(square.isLiesInsideThe(round));
    }

    @Test
    void getArea_100() {
        PolygonCCoA square = new PolygonCCoAClass(Arrays.<PointCCoA>asList(new PointCCoAClass[]{
                new PointCCoAClass(0, 0),
                new PointCCoAClass(0, 10),
                new PointCCoAClass(10, 10),
                new PointCCoAClass(10, 0)
        }));

        assertEquals(100, square.getArea());
    }

    @Test
    void getArea_100MoveTo() {
        PolygonCCoA square = new PolygonCCoAClass(Arrays.<PointCCoA>asList(new PointCCoAClass[]{
                new PointCCoAClass(0, 0),
                new PointCCoAClass(0, 10),
                new PointCCoAClass(10, 10),
                new PointCCoAClass(10, 0)
        }));

        for (int i = -40; i < 40; i = i + 5) {
            for (int j = -40; j < 40; j = j + 5) {
                PointCCoA vector = new PointCCoAClass(i, j);
                PolygonCCoA deposedSquare = square.getDeposeOn(vector);
                
                assertEquals(100, deposedSquare.getArea());
            }
        }


    }
}