package com.alamutra.ccoa.Core.Logic.FootprintSpaceTime;

import com.alamutra.ccoa.Core.Logic.ControllerMachines.Node;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HexagonClassTest {

    private List<PointCCoA> expectedPoints = createExpectedPoints();

    private List<PointCCoA> createExpectedPoints() {
        List<PointCCoA> expectedPoints = new ArrayList<>();

        expectedPoints.add(new PointCCoAClass(-10.0, -5.773502691896259));
        expectedPoints.add(new PointCCoAClass(-10.0, 5.773502691896258));
        expectedPoints.add(new PointCCoAClass(-1.295584077354306E-6, 11.547007627809382));
        expectedPoints.add(new PointCCoAClass(9.999997408831845, 5.773502691896257));
        expectedPoints.add(new PointCCoAClass(9.999997408831845, -5.773502691896259));
        expectedPoints.add(new PointCCoAClass(-1.295584077354306E-6, -11.547007627809384));

        return expectedPoints;
    }

    @Test
    void testForm_side10StartCenterX0Y0() {
        PointCCoA center = new PointCCoAClass(0, 0);
        PolygonCCoA hexagon = new HexagonClass(10, center);

        int numberPointInHexagon = 6;
        for (int i = 0; i < numberPointInHexagon; i++) {
            PointCCoA expected = expectedPoints.get(i);
            PointCCoA actual = hexagon.getPoint(i);
            assertEquals(expected, actual);
        }
    }

    /*
     *
     *
     *             a╱  ╲
     *           ╱        ╲a
     *         |            |
     *        a|     *---r--|
     *         |            |a
     *           ╲        /
     *           a  ╲  /
     *
     * r = (a√3)/2
     * a = 2r/√3
     */
    @Test
    void testForm_side10StartCenterX0Y0ConstructorLengthSide() {
        PointCCoA leftBottom = new PointCCoAClass(-10.0, -5.773502691896259);

        double littleRadius = 10;
        double lengthSide = 2 *  littleRadius / Math.sqrt(3);

        PolygonCCoA hexagon = new HexagonClass(0, leftBottom,  lengthSide);

        int numberPointInHexagon = 6;
        for (int i = 0; i < numberPointInHexagon; i++) {
            PointCCoA expected = expectedPoints.get(i);
            PointCCoA actual = hexagon.getPoint(i);
            assertEquals(expected, actual);
        }
    }
    @Test
    void testForm_side10StartCenterX0Y0ConstructorNumber0PointRadius() {
        PointCCoA leftBottom = new PointCCoAClass(-10.0, -5.773502691896259);
        PolygonCCoA hexagon = new HexagonClass(10, 0, leftBottom);

        int numberPointInHexagon = 6;
        for (int i = 0; i < numberPointInHexagon; i++) {
            PointCCoA expected = expectedPoints.get(i);
            PointCCoA actual = hexagon.getPoint(i);
            assertEquals(expected, actual);
        }
    }
    @Test
    void testForm_side10StartCenterX0Y0ConstructorNumber1PointRadius() {
        PointCCoA leftTop = new PointCCoAClass(-10.0, 5.773502691896258);
        PolygonCCoA hexagon = new HexagonClass(10, 1, leftTop);

        int numberPointInHexagon = 6;
        for (int i = 0; i < numberPointInHexagon; i++) {
            PointCCoA expected = expectedPoints.get(i);
            PointCCoA actual = hexagon.getPoint(i);
            assertEquals(expected, actual);
        }
    }
    @Test
    void testForm_side10StartCenterX0Y0ConstructorNumber2PointRadius() {
        PointCCoA top = new PointCCoAClass(-1.295584077354306E-6, 11.547007627809382);
        PolygonCCoA hexagon = new HexagonClass(10, 2, top);

        int numberPointInHexagon = 6;
        for (int i = 0; i < numberPointInHexagon; i++) {
            PointCCoA expected = expectedPoints.get(i);
            PointCCoA actual = hexagon.getPoint(i);
            assertEquals(expected, actual);
        }
    }
    @Test
    void testForm_side10StartCenterX0Y0ConstructorNumber3PointRadius() {
        PointCCoA rightTop = new PointCCoAClass(9.999997408831845, 5.773502691896257);
        PolygonCCoA hexagon = new HexagonClass(10, 3, rightTop);

        int numberPointInHexagon = 6;
        for (int i = 0; i < numberPointInHexagon; i++) {
            PointCCoA expected = expectedPoints.get(i);
            PointCCoA actual = hexagon.getPoint(i);
            assertEquals(expected, actual);
        }
    }
    @Test
    void testForm_side10StartCenterX0Y0ConstructorNumber4PointRadius() {
        PointCCoA rightBottom = new PointCCoAClass(9.999997408831845, -5.773502691896259);
        PolygonCCoA hexagon = new HexagonClass(10, 4, rightBottom);

        int numberPointInHexagon = 6;
        for (int i = 0; i < numberPointInHexagon; i++) {
            PointCCoA expected = expectedPoints.get(i);
            PointCCoA actual = hexagon.getPoint(i);
            assertEquals(expected, actual);
        }
    }
    @Test
    void testForm_side10StartCenterX0Y0ConstructorNumber5PointRadius() {
        PointCCoA bottom = new PointCCoAClass(-1.295584077354306E-6, -11.547007627809384);
        PolygonCCoA hexagon = new HexagonClass(10, 5, bottom);

        int numberPointInHexagon = 6;
        for (int i = 0; i < numberPointInHexagon; i++) {
            PointCCoA expected = expectedPoints.get(i);
            PointCCoA actual = hexagon.getPoint(i);
            assertEquals(expected, actual);
        }
    }


    @Test
    void getVector_0() {
        PointCCoA center = new PointCCoAClass(0 ,0);
        HexagonVectors hexagonVectors = new HexagonClass(10, center);

    }

    @Test
    void getVector_allDegree() {
        PointCCoA center = new PointCCoAClass(0 ,0);

        PointCCoA origin = center.clone();

        double degreeM180 = -3.14159;
        double degree1 = 0.0174533;
        double degree180 = 3.14159;
        for (double angle = degreeM180; angle < degree180; angle += degree1) {
            HexagonVectors hexagonVectors = new HexagonClass(10, center);
            hexagonVectors.rotateRelative(origin, angle);
            int numberSideHexagon = 6;
            for (int i = 0; i < numberSideHexagon; i++) {
                PointCCoA vector = hexagonVectors.getVectorHexagonWithBottomLeftStart(i);
                PointCCoA expected = vector.getRotateRelative(origin, angle);
                PointCCoA actual = hexagonVectors.getVector(i);
                assertTrue(expected.equals(actual),
                        "angle: " + angle + " i: " + i + " expected: " + expected + " actual: " + actual);
            }
        }
    }

    @Test
    void replacingOrderPointsStandardOne_0() {
        PointCCoA leftTop = new PointCCoAClass(-10.0, 5.773502691896258);
        PolygonCCoA hexagon = new HexagonClass(10, 1, leftTop);

        List<PointCCoA> expectedPoints = new ArrayList<>();

        expectedPoints.add(new PointCCoAClass(-10.0, -5.773502691896259));
        expectedPoints.add(new PointCCoAClass(-10.0, 5.773502691896258));
        expectedPoints.add(new PointCCoAClass(-1.295584077354306E-6, 11.547007627809382));
        expectedPoints.add(new PointCCoAClass(9.999997408831845, 5.773502691896257));
        expectedPoints.add(new PointCCoAClass(9.999997408831845, -5.773502691896259));
        expectedPoints.add(new PointCCoAClass(-1.295584077354306E-6, -11.547007627809384));

        int numberPointInHexagon = 6;
        for (int i = 0; i < numberPointInHexagon; i++) {
            PointCCoA expected = expectedPoints.get(i);
            PointCCoA actual = hexagon.getPoint(i);
            assertEquals(expected, actual);
        }
    }
}