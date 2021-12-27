package com.alamutra.ccoa.Logic.FootprintSpaceTime;

import com.alamutra.ccoa.Logic.MovingObjects.FabricParametersMoving;
import com.alamutra.ccoa.Logic.MovingObjects.FabricParametersMovingClass;
import com.alamutra.ccoa.Logic.MovingObjects.ParametersMoving;
import com.alamutra.ccoa.Logic.MovingObjects.ParametersMovingClass;
import com.alamutra.ccoa.Logic.PathsMachines.PositionClass;
import com.alamutra.ccoa.Logic.Position;
import com.alamutra.ccoa.StatementTaskRendering.TypeMachinesBody;
import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.*;

class FootprintClassTest {

    @Test
    public void getIdObject() {
        //FIXME ADD_FORMALNOST_TEST
    }


    @Test
    public void getPosition() {
        //FIXME ADD_FORMALNOST_TEST
    }

    @Test
    public void getTimeStanding() {
        //FIXME ADD_FORMALNOST_TEST
    }


    private Point[] arrayPoints = {
            new PointClass(10, 10),
            new PointClass(10, 20),
            new PointClass(20, 20),
            new PointClass(20, 10),
    };

    private ParametersMoving getStandartMovingObjet() {
        ParametersMoving parametersMoving = new ParametersMovingClass(10, getStandardFormMachine(), TypeMachinesBody.TEST_SQUARE_20);
        return parametersMoving;
    }

    @Test
    public void getOccupiedLocation_margin() {
        Point margin = new PointClass(10, 10);
        Position position = new PositionClass(margin, 0.0);

        Footprint footprint = new FootprintClass(position, 1, getStandartMovingObjet());
        PolygonExtended actualOccupiedLocation = footprint.getOccupiedLocation();
        PolygonExtended expectedPolygonExtended = new PolygonExtendedClass();
        expectedPolygonExtended.addPoint(new PointClass(5, 5));
        expectedPolygonExtended.addPoint(new PointClass(5, 15));
        expectedPolygonExtended.addPoint(new PointClass(15, 15));
        expectedPolygonExtended.addPoint(new PointClass(15, 5));

        assertEquals(expectedPolygonExtended, actualOccupiedLocation);
    }

    private PolygonExtended expectedAfterMargin(Point margin) {
        PolygonExtended expectedPolygonExtended = new PolygonExtendedClass();

        for (Point point : arrayPoints) {
            Point marginedPoint = new PointClass(
                    point.getX() + margin.getX(),
                    point.getY() + margin.getY()
            );
            expectedPolygonExtended.addPoint(marginedPoint);
        }

        return expectedPolygonExtended;
    }

    private PolygonExtended getStandardFormMachine() {
        PolygonExtended formMachine = new PolygonExtendedClass();


        for (Point point : arrayPoints) {
            formMachine.addPoint(point);
        }

        return formMachine;
    }

    @Test
    public void getOccupiedLocation_rotatedMargin() {
        Point margin = new PointClass(15, 15);
        Position position = new PositionClass(margin, -PI / 2);

        Footprint footprint = new FootprintClass(position, 1, getStandartMovingObjet());
        PolygonExtended actualOccupiedLocation = footprint.getOccupiedLocation();
        PolygonExtended expectedPolygonExtended = new PolygonExtendedClass();
        expectedPolygonExtended.addPoint(new PointClass(10, 20));
        expectedPolygonExtended.addPoint(new PointClass(20, 20));
        expectedPolygonExtended.addPoint(new PointClass(20, 10));
        expectedPolygonExtended.addPoint(new PointClass(10, 10));


        assertEquals(expectedPolygonExtended, actualOccupiedLocation);
    }

    private PolygonExtended expectedRoteted(Point margin) {
        PolygonExtended expectedPolygonExtended = new PolygonExtendedClass();

        int endIndex = arrayPoints.length - 1;
        for (int i = 0; i < arrayPoints.length; i++) {
            int indexNextPoint = i + 1;
            Point nextCyclePoint = nextCyclePoint(indexNextPoint, endIndex);

            Point marginedPoint = new PointClass(
                    nextCyclePoint.getX() + margin.getX(),
                    nextCyclePoint.getY() + margin.getY()
            );
            expectedPolygonExtended.addPoint(marginedPoint);
        }

        return expectedPolygonExtended;
    }

    private Point nextCyclePoint(int indexNextPoint, int endIndex) {
        Point nextPoint;
        if (indexNextPoint <= endIndex) {
            nextPoint = arrayPoints[indexNextPoint];
        } else {
            nextPoint = arrayPoints[0];
        }

        return nextPoint;
    }

    @org.junit.jupiter.api.Test
    void getApproximation_commutativeProperty() {
        FabricParametersMoving fabricParametersMoving = new FabricParametersMovingClass();
        ParametersMoving machine = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);

        Position firstPosition = new PositionClass(new PointClass(0, 0), 1.54);
        double anyTimeToNextFootprint = 4;
        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine);

        Position secondPosition = new PositionClass(new PointClass(10, 0), 1.54);
        Footprint second = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine);


        double timeFirst = 0;
        double timeSecond = 1;
        double timeApproximation = 0.5;

        Footprint firstSecondApproximation = first.getApproximation(timeFirst, second, timeSecond, timeApproximation);
        Footprint secondFirstApproximation = second.getApproximation(timeSecond, first, timeFirst, timeApproximation);

        assertEquals(firstSecondApproximation, secondFirstApproximation);
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        FabricParametersMoving fabricParametersMoving = new FabricParametersMovingClass();
        ParametersMoving machine = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);

        Position firstPosition = new PositionClass(new PointClass(0, 0), 1.54);
        double anyTimeToNextFootprint = 4;
        Footprint footprint = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine);

        String actual = footprint.toString();
        String expected =
                "((x: 0.0   y: 0.0) rotation: 1.54) MovObjtype: TEST_SQUARE_20 id: -1158177819 speed: 10.0 timeStanding: 4.0";
        expected = getStringWithoutId(expected);
        actual = getStringWithoutId(actual);
        assertEquals(expected, actual); //FIXME test with RegExp
    }

    @org.junit.jupiter.api.Test
    void testHashCode_createdFromDifferentEqualsObjects0() {
        FabricParametersMoving fabricParametersMoving = new FabricParametersMovingClass();
        ParametersMoving machine = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);

        Position firstPosition = new PositionClass(new PointClass(0, 0), 0);
        double anyTimeToNextFootprint = 0;
        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine);

        double any2TimeToNextFootprint = 0;
        Position secondPosition = new PositionClass(new PointClass(0, 0), 0);
        Footprint second = new FootprintClass(secondPosition, any2TimeToNextFootprint, machine);

        assertEquals(first.hashCode(), second.hashCode());
    }

    @org.junit.jupiter.api.Test
    void testHashCode_createdFromSingleObjects0() {
        FabricParametersMoving fabricParametersMoving = new FabricParametersMovingClass();
        ParametersMoving machine = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);

        Position firstPosition = new PositionClass(new PointClass(0, 0), 0);
        double anyTimeToNextFootprint = 0;
        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine);

        Footprint second = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine);

        assertEquals(first.hashCode(), second.hashCode());
    }

    @org.junit.jupiter.api.Test
    void testHashCode_createdFromSingleObjects() {
        FabricParametersMoving fabricParametersMoving = new FabricParametersMovingClass();
        ParametersMoving machine = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);

        Position firstPosition = new PositionClass(new PointClass(0, 0), 1.54);
        double anyTimeToNextFootprint = 4;

        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine);
        Footprint second = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine);

        assertEquals(first.hashCode(), second.hashCode());
    }

    @org.junit.jupiter.api.Test
    void testEquals_createdFromSingleObjects() {
        FabricParametersMoving fabricParametersMoving = new FabricParametersMovingClass();
        ParametersMoving machine = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);

        Position firstPosition = new PositionClass(new PointClass(0, 0), 1.54);
        double anyTimeToNextFootprint = 4;

        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine);
        Footprint second = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine);

        assertEquals(first, second);
    }

    @org.junit.jupiter.api.Test
    void testEquals_createdFromSingleObjects0() {
        FabricParametersMoving fabricParametersMoving = new FabricParametersMovingClass();
        ParametersMoving machine = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);

        Position firstPosition = new PositionClass(new PointClass(0, 0), 0);
        double anyTimeToNextFootprint = 0;
        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine);

        Footprint second = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine);

        assertEquals(first, second);
    }

    @org.junit.jupiter.api.Test
    void testEquals_differentXCoordinateInPoint() {
        FabricParametersMoving fabricParametersMoving = new FabricParametersMovingClass();
        ParametersMoving machine = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
        double anyTimeToNextFootprint = 0;

        Position firstPosition = new PositionClass(new PointClass(0, 0), 0);
        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine);

        Position secondPosition = new PositionClass(new PointClass(1, 0), 0);
        Footprint second = new FootprintClass(secondPosition, anyTimeToNextFootprint, machine);

        assertNotEquals(first, second);
    }

    @org.junit.jupiter.api.Test
    void testEquals_differentYCoordinateInPoint() {
        FabricParametersMoving fabricParametersMoving = new FabricParametersMovingClass();
        ParametersMoving machine = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
        double anyTimeToNextFootprint = 0;

        Position firstPosition = new PositionClass(new PointClass(0, 0), 0);
        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine);

        Position secondPosition = new PositionClass(new PointClass(0, 1), 0);
        Footprint second = new FootprintClass(secondPosition, anyTimeToNextFootprint, machine);

        assertNotEquals(first, second);
    }

    @org.junit.jupiter.api.Test
    void testEquals_differentAngle() {
        FabricParametersMoving fabricParametersMoving = new FabricParametersMovingClass();
        ParametersMoving machine = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
        double anyTimeToNextFootprint = 0;

        Position firstPosition = new PositionClass(new PointClass(0, 0), 0);
        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine);

        Position secondPosition = new PositionClass(new PointClass(0, 0), 1);
        Footprint second = new FootprintClass(secondPosition, anyTimeToNextFootprint, machine);

        assertNotEquals(first, second);
    }

    @org.junit.jupiter.api.Test
    void testEquals_differentTimeToNextFootprint() {
        FabricParametersMoving fabricParametersMoving = new FabricParametersMovingClass();
        ParametersMoving machine = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
        double anyTimeToNextFootprint = 0;

        Position firstPosition = new PositionClass(new PointClass(0, 0), 0);
        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine);

        double any2TimeToNextFootprint = 1;
        Position secondPosition = new PositionClass(new PointClass(0, 0), 0);
        Footprint second = new FootprintClass(secondPosition, any2TimeToNextFootprint, machine);

        assertNotEquals(first, second);
    }

    @org.junit.jupiter.api.Test
    void testEquals_differentMovingObject() {
        FabricParametersMoving fabricParametersMoving = new FabricParametersMovingClass();
        double anyTimeToNextFootprint = 0;

        ParametersMoving machine1 = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
        Position firstPosition = new PositionClass(new PointClass(0, 0), 0);
        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine1);

        ParametersMoving machine2 = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
        Position secondPosition = new PositionClass(new PointClass(0, 0), 0);
        Footprint second = new FootprintClass(secondPosition, anyTimeToNextFootprint, machine2);

        assertNotEquals(first, second);
    }

    @org.junit.jupiter.api.Test
    void testEquals_createdFromDifferentEqualsObjects0() {
        FabricParametersMoving fabricParametersMoving = new FabricParametersMovingClass();
        ParametersMoving machine = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);

        Position firstPosition = new PositionClass(new PointClass(0, 0), 0);
        double anyTimeToNextFootprint = 0;
        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine);

        double any2TimeToNextFootprint = 0;
        Position secondPosition = new PositionClass(new PointClass(0, 0), 0);
        Footprint second = new FootprintClass(secondPosition, any2TimeToNextFootprint, machine);

        assertEquals(first, second);
    }

    private String getStringWithoutId(String inputString) {
        int startIndex = inputString.indexOf("id:");
        int endIndex = inputString.indexOf("speed");
        String replacement = "-0000000000 ";
        String toBeReplaced = inputString.substring(startIndex + 4, endIndex);
        System.out.println(inputString.replace(toBeReplaced, replacement));

        return "";
    }
}