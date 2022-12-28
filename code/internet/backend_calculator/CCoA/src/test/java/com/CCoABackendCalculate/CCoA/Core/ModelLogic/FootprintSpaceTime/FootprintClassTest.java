package com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.FabricParametersMovingUnique;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.FabricParametersMovingUniqueClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.ParametersMovingUnique;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.ParametersMovingUniqueClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.PathsMachines.PositionClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.Position;
import com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.TypeMachinesBody;
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


    private PointCCoA[] arrayPointCCoAS = {
            new PointCCoAClass(10, 10),
            new PointCCoAClass(10, 20),
            new PointCCoAClass(20, 20),
            new PointCCoAClass(20, 10),
    };

    private ParametersMovingUnique getStandartMovingObjet() {
        ParametersMovingUnique parametersMovingUnique = new ParametersMovingUniqueClass(10, getStandardFormMachine(), TypeMachinesBody.TEST_SQUARE_20, 13);
        return parametersMovingUnique;
    }

    @Test
    public void getOccupiedLocation_margin() {
        PointCCoA margin = new PointCCoAClass(10, 10);
        Position position = new PositionClass(margin, 0.0);
        Route route = new RouteClass();

        Footprint footprint = new FootprintClass(position, 1, getStandartMovingObjet(), route);
        PolygonCCoA actualOccupiedLocation = footprint.getOccupiedLocation();
        PolygonCCoA expectedPolygonCCoA = new PolygonCCoAClass();
        expectedPolygonCCoA.addPoint(new PointCCoAClass(5, 5));
        expectedPolygonCCoA.addPoint(new PointCCoAClass(5, 15));
        expectedPolygonCCoA.addPoint(new PointCCoAClass(15, 15));
        expectedPolygonCCoA.addPoint(new PointCCoAClass(15, 5));

        assertEquals(expectedPolygonCCoA, actualOccupiedLocation);
    }

    private PolygonCCoA expectedAfterMargin(PointCCoA margin) {
        PolygonCCoA expectedPolygonCCoA = new PolygonCCoAClass();

        for (PointCCoA pointCCoA : arrayPointCCoAS) {
            PointCCoA marginedPointCCoA = new PointCCoAClass(
                    pointCCoA.getX() + margin.getX(),
                    pointCCoA.getY() + margin.getY()
            );
            expectedPolygonCCoA.addPoint(marginedPointCCoA);
        }

        return expectedPolygonCCoA;
    }

    private PolygonCCoA getStandardFormMachine() {
        PolygonCCoA formMachine = new PolygonCCoAClass();


        for (PointCCoA pointCCoA : arrayPointCCoAS) {
            formMachine.addPoint(pointCCoA);
        }

        return formMachine;
    }

    @Test
    public void getOccupiedLocation_rotatedMargin() {
        PointCCoA margin = new PointCCoAClass(15, 15);
        Position position = new PositionClass(margin, -PI / 2);
        Route route = new RouteClass();

        Footprint footprint = new FootprintClass(position, 1, getStandartMovingObjet(), route);
        PolygonCCoA actualOccupiedLocation = footprint.getOccupiedLocation();
        PolygonCCoA expectedPolygonCCoA = new PolygonCCoAClass();
        expectedPolygonCCoA.addPoint(new PointCCoAClass(10, 20));
        expectedPolygonCCoA.addPoint(new PointCCoAClass(20, 20));
        expectedPolygonCCoA.addPoint(new PointCCoAClass(20, 10));
        expectedPolygonCCoA.addPoint(new PointCCoAClass(10, 10));


        assertEquals(expectedPolygonCCoA, actualOccupiedLocation);
    }

    private PolygonCCoA expectedRoteted(PointCCoA margin) {
        PolygonCCoA expectedPolygonCCoA = new PolygonCCoAClass();

        int endIndex = arrayPointCCoAS.length - 1;
        for (int i = 0; i < arrayPointCCoAS.length; i++) {
            int indexNextPoint = i + 1;
            PointCCoA nextCyclePointCCoA = nextCyclePoint(indexNextPoint, endIndex);

            PointCCoA marginedPointCCoA = new PointCCoAClass(
                    nextCyclePointCCoA.getX() + margin.getX(),
                    nextCyclePointCCoA.getY() + margin.getY()
            );
            expectedPolygonCCoA.addPoint(marginedPointCCoA);
        }

        return expectedPolygonCCoA;
    }

    private PointCCoA nextCyclePoint(int indexNextPoint, int endIndex) {
        PointCCoA nextPointCCoA;
        if (indexNextPoint <= endIndex) {
            nextPointCCoA = arrayPointCCoAS[indexNextPoint];
        } else {
            nextPointCCoA = arrayPointCCoAS[0];
        }

        return nextPointCCoA;
    }

    @org.junit.jupiter.api.Test
    void getApproximation_commutativeProperty() {
        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
        ParametersMovingUnique machine = fabricParametersMovingUnique.getMoving(TypeMachinesBody.TEST_SQUARE_20, 13);
        Route route = new RouteClass();

        Position firstPosition = new PositionClass(new PointCCoAClass(0, 0), 1.54);
        double anyTimeToNextFootprint = 4;
        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine, route);

        Position secondPosition = new PositionClass(new PointCCoAClass(10, 0), 1.54);
        Footprint second = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine, route);


        double timeFirst = 0;
        double timeSecond = 1;
        double timeApproximation = 0.5;

        Footprint firstSecondApproximation = first.getApproximation(timeFirst, second, timeSecond, timeApproximation);
        Footprint secondFirstApproximation = second.getApproximation(timeSecond, first, timeFirst, timeApproximation);

        assertEquals(firstSecondApproximation, secondFirstApproximation);
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
        ParametersMovingUnique machine = fabricParametersMovingUnique.getMoving(TypeMachinesBody.TEST_SQUARE_20, 13);
        Route route = new RouteClass();

        Position firstPosition = new PositionClass(new PointCCoAClass(0, 0), 1.54);
        double anyTimeToNextFootprint = 4;
        Footprint footprint = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine, route);

        String actual = footprint.toString();
        String expected =
                "((x: 0.0   y: 0.0) rotation: 1.54) MovObjtype: TEST_SQUARE_20 id: -1158177819 speed: 10.0 timeStanding: 4.0";
        expected = getStringWithoutId(expected);
        actual = getStringWithoutId(actual);
        assertEquals(expected, actual); //FIXME test with RegExp
    }

    @org.junit.jupiter.api.Test
    void testHashCode_createdFromDifferentEqualsObjects0() {
        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
        ParametersMovingUnique machine = fabricParametersMovingUnique.getMoving(TypeMachinesBody.TEST_SQUARE_20, 13);
        Route route = new RouteClass();

        Position firstPosition = new PositionClass(new PointCCoAClass(0, 0), 0);
        double anyTimeToNextFootprint = 0;
        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine, route);

        double any2TimeToNextFootprint = 0;
        Position secondPosition = new PositionClass(new PointCCoAClass(0, 0), 0);
        Footprint second = new FootprintClass(secondPosition, any2TimeToNextFootprint, machine, route);

        assertEquals(first.hashCode(), second.hashCode());
    }

    @org.junit.jupiter.api.Test
    void testHashCode_createdFromSingleObjects0() {
        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
        ParametersMovingUnique machine = fabricParametersMovingUnique.getMoving(TypeMachinesBody.TEST_SQUARE_20, 13);
        Route route = new RouteClass();

        Position firstPosition = new PositionClass(new PointCCoAClass(0, 0), 0);
        double anyTimeToNextFootprint = 0;
        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine, route);

        Footprint second = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine, route);

        assertEquals(first.hashCode(), second.hashCode());
    }

    @org.junit.jupiter.api.Test
    void testHashCode_createdFromSingleObjects() {
        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
        ParametersMovingUnique machine = fabricParametersMovingUnique.getMoving(TypeMachinesBody.TEST_SQUARE_20, 13);
        Route route = new RouteClass();

        Position firstPosition = new PositionClass(new PointCCoAClass(0, 0), 1.54);
        double anyTimeToNextFootprint = 4;

        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine, route);
        Footprint second = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine, route);

        assertEquals(first.hashCode(), second.hashCode());
    }

    @org.junit.jupiter.api.Test
    void testEquals_createdFromSingleObjects() {
        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
        ParametersMovingUnique machine = fabricParametersMovingUnique.getMoving(TypeMachinesBody.TEST_SQUARE_20, 13);
        Route route = new RouteClass();

        Position firstPosition = new PositionClass(new PointCCoAClass(0, 0), 1.54);
        double anyTimeToNextFootprint = 4;

        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine, route);
        Footprint second = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine, route);

        assertEquals(first, second);
    }

    @org.junit.jupiter.api.Test
    void testEquals_createdFromSingleObjects0() {
        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
        ParametersMovingUnique machine = fabricParametersMovingUnique.getMoving(TypeMachinesBody.TEST_SQUARE_20, 13);
        Route route = new RouteClass();

        Position firstPosition = new PositionClass(new PointCCoAClass(0, 0), 0);
        double anyTimeToNextFootprint = 0;
        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine, route);

        Footprint second = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine, route);

        assertEquals(first, second);
    }

    @org.junit.jupiter.api.Test
    void testEquals_differentXCoordinateInPoint() {
        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
        ParametersMovingUnique machine = fabricParametersMovingUnique.getMoving(TypeMachinesBody.TEST_SQUARE_20, 13);
        Route route = new RouteClass();
        double anyTimeToNextFootprint = 0;

        Position firstPosition = new PositionClass(new PointCCoAClass(0, 0), 0);
        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine, route);

        Position secondPosition = new PositionClass(new PointCCoAClass(1, 0), 0);
        Footprint second = new FootprintClass(secondPosition, anyTimeToNextFootprint, machine, route);

        assertNotEquals(first, second);
    }

    @org.junit.jupiter.api.Test
    void testEquals_differentYCoordinateInPoint() {
        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
        ParametersMovingUnique machine = fabricParametersMovingUnique.getMoving(TypeMachinesBody.TEST_SQUARE_20, 13);
        Route route = new RouteClass();
        double anyTimeToNextFootprint = 0;

        Position firstPosition = new PositionClass(new PointCCoAClass(0, 0), 0);
        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine, route);

        Position secondPosition = new PositionClass(new PointCCoAClass(0, 1), 0);
        Footprint second = new FootprintClass(secondPosition, anyTimeToNextFootprint, machine, route);

        assertNotEquals(first, second);
    }

    @org.junit.jupiter.api.Test
    void testEquals_differentAngle() {
        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
        ParametersMovingUnique machine = fabricParametersMovingUnique.getMoving(TypeMachinesBody.TEST_SQUARE_20, 13);
        Route route = new RouteClass();
        double anyTimeToNextFootprint = 0;

        Position firstPosition = new PositionClass(new PointCCoAClass(0, 0), 0);
        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine, route);

        Position secondPosition = new PositionClass(new PointCCoAClass(0, 0), 1);
        Footprint second = new FootprintClass(secondPosition, anyTimeToNextFootprint, machine, route);

        assertNotEquals(first, second);
    }

    @org.junit.jupiter.api.Test
    void testEquals_differentTimeToNextFootprint() {
        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
        ParametersMovingUnique machine = fabricParametersMovingUnique.getMoving(TypeMachinesBody.TEST_SQUARE_20, 13);
        Route route = new RouteClass();
        double anyTimeToNextFootprint = 0;

        Position firstPosition = new PositionClass(new PointCCoAClass(0, 0), 0);
        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine, route);

        double any2TimeToNextFootprint = 1;
        Position secondPosition = new PositionClass(new PointCCoAClass(0, 0), 0);
        Footprint second = new FootprintClass(secondPosition, any2TimeToNextFootprint, machine, route);

        assertNotEquals(first, second);
    }

    @org.junit.jupiter.api.Test
    void testEquals_differentMovingObject() {
        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
        Route route = new RouteClass();
        double anyTimeToNextFootprint = 0;

        ParametersMovingUnique machine1 = fabricParametersMovingUnique.getMoving(TypeMachinesBody.TEST_SQUARE_20, 13);
        Position firstPosition = new PositionClass(new PointCCoAClass(0, 0), 0);
        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine1, route);

        ParametersMovingUnique machine2 = fabricParametersMovingUnique.getMoving(TypeMachinesBody.TEST_SQUARE_20, 13);
        Position secondPosition = new PositionClass(new PointCCoAClass(0, 0), 0);
        Footprint second = new FootprintClass(secondPosition, anyTimeToNextFootprint, machine2, route);

        assertNotEquals(first, second);
    }

    @org.junit.jupiter.api.Test
    void testEquals_createdFromDifferentEqualsObjects0() {
        FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
        ParametersMovingUnique machine = fabricParametersMovingUnique.getMoving(TypeMachinesBody.TEST_SQUARE_20, 13);
        Route route = new RouteClass();

        Position firstPosition = new PositionClass(new PointCCoAClass(0, 0), 0);
        double anyTimeToNextFootprint = 0;
        Footprint first = new FootprintClass(firstPosition, anyTimeToNextFootprint, machine, route);

        double any2TimeToNextFootprint = 0;
        Position secondPosition = new PositionClass(new PointCCoAClass(0, 0), 0);
        Footprint second = new FootprintClass(secondPosition, any2TimeToNextFootprint, machine, route);

        assertEquals(first, second);
    }

    private String getStringWithoutId(String inputString) {
        int startIndex = inputString.indexOf("id:");
        int endIndex = inputString.indexOf("speed");
        String replacement = "-0000000000 ";
        String toBeReplaced = inputString.substring(startIndex + 4, endIndex);

        return "";
    }
}