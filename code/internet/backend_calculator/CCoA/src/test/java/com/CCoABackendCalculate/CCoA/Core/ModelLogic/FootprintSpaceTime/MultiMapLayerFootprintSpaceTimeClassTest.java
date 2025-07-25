package com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.GlobalVariable;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.IndexLayer;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.IndexLayerClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.*;
import com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.TypeMachinesBody;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class MultiMapLayerFootprintSpaceTimeClassTest {
    private IndexLayer defaultLevel = new IndexLayerClass(0);

    FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
    ParametersMovingUnique squareMoving = fabricParametersMovingUnique.getMoving(TypeMachinesBody.TEST_SQUARE_20, 13);

    private Corridor tunnel1CorridorY0to20 = tunnel1CorridorY0to20();

    MultiMapLayerFootprintSpaceTimeClassTest() throws СrashIntoAnImpassableObjectException {
    }

    private Corridor tunnel1CorridorY0to20() {
        TreeMap<Double, Round> corridorMap = new TreeMap<>();
        corridorMap.put(0.0, new RoundClass(new PointCCoAClass(0, 0), 30));
        corridorMap.put(2.0, new RoundClass(new PointCCoAClass(0, 20), 30));
        Corridor corridor = new RoundsCorridorClass(corridorMap);
        return corridor;
    }

    @Test
    void isPathMovingObjectEnteringCorridor_tunnel1NotPath() {
        LayerFootprintSpaceTime layerFootprintSpaceTime = new MultiMapLayerFootprintSpaceTimeClass();
        assertFalse(layerFootprintSpaceTime.isPathMovingObjectEnteringCorridor(squareMoving, tunnel1CorridorY0to20));
    }

    @Test
    void isPathMovingObjectEnteringCorridor_tunnel1CorridorDoesNotTakeIntoAccountStopUntilEndOfTime()
            throws СrashIntoAnImpassableObjectException {
        PathCCoA pathCCoA = new PathCCoAClass();
        pathCCoA.addPoint(new PointCCoAClass(0, 0));
        pathCCoA.addPoint(new PointCCoAClass(0, 20));


        FootprintsSpaceTime footprintsSpaceTime = new FootprintsSpaceTimeClass();
        squareMoving.mark(footprintsSpaceTime, pathCCoA, 0.0, defaultLevel);

        assertFalse(footprintsSpaceTime.isPathMovingObjectEnteringCorridor(
                squareMoving,
                tunnel1CorridorY0to20,
                defaultLevel)
        );
    }

    @Test
    void isPathMovingObjectEnteringCorridor_tunnel1False() throws СrashIntoAnImpassableObjectException {
        PathCCoA pathCCoA = new PathCCoAClass();
        pathCCoA.addPoint(new PointCCoAClass(0, 0));
        pathCCoA.addPoint(new PointCCoAClass(0, -20));


        FootprintsSpaceTime footprintsSpaceTime = new FootprintsSpaceTimeClass();
        squareMoving.mark(footprintsSpaceTime, pathCCoA, 0.0, defaultLevel);

        assertFalse(footprintsSpaceTime.isPathMovingObjectEnteringCorridor(
                squareMoving,
                tunnel1CorridorY0to20,
                defaultLevel)
        );
    }

    @Test
    void isPathMovingObjectEnteringCorridor_tunnel1MachineStanding() throws СrashIntoAnImpassableObjectException {
        PathCCoA pathCCoA = new PathCCoAClass();
        pathCCoA.addPoint(new PointCCoAClass(0, 0));
        pathCCoA.addPoint(new PointCCoAClass(0, 0));

        FootprintsSpaceTime footprintsSpaceTime = new FootprintsSpaceTimeClass();
        squareMoving.mark(footprintsSpaceTime, pathCCoA, 0.0, defaultLevel);

        assertFalse(footprintsSpaceTime.isPathMovingObjectEnteringCorridor(
                squareMoving,
                tunnel1CorridorY0to20,
                defaultLevel)
        );
    }


    private Corridor tunnel1CorridorChangingRadius = tunnel1CorridorChangingRadius();

    private Corridor tunnel1CorridorChangingRadius() {
        TreeMap<Double, Round> corridorMap = new TreeMap<>();
        corridorMap.put(0.0, new RoundClass(new PointCCoAClass(0, 0), 30));
        corridorMap.put(2.0, new RoundClass(new PointCCoAClass(0, 0), 50));
        corridorMap.put(
                GlobalVariable.MAX_TIME_STANDING,
                new RoundClass(new PointCCoAClass(0, 0), 50)
        );
        Corridor corridor = new RoundsCorridorClass(corridorMap);
        return corridor;
    }


    @Test
    void isPathMovingObjectEnteringCorridor_continuouslyChangingRadius() throws СrashIntoAnImpassableObjectException {
        PathCCoA pathCCoA = new PathCCoAClass();
        pathCCoA.addPoint(new PointCCoAClass(0, 0));
        pathCCoA.addPoint(new PointCCoAClass(0, 0));

        FootprintsSpaceTime footprintsSpaceTime = new FootprintsSpaceTimeClass();
        squareMoving.mark(footprintsSpaceTime, pathCCoA, 0.0, defaultLevel);


        assertTrue(footprintsSpaceTime.isPathMovingObjectEnteringCorridor(
                squareMoving,
                tunnel1CorridorChangingRadius,
                defaultLevel)
        );
    }


    /*
     * ZIG-ZAG and Straight path machine TEST
     * + corridor           x path machine
     * +                    x
     *  \                   |
     *   \                  |
     *    \                 |
     *     +                |
     *    /                 |
     *   /                  |
     *  /                   |
     * +                    x
     * In the situation show above, there will be error
     * checked entry into the corridor, if you check only points
     * of the path of machine
     */

    private Corridor tunnel1ZigZag = tunnel1CorridorZigZag();

    private Corridor tunnel1CorridorZigZag() {
        TreeMap<Double, Round> corridorMap = new TreeMap<>();
        corridorMap.put(0.0, new RoundClass(new PointCCoAClass(0, 0), 30));
        corridorMap.put(2.0, new RoundClass(new PointCCoAClass(0, 20), 30));
        corridorMap.put(4.0, new RoundClass(new PointCCoAClass(0, 0), 30));
        corridorMap.put(6.0, new RoundClass(new PointCCoAClass(0, 20), 30));
        corridorMap.put(8.0, new RoundClass(new PointCCoAClass(0, 0), 30));
        corridorMap.put(10.0, new RoundClass(new PointCCoAClass(0, 20), 30));
        corridorMap.put(
                GlobalVariable.MAX_TIME_STANDING,
                new RoundClass(new PointCCoAClass(0, 20), 30)
        );
        Corridor corridor = new RoundsCorridorClass(corridorMap);
        return corridor;
    }

    @Test
    void isPathMovingObjectEnteringCorridor_zigZagTest() throws СrashIntoAnImpassableObjectException {
        PathCCoA pathCCoA = new PathCCoAClass();
        pathCCoA.addPoint(new PointCCoAClass(0, 0));
        pathCCoA.addPoint(new PointCCoAClass(0, 20));
        pathCCoA.addPoint(new PointCCoAClass(0, 0));
        pathCCoA.addPoint(new PointCCoAClass(0, 20));
        pathCCoA.addPoint(new PointCCoAClass(0, 0));
        pathCCoA.addPoint(new PointCCoAClass(0, 20));

        FootprintsSpaceTime footprintsSpaceTime = new FootprintsSpaceTimeClass();
        squareMoving.mark(footprintsSpaceTime, pathCCoA, 0.0, defaultLevel);


        assertTrue(footprintsSpaceTime.isPathMovingObjectEnteringCorridor(
                squareMoving,
                tunnel1ZigZag,
                defaultLevel)
        );
    }

    @Test
    void isPathMovingObjectEnteringCorridor_zigZagStraightPathMachineTest() throws СrashIntoAnImpassableObjectException {
        PathCCoA pathCCoA = new PathCCoAClass();
        pathCCoA.addPoint(new PointCCoAClass(0, 0));
        pathCCoA.addPoint(new PointCCoAClass(0, 0));

        FootprintsSpaceTime footprintsSpaceTime = new FootprintsSpaceTimeClass();
        squareMoving.mark(footprintsSpaceTime, pathCCoA, 0.0, defaultLevel);


        assertFalse(footprintsSpaceTime.isPathMovingObjectEnteringCorridor(
                squareMoving,
                tunnel1ZigZag,
                defaultLevel)
        );
    }


    private Corridor tunnel1CorridorY0to20ToEndOfTime = tunnel1CorridorToEndOfTime();

    private Corridor tunnel1CorridorToEndOfTime() {
        TreeMap<Double, Round> corridorMap = new TreeMap<>();
        corridorMap.put(0.0, new RoundClass(new PointCCoAClass(0, 0), 30));
        corridorMap.put(2.0, new RoundClass(new PointCCoAClass(0, 20), 30));
        corridorMap.put(
                GlobalVariable.MAX_TIME_STANDING,
                new RoundClass(new PointCCoAClass(0, 20), 30)
        );
        Corridor corridor = new RoundsCorridorClass(corridorMap);
        return corridor;
    }

    @Test
    void isPathMovingObjectEnteringCorridor_tunnel1ToEndOfTime()
            throws СrashIntoAnImpassableObjectException {
        PathCCoA pathCCoA = new PathCCoAClass();
        pathCCoA.addPoint(new PointCCoAClass(0, 0));
        pathCCoA.addPoint(new PointCCoAClass(0, 20));


        FootprintsSpaceTime footprintsSpaceTime = new FootprintsSpaceTimeClass();
        squareMoving.mark(footprintsSpaceTime, pathCCoA, 0.0, defaultLevel);

        assertTrue(footprintsSpaceTime.isPathMovingObjectEnteringCorridor(
                squareMoving,
                tunnel1CorridorY0to20ToEndOfTime,
                defaultLevel)
        );
    }



    private Corridor tunnel1CorridorY0to20WithoutStandingUntilEndTime = tunnel1Corridor();

    private Corridor tunnel1Corridor() {
        TreeMap<Double, Round> corridorMap = new TreeMap<>();
        corridorMap.put(0.0, new RoundClass(new PointCCoAClass(0, 0), 30));
        corridorMap.put(2.0, new RoundClass(new PointCCoAClass(0, 20), 30));
        corridorMap.put(
                2.0 + GlobalVariable.MIN_TIME_STANDING,
                new RoundClass(new PointCCoAClass(0, 20), 30)
        );

        Corridor corridor = new RoundsCorridorClass(corridorMap);
        return corridor;
    }

    @Test
    void addFootprintsPathWithoutEndStandingUntilEndTime_tunnel1ToEndOfTime()
            throws СrashIntoAnImpassableObjectException {
        PathCCoA pathCCoA = new PathCCoAClass();
        pathCCoA.addPoint(new PointCCoAClass(0, 0));
        pathCCoA.addPoint(new PointCCoAClass(0, 20));


        FootprintsSpaceTime footprintsSpaceTime = new FootprintsSpaceTimeClass();
        squareMoving.markWithoutStandingUntilEndTime(footprintsSpaceTime, pathCCoA, 0.0, defaultLevel);

        boolean res = footprintsSpaceTime.isPathMovingObjectEnteringCorridor(
                squareMoving,
                tunnel1CorridorY0to20WithoutStandingUntilEndTime,
                defaultLevel);
        assertTrue(res
        );
    }


    /*
     * F - free space
     * T - take seat place
     *
     * IS GOOD:
     * Time
     * ^
     * |                      FFFFFFFFFFFFFFF
     * |                    F5 +-------+ F10
     * |                 F4 +-------+ F9
     * |              F3 +-------+ F8
     * |           F2 +-------+ F7
     * |        F1 +-------+ F6
     * |       FFFFFFFFFFFFFFFF
     * +--------------------------------------> XY
     *
     *
     * Time
     * ^
     * |                      FFFFFFFFFFFFFFF
     * |                    F5 +-------+ F10
     * |                 F4 +-------TTTTTTT (one point shared)
     * |              F3 +-----TTTTTTTTT
     * |           F2 +-------+ F7
     * |        F1 +-------+ F6
     * |       FFFFFFFFFFFFFFFF
     * +--------------------------------------> XY
     *
     *
     * IS BAD (teleportation instead of uniform movement):
     * Time
     * ^
     * |            F +-------+ F
     * |            F +-------+ F
     * |            F +-------+ F
     * |   F +-------+ F
     * |   F +-------+ F
     * |   F +-------+ F
     * +--------------------------------------> XY
     *
     */

    private FootprintsSpaceTime footprintsSpaceTimeStraightLine = movingInStraightLine();
    private PolygonCCoA square40 = createSquare40();

    private PolygonCCoA createSquare40() {
        PolygonCCoA square40 = new PolygonCCoAClass();
        square40.addPoint(new PointCCoAClass(-20, -20));
        square40.addPoint(new PointCCoAClass(20, -20));
        square40.addPoint(new PointCCoAClass(20, 20));
        square40.addPoint(new PointCCoAClass(-20, 20));

        return square40;
    }


    private FootprintsSpaceTime movingInStraightLine() throws СrashIntoAnImpassableObjectException {
        FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass();

        ParametersMovingUnique movingObjectSimpleParametersMovingOnLineUnique = createTestSquare20Machine();

        PathCCoA pathCCoA = new PathCCoAClass();
        pathCCoA.addPoint(new PointCCoAClass(0, 0));
        pathCCoA.addPoint(new PointCCoAClass(100, 0));

        movingObjectSimpleParametersMovingOnLineUnique.mark(onlyFootprintsSpaceTime, pathCCoA, 0.0, defaultLevel);
        return onlyFootprintsSpaceTime;
    }

    private ParametersMovingUnique createTestSquare20Machine() {
        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
        ParametersMovingUnique parametersMovingUnique =
                fabricParametersMovingUnique.getMoving(TypeMachinesBody.TEST_SQUARE_20, 13);
        return parametersMovingUnique;
    }

    private double getSpeedTestSquare20() {
        ParametersMovingUnique parametersMovingUnique = createTestSquare20Machine();
        return parametersMovingUnique.getSpeed();
    }


    @Test
    void testGetIsSeatTaken_ApproximationLinearBottom() {
        PolygonCCoA place = square40.clone();

        boolean placeIsTaken = footprintsSpaceTimeStraightLine.isSeatTaken(place, -0.01, defaultLevel);
        assertFalse(placeIsTaken);
    }
    @Test
    void testGetIsSeatTaken_ApproximationLinearTopStandingEndless() {
        PolygonCCoA place = square40.clone();

        place.deposeOn(new PointCCoAClass(100, 0));

        boolean placeIsTaken = footprintsSpaceTimeStraightLine.isSeatTaken(place, 16.1, defaultLevel);
        assertTrue(placeIsTaken);
    }

    @Test
    void testGetIsSeatTaken_ApproximationLinearFree() {
        double speed = getSpeedTestSquare20();
        double timeVectorSec = 0.05;

        for (int i = 0; i < 20; i++) {
            PointCCoA spaceMoveVector = new PointCCoAClass(speed * timeVectorSec * i, 0);
            String iData = "i: " + i + " timeVector: " + timeVectorSec + " vector: " + spaceMoveVector;
            assertFalse(
                    testGetIsSeatTaken_ApproximationLinearFree1FromBehind(timeVectorSec * i, spaceMoveVector),
                    iData + " FromBehind"
            );
            assertFalse(
                    testGetIsSeatTaken_ApproximationLinearFree1Ahead(timeVectorSec * i, spaceMoveVector),
                    iData + " Ahead"
            );
            assertFalse(
                    testGetIsSeatTaken_ApproximationLinearFree1Right(timeVectorSec * i, spaceMoveVector),
                    iData + " Right"
            );
            assertFalse(
                    testGetIsSeatTaken_ApproximationLinearFree1Left(timeVectorSec * i, spaceMoveVector),
                    iData + " Left"
            );
        }
    }
    @Test
    void testGetIsSeatTaken_ApproximationLinearIsSeatTakenOnePoint() {
        double speed = getSpeedTestSquare20();
        double timeVectorSec = 0.05;

        /*
         * TODO: precision break in 12 iteration.
         * The accuracy is sufficient to describe reality.
         * I consider this an acceptable margin of error.
        **/
        for (int i = 0; i < 11; i++) {
            int forContactAtOnePoint = 1;
            double timeOffset = timeVectorSec * i;
            double xOffset = speed * timeVectorSec * i;
            PointCCoA spaceMoveVector = new PointCCoAClass(xOffset, 0);
            String iData = "i: " + i + " timeVector: " + timeVectorSec + " vector: " + spaceMoveVector;

            PointCCoA spaceMoveVectorFromBehind = new PointCCoAClass(xOffset + forContactAtOnePoint, 0);
            assertTrue(
                    testGetIsSeatTaken_ApproximationLinearFree1FromBehind(timeOffset, spaceMoveVectorFromBehind),
                    iData + " FromBehind" + " localVector: " + spaceMoveVectorFromBehind
            );
            PointCCoA spaceMoveVectorFromAhead = new PointCCoAClass(xOffset - forContactAtOnePoint, 0);
            assertTrue(
                    testGetIsSeatTaken_ApproximationLinearFree1Ahead(timeOffset, spaceMoveVectorFromAhead),
                    iData + " Ahead"
            );

            PointCCoA spaceMoveVectorFromRight = new PointCCoAClass(xOffset, forContactAtOnePoint);
            assertTrue(
                    testGetIsSeatTaken_ApproximationLinearFree1Right(timeOffset, spaceMoveVectorFromRight),
                    iData + " Right"
            );

            PointCCoA spaceMoveVectorFromLeft = new PointCCoAClass(xOffset, -forContactAtOnePoint);
            assertTrue(
                    testGetIsSeatTaken_ApproximationLinearFree1Left(timeOffset, spaceMoveVectorFromLeft),
                    iData + " Left"
            );
        }
    }
    @Test
    void testGetIsSeatTaken_ApproximationLinearIsSeatTaken() {
        double speed = getSpeedTestSquare20();
        double timeVectorSec = 0.05;

        for (int i = 0; i < 11; i++) {
            int forContactAtOnePoint = 7;
            double timeOffset = timeVectorSec * i;
            double xOffset = speed * timeVectorSec * i;
            PointCCoA spaceMoveVector = new PointCCoAClass(xOffset, 0);
            String iData = "i: " + i + " timeVector: " + timeVectorSec + " vector: " + spaceMoveVector;

            PointCCoA spaceMoveVectorFromBehind = new PointCCoAClass(xOffset + forContactAtOnePoint, 0);
            assertTrue(
                    testGetIsSeatTaken_ApproximationLinearFree1FromBehind(timeOffset, spaceMoveVectorFromBehind),
                    iData + " FromBehind" + " localVector: " + spaceMoveVectorFromBehind
            );
            PointCCoA spaceMoveVectorFromAhead = new PointCCoAClass(xOffset - forContactAtOnePoint, 0);
            assertTrue(
                    testGetIsSeatTaken_ApproximationLinearFree1Ahead(timeOffset, spaceMoveVectorFromAhead),
                    iData + " Ahead"
            );

            PointCCoA spaceMoveVectorFromRight = new PointCCoAClass(xOffset, forContactAtOnePoint);
            assertTrue(
                    testGetIsSeatTaken_ApproximationLinearFree1Right(timeOffset, spaceMoveVectorFromRight),
                    iData + " Right"
            );

            PointCCoA spaceMoveVectorFromLeft = new PointCCoAClass(xOffset, -forContactAtOnePoint);
            assertTrue(
                    testGetIsSeatTaken_ApproximationLinearFree1Left(timeOffset, spaceMoveVectorFromLeft),
                    iData + " Left"
            );
        }
    }


    private boolean testGetIsSeatTaken_ApproximationLinearFree1FromBehind(double timeOffset, PointCCoA vector) {
        PolygonCCoA place = square40.clone();

        place.deposeOn(new PointCCoAClass(-31, 0));
        place.deposeOn(vector);

        boolean placeIsTaken = footprintsSpaceTimeStraightLine.isSeatTaken(place, 0.0 + timeOffset, defaultLevel);
        return placeIsTaken;
    }

    private boolean testGetIsSeatTaken_ApproximationLinearFree1Ahead(double timeOffset, PointCCoA vector) {
        PolygonCCoA place = square40.clone();

        place.deposeOn(new PointCCoAClass(31, 0));
        place.deposeOn(vector);

        boolean placeIsTaken = footprintsSpaceTimeStraightLine.isSeatTaken(place, 0.0 + timeOffset, defaultLevel);
        return placeIsTaken;
    }

    private boolean testGetIsSeatTaken_ApproximationLinearFree1Right(double timeOffset, PointCCoA vector) {
        PolygonCCoA place = square40.clone();

        place.deposeOn(new PointCCoAClass(0, -31));
        place.deposeOn(vector);

        boolean placeIsTaken = footprintsSpaceTimeStraightLine.isSeatTaken(place, 0.0 + timeOffset, defaultLevel);
        return placeIsTaken;
    }

    private boolean testGetIsSeatTaken_ApproximationLinearFree1Left(double timeOffset, PointCCoA vector) {
        PolygonCCoA place = square40.clone();

        place.deposeOn(new PointCCoAClass(0, 31));
        place.deposeOn(vector);

        boolean placeIsTaken = footprintsSpaceTimeStraightLine.isSeatTaken(place, 0.0 + timeOffset, defaultLevel);
        return placeIsTaken;
    }

    /*
     * T - test is seat taken
     *
     * Time
     * ^
     * |   F +TTTTTTT+ F
     * |   F +-------+ F
     * |   F +TTTTTTT+ F
     * +--------------------------------------> XY
     */

    @Test
    void testGetIsSeatTaken_BottomBorderIsTaken() {

    }


    /*
     * T - test is seat taken
     *
     * Time
     * ^
     * |   FFFFFFFFFFFFF
     * |   F +-------TTTTTTT (one pointer shared)
     * |   F +----TTTTTTTTTT
     * |   F +-------+ F
     * |   FFFFFFFFFFFFF
     * +--------------------------------------> XY
     */
    @Test
    void testGetIsSeatTaken_TopBorderIsTaken() {

    }

    //FIXME add test on idMovingObject. Different movingObjects with same id are included in the same corridor.
}