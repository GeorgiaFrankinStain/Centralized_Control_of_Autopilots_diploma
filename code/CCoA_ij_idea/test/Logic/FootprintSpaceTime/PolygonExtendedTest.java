package Logic.FootprintSpaceTime;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;


public class PolygonExtendedTest {

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

    PolygonExtended square = new PolygonExtendedClass(Arrays.asList(new PointClass[]{
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


    private PolygonExtended triangle = new PolygonExtendedClass(Arrays.asList(new PointClass[]{
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


    PolygonExtended glassesPolygon = new PolygonExtendedClass(Arrays.asList(new PointClass[]{
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
        PolygonExtended square = new PolygonExtendedClass(Arrays.asList(new PointClass[]{
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
        PolygonExtended square = new PolygonExtendedClass(Arrays.asList(new PointClass[]{
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
        PolygonExtended square = new PolygonExtendedClass(Arrays.asList(new PointClass[]{
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
        PolygonExtended square = new PolygonExtendedClass(Arrays.asList(new PointClass[]{
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
        PolygonExtended square = new PolygonExtendedClass(Arrays.asList(new PointClass[]{
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
        PolygonExtended square = new PolygonExtendedClass(Arrays.asList(new PointClass[]{
                new PointClass(-90, -100),
                new PointClass(-100, -110),
                new PointClass(-110, -100),
                new PointClass(-100, -90)
        }));

        assertTrue(square.isLiesInsideThe(round));
    }
}