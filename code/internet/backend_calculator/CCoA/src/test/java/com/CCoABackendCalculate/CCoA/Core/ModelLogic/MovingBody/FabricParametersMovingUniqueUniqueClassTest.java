package com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoAClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PolygonCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PolygonCCoAClass;
import com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.TypeMachinesBody;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FabricParametersMovingUniqueUniqueClassTest {

    ParametersMovingUnique expectedParametersTestSquare20 = createParametersTestSquare20();

    ParametersMovingUnique actualParametersReturned = actualParametersReturned();

    private ParametersMovingUnique createParametersTestSquare20() {
        BuilderParametersMovingUnique builder = createBuilderTestSquare20();

        return builder.getParametersMoving(4);
    }

    private ParametersMovingUnique actualParametersReturned() {
        FabricParametersMovingUnique fabric = new FabricParametersMovingUniqueClass();
        ParametersMovingUnique actual = fabric.getMoving(TypeMachinesBody.TEST_SQUARE_20, 13);

        return actual;
    }

    @Test
    void getMoving_autoSetUniqueIdEquals() {
        assertNotEquals(actualParametersReturned, expectedParametersTestSquare20);
    }

    @Test
    void getMoving_uniqueId() {
        assertNotEquals(actualParametersReturned.getID(), expectedParametersTestSquare20.getID());
    }

    @Test
    void getMoving_square20EqualsShape() {
        assertEquals(actualParametersReturned.getShape(), expectedParametersTestSquare20.getShape());
    }

    @Test
    void getMoving_square20EqualsSpeed() {
        assertEquals(actualParametersReturned.getSpeed(), expectedParametersTestSquare20.getSpeed());
    }

    @Test
    void getMoving_square20EqualsType() {
        assertEquals(actualParametersReturned.getSkin(), expectedParametersTestSquare20.getSkin());
    }

    @Test
    void getMoving_square20EqualsTypeInLevel() {
        assertEquals(actualParametersReturned.getTypeInLevel(), expectedParametersTestSquare20.getTypeInLevel());
    }

    @Test
    void getMoving_square20EqualsRadius() {
        assertEquals(actualParametersReturned.getRadius(), expectedParametersTestSquare20.getRadius());
    }

    @Test
    void getMoving_square20EqualsVectorFromTopLEftToAppliedCoordinates() {
        assertEquals(actualParametersReturned.getRadius(), expectedParametersTestSquare20.getRadius());
    }

    @Test
    void getMoving_square20EqualsPointWhereCoordinatesAreApplied() {
        assertEquals(
                actualParametersReturned.getPointWhereCoordinatesAreApplied(),
                expectedParametersTestSquare20.getPointWhereCoordinatesAreApplied()
        );
    }

    private BuilderParametersMovingUnique builderTestSquare20 = createBuilderTestSquare20();

    private BuilderParametersMovingUnique actualBuilder = getActualBuilderReturned();

    private BuilderParametersMovingUnique createBuilderTestSquare20() {
        PolygonCCoA formMachine = new PolygonCCoAClass();
        formMachine.addPoint(new PointCCoAClass(0, 0));
        formMachine.addPoint(new PointCCoAClass(20, 0));
        formMachine.addPoint(new PointCCoAClass(20, 20));
        formMachine.addPoint(new PointCCoAClass(0, 20));

        BuilderParametersMovingUnique builder = new BuilderParametersMovingUniqueClass();
        builder.setSpeed(10); //~=40 kilometr / hour
        builder.setShape(formMachine);
        builder.setTypeMachinesBody(TypeMachinesBody.TEST_SQUARE_20);

        return builder;
    }

    private BuilderParametersMovingUnique getActualBuilderReturned() {
        FabricParametersMovingUnique fabric = new FabricParametersMovingUniqueClass();
        BuilderParametersMovingUnique actual = fabric.getNewBuilderMoving(TypeMachinesBody.TEST_SQUARE_20);

        return actual;
    }

    @Test
    void getBuilderMoving_uniqueId() {
        ParametersMovingUnique expectedParametersMovingUnique = builderTestSquare20.getParametersMoving(13);
        ParametersMovingUnique actualParametersMovingUnique = actualBuilder.getParametersMoving(4);
        assertNotEquals(actualParametersMovingUnique.getID(), expectedParametersMovingUnique.getID());
    }

    @Test
    void getBuilderMoving_square20EqualsShape() {
        ParametersMovingUnique expectedParametersMovingUnique = builderTestSquare20.getParametersMoving(13);
        ParametersMovingUnique actualParametersMovingUnique = actualBuilder.getParametersMoving(13);
        assertEquals(actualParametersMovingUnique.getShape(), expectedParametersMovingUnique.getShape());
    }

    @Test
    void getBuilderMoving_square20EqualsSpeed() {
        ParametersMovingUnique expectedParametersMovingUnique = builderTestSquare20.getParametersMoving(13);
        ParametersMovingUnique actualParametersMovingUnique = actualBuilder.getParametersMoving(13);
        assertEquals(actualParametersMovingUnique.getSpeed(), expectedParametersMovingUnique.getSpeed());
    }

    @Test
    void getBuilderMoving_square20EqualsType() {
        ParametersMovingUnique expectedParametersMovingUnique = builderTestSquare20.getParametersMoving(13);
        ParametersMovingUnique actualParametersMovingUnique = actualBuilder.getParametersMoving(13);
        assertEquals(actualParametersMovingUnique.getSkin(), expectedParametersMovingUnique.getSkin());
    }

    @Test
    void getBuilderMoving_square20EqualsTypeInLevel() {
        ParametersMovingUnique expectedParametersMovingUnique = builderTestSquare20.getParametersMoving(13);
        ParametersMovingUnique actualParametersMovingUnique = actualBuilder.getParametersMoving(13);
        assertEquals(actualParametersMovingUnique.getTypeInLevel(), expectedParametersMovingUnique.getTypeInLevel());
    }

    @Test
    void getBuilderMoving_square20EqualsRadius() {
        ParametersMovingUnique expectedParametersMovingUnique = builderTestSquare20.getParametersMoving(13);
        ParametersMovingUnique actualParametersMovingUnique = actualBuilder.getParametersMoving(13);
        assertEquals(actualParametersMovingUnique.getRadius(), expectedParametersMovingUnique.getRadius());
    }

    @Test
    void getBuilderMoving_square20EqualsVectorFromTopLEftToAppliedCoordinates() {
        ParametersMovingUnique expectedParametersMovingUnique = builderTestSquare20.getParametersMoving(13);
        ParametersMovingUnique actualParametersMovingUnique = actualBuilder.getParametersMoving(13);
        assertEquals(actualParametersMovingUnique.getRadius(), expectedParametersMovingUnique.getRadius());
    }

    @Test
    void getBuilderMoving_square20EqualsPointWhereCoordinatesAreApplied() {
        ParametersMovingUnique expectedParametersMovingUnique = builderTestSquare20.getParametersMoving(13);
        ParametersMovingUnique actualParametersMovingUnique = actualBuilder.getParametersMoving(13);
        assertEquals(
                actualParametersMovingUnique.getPointWhereCoordinatesAreApplied(),
                expectedParametersMovingUnique.getPointWhereCoordinatesAreApplied()
        );
    }
}