package com.alamutra.ccoa.Logic.MovingObjects;

import com.alamutra.ccoa.GUI.StatementTaskRendering.TypeMachinesBody;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParametersMovingClassTest {

    @Test
    void getShape_allPointsOfShapeAreVectorsFromCoordinateApplicationPointQuarter1() {
        PolygonExtended inputFormMachine = new PolygonExtendedClass();
        inputFormMachine.addPoint(new PointClass(0, 0));
        inputFormMachine.addPoint(new PointClass(0, 10));
        inputFormMachine.addPoint(new PointClass(10, 10));
        inputFormMachine.addPoint(new PointClass(10, 0));
        ParametersMoving parametersMoving = new ParametersMovingClass(10, inputFormMachine, TypeMachinesBody.TEST_SQUARE_20);

        PolygonExtended expectedShapeMachine = new PolygonExtendedClass();
        expectedShapeMachine.addPoint(new PointClass(-5, -5));
        expectedShapeMachine.addPoint(new PointClass(-5, 5));
        expectedShapeMachine.addPoint(new PointClass(5, 5));
        expectedShapeMachine.addPoint(new PointClass(5, -5));

        PolygonExtended actualShape = parametersMoving.getShape();
        assertEquals(expectedShapeMachine, actualShape);
    }
    @Test
    void getShape_allPointsOfShapeAreVectorsFromCoordinateApplicationPointNoChangesRequired() {
        PolygonExtended expectedShapeMachine = new PolygonExtendedClass();
        expectedShapeMachine.addPoint(new PointClass(-5, -5));
        expectedShapeMachine.addPoint(new PointClass(-5, 5));
        expectedShapeMachine.addPoint(new PointClass(5, 5));
        expectedShapeMachine.addPoint(new PointClass(5, -5));
        ParametersMoving parametersMoving = new ParametersMovingClass(10, expectedShapeMachine, TypeMachinesBody.TEST_SQUARE_20);

        PolygonExtended actualShape = parametersMoving.getShape();
        assertEquals(expectedShapeMachine, actualShape);
    }

    @Test
    void constructor_withSpeed() {
        PolygonExtended shape = new PolygonExtendedClass();
        shape.addPoint(new PointClass(0, 0));
        shape.addPoint(new PointClass(0, 1));
        shape.addPoint(new PointClass(1, 0));
        double expectedSpeed = 10;
        ParametersMoving parametersMoving = new ParametersMovingClass(expectedSpeed, shape, TypeMachinesBody.TEST_SQUARE_20);

        double actualSpeed = parametersMoving.getSpeed();
        assertEquals(expectedSpeed, actualSpeed);
    }
}