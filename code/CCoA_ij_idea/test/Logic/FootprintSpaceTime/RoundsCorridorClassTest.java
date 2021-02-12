package Logic.FootprintSpaceTime;

import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class RoundsCorridorClassTest {

    private Corridor corridorSizeFrom0To2 = createCorridorFrom0To2();

    private Corridor createCorridorFrom0To2() {
        TreeMap<Double, Round> forCorridor = new TreeMap<>();
        forCorridor.put(0.0, new RoundClass(new PointClass(0, 0), 1));
        forCorridor.put(2.0, new RoundClass(new PointClass(2, 2), 1));
        Corridor corridor = new RoundsCorridorClass(forCorridor);
        return corridor;
    }

    private PolygonExtended polygonNearOriginRadius01 = createPolygonNearOriginRadius01();

    private PolygonExtended createPolygonNearOriginRadius01() {
        PolygonExtended polygon = new PolygonExtendedClass();
        polygon.addPoint(new PointClass(0.1, 0.1));
        polygon.addPoint(new PointClass(0.1, -0.1));
        polygon.addPoint(new PointClass(-0.1, -0.1));
        polygon.addPoint(new PointClass(-0.1, 0.1));

        return polygon;
    }


    @Test
    void isPolygonEntering_corridorSize2TimeM1() {
        assertFalse(corridorSizeFrom0To2.isCoverPolygon(-1.0, polygonNearOriginRadius01));
    }

    @Test
    void isPolygonEntering_corridorSize2Time0() {
        assertTrue(corridorSizeFrom0To2.isCoverPolygon(0.0, polygonNearOriginRadius01));
    }

    @Test
    void isPolygonEntering_corridorSize2Time1() {
        assertFalse(corridorSizeFrom0To2.isCoverPolygon(1.0, polygonNearOriginRadius01));
    }

    @Test
    void isPolygonEntering_corridorSize2Time2() {
        assertFalse(corridorSizeFrom0To2.isCoverPolygon(2.0, polygonNearOriginRadius01));
    }

    @Test
    void isPolygonEntering_corridorSize2Time3() {
        assertFalse(corridorSizeFrom0To2.isCoverPolygon(3.0, polygonNearOriginRadius01));
    }


    private PolygonExtended polygonNearOriginRadius2 = createPolygonNearOriginRadius2();

    private PolygonExtended createPolygonNearOriginRadius2() {
        PolygonExtended polygon = new PolygonExtendedClass();
        polygon.addPoint(new PointClass(2, 2));
        polygon.addPoint(new PointClass(2, -2));
        polygon.addPoint(new PointClass(-2, -2));
        polygon.addPoint(new PointClass(-2, 2));

        return polygon;
    }


    @Test
    void isPolygonEntering_CorridorSize2Time0_partialEntry() {
        assertFalse(corridorSizeFrom0To2.isCoverPolygon(0.0, polygonNearOriginRadius2));
    }

    @Test
    void isPolygonEntering_corridorSize2Time1_partialEntry() {
        assertFalse(corridorSizeFrom0To2.isCoverPolygon(1.0, polygonNearOriginRadius2));
    }

    @Test
    void isPolygonEntering_corridorSize2Time2_partialEntry() {
        assertFalse(corridorSizeFrom0To2.isCoverPolygon(2.0, polygonNearOriginRadius2));
    }


    private PolygonExtended polygon1d1Radius1 = createPolygon1d1Radius1();

    private PolygonExtended createPolygon1d1Radius1() {
        PolygonExtended polygon = polygonNearOriginRadius01.clone();
        Point vector = new PointClass(1, 1);
        polygon.deposeOn(vector);

        return polygon;
    }

    @Test
    void isPolygonEntering_corridorSize2Time0_centralCorridorEntry() {
        assertFalse(corridorSizeFrom0To2.isCoverPolygon(0.0, polygon1d1Radius1));
    }

    @Test
    void isPolygonEntering_corridorSize2Time1_centralCorridorEntry() {
        assertTrue(corridorSizeFrom0To2.isCoverPolygon(1.0, polygon1d1Radius1));
    }

    @Test
    void isPolygonEntering_corridorSize2Time2_centralCorridorEntry() {
        assertFalse(corridorSizeFrom0To2.isCoverPolygon(2.0, polygon1d1Radius1));
    }


    private PolygonExtended polygon2d2Radius1 = createPolygon2d2Radius1();

    private PolygonExtended createPolygon2d2Radius1() {
        PolygonExtended polygon = polygonNearOriginRadius01.clone();
        Point vector = new PointClass(2, 2);
        polygon.deposeOn(vector);

        return polygon;
    }

    @Test
    void isPolygonEntering_corridorSize2Time0_endCorridorEntry() {
        assertFalse(corridorSizeFrom0To2.isCoverPolygon(0.0, polygon2d2Radius1));
    }

    @Test
    void isPolygonEntering_corridorSize2Time1_endCorridorEntry() {
        assertFalse(corridorSizeFrom0To2.isCoverPolygon(1.0, polygon2d2Radius1));
    }

    @Test
    void isPolygonEntering_corridorSize2Time2_endCorridorEntry() {
        assertTrue(corridorSizeFrom0To2.isCoverPolygon(2.0, polygon2d2Radius1));
    }
}