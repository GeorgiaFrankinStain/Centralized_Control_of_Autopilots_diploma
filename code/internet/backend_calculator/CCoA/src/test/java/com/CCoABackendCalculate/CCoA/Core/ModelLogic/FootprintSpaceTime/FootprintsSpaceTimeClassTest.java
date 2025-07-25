package com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.AStar.AStarSpaceTimePlanarGraphClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.AlhorithmFastFindPath;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.FabricNetworkNodes.FabricHexagonNodes;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.FabricNetworkNodes.FabricNetworkNodes;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.Hexagon.HexagonTile;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.NetworkNodes;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.GlobalVariable;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.IndexLayer;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.IndexLayerClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.*;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.PathsMachines.PositionClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.Position;
import com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.HistoryChanges;
import com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.TypeMachinesBody;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.*;

class FootprintsSpaceTimeClassTest { //FIXME add test add path of moving object with speed 0

    FootprintsSpaceTimeClassTest() throws СrashIntoAnImpassableObjectException {
    }


    private IndexLayer defaultIndexLayer = new IndexLayerClass(0);

    PolygonCCoA areaRendering = new PolygonCCoAClass(Arrays.<PointCCoA>asList(new PointCCoAClass[]{
            new PointCCoAClass(0, 0),
            new PointCCoAClass(900, 0),
            new PointCCoAClass(900, 900),
            new PointCCoAClass(0, 900)
    }));

    @Test
    void addFootprintsPathWithoutEndStandingUntilEndTime() {
    }

    @Test
    void testEquals_sameCommand() throws СrashIntoAnImpassableObjectException {
        FootprintsSpaceTime footprintsSpaceTime1 = createFootprintSpaceTime1();
        FootprintsSpaceTime footprintsSpaceTime2 = createFootprintSpaceTime1();

        assertTrue(footprintsSpaceTime1.equalsWithoutUniqueId(footprintsSpaceTime2));
    }

    @Test
    void testEquals_notSameCommand() throws СrashIntoAnImpassableObjectException {
        FootprintsSpaceTime footprintsSpaceTime1 = createFootprintSpaceTime1();
        FootprintsSpaceTime footprintsSpaceTime2 = createFootprintSpaceTime2();

        assertFalse(footprintsSpaceTime1.equalsWithoutUniqueId(footprintsSpaceTime2));
    }
    @Test
    void testEquals_twoMachinesCommand() throws СrashIntoAnImpassableObjectException {
        FootprintsSpaceTime footprintsSpaceTime1 = createFootprintSpaceTimeTwoMachines();
        FootprintsSpaceTime footprintsSpaceTime2 = createFootprintSpaceTimeTwoMachines();

        assertTrue(footprintsSpaceTime1.equalsWithoutUniqueId(footprintsSpaceTime2));
    }

    @Test
    void testEquals_sameSelf() throws СrashIntoAnImpassableObjectException {
        FootprintsSpaceTime footprintsSpaceTime1 = createFootprintSpaceTime1();

        assertEquals(footprintsSpaceTime1, footprintsSpaceTime1);
    }

    private FootprintsSpaceTime createFootprintSpaceTimeTwoMachines() throws СrashIntoAnImpassableObjectException {


        FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass(); //create FootprintsSpaceTime (Landscape) //PUNKT_1

        IndexLayer indexLayer = new IndexLayerClass(0);



        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
        ParametersMovingUnique parametersMovingUnique = fabricParametersMovingUnique.getMoving(
                TypeMachinesBody.TEST_SQUARE_20,
                onlyFootprintsSpaceTime.getIdForNewParametersMovingUnique()
        );
        double degree60 = 1.0472;
        FabricNetworkNodes fabricNetworkNodes = new FabricHexagonNodes(degree60 / 9, parametersMovingUnique);
        AlhorithmFastFindPath fastFinderPath = new AStarSpaceTimePlanarGraphClass(fabricNetworkNodes, onlyFootprintsSpaceTime);


        {
            PointCCoA from = new PointCCoAClass(0, 131); //FIXME BAG don't multipoly 20 (size car)
            PointCCoA to = new PointCCoAClass(0, 300);


            double timeAdding = 0.0;
            PathCCoA actualPath = fastFinderPath.getPath(from, to, parametersMovingUnique, timeAdding);
            try {
                parametersMovingUnique.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer); //FIXME bag sequense time adding
            } catch (СrashIntoAnImpassableObjectException ex) {
            }
        }
        {
            ParametersMovingUnique localNewCar = fabricParametersMovingUnique.getMoving(
                    TypeMachinesBody.TEST_SQUARE_20,
                    onlyFootprintsSpaceTime.getIdForNewParametersMovingUnique()
            );
            PointCCoA from = new PointCCoAClass(30, 131); //FIXME BAG don't multipoly 20 (size car)
            PointCCoA to = new PointCCoAClass(70, 300);


            double timeAdding = 0.0;
            PathCCoA actualPath = fastFinderPath.getPath(from, to, localNewCar, timeAdding);
            try {
                localNewCar.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer); //FIXME bag sequense time adding
            } catch (СrashIntoAnImpassableObjectException ex) {
            }
        }

        return onlyFootprintsSpaceTime;
    }
    private FootprintsSpaceTime createFootprintSpaceTime1() throws СrashIntoAnImpassableObjectException {

        FootprintsSpaceTime footprintsSpaceTime = new FootprintsSpaceTimeClass(); //create FootprintsSpaceTime (Landscape) //PUNKT_1

        IndexLayer indexLayer = new IndexLayerClass(0);

        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
        ParametersMovingUnique parametersMovingUnique = fabricParametersMovingUnique.getMoving(
                TypeMachinesBody.TEST_SQUARE_20,
                footprintsSpaceTime.getIdForNewParametersMovingUnique()
        );
        double degree60 = 1.0472;
        NetworkNodes networkNodesFabrica = new HexagonTile(degree60, parametersMovingUnique);
        FabricNetworkNodes fabricNetworkNodes = new FabricHexagonNodes(degree60 / 9, parametersMovingUnique);
        AlhorithmFastFindPath fastFinderPath = new AStarSpaceTimePlanarGraphClass(fabricNetworkNodes, footprintsSpaceTime);


        {
            PointCCoA from = new PointCCoAClass(0, 131); //FIXME BAG don't multipoly 20 (size car)
            PointCCoA to = new PointCCoAClass(0, 300);


            double timeAdding = 0.0;
            PathCCoA actualPath = fastFinderPath.getPath(from, to, parametersMovingUnique, timeAdding);
            parametersMovingUnique.mark(footprintsSpaceTime, actualPath, timeAdding, indexLayer); //FIXME bag sequense time adding
        }
        return footprintsSpaceTime;
    }

    private FootprintsSpaceTime createFootprintSpaceTime2() throws СrashIntoAnImpassableObjectException {

        FootprintsSpaceTime footprintsSpaceTime = new FootprintsSpaceTimeClass(); //create FootprintsSpaceTime (Landscape) //PUNKT_1

        IndexLayer indexLayer = new IndexLayerClass(0);

        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
        ParametersMovingUnique parametersMovingUnique = fabricParametersMovingUnique.getMoving(
                TypeMachinesBody.TEST_SQUARE_20,
                footprintsSpaceTime.getIdForNewParametersMovingUnique()
        );
        double degree60 = 1.0472;
        FabricNetworkNodes fabricNetworkNodes = new FabricHexagonNodes(degree60 / 9, parametersMovingUnique);
        AlhorithmFastFindPath fastFinderPath = new AStarSpaceTimePlanarGraphClass(fabricNetworkNodes, footprintsSpaceTime);



        {
            ParametersMovingUnique localNewCar = fabricParametersMovingUnique.getMoving(
                    TypeMachinesBody.TEST_SQUARE_20,
                    footprintsSpaceTime.getIdForNewParametersMovingUnique()
            );
            PointCCoA from = new PointCCoAClass(30, 131); //FIXME BAG don't multipoly 20 (size car)
            PointCCoA to = new PointCCoAClass(70, 300);


            double timeAdding = 0.0;
            PathCCoA actualPath = fastFinderPath.getPath(from, to, localNewCar, timeAdding);
            try {
                localNewCar.mark(footprintsSpaceTime, actualPath, timeAdding, indexLayer); //FIXME bag sequense time adding
            } catch (СrashIntoAnImpassableObjectException ex) {
            }
        }
        return footprintsSpaceTime;
    }



    private class TestedFootprintSpaceTime {
        private FootprintsSpaceTime localFootprintsSpaceTime = new FootprintsSpaceTimeClass();
        private IndexLayer defaultIndexLayer = new IndexLayerClass(0);
        private ParametersMovingUnique passengerCar;
        private ParametersMovingUnique wallCar;
        private Footprint wallCarFootprint;

        private PathCCoA wallPathCCoA;
        private PathCCoA carPathCCoA;

        public TestedFootprintSpaceTime(PathCCoA wallPathCCoA, PathCCoA carPathCCoA) throws СrashIntoAnImpassableObjectException {
            this.wallPathCCoA = wallPathCCoA;
            this.carPathCCoA = carPathCCoA;


            markFootprintsTwoMachine();
            wallCarFootprintCreatePrivateVariable();
        }

        public TestedFootprintSpaceTime() throws СrashIntoAnImpassableObjectException {
            this.wallPathCCoA = new PathCCoAClass();
            this.wallPathCCoA.addPoint(new PointCCoAClass(0, 60));

            this.carPathCCoA = new PathCCoAClass();
            this.carPathCCoA.addPoint(new PointCCoAClass(20, 10));
            this.carPathCCoA.addPoint(new PointCCoAClass(20, 115));

            markFootprintsTwoMachine();
            wallCarFootprintCreatePrivateVariable();

        }

        public boolean toTest_wallRemainedInPlace(double time) {
            List<Footprint> footprints =
                    localFootprintsSpaceTime.getRenderingFootprintsFromWhenDefaultLayer(areaRendering, time);

            return wallCarFootprint.equals(footprints.get(0));
        }

        public boolean toTestPositionsCars(double time, Position position) {
            return toTest_positionPassengerCarAfterMove(time, position) && toTest_wallRemainedInPlace(time);
        }

        public boolean toTestPositionsCars(double time, Position position, double timeStanding) {
            return toTest_positionPassengerCarAfterMove(time, position, timeStanding) && toTest_wallRemainedInPlace(time);
        }

        public boolean toTest_positionPassengerCarAfterMove(double time, Position position) {
            return toTest_positionPassengerCarAfterMove(time, position, 2.0);
        }

        public boolean toTest_positionPassengerCarAfterMove(double time, Position position, double timeStanding) {
            List<Footprint> footprints =
                    localFootprintsSpaceTime.getRenderingFootprintsFromWhenDefaultLayer(areaRendering, time);
            int idFindMovingObject = footprints.get(1).getIdMovingObject();
            Route route = new RouteClass();

            Footprint carFootrpint = new FootprintClass(
                    position,
                    timeStanding,
                    passengerCar,
                    route
            );

            Footprint found = footprints.get(1);
            return carFootrpint.equals(found);
        }

        private void markFootprintsTwoMachine() throws СrashIntoAnImpassableObjectException {
            wallCarMarkFootprint();
            passangerCarStopsCrashing();
        }

        private void wallCarMarkFootprint() throws СrashIntoAnImpassableObjectException {
            FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
            wallCar = fabricParametersMovingUnique.getMoving(
                    TypeMachinesBody.WALL_CAR,
                    localFootprintsSpaceTime.getIdForNewParametersMovingUnique()
            );
            wallCar.mark(localFootprintsSpaceTime, wallPathCCoA, 0.0, defaultIndexLayer);
        }

        private void passangerCarStopsCrashing() throws СrashIntoAnImpassableObjectException {
            FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
            passengerCar = fabricParametersMovingUnique.getMoving(
                    TypeMachinesBody.TEST_SQUARE_20,
                    localFootprintsSpaceTime.getIdForNewParametersMovingUnique()
            );
            try {
                passengerCar.mark(localFootprintsSpaceTime, carPathCCoA, 0.0, defaultIndexLayer);
            } catch (СrashIntoAnImpassableObjectException ex) {
            }
        }

        private void wallCarFootprintCreatePrivateVariable() {
            Route route = new RouteClass();
            wallCarFootprint = new FootprintClass(
                    new PositionClass(new PointCCoAClass(0, 60), 0.0),
                    GlobalVariable.MAX_TIME_STANDING,
                    wallCar,
                    route
            );
        }
    }
    private TestedFootprintSpaceTime testerFootprintSpaceTime = new TestedFootprintSpaceTime();
/*    @Test
    void getRenderingFootprintsFromWhen_0() throws СrashIntoAnImpassableObjectExeption {
        assertTrue(this.testerFootprintSpaceTime.toTestPositionsCars(
                0.0,
                new PositionClass(new PointClass(20, 10), 1.5707963267948966)
        ));
    }*/
/*


    @Test
    void getRenderingFootprintsFromWhen_1() throws СrashIntoAnImpassableObjectExeption {
        assertTrue(this.onlyFootprintSpaceTime.toTestPositionsCars(
                1.0,
                new PositionClass(new PointClass(20, 10), 1.5707963267948966)
        ));
    }

    @Test
    void getRenderingFootprintsFromWhen_standingFrom2to10() throws СrashIntoAnImpassableObjectExeption {
        for (double time = 2.0; time < 11; time += 2.0) {
            assertTrue(this.onlyFootprintSpaceTime.toTestPositionsCars(
                    time,
                    new PositionClass(new PointClass(20, 10), 1.5707963267948966),
                    GlobalVariable.MAX_TIME_STANDING
            ));
        }
    }

    @Test
    void getRenderingFootprintsFromWhen_standing1999() throws СrashIntoAnImpassableObjectExeption {
        assertTrue(this.onlyFootprintSpaceTime.toTestPositionsCars(
                1999,
                new PositionClass(new PointClass(20, 10), 1.5707963267948966),
                GlobalVariable.MAX_TIME_STANDING
        ));
    }*/

    private FootprintsSpaceTime footprintSpaceTimeSimplyMovingOnLine = footprintSpaceTimeSimplyMovingOnLine();
    private ParametersMovingUnique movingObjectSimpleParametersMovingOnLineUnique;

    private FootprintsSpaceTime footprintSpaceTimeSimplyMovingOnLine() throws СrashIntoAnImpassableObjectException {
        FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass();

        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
        movingObjectSimpleParametersMovingOnLineUnique = fabricParametersMovingUnique.getMoving(
                TypeMachinesBody.TEST_SQUARE_20,
                onlyFootprintsSpaceTime.getIdForNewParametersMovingUnique()
        );

        PathCCoA pathCCoA = new PathCCoAClass();
        pathCCoA.addPoint(new PointCCoAClass(0, 0));
        pathCCoA.addPoint(new PointCCoAClass(10, 0));

        movingObjectSimpleParametersMovingOnLineUnique.mark(onlyFootprintsSpaceTime, pathCCoA, 0.0, defaultIndexLayer);
        return onlyFootprintsSpaceTime;
    }

    @Test
    void getPositionInDefaultLevel_0() throws СrashIntoAnImpassableObjectException {
        Position actualPosition = footprintSpaceTimeSimplyMovingOnLine.getPositionInDefaultLevel(
                movingObjectSimpleParametersMovingOnLineUnique,
                0.0
        );
        Position expected = new PositionClass(new PointCCoAClass(0, 0), 0.0);

        assertEquals(expected, actualPosition);
    }

    @Test
    void getPositionInDefaultLevel_05() throws СrashIntoAnImpassableObjectException {
        Position actualPosition = footprintSpaceTimeSimplyMovingOnLine.getPositionInDefaultLevel(
                movingObjectSimpleParametersMovingOnLineUnique,
                0.5
        );
        Position expected = new PositionClass(new PointCCoAClass(5, 0), 0.0);

        assertEquals(expected, actualPosition);
    }
    @Test
    void getPositionInDefaultLevel_1() throws СrashIntoAnImpassableObjectException {
        Position actualPosition = footprintSpaceTimeSimplyMovingOnLine.getPositionInDefaultLevel(
                movingObjectSimpleParametersMovingOnLineUnique,
                1.0
        );
        Position expected = new PositionClass(new PointCCoAClass(10, 0), 0.0);

        assertEquals(expected, actualPosition);
    }

    private interface TesterOneMark {
        public boolean isCarPassedRightPlace(Position expected, double timePosition);

        public double getLengthStep();

        public double getTimePositionForTheSpeedCar(int numberSteps);
    }

    private class TesterOneMarkClass implements TesterOneMark {

        private double timeAddingPath;
        private FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass();
        private ParametersMovingUnique parametersMovingUnique;
        private double timeStandingOnOneStep = 2;
        private double lengthStep = 20;
        double speed = 10;
        private PointCCoA endPointCCoA = null;
        private PathCCoA pathCCoA = null;

        public TesterOneMarkClass(double timeAddingPath) throws СrashIntoAnImpassableObjectException {
            this.timeAddingPath = timeAddingPath;

            sharedConstructor();
        }

        public TesterOneMarkClass(double timeAddingPath, PathCCoA pathCCoA) throws СrashIntoAnImpassableObjectException {
            this.timeAddingPath = timeAddingPath;
            this.pathCCoA = pathCCoA;

            sharedConstructor();
        }

        public TesterOneMarkClass(double timeAddingPath, PointCCoA endPointCCoA) throws СrashIntoAnImpassableObjectException {
            this.timeAddingPath = timeAddingPath;
            this.endPointCCoA = endPointCCoA;

            sharedConstructor();

        }

        private void sharedConstructor() throws СrashIntoAnImpassableObjectException {
            setValueForNullVariable();
            createPassangerCar();
            parametersMovingUnique.mark(onlyFootprintsSpaceTime, pathCCoA, timeAddingPath, defaultIndexLayer);
        }

        private void createPassangerCar() {
            FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
            BuilderParametersMovingUnique builder = fabricParametersMovingUnique.getNewBuilderMoving(TypeMachinesBody.TEST_SQUARE_20);
            builder.setSpeed(speed);
            parametersMovingUnique = builder.getParametersMoving(245);
        }

        private void setValueForNullVariable() {
            if (endPointCCoA == null) {
                endPointCCoA = new PointCCoAClass(100, 100);
            }
            if (pathCCoA == null) {
                pathCCoA = new PathCCoAClass();
                pathCCoA.addPoint(new PointCCoAClass(0, 100));
                pathCCoA.addPoint(endPointCCoA);
            }
        }

        @Override
        public boolean isCarPassedRightPlace(Position expected, double timePosition) {
            Position actualPosition = onlyFootprintsSpaceTime.getPositionInDefaultLevel(
                    parametersMovingUnique,
                    timePosition
            );

            return expected.equals(actualPosition);
        }

        @Override
        public double getLengthStep() {
            return lengthStep;
        }

        @Override
        public double getTimePositionForTheSpeedCar(int numberSteps) {
            return timeStandingOnOneStep * numberSteps;
        }
    }

    private TesterOneMark testerOneMark0 = new TesterOneMarkClass(0.0);

    @Test
    void addFootprint_move() {
        for (int numberStep = 0; numberStep < 6; numberStep++) {
            double lengthStep = testerOneMark0.getLengthStep();
            assert (testerOneMark0.isCarPassedRightPlace(
                    new PositionClass(new PointCCoAClass(lengthStep * numberStep, 100), 0),
                    testerOneMark0.getTimePositionForTheSpeedCar(numberStep)
            )) : "numberStep: " + numberStep + " lengthStep: " + lengthStep;
        }
    }

    @Test
    void addFootprint_stopAfterMove() {
        assert (testerOneMark0.isCarPassedRightPlace(
                new PositionClass(new PointCCoAClass(testerOneMark0.getLengthStep() * 5, 100), 0),
                1000
        ));
    }

    @Test
    void addFootprint_approximationHalfFirstStep() {
        assert (testerOneMark0.isCarPassedRightPlace(
                new PositionClass(new PointCCoAClass(5, 100), 0),
                0.5
        ));
    }

    @Test
    void addFootprint_approximationStartFirstStep() {
        assert (testerOneMark0.isCarPassedRightPlace(
                new PositionClass(new PointCCoAClass(0, 100), 0),
                0
        ));
    }

    @Test
    void addFootprint_approximationEndFirstStep() {
        assert (testerOneMark0.isCarPassedRightPlace(
                new PositionClass(new PointCCoAClass(10, 100), 0),
                1
        ));
    }


    private PointCCoA endPointCCoA = new PointCCoAClass(110, 100);
    private TesterOneMark testerOneMark0NonMultipleStep = new TesterOneMarkClass(0.0, endPointCCoA);

    @Test
    void addFootprint_littleStepInEnd() {
        boolean isExistRemainsDivide =
                endPointCCoA.getX() % testerOneMark0NonMultipleStep.getLengthStep() > GlobalVariable.DOUBLE_COMPARISON_ACCURACY;
        boolean isNeedForEndLittleStep = isExistRemainsDivide;
        assert (isNeedForEndLittleStep);
        assert (testerOneMark0NonMultipleStep.isCarPassedRightPlace(
                new PositionClass(endPointCCoA, 0),
                400
        ));
    }


    private PathCCoA verticalPathCCoAMoreOneStep = new PathCCoAClass(Arrays.<PointCCoA>asList(new PointCCoAClass[]{
            new PointCCoAClass(0, 0),
            new PointCCoAClass(0, 100)
    }));
    private TesterOneMark testerOneMark0VerticalPath =
            new TesterOneMarkClass(0.0, verticalPathCCoAMoreOneStep);

    @Test
    void addFootprint_vertical_8() {
        assertTrue(testerOneMark0VerticalPath.isCarPassedRightPlace(
                new PositionClass(new PointCCoAClass(0, 80), PI / 2),
                8
        ));
    }

    @Test
    void addFootprint_vertical_10() {
        assert (testerOneMark0VerticalPath.isCarPassedRightPlace(
                new PositionClass(new PointCCoAClass(0, 100), PI / 2),
                10
        ));
    }

    @Test
    void addFootprint_verticalStandingAfterMove() {
        assert (testerOneMark0VerticalPath.isCarPassedRightPlace(
                new PositionClass(new PointCCoAClass(0, 100), PI / 2),
                10000000
        ));
    }

    private PathCCoA pathCCoAStanding = new PathCCoAClass(Arrays.<PointCCoA>asList(new PointCCoAClass[]{
            new PointCCoAClass(0, 0)
    }));
    private TesterOneMark testerMarkStanding = new TesterOneMarkClass(0.0, pathCCoAStanding);

    @Test
    void addFootprint_verticalStandingAfterMove_0() {
        assert (testerMarkStanding.isCarPassedRightPlace(
                new PositionClass(new PointCCoAClass(0, 0), 0.0),
                0
        ));
    }

    @Test
    void addFootprint_verticalStandingAfterMove_23456() {
        assert (testerMarkStanding.isCarPassedRightPlace(
                new PositionClass(new PointCCoAClass(0, 0), 0.0),
                23456
        ));
    }

    @Test
    void addFootprint_verticalStandingAfterMove_234563456() {
        assert (testerMarkStanding.isCarPassedRightPlace(
                new PositionClass(new PointCCoAClass(0, 0), 0.0),
                234563456
        ));
    }


    private PathCCoA zigZagPathCCoA = new PathCCoAClass(Arrays.<PointCCoA>asList(new PointCCoAClass[]{
            new PointCCoAClass(0, 0),
            new PointCCoAClass(100, 0),
            new PointCCoAClass(100, 100),
            new PointCCoAClass(200, 100)
    }));
    private TesterOneMark zigZagTester = new TesterOneMarkClass(0.0, zigZagPathCCoA);


    /*
     *          X
     *    0----->
     *    |
     *    |
     *  Y v
     *
     * 0--0--0--1
     *          |
     *          |
     *          1
     *          |
     *          |
     *          1
     *          2--2--2--2--3
     *                      ^
     *          end point in endless (timeStanding is endless)
     * <p>
     * <p>
     * number this is number interation cicle up
     * number this is point
     * -- this is time standing (Length equal length movingObject)
     */
    @Test
    void addFootprint_zigZagZeroLine() {
        for (int numberStep = 0; numberStep <= 4; numberStep++) {
            double lengthStep = zigZagTester.getLengthStep();
            assert (zigZagTester.isCarPassedRightPlace(
                    new PositionClass(new PointCCoAClass(lengthStep * numberStep, 0), 0.0),
                    zigZagTester.getTimePositionForTheSpeedCar(numberStep)
            )) : "numberStep: " + numberStep + " lengthStep: " + lengthStep;
        }
    }

    @Test
    void addFootprint_zigZagFirstLine() {
        for (int numberStep = 0; numberStep <= 4; numberStep++) {
            double timeMovingInFirstLine = zigZagTester.getTimePositionForTheSpeedCar(5);
            double lengthStep = zigZagTester.getLengthStep();
            assert (zigZagTester.isCarPassedRightPlace(
                    new PositionClass(new PointCCoAClass(100, lengthStep * numberStep), 1.5707963267948966),
                    timeMovingInFirstLine + zigZagTester.getTimePositionForTheSpeedCar(numberStep)
            )) : "numberStep: " + numberStep + " lengthStep: " + lengthStep;
        }
    }

    @Test
    void addFootprint_zigZagSecondLine() {
        double length5Step = zigZagTester.getLengthStep() * 5;
        assert (zigZagTester.isCarPassedRightPlace(
                new PositionClass(new PointCCoAClass(length5Step, length5Step), 0.0),
                zigZagTester.getTimePositionForTheSpeedCar(10)
        ));
    }

    @Test
    void addFootprint_zigZag3Line() {
        double length5Step = zigZagTester.getLengthStep() * 5;
        double length10Step = zigZagTester.getLengthStep() * 10;
        assert (zigZagTester.isCarPassedRightPlace(
                new PositionClass(new PointCCoAClass(length10Step, length5Step), 0.0),
                zigZagTester.getTimePositionForTheSpeedCar(15)
        ));
    }

    /*
     *          X
     *    0----->
     *    |
     *    |
     *  Y v
     *
     *
     * 0--0--0--0-1
     *            |
     *            |
     *            1
     *            |
     *            |
     *            1
     *            |
     *            2--2--2--2--2-3
     *                          ^
     *               end point in endless (timeStanding is endless)
     * <p>
     * <p>
     * number this is number interation cicle up
     * number this is point
     * -- this is time standing (Length equal length movingObject)
     * - this is lastLittleStep (last Little Time Standing)
     */
    private double tailLength = 3;
    private PathCCoA zigZagPathCCoAWithTail = new PathCCoAClass(Arrays.<PointCCoA>asList(new PointCCoAClass[]{
            new PointCCoAClass(0, 0),
            new PointCCoAClass(100, 0),
            new PointCCoAClass(100, 100),
            new PointCCoAClass(200, 100),
            new PointCCoAClass(200 + tailLength, 100)
    }));
    private TesterOneMark zigZagWithTailTester = new TesterOneMarkClass(0.0, zigZagPathCCoAWithTail);
    @Test
    void addFootprint_zigZagWithLastLittleStep_endTailStep() {
        double length5Step = zigZagWithTailTester.getLengthStep() * 5;
        double length10Step = zigZagWithTailTester.getLengthStep() * 10;
        double speedTestedCarSquare = 10;
        double timeMovingTail = tailLength / speedTestedCarSquare;
        assert (zigZagWithTailTester.isCarPassedRightPlace(
                new PositionClass(new PointCCoAClass(length10Step + tailLength, length5Step), 0.0),
                zigZagWithTailTester.getTimePositionForTheSpeedCar(15) + timeMovingTail
        ));
    }


    private PathCCoA standingPathCCoA = new PathCCoAClass(Arrays.<PointCCoA>asList(new PointCCoAClass[]{
            new PointCCoAClass(0, 0)
    }));
    private TesterOneMark standingTester = new TesterOneMarkClass(0.0, standingPathCCoA);

    @Test
    void addFootprint_standing() {
        standingTester.isCarPassedRightPlace(new PositionClass(new PointCCoAClass(0, 0), 0.0), 0);
    }


    private TesterOneMark maxTimeStanding =
            new TesterOneMarkClass(GlobalVariable.MAX_TIME_STANDING / 2);

    @Test
    void addFootprint_addInMaxTimeStanding() {
        maxTimeStanding.isCarPassedRightPlace(new PositionClass(
                        new PointCCoAClass(0, 100), 0),
                GlobalVariable.MAX_TIME_STANDING / 2
        );
    }


    @Test
    void addFootprint_throwExeption() {

        FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass();

        wallInstalation(onlyFootprintsSpaceTime);


        boolean exeptionThrowed = throwingExeptionWhenTryingPassThroughWall(onlyFootprintsSpaceTime);

        assertTrue(exeptionThrowed);
    }



    @Test
    void getElbowDTOToEndCalculateExistJSONFrom_car() {


        FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass();

        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
        ParametersMovingUnique car = fabricParametersMovingUnique.getMoving(
                TypeMachinesBody.TEST_SQUARE_20,
                onlyFootprintsSpaceTime.getIdForNewParametersMovingUnique()
        );

        PathCCoA path = new PathCCoAClass();
        path.addPoint(new PointCCoAClass(10, 10));
        path.addPoint(new PointCCoAClass(10, 50));

        try {
            car.markWithoutStandingUntilEndTime(onlyFootprintsSpaceTime, path, 0.0, defaultIndexLayer);
        } catch (СrashIntoAnImpassableObjectException e) {
            throw new RuntimeException(e);
        }

        double anyTime = 0.1;
        HistoryChanges historyChanges = (HistoryChanges) onlyFootprintsSpaceTime;


        JsonObject expected = JsonParser.parseString("{\"elbow_moving_objects\":[{\"id_moving_unique_object\":3,\"appearanceType\":\"TEST_SQUARE_20\",\"appearancePolygonForm\":[{\"x\":-10.0,\"y\":-10.0},{\"x\":10.0,\"y\":-10.0},{\"x\":10.0,\"y\":10.0},{\"x\":-10.0,\"y\":10.0}],\"timeSpaceCoordinates\":[{\"t\":0.0,\"layer\":0,\"x\":10.0,\"y\":11.0,\"angle\":0.0},{\"t\":2.0,\"layer\":0,\"x\":10.000000000000002,\"y\":30.0,\"angle\":0.0},{\"t\":4.0,\"layer\":0,\"x\":10.0,\"y\":50.0,\"angle\":0.0}]}]}").getAsJsonObject();
        JsonObject actual = historyChanges.getElbowDTOToEndCalculateExistJSONFrom(anyTime);

        assertEquals(expected, actual);


    }


    @Test
    void getElbowDTOToEndCalculateExistJSONFrom_wall() {

        FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass();

        wallInstalation(onlyFootprintsSpaceTime);

        double anyTime = 0.1;
        HistoryChanges historyChanges = (HistoryChanges) onlyFootprintsSpaceTime;

        JsonObject expected = JsonParser.parseString("{\"elbow_moving_objects\":[{\"id_moving_unique_object\":3,\"appearanceType\":\"non-uniform\",\"appearancePolygonForm\":[{\"x\":-250.0,\"y\":-25.0},{\"x\":250.0,\"y\":-25.0},{\"x\":250.0,\"y\":25.0},{\"x\":-250.0,\"y\":25.0}],\"timeSpaceCoordinates\":[{\"t\":0.0,\"layer\":0,\"x\":60.0,\"y\":60.0,\"angle\":0.0},{\"t\":1.7078084781191998E308,\"layer\":0,\"x\":60.0,\"y\":60.0,\"angle\":0.0}]}]}\n").getAsJsonObject();

        JsonObject actual = historyChanges.getElbowDTOToEndCalculateExistJSONFrom(anyTime);
        assertEquals(expected, actual);
    }

    private void wallInstalation(FootprintsSpaceTime onlyFootprintsSpaceTime) {

        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();

        ParametersMovingUnique wall = fabricParametersMovingUnique.getMoving(
                TypeMachinesBody.WALL_CAR,
                onlyFootprintsSpaceTime.getIdForNewParametersMovingUnique()
        );
        try {
            wall.mark(onlyFootprintsSpaceTime, createPathWall(), 0.0, defaultIndexLayer);
        } catch (СrashIntoAnImpassableObjectException ex) {
            int error = 5; //for debugger, checking the passage through this place
        }
    }

    private boolean throwingExeptionWhenTryingPassThroughWall(FootprintsSpaceTime onlyFootprintsSpaceTime) {
        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
        ParametersMovingUnique parametersMovingUnique = fabricParametersMovingUnique.getMoving(
                TypeMachinesBody.TEST_SQUARE_20,
                onlyFootprintsSpaceTime.getIdForNewParametersMovingUnique()
        );
        try {
            parametersMovingUnique.mark(onlyFootprintsSpaceTime, createPassengerCarPath(), 0.0, defaultIndexLayer);
        } catch (СrashIntoAnImpassableObjectException ex) {
            return true;
        }
        return false;
    }

    private PathCCoA createPassengerCarPath() { //FIXME IMITATION
        PathCCoA resPathCCoA = new PathCCoAClass();
        resPathCCoA.addPoint(new PointCCoAClass(10, 10));
        resPathCCoA.addPoint(new PointCCoAClass(15, 15));
        resPathCCoA.addPoint(new PointCCoAClass(200, 15));
        resPathCCoA.addPoint(new PointCCoAClass(20, 250));
        resPathCCoA.addPoint(new PointCCoAClass(30, 30));
        resPathCCoA.addPoint(new PointCCoAClass(35, 35));
        resPathCCoA.addPoint(new PointCCoAClass(40, 40));
        resPathCCoA.addPoint(new PointCCoAClass(400, 40));

        return resPathCCoA;
    }

    private PathCCoA createPathWall() {
        PathCCoA resPathCCoA = new PathCCoAClass();
        resPathCCoA.addPoint(new PointCCoAClass(60, 60));

        return resPathCCoA;
    }

}