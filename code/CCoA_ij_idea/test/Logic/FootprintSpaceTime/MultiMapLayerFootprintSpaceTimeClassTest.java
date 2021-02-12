package Logic.FootprintSpaceTime;

import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.FabricMovingObjects;
import Logic.FabricMovingObjectsClass;
import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import Logic.LevelLayer;
import Logic.LevelLayerClass;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.Path;
import Logic.MovingObjects.PathClass;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MultiMapLayerFootprintSpaceTimeClassTest {
    LevelLayer defaultLevel = new LevelLayerClass(0);

    FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();
    MovingObject squareMoving = fabricMovingObjects.getMachine(TypeMachinesBody.TEST_SQUARE_20);

    private Corridor tunnel1CorridorY0to20 = tunnel1CorridorY0to20();

    private Corridor tunnel1CorridorY0to20() {
        TreeMap<Double, Round> corridorMap = new TreeMap<>();
        corridorMap.put(0.0, new RoundClass(new PointClass(0, 0), 30));
        corridorMap.put(2.0, new RoundClass(new PointClass(0, 20), 30));
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
            throws СrashIntoAnImpassableObjectExeption {
        Path path = new PathClass();
        path.addPoint(new PointClass(0, 0));
        path.addPoint(new PointClass(0, 20));


        FootprintsSpaceTime footprintsSpaceTime = new FootprintsSpaceTimeClass();
        squareMoving.mark(footprintsSpaceTime, path, 0.0, defaultLevel);

        assertFalse(footprintsSpaceTime.isPathMovingObjectEnteringCorridor(
                squareMoving,
                tunnel1CorridorY0to20,
                defaultLevel)
        );
    }

    @Test
    void isPathMovingObjectEnteringCorridor_tunnel1False() throws СrashIntoAnImpassableObjectExeption {
        Path path = new PathClass();
        path.addPoint(new PointClass(0, 0));
        path.addPoint(new PointClass(0, -20));


        FootprintsSpaceTime footprintsSpaceTime = new FootprintsSpaceTimeClass();
        squareMoving.mark(footprintsSpaceTime, path, 0.0, defaultLevel);

        assertFalse(footprintsSpaceTime.isPathMovingObjectEnteringCorridor(
                squareMoving,
                tunnel1CorridorY0to20,
                defaultLevel)
        );
    }

    @Test
    void isPathMovingObjectEnteringCorridor_tunnel1MachineStanding() throws СrashIntoAnImpassableObjectExeption {
        Path path = new PathClass();
        path.addPoint(new PointClass(0, 0));
        path.addPoint(new PointClass(0, 0));

        FootprintsSpaceTime footprintsSpaceTime = new FootprintsSpaceTimeClass();
        squareMoving.mark(footprintsSpaceTime, path, 0.0, defaultLevel);

        assertFalse(footprintsSpaceTime.isPathMovingObjectEnteringCorridor(
                squareMoving,
                tunnel1CorridorY0to20,
                defaultLevel)
        );
    }


    private Corridor tunnel1CorridorChangingRadius = tunnel1CorridorChangingRadius();

    private Corridor tunnel1CorridorChangingRadius() {
        TreeMap<Double, Round> corridorMap = new TreeMap<>();
        corridorMap.put(0.0, new RoundClass(new PointClass(0, 0), 30));
        corridorMap.put(2.0, new RoundClass(new PointClass(0, 0), 50));
        corridorMap.put(
                CreatorMarksOfPathClass.MAX_TIME_STANDING,
                new RoundClass(new PointClass(0, 0), 50)
        );
        Corridor corridor = new RoundsCorridorClass(corridorMap);
        return corridor;
    }


    @Test
    void isPathMovingObjectEnteringCorridor_continuouslyChangingRadius() throws СrashIntoAnImpassableObjectExeption {
        Path path = new PathClass();
        path.addPoint(new PointClass(0, 0));
        path.addPoint(new PointClass(0, 0));

        FootprintsSpaceTime footprintsSpaceTime = new FootprintsSpaceTimeClass();
        squareMoving.mark(footprintsSpaceTime, path, 0.0, defaultLevel);


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
        corridorMap.put(0.0, new RoundClass(new PointClass(0, 0), 30));
        corridorMap.put(2.0, new RoundClass(new PointClass(0, 20), 30));
        corridorMap.put(4.0, new RoundClass(new PointClass(0, 0), 30));
        corridorMap.put(6.0, new RoundClass(new PointClass(0, 20), 30));
        corridorMap.put(8.0, new RoundClass(new PointClass(0, 0), 30));
        corridorMap.put(10.0, new RoundClass(new PointClass(0, 20), 30));
        corridorMap.put(
                CreatorMarksOfPathClass.MAX_TIME_STANDING,
                new RoundClass(new PointClass(0, 20), 30)
        );
        Corridor corridor = new RoundsCorridorClass(corridorMap);
        return corridor;
    }

    @Test
    void isPathMovingObjectEnteringCorridor_zigZagTest() throws СrashIntoAnImpassableObjectExeption {
        Path path = new PathClass();
        path.addPoint(new PointClass(0, 0));
        path.addPoint(new PointClass(0, 20));
        path.addPoint(new PointClass(0, 0));
        path.addPoint(new PointClass(0, 20));
        path.addPoint(new PointClass(0, 0));
        path.addPoint(new PointClass(0, 20));

        FootprintsSpaceTime footprintsSpaceTime = new FootprintsSpaceTimeClass();
        squareMoving.mark(footprintsSpaceTime, path, 0.0, defaultLevel);


        assertTrue(footprintsSpaceTime.isPathMovingObjectEnteringCorridor(
                squareMoving,
                tunnel1ZigZag,
                defaultLevel)
        );
    }

    @Test
    void isPathMovingObjectEnteringCorridor_zigZagStraightPathMachineTest() throws СrashIntoAnImpassableObjectExeption {
        Path path = new PathClass();
        path.addPoint(new PointClass(0, 0));
        path.addPoint(new PointClass(0, 0));

        FootprintsSpaceTime footprintsSpaceTime = new FootprintsSpaceTimeClass();
        squareMoving.mark(footprintsSpaceTime, path, 0.0, defaultLevel);


        assertFalse(footprintsSpaceTime.isPathMovingObjectEnteringCorridor(
                squareMoving,
                tunnel1ZigZag,
                defaultLevel)
        );
    }


    private Corridor tunnel1CorridorY0to20ToEndOfTime = tunnel1CorridorToEndOfTime();

    private Corridor tunnel1CorridorToEndOfTime() {
        TreeMap<Double, Round> corridorMap = new TreeMap<>();
        corridorMap.put(0.0, new RoundClass(new PointClass(0, 0), 30));
        corridorMap.put(2.0, new RoundClass(new PointClass(0, 20), 30));
        corridorMap.put(
                CreatorMarksOfPathClass.MAX_TIME_STANDING,
                new RoundClass(new PointClass(0, 20), 30)
        );
        Corridor corridor = new RoundsCorridorClass(corridorMap);
        return corridor;
    }


    @Test
    void isPathMovingObjectEnteringCorridor_tunnel1ToEndOfTime()
            throws СrashIntoAnImpassableObjectExeption {
        Path path = new PathClass();
        path.addPoint(new PointClass(0, 0));
        path.addPoint(new PointClass(0, 20));


        FootprintsSpaceTime footprintsSpaceTime = new FootprintsSpaceTimeClass();
        squareMoving.mark(footprintsSpaceTime, path, 0.0, defaultLevel);

        assertTrue(footprintsSpaceTime.isPathMovingObjectEnteringCorridor(
                squareMoving,
                tunnel1CorridorY0to20ToEndOfTime,
                defaultLevel)
        );
    }
    //FIXME add test on idMovingObject. Different movingObjects with same id are included in the same corridor.
}