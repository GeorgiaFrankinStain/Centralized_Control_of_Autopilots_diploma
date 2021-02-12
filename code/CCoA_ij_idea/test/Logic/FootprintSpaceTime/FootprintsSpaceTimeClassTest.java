package Logic.FootprintSpaceTime;

import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.*;
import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.Path;
import Logic.MovingObjects.PathClass;
import Logic.PathsMachines.PositionClass;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.PI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class FootprintsSpaceTimeClassTest { //FIXME add test add path of moving object with speed 0

    FootprintsSpaceTimeClassTest() throws СrashIntoAnImpassableObjectExeption {
    }


    private LevelLayer defaultLevelLayer = new LevelLayerClass(0);

    PolygonExtended areaRendering = new PolygonExtendedClass(Arrays.asList(new PointClass[]{
            new PointClass(0, 0),
            new PointClass(900, 0),
            new PointClass(900, 900),
            new PointClass(0, 900)
    }));


    private class TestedFootprintSpaceTime {
        private FootprintsSpaceTime localFootprintsSpaceTime = new FootprintsSpaceTimeClass();
        private LevelLayer defaultLevelLayer = new LevelLayerClass(0);
        private MovingObject passengerCar;
        private MovingObject wallCar;
        private Footprint wallCarFootprint;

        private Path wallPath;
        private Path carPath;

        public TestedFootprintSpaceTime(Path wallPath, Path carPath) throws СrashIntoAnImpassableObjectExeption {
            this.wallPath = wallPath;
            this.carPath = carPath;


            markFootprintsTwoMachine();
            wallCarFootprintCreatePrivateVariable();
        }

        public TestedFootprintSpaceTime() throws СrashIntoAnImpassableObjectExeption {
            this.wallPath = new PathClass();
            this.wallPath.addPoint(new PointClass(0, 60));

            this.carPath = new PathClass();
            this.carPath.addPoint(new PointClass(20, 10));
            this.carPath.addPoint(new PointClass(20, 115));

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
            int idFindMovingObject = footprints.get(1).getIdObject();

            Footprint carFootrpint = new FootprintClass(
                    idFindMovingObject,
                    position,
                    timeStanding,
                    passengerCar
            );

            Footprint found = footprints.get(1);
            return carFootrpint.equals(found);
        }

        private void markFootprintsTwoMachine() throws СrashIntoAnImpassableObjectExeption {
            wallCarMarkFootprint();
            passangerCarStopsCrashing();

        }

        private void wallCarMarkFootprint() throws СrashIntoAnImpassableObjectExeption {
            FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();
            wallCar = fabricMovingObjects.getMachine(TypeMachinesBody.WALL_CAR);
            wallCar.mark(localFootprintsSpaceTime, wallPath, 0.0, defaultLevelLayer);
        }

        private void passangerCarStopsCrashing() throws СrashIntoAnImpassableObjectExeption {
            FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();
            passengerCar = fabricMovingObjects.getMachine(TypeMachinesBody.TEST_SQUARE_20);
            try {
                passengerCar.mark(localFootprintsSpaceTime, carPath, 0.0, defaultLevelLayer);
            } catch (СrashIntoAnImpassableObjectExeption ex) {
            }
        }

        private void wallCarFootprintCreatePrivateVariable() {
            wallCarFootprint = new FootprintClass(
                    -1183596282, //FIXME
                    new PositionClass(new PointClass(0, 60), 0.0),
                    CreatorMarksOfPathClass.MAX_TIME_STANDING,
                    wallCar
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
                    CreatorMarksOfPathClass.MAX_TIME_STANDING
            ));
        }
    }

    @Test
    void getRenderingFootprintsFromWhen_standing1999() throws СrashIntoAnImpassableObjectExeption {
        assertTrue(this.onlyFootprintSpaceTime.toTestPositionsCars(
                1999,
                new PositionClass(new PointClass(20, 10), 1.5707963267948966),
                CreatorMarksOfPathClass.MAX_TIME_STANDING
        ));
    }*/

    private FootprintsSpaceTime footprintSpaceTimeSimplyMovingOnLine = footprintSpaceTimeSimplyMovingOnLine();
    private MovingObject movingObjectSimpleMovingOnLine;

    private FootprintsSpaceTime footprintSpaceTimeSimplyMovingOnLine() throws СrashIntoAnImpassableObjectExeption {
        FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass();

        FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();
        movingObjectSimpleMovingOnLine = fabricMovingObjects.getMachine(TypeMachinesBody.TEST_SQUARE_20);

        Path path = new PathClass();
        path.addPoint(new PointClass(0, 0));
        path.addPoint(new PointClass(10, 0));

        movingObjectSimpleMovingOnLine.mark(onlyFootprintsSpaceTime, path, 0.0, defaultLevelLayer);
        return onlyFootprintsSpaceTime;
    }

    @Test
    void getPositionInDefaultLevel_0() throws СrashIntoAnImpassableObjectExeption {
        Position actualPosition = footprintSpaceTimeSimplyMovingOnLine.getPositionInDefaultLevel(
                movingObjectSimpleMovingOnLine,
                0.0
        );
        Position expected = new PositionClass(new PointClass(0, 0), 0.0);

        assertEquals(expected, actualPosition);
    }

    @Test
    void getPositionInDefaultLevel_05() throws СrashIntoAnImpassableObjectExeption {
        Position actualPosition = footprintSpaceTimeSimplyMovingOnLine.getPositionInDefaultLevel(
                movingObjectSimpleMovingOnLine,
                0.5
        );
        Position expected = new PositionClass(new PointClass(5, 0), 0.0);

        assertEquals(expected, actualPosition);
    }
    @Test
    void getPositionInDefaultLevel_1() throws СrashIntoAnImpassableObjectExeption {
        Position actualPosition = footprintSpaceTimeSimplyMovingOnLine.getPositionInDefaultLevel(
                movingObjectSimpleMovingOnLine,
                1.0
        );
        Position expected = new PositionClass(new PointClass(10, 0), 0.0);

        assertEquals(expected, actualPosition);
    }

    private interface TesterOneMark {
        public boolean isCarPassedRightPlace(Position expected, double timePosition);

        public double getLengthStep();

        public double getTimePositionForTheSpeedCar(int numberSteps);
    }

    private class TesterOneMarkClass implements TesterOneMark, Logic.FootprintSpaceTime.TesterOneMarkClass {

        private double timeAddingPath;
        private FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass();
        private MovingObject movingObject;
        private double timeStandingOnOneStep = 2;
        private double lengthStep = 20;
        double speed = 10;
        private Point endPoint = null;
        private Path path = null;

        public TesterOneMarkClass(double timeAddingPath) throws СrashIntoAnImpassableObjectExeption {
            this.timeAddingPath = timeAddingPath;

            sharedConstructor();
        }

        public TesterOneMarkClass(double timeAddingPath, Path path) throws СrashIntoAnImpassableObjectExeption {
            this.timeAddingPath = timeAddingPath;
            this.path = path;

            sharedConstructor();
        }

        public TesterOneMarkClass(double timeAddingPath, Point endPoint) throws СrashIntoAnImpassableObjectExeption {
            this.timeAddingPath = timeAddingPath;
            this.endPoint = endPoint;

            sharedConstructor();

        }

        private void sharedConstructor() throws СrashIntoAnImpassableObjectExeption {
            setValueForNullVariable();
            createPassangerCar();
            movingObject.mark(onlyFootprintsSpaceTime, path, timeAddingPath, defaultLevelLayer);
        }

        private void createPassangerCar() {
            FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();
            movingObject = fabricMovingObjects.getMachine(TypeMachinesBody.TEST_SQUARE_20);
            movingObject.setSpeed(speed);
        }

        private void setValueForNullVariable() {
            if (endPoint == null) {
                endPoint = new PointClass(100, 100);
            }
            if (path == null) {
                path = new PathClass();
                path.addPoint(new PointClass(0, 100));
                path.addPoint(endPoint);
            }
        }

        @Override
        public boolean isCarPassedRightPlace(Position expected, double timePosition) {
            Position actualPosition = onlyFootprintsSpaceTime.getPositionInDefaultLevel(
                    movingObject,
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
                    new PositionClass(new PointClass(lengthStep * numberStep, 100), 0),
                    testerOneMark0.getTimePositionForTheSpeedCar(numberStep)
            )) : "numberStep: " + numberStep + " lengthStep: " + lengthStep;
        }
    }

    @Test
    void addFootprint_stopAfterMove() {
        assert (testerOneMark0.isCarPassedRightPlace(
                new PositionClass(new PointClass(testerOneMark0.getLengthStep() * 5, 100), 0),
                1000
        ));
    }

    @Test
    void addFootprint_approximationHalfFirstStep() {
        assert (testerOneMark0.isCarPassedRightPlace(
                new PositionClass(new PointClass(5, 100), 0),
                0.5
        ));
    }

    @Test
    void addFootprint_approximationStartFirstStep() {
        assert (testerOneMark0.isCarPassedRightPlace(
                new PositionClass(new PointClass(0, 100), 0),
                0
        ));
    }

    @Test
    void addFootprint_approximationEndFirstStep() {
        assert (testerOneMark0.isCarPassedRightPlace(
                new PositionClass(new PointClass(10, 100), 0),
                1
        ));
    }


    private Point endPoint = new PointClass(110, 100);
    private TesterOneMark testerOneMark0NonMultipleStep = new TesterOneMarkClass(0.0, endPoint);

    @Test
    void addFootprint_littleStepInEnd() {
        boolean isExistRemainsDivide =
                endPoint.getX() % testerOneMark0NonMultipleStep.getLengthStep() > GlobalVariable.DOUBLE_COMPARISON_ACCURACY;
        boolean isNeedForEndLittleStep = isExistRemainsDivide;
        assert (isNeedForEndLittleStep);
        assert (testerOneMark0NonMultipleStep.isCarPassedRightPlace(
                new PositionClass(endPoint, 0),
                400
        ));
    }


    private Path verticalPathMoreOneStep = new PathClass(Arrays.asList(new PointClass[]{
            new PointClass(0, 0),
            new PointClass(0, 100)
    }));
    private TesterOneMark testerOneMark0VerticalPath =
            new TesterOneMarkClass(0.0, verticalPathMoreOneStep);

    @Test
    void addFootprint_vertical_8() {
        assertTrue(testerOneMark0VerticalPath.isCarPassedRightPlace(
                new PositionClass(new PointClass(0, 80), PI / 2),
                8
        ));
    }

    @Test
    void addFootprint_vertical_10() {
        assert (testerOneMark0VerticalPath.isCarPassedRightPlace(
                new PositionClass(new PointClass(0, 100), PI / 2),
                10
        ));
    }

    @Test
    void addFootprint_verticalStandingAfterMove() {
        assert (testerOneMark0VerticalPath.isCarPassedRightPlace(
                new PositionClass(new PointClass(0, 100), PI / 2),
                10000000
        ));
    }

    private Path pathStanding = new PathClass(Arrays.asList(new PointClass[]{
            new PointClass(0, 0)
    }));
    private TesterOneMark testerMarkStanding = new TesterOneMarkClass(0.0, pathStanding);

    @Test
    void addFootprint_verticalStandingAfterMove_0() {
        assert (testerMarkStanding.isCarPassedRightPlace(
                new PositionClass(new PointClass(0, 0), 0.0),
                0
        ));
    }

    @Test
    void addFootprint_verticalStandingAfterMove_23456() {
        assert (testerMarkStanding.isCarPassedRightPlace(
                new PositionClass(new PointClass(0, 0), 0.0),
                23456
        ));
    }

    @Test
    void addFootprint_verticalStandingAfterMove_234563456() {
        assert (testerMarkStanding.isCarPassedRightPlace(
                new PositionClass(new PointClass(0, 0), 0.0),
                234563456
        ));
    }


    private Path zigZagPath = new PathClass(Arrays.asList(new PointClass[]{
            new PointClass(0, 0),
            new PointClass(100, 0),
            new PointClass(100, 100),
            new PointClass(200, 100)
    }));
    private TesterOneMark zigZagTester = new TesterOneMarkClass(0.0, zigZagPath);


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
                    new PositionClass(new PointClass(lengthStep * numberStep, 0), 0.0),
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
                    new PositionClass(new PointClass(100, lengthStep * numberStep), 1.5707963267948966),
                    timeMovingInFirstLine + zigZagTester.getTimePositionForTheSpeedCar(numberStep)
            )) : "numberStep: " + numberStep + " lengthStep: " + lengthStep;
        }
    }

    @Test
    void addFootprint_zigZagSecondLine() {
        double length5Step = zigZagTester.getLengthStep() * 5;
        assert (zigZagTester.isCarPassedRightPlace(
                new PositionClass(new PointClass(length5Step, length5Step), 0.0),
                zigZagTester.getTimePositionForTheSpeedCar(10)
        ));
    }

    @Test
    void addFootprint_zigZag3Line() {
        double length5Step = zigZagTester.getLengthStep() * 5;
        double length10Step = zigZagTester.getLengthStep() * 10;
        assert (zigZagTester.isCarPassedRightPlace(
                new PositionClass(new PointClass(length10Step, length5Step), 0.0),
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
    private Path zigZagPathWithTail = new PathClass(Arrays.asList(new PointClass[]{
            new PointClass(0, 0),
            new PointClass(100, 0),
            new PointClass(100, 100),
            new PointClass(200, 100),
            new PointClass(200 + tailLength, 100)
    }));
    private TesterOneMark zigZagWithTailTester = new TesterOneMarkClass(0.0, zigZagPathWithTail);
    @Test
    void addFootprint_zigZagWithLastLittleStep_endTailStep() {
        double length5Step = zigZagWithTailTester.getLengthStep() * 5;
        double length10Step = zigZagWithTailTester.getLengthStep() * 10;
        double speedTestedCarSquare = 10;
        double timeMovingTail = tailLength / speedTestedCarSquare;
        assert (zigZagWithTailTester.isCarPassedRightPlace(
                new PositionClass(new PointClass(length10Step + tailLength, length5Step), 0.0),
                zigZagWithTailTester.getTimePositionForTheSpeedCar(15) + timeMovingTail
        ));
    }


    private Path standingPath = new PathClass(Arrays.asList(new PointClass[]{
            new PointClass(0, 0)
    }));
    private TesterOneMark standingTester = new TesterOneMarkClass(0.0, standingPath);

    @Test
    void addFootprint_standing() {
        standingTester.isCarPassedRightPlace(new PositionClass(new PointClass(0, 0), 0.0), 0);
    }


    private TesterOneMark maxTimeStanding =
            new TesterOneMarkClass(CreatorMarksOfPathClass.MAX_TIME_STANDING / 2);

    @Test
    void addFootprint_addInMaxTimeStanding() {
        maxTimeStanding.isCarPassedRightPlace(new PositionClass(
                        new PointClass(0, 100), 0),
                CreatorMarksOfPathClass.MAX_TIME_STANDING / 2
        );
    }


    @Test
    void addFootprint_throwExeption() {

        FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass();

        wallInstalation(onlyFootprintsSpaceTime);


        boolean exeptionThrowed = throwingExeptionWhenTryingPassThroughWall(onlyFootprintsSpaceTime);

        assertTrue(exeptionThrowed);
    }

    private void wallInstalation(FootprintsSpaceTime onlyFootprintsSpaceTime) {

        FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();

        MovingObject wall = fabricMovingObjects.getMachine(TypeMachinesBody.WALL_CAR);
        try {
            wall.mark(onlyFootprintsSpaceTime, createPathWall(), 0.0, defaultLevelLayer);
        } catch (СrashIntoAnImpassableObjectExeption ex) {
            int error = 5; //for debugger, checking the passage through this place
        }
    }

    private boolean throwingExeptionWhenTryingPassThroughWall(FootprintsSpaceTime onlyFootprintsSpaceTime) {
        FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();
        MovingObject movingObject = fabricMovingObjects.getMachine(TypeMachinesBody.TEST_SQUARE_20);
        try {
            movingObject.mark(onlyFootprintsSpaceTime, createPassengerCarPath(), 0.0, defaultLevelLayer);
        } catch (СrashIntoAnImpassableObjectExeption ex) {
            return true;
        }
        return false;
    }

    private Path createPassengerCarPath() { //FIXME IMITATION
        Path resPath = new PathClass();
        resPath.addPoint(new PointClass(10, 10));
        resPath.addPoint(new PointClass(15, 15));
        resPath.addPoint(new PointClass(200, 15));
        resPath.addPoint(new PointClass(20, 250));
        resPath.addPoint(new PointClass(30, 30));
        resPath.addPoint(new PointClass(35, 35));
        resPath.addPoint(new PointClass(40, 40));
        resPath.addPoint(new PointClass(400, 40));

        return resPath;
    }

    private Path createPathWall() {
        Path resPath = new PathClass();
        resPath.addPoint(new PointClass(60, 60));

        return resPath;
    }
}