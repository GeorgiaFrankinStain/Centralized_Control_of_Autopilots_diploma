package com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoAClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PolygonCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PolygonCCoAClass;
import com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.TypeMachinesBody;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParametersMovingUniqueClassTest {

    @Test
    void getShape_allPointsOfShapeAreVectorsFromCoordinateApplicationPointQuarter1() {
        PolygonCCoA inputFormMachine = new PolygonCCoAClass();
        inputFormMachine.addPoint(new PointCCoAClass(0, 0));
        inputFormMachine.addPoint(new PointCCoAClass(0, 10));
        inputFormMachine.addPoint(new PointCCoAClass(10, 10));
        inputFormMachine.addPoint(new PointCCoAClass(10, 0));
        ParametersMovingUnique parametersMovingUnique = new ParametersMovingUniqueClass(10, inputFormMachine, TypeMachinesBody.TEST_SQUARE_20, 13);

        PolygonCCoA expectedShapeMachine = new PolygonCCoAClass();
        expectedShapeMachine.addPoint(new PointCCoAClass(-5, -5));
        expectedShapeMachine.addPoint(new PointCCoAClass(-5, 5));
        expectedShapeMachine.addPoint(new PointCCoAClass(5, 5));
        expectedShapeMachine.addPoint(new PointCCoAClass(5, -5));

        PolygonCCoA actualShape = parametersMovingUnique.getShape();
        assertEquals(expectedShapeMachine, actualShape);
    }
    @Test
    void getShape_allPointsOfShapeAreVectorsFromCoordinateApplicationPointNoChangesRequired() {
        PolygonCCoA expectedShapeMachine = new PolygonCCoAClass();
        expectedShapeMachine.addPoint(new PointCCoAClass(-5, -5));
        expectedShapeMachine.addPoint(new PointCCoAClass(-5, 5));
        expectedShapeMachine.addPoint(new PointCCoAClass(5, 5));
        expectedShapeMachine.addPoint(new PointCCoAClass(5, -5));
        ParametersMovingUnique parametersMovingUnique = new ParametersMovingUniqueClass(10, expectedShapeMachine, TypeMachinesBody.TEST_SQUARE_20, 13);

        PolygonCCoA actualShape = parametersMovingUnique.getShape();
        assertEquals(expectedShapeMachine, actualShape);
    }

    @Test
    void constructor_withSpeed() {
        PolygonCCoA shape = new PolygonCCoAClass();
        shape.addPoint(new PointCCoAClass(0, 0));
        shape.addPoint(new PointCCoAClass(0, 1));
        shape.addPoint(new PointCCoAClass(1, 0));
        double expectedSpeed = 10;
        ParametersMovingUnique parametersMovingUnique = new ParametersMovingUniqueClass(expectedSpeed, shape, TypeMachinesBody.TEST_SQUARE_20, 13);

        double actualSpeed = parametersMovingUnique.getSpeed();
        assertEquals(expectedSpeed, actualSpeed);
    }

    @Test
    void getLengthStep_20() {
        ParametersMovingUnique car = new Square20ParametersMovingUnique(13);
        assertEquals(car.getLengthStep(), 20.0);
    }
    @Test
    void getLengthStep_10() {
        PolygonCCoA expectedShapeMachine = new PolygonCCoAClass();
        expectedShapeMachine.addPoint(new PointCCoAClass(-5, -5));
        expectedShapeMachine.addPoint(new PointCCoAClass(-5, 5));
        expectedShapeMachine.addPoint(new PointCCoAClass(5, 5));
        expectedShapeMachine.addPoint(new PointCCoAClass(5, -5));
        ParametersMovingUnique parametersMovingUnique = new ParametersMovingUniqueClass(10, expectedShapeMachine, TypeMachinesBody.TEST_SQUARE_20, 13);


        assertEquals(parametersMovingUnique.getLengthStep(), 10.0);
    }
    @Test
    void getLengthStep_10OnlyNegativeCoordinates() {
        PolygonCCoA expectedShapeMachine = new PolygonCCoAClass();
        expectedShapeMachine.addPoint(new PointCCoAClass(0, 0));
        expectedShapeMachine.addPoint(new PointCCoAClass(-10, 0));
        expectedShapeMachine.addPoint(new PointCCoAClass(-10, -10));
        expectedShapeMachine.addPoint(new PointCCoAClass(0, -10));
        ParametersMovingUnique parametersMovingUnique = new ParametersMovingUniqueClass(10, expectedShapeMachine, TypeMachinesBody.TEST_SQUARE_20, 13);


        assertEquals(parametersMovingUnique.getLengthStep(), 10.0);
    }
}