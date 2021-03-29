package Logic.MovingObjects;

import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.FootprintSpaceTime.PointClass;
import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.FootprintSpaceTime.PolygonExtendedClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuilderParametersMovingClassTest {

    @Test
    void setSpeed_0() {
        BuilderParametersMoving builder = new BuilderParametersMovingClass();

        builder.setTypeMachinesBody(TypeMachinesBody.TEST_SQUARE_20);

        PolygonExtended polygon = new PolygonExtendedClass();
        polygon.addPoint(new PointClass(0, 0));
        polygon.addPoint(new PointClass(0, 1));
        polygon.addPoint(new PointClass(1, 0));
        builder.setShape(polygon);

        builder.setSpeed(0);

        ParametersMoving parametersMoving = builder.getParametersMoving();
        assertEquals(parametersMoving.getSpeed(), 0);
    }
    @Test
    void setSpeed_7() {
        BuilderParametersMoving builder = new BuilderParametersMovingClass();

        builder.setTypeMachinesBody(TypeMachinesBody.TEST_SQUARE_20);

        PolygonExtended polygon = new PolygonExtendedClass();
        polygon.addPoint(new PointClass(0, 0));
        polygon.addPoint(new PointClass(0, 1));
        polygon.addPoint(new PointClass(1, 0));
        builder.setShape(polygon);

        builder.setSpeed(7);

        ParametersMoving parametersMoving = builder.getParametersMoving();
        assertEquals(parametersMoving.getSpeed(), 7);
    }
    @Test
    void setSpeed_negativeThrow() {
        BuilderParametersMoving builder = new BuilderParametersMovingClass();

        builder.setTypeMachinesBody(TypeMachinesBody.TEST_SQUARE_20);

        PolygonExtended polygon = new PolygonExtendedClass();
        polygon.addPoint(new PointClass(0, 0));
        polygon.addPoint(new PointClass(0, 1));
        polygon.addPoint(new PointClass(1, 0));
        builder.setShape(polygon);

        try {
            builder.setSpeed(-1);
        } catch ();

        ParametersMoving parametersMoving = builder.getParametersMoving();
    }

    @Test
    void setShape() {
        assert (false);
    }
}