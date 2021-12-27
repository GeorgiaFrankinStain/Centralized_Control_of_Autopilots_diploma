package com.alamutra.ccoa.Logic.MovingObjects;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.PointClass;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.PolygonExtended;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.PolygonExtendedClass;
import com.alamutra.ccoa.StatementTaskRendering.TypeMachinesBody;
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
        } catch (IllegalArgumentException e) {
            assertEquals("the speed is less than 0", e.getMessage());
        };
    }

    @Test
    void setShape_offset_center() {
        BuilderParametersMoving builder = new BuilderParametersMovingClass();

        builder.setTypeMachinesBody(TypeMachinesBody.TEST_SQUARE_20);

        PolygonExtended offsetShape = new PolygonExtendedClass();
        offsetShape.addPoint(new PointClass(0, 0));
        offsetShape.addPoint(new PointClass(0, 2));
        offsetShape.addPoint(new PointClass(2, 2));
        offsetShape.addPoint(new PointClass(2, 0));
        builder.setShape(offsetShape);

        builder.setSpeed(1);

        PolygonExtended expectedShape = new PolygonExtendedClass();
        expectedShape.addPoint(new PointClass(-1, -1));
        expectedShape.addPoint(new PointClass(-1, 1));
        expectedShape.addPoint(new PointClass(1, 1));
        expectedShape.addPoint(new PointClass(1, -1));

        ParametersMoving parametersMoving = builder.getParametersMoving();
        PolygonExtended actualShape = parametersMoving.getShape();
        assertEquals(expectedShape, actualShape);
    }
}