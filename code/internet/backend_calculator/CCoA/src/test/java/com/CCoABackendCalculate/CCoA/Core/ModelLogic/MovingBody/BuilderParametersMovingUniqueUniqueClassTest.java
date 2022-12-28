package com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoAClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PolygonCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PolygonCCoAClass;
import com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.TypeMachinesBody;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuilderParametersMovingUniqueUniqueClassTest {

    @Test
    void setSpeed_0() {
        BuilderParametersMovingUnique builder = new BuilderParametersMovingUniqueClass();

        builder.setTypeMachinesBody(TypeMachinesBody.TEST_SQUARE_20);

        PolygonCCoA polygon = new PolygonCCoAClass();
        polygon.addPoint(new PointCCoAClass(0, 0));
        polygon.addPoint(new PointCCoAClass(0, 1));
        polygon.addPoint(new PointCCoAClass(1, 0));
        builder.setShape(polygon);

        builder.setSpeed(0);

        ParametersMovingUnique parametersMovingUnique = builder.getParametersMoving(452);
        assertEquals(parametersMovingUnique.getSpeed(), 0);
    }
    @Test
    void setSpeed_7() {
        BuilderParametersMovingUnique builder = new BuilderParametersMovingUniqueClass();

        builder.setTypeMachinesBody(TypeMachinesBody.TEST_SQUARE_20);

        PolygonCCoA polygon = new PolygonCCoAClass();
        polygon.addPoint(new PointCCoAClass(0, 0));
        polygon.addPoint(new PointCCoAClass(0, 1));
        polygon.addPoint(new PointCCoAClass(1, 0));
        builder.setShape(polygon);

        builder.setSpeed(7);

        ParametersMovingUnique parametersMovingUnique = builder.getParametersMoving(5732457);
        assertEquals(parametersMovingUnique.getSpeed(), 7);
    }
    @Test
    void setSpeed_negativeThrow() {
        BuilderParametersMovingUnique builder = new BuilderParametersMovingUniqueClass();

        builder.setTypeMachinesBody(TypeMachinesBody.TEST_SQUARE_20);

        PolygonCCoA polygon = new PolygonCCoAClass();
        polygon.addPoint(new PointCCoAClass(0, 0));
        polygon.addPoint(new PointCCoAClass(0, 1));
        polygon.addPoint(new PointCCoAClass(1, 0));
        builder.setShape(polygon);

        try {
            builder.setSpeed(-1);
        } catch (IllegalArgumentException e) {
            assertEquals("the speed is less than 0", e.getMessage());
        };
    }

    @Test
    void setShape_offset_center() {
        BuilderParametersMovingUnique builder = new BuilderParametersMovingUniqueClass();

        builder.setTypeMachinesBody(TypeMachinesBody.TEST_SQUARE_20);

        PolygonCCoA offsetShape = new PolygonCCoAClass();
        offsetShape.addPoint(new PointCCoAClass(0, 0));
        offsetShape.addPoint(new PointCCoAClass(0, 2));
        offsetShape.addPoint(new PointCCoAClass(2, 2));
        offsetShape.addPoint(new PointCCoAClass(2, 0));
        builder.setShape(offsetShape);

        builder.setSpeed(1);

        PolygonCCoA expectedShape = new PolygonCCoAClass();
        expectedShape.addPoint(new PointCCoAClass(-1, -1));
        expectedShape.addPoint(new PointCCoAClass(-1, 1));
        expectedShape.addPoint(new PointCCoAClass(1, 1));
        expectedShape.addPoint(new PointCCoAClass(1, -1));

        ParametersMovingUnique parametersMovingUnique = builder.getParametersMoving(2547345);
        PolygonCCoA actualShape = parametersMovingUnique.getShape();
        assertEquals(expectedShape, actualShape);
    }
}