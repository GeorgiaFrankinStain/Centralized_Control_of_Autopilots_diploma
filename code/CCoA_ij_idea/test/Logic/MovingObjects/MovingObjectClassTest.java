package Logic.MovingObjects;

import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.FootprintSpaceTime.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovingObjectClassTest {

    @Test
    void getShape_allPointsOfShapeAreVectorsFromCoordinateApplicationPointQuarter1() {
        PolygonExtended inputFormMachine = new PolygonExtendedClass();
        inputFormMachine.addPoint(new PointClass(0, 0));
        inputFormMachine.addPoint(new PointClass(0, 10));
        inputFormMachine.addPoint(new PointClass(10, 10));
        inputFormMachine.addPoint(new PointClass(10, 0));
        MovingObject movingObject = new MovingObjectClass(inputFormMachine, TypeMachinesBody.TEST_SQUARE_20);

        PolygonExtended expectedShapeMachine = new PolygonExtendedClass();
        expectedShapeMachine.addPoint(new PointClass(-5, -5));
        expectedShapeMachine.addPoint(new PointClass(-5, 5));
        expectedShapeMachine.addPoint(new PointClass(5, 5));
        expectedShapeMachine.addPoint(new PointClass(5, -5));

        PolygonExtended actualShape = movingObject.getShape();
        assertEquals(expectedShapeMachine, actualShape);
    }
    @Test
    void getShape_allPointsOfShapeAreVectorsFromCoordinateApplicationPointNoChangesRequired() {
        PolygonExtended expectedShapeMachine = new PolygonExtendedClass();
        expectedShapeMachine.addPoint(new PointClass(-5, -5));
        expectedShapeMachine.addPoint(new PointClass(-5, 5));
        expectedShapeMachine.addPoint(new PointClass(5, 5));
        expectedShapeMachine.addPoint(new PointClass(5, -5));
        MovingObject movingObject = new MovingObjectClass(expectedShapeMachine, TypeMachinesBody.TEST_SQUARE_20);

        PolygonExtended actualShape = movingObject.getShape();
        assertEquals(expectedShapeMachine, actualShape);
    }
}