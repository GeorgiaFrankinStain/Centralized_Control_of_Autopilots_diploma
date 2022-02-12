package com.alamutra.ccoa.Core.Logic.MovingBody;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoAClass;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PolygonCCoAClass;
import com.alamutra.ccoa.Core.SettingRenderingTasks.TypeMachinesBody;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParametersMovingClassTest {

    @Test
    void getShape_allPointsOfShapeAreVectorsFromCoordinateApplicationPointQuarter1() {
        PolygonCCoA inputFormMachine = new PolygonCCoAClass();
        inputFormMachine.addPoint(new PointCCoAClass(0, 0));
        inputFormMachine.addPoint(new PointCCoAClass(0, 10));
        inputFormMachine.addPoint(new PointCCoAClass(10, 10));
        inputFormMachine.addPoint(new PointCCoAClass(10, 0));
        ParametersMoving parametersMoving = new ParametersMovingClass(10, inputFormMachine, TypeMachinesBody.TEST_SQUARE_20);

        PolygonCCoA expectedShapeMachine = new PolygonCCoAClass();
        expectedShapeMachine.addPoint(new PointCCoAClass(-5, -5));
        expectedShapeMachine.addPoint(new PointCCoAClass(-5, 5));
        expectedShapeMachine.addPoint(new PointCCoAClass(5, 5));
        expectedShapeMachine.addPoint(new PointCCoAClass(5, -5));

        PolygonCCoA actualShape = parametersMoving.getShape();
        assertEquals(expectedShapeMachine, actualShape);
    }
    @Test
    void getShape_allPointsOfShapeAreVectorsFromCoordinateApplicationPointNoChangesRequired() {
        PolygonCCoA expectedShapeMachine = new PolygonCCoAClass();
        expectedShapeMachine.addPoint(new PointCCoAClass(-5, -5));
        expectedShapeMachine.addPoint(new PointCCoAClass(-5, 5));
        expectedShapeMachine.addPoint(new PointCCoAClass(5, 5));
        expectedShapeMachine.addPoint(new PointCCoAClass(5, -5));
        ParametersMoving parametersMoving = new ParametersMovingClass(10, expectedShapeMachine, TypeMachinesBody.TEST_SQUARE_20);

        PolygonCCoA actualShape = parametersMoving.getShape();
        assertEquals(expectedShapeMachine, actualShape);
    }

    @Test
    void constructor_withSpeed() {
        PolygonCCoA shape = new PolygonCCoAClass();
        shape.addPoint(new PointCCoAClass(0, 0));
        shape.addPoint(new PointCCoAClass(0, 1));
        shape.addPoint(new PointCCoAClass(1, 0));
        double expectedSpeed = 10;
        ParametersMoving parametersMoving = new ParametersMovingClass(expectedSpeed, shape, TypeMachinesBody.TEST_SQUARE_20);

        double actualSpeed = parametersMoving.getSpeed();
        assertEquals(expectedSpeed, actualSpeed);
    }
}