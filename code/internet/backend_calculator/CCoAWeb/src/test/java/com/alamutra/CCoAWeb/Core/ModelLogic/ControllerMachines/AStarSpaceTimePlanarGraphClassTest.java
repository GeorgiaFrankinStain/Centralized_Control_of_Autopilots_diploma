package com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines;

import com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines.AStar.AStarSpaceTimePlanarGraphClass;
import com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines.FabricNetworkNodes.FabricNetworkNodes;
import com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines.FabricNetworkNodes.FabricSquareNetworkNodes;
import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.*;
import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.alamutra.CCoAWeb.Core.ModelLogic.GlobalVariable;
import com.alamutra.CCoAWeb.Core.ModelLogic.IndexLayer;
import com.alamutra.CCoAWeb.Core.ModelLogic.IndexLayerClass;
import com.alamutra.CCoAWeb.Core.ModelLogic.MovingBody.*;
import com.alamutra.CCoAWeb.Core.ViewSettingRenderingTasks.TypeMachinesBody;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class AStarSpaceTimePlanarGraphClassTest {

    private static final Logger LOG = LoggerFactory.getLogger(AStarSpaceTimePlanarGraphClassTest.class);


    private ParametersMovingUnique squareParametersMovingUnique = createSquareParametersMoving();
    private AlhorithmFastFindPath fastFinderPath = pathCreatedFastFingerPath();
    private IndexLayer defaultLayer = new IndexLayerClass(0);

    AStarSpaceTimePlanarGraphClassTest() throws СrashIntoAnImpassableObjectException {
    }

    private AlhorithmFastFindPath pathCreatedFastFingerPath() {
        FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass();


        FabricNetworkNodes fabricNetworkNodes = new FabricSquareNetworkNodes(squareParametersMovingUnique);
        AlhorithmFastFindPath fastFinderPath = new AStarSpaceTimePlanarGraphClass(fabricNetworkNodes, onlyFootprintsSpaceTime);

        return fastFinderPath;
    }


    private ParametersMovingUnique createSquareParametersMoving() {
        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
        ParametersMovingUnique parametersMovingUnique = fabricParametersMovingUnique.getMoving(TypeMachinesBody.TEST_SQUARE_20);
        return parametersMovingUnique;
    }

    private double timeAddingPath = 0.0;
    ;

    @Test
    public void getPath_gorizontal() throws СrashIntoAnImpassableObjectException {
        IndexLayer defaultIndexLayer = new IndexLayerClass(0);
        {


            PointCCoA from = new PointCCoAClass(0, 0);
            PointCCoA to = new PointCCoAClass(100, 0);

            PathCCoA actualPathCCoA = fastFinderPath.getPath(from, to, squareParametersMovingUnique, timeAddingPath);

            PathCCoA expectedPathCCoA = new PathCCoAClass();
            expectedPathCCoA.addPoint(new PointCCoAClass(0.0, 0.0));
            expectedPathCCoA.addPoint(new PointCCoAClass(20.0, 0.0));
            expectedPathCCoA.addPoint(new PointCCoAClass(40.0, 0.0));
            expectedPathCCoA.addPoint(new PointCCoAClass(60.0, 0.0));
            expectedPathCCoA.addPoint(new PointCCoAClass(80.0, 0.0));
            expectedPathCCoA.addPoint(new PointCCoAClass(100.0, 0.0));

            assertEquals(expectedPathCCoA, actualPathCCoA);
        }
    }

    @Test
    public void getPath_vertical() throws СrashIntoAnImpassableObjectException {
        {

            FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();

            IndexLayer indexLayer = new IndexLayerClass(0);


            ParametersMovingUnique wall = fabricParametersMovingUnique.getMoving(TypeMachinesBody.WALL_CAR);


            PointCCoA from = new PointCCoAClass(60, 0);
            PointCCoA to = new PointCCoAClass(60, 200);

            PathCCoA actualPathCCoA = fastFinderPath.getPath(from, to, squareParametersMovingUnique, timeAddingPath);

            PathCCoA expectedPathCCoA = new PathCCoAClass();
            expectedPathCCoA.addPoint(new PointCCoAClass(60.0, 0.0));
            expectedPathCCoA.addPoint(new PointCCoAClass(60.0, 20.0));
            expectedPathCCoA.addPoint(new PointCCoAClass(60.0, 40.0));
            expectedPathCCoA.addPoint(new PointCCoAClass(60.0, 60.0));
            expectedPathCCoA.addPoint(new PointCCoAClass(60.0, 80.0));
            expectedPathCCoA.addPoint(new PointCCoAClass(60.0, 100.0));
            expectedPathCCoA.addPoint(new PointCCoAClass(60.0, 120.0));
            expectedPathCCoA.addPoint(new PointCCoAClass(60.0, 140.0));
            expectedPathCCoA.addPoint(new PointCCoAClass(60.0, 160.0));
            expectedPathCCoA.addPoint(new PointCCoAClass(60.0, 180.0));
            expectedPathCCoA.addPoint(new PointCCoAClass(60.0, 200.0));

            assertEquals(expectedPathCCoA, actualPathCCoA);
        }
    }

    BoyNextDoorTest aheadBoyNextDoorTest = new BoyNextDoorTestClass();

    @Test
    void getPath_boyNextDoorAhead_car1EnteringCorridor() {
        assertTrue(aheadBoyNextDoorTest.isCar1EnteringCorridor());
    }

    @Test
    void getPath_boyNextDoorAhead_isCar1StartPointEqualsStartTaskPath() {
        assertTrue(aheadBoyNextDoorTest.isCar1StartPointEqualsStartTaskPath());
    }

    @Test
    void getPath_boyNextDoorAhead_isCar1EndPointEqualsEndTaskPath() {
        assertTrue(aheadBoyNextDoorTest.isCar1EndPointEqualsEndTaskPath());
    }

    @Test
    void getPath_boyNextDoorAhead_car2EnteringCorridor() {
        assertTrue(aheadBoyNextDoorTest.isCar2EnteringCorridor());
    }

    @Test
    void getPath_boyNextDoorAhead_isCar2StartPointEqualsStartTaskPath() {
        assertTrue(aheadBoyNextDoorTest.isCar2StartPointEqualsStartTaskPath());
    }

    @Test
    void getPath_boyNextDoorAhead_isCar2EndPointEqualsEndTaskPath() {
        assertTrue(aheadBoyNextDoorTest.isCar2EndPointEqualsEndTaskPath());
    }


    private interface BoyNextDoorTest {
        boolean isCar1EnteringCorridor();

        boolean isCar1StartPointEqualsStartTaskPath();
        boolean isCar1EndPointEqualsEndTaskPath();

        boolean isCar2EnteringCorridor();

        boolean isCar2StartPointEqualsStartTaskPath();
        boolean isCar2EndPointEqualsEndTaskPath();
    }

    private class BoyNextDoorTestClass implements BoyNextDoorTest {
        private ParametersMovingUnique car1 = createSquareParametersMoving();
        private ParametersMovingUnique car2 = createSquareParametersMoving();


        private PointCCoA from1 = new PointCCoAClass(0, 0);
        private PointCCoA to1 = new PointCCoAClass(0, 200);


        private double widthCar = 20;
        private double withCar = widthCar + GlobalVariable.DOUBLE_COMPARISON_ACCURACY;
        private PointCCoA aheadOffset = new PointCCoAClass(0, withCar);
        private PointCCoA fromBoyNextDoor = from1.getDeposeOn(aheadOffset);
        private PointCCoA toBoyNextDoor = to1.getDeposeOn(aheadOffset);

        private FootprintsSpaceTime footprintsSpaceTime = new FootprintsSpaceTimeClass();

        private PathCCoA actualPathCCoA = fastFinderPath.getPath(
                from1,
                to1,
                car1, timeAddingPath
        );

        private PathCCoA pathCar2 = createPath2();

        private Corridor corridor = createCorridor1();

        private Corridor corridor2 = createCorridor2();

        public BoyNextDoorTestClass() throws СrashIntoAnImpassableObjectException {
            car1.mark(footprintsSpaceTime, actualPathCCoA, timeAddingPath, defaultLayer);
            car2.mark(footprintsSpaceTime, pathCar2, timeAddingPath, defaultLayer);
        }

        @Override
        public boolean isCar1EnteringCorridor() {
            return footprintsSpaceTime.isPathMovingObjectEnteringCorridor(car1, corridor, defaultLayer);
        }

        @Override
        public boolean isCar1StartPointEqualsStartTaskPath() {
            PointCCoA startPath = actualPathCCoA.getPoint(0);
            return from1.equals(startPath);
        }

        @Override
        public boolean isCar1EndPointEqualsEndTaskPath() {
            PointCCoA endPath = actualPathCCoA.getPointLast();
            return to1.equals(endPath);
        }

        @Override
        public boolean isCar2EnteringCorridor() {
            return footprintsSpaceTime.isPathMovingObjectEnteringCorridor(car2, corridor2, defaultLayer);
        }

        @Override
        public boolean isCar2StartPointEqualsStartTaskPath() {
            PointCCoA startPath2 = pathCar2.getPoint(0);
            return startPath2.equals(fromBoyNextDoor);
        }

        @Override
        public boolean isCar2EndPointEqualsEndTaskPath() {
            PointCCoA endPath2 = pathCar2.getPointLast();
            return endPath2.equals(toBoyNextDoor);
        }

        private PathCCoA createPath2() {

            PathCCoA pathCar2 = fastFinderPath.getPath(
                    fromBoyNextDoor,
                    toBoyNextDoor,
                    car2,
                    timeAddingPath

            );

            return pathCar2;
        }

        private Corridor createCorridor1() {
            double diameterCar = car1.getRadius() * 2 + GlobalVariable.DOUBLE_COMPARISON_ACCURACY;
            TreeMap<Double, Round> forCorridor = new TreeMap<>();
            forCorridor.put(-1.0, new RoundClass(from1, diameterCar));
            forCorridor.put(20.1, new RoundClass(to1, diameterCar));
            forCorridor.put(Double.MAX_VALUE, new RoundClass(to1, diameterCar));
            Corridor corridor = new RoundsCorridorClass(forCorridor);
            return corridor;
        }

        private Corridor createCorridor2() {
            double diameterCar = car2.getRadius() * 2 + GlobalVariable.DOUBLE_COMPARISON_ACCURACY;
            TreeMap<Double, Round> forCorridor2 = new TreeMap<>();
            forCorridor2.put(-1.0, new RoundClass(fromBoyNextDoor, diameterCar));
            forCorridor2.put(20.1, new RoundClass(toBoyNextDoor, diameterCar));
            forCorridor2.put(Double.MAX_VALUE, new RoundClass(toBoyNextDoor, diameterCar));
            Corridor corridor2 = new RoundsCorridorClass(forCorridor2);
            return corridor2;
        }
    }
}