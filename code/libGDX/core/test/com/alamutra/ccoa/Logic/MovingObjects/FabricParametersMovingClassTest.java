package com.alamutra.ccoa.Logic.MovingObjects;

import com.alamutra.ccoa.GUI.StatementTaskRendering.TypeMachinesBody;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.PointClass;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.PolygonExtended;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.PolygonExtendedClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FabricParametersMovingClassTest {

    ParametersMoving expectedParametersTestSquare20 = createParametersTestSquare20();

    ParametersMoving actualParametersReturned = actualParametersReturned();

    private ParametersMoving createParametersTestSquare20() {
        BuilderParametersMoving builder = createBuilderTestSquare20();

        return builder.getParametersMoving();
    }

    private ParametersMoving actualParametersReturned() {
        FabricParametersMoving fabric = new FabricParametersMovingClass();
        ParametersMoving actual = fabric.getMoving(TypeMachinesBody.TEST_SQUARE_20);

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
        assertEquals(actualParametersReturned.getTypeTitle(), expectedParametersTestSquare20.getTypeTitle());
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

    private BuilderParametersMoving builderTestSquare20 = createBuilderTestSquare20();

    private BuilderParametersMoving actualBuilder = getActualBuilderReturned();

    private BuilderParametersMoving createBuilderTestSquare20() {
        PolygonExtended formMachine = new PolygonExtendedClass();
        formMachine.addPoint(new PointClass(0, 0));
        formMachine.addPoint(new PointClass(20, 0));
        formMachine.addPoint(new PointClass(20, 20));
        formMachine.addPoint(new PointClass(0, 20));

        BuilderParametersMoving builder = new BuilderParametersMovingClass();
        builder.setSpeed(10); //~=40 kilometr / hour
        builder.setShape(formMachine);
        builder.setTypeMachinesBody(TypeMachinesBody.TEST_SQUARE_20);

        return builder;
    }

    private BuilderParametersMoving getActualBuilderReturned() {
        FabricParametersMoving fabric = new FabricParametersMovingClass();
        BuilderParametersMoving actual = fabric.getBuilderMoving(TypeMachinesBody.TEST_SQUARE_20);

        return actual;
    }

    @Test
    void getBuilderMoving_uniqueId() {
        ParametersMoving expectedParametersMoving = builderTestSquare20.getParametersMoving();
        ParametersMoving actualParametersMoving = actualBuilder.getParametersMoving();
        assertNotEquals(actualParametersMoving.getID(), expectedParametersMoving.getID());
    }

    @Test
    void getBuilderMoving_square20EqualsShape() {
        ParametersMoving expectedParametersMoving = builderTestSquare20.getParametersMoving();
        ParametersMoving actualParametersMoving = actualBuilder.getParametersMoving();
        assertEquals(actualParametersMoving.getShape(), expectedParametersMoving.getShape());
    }

    @Test
    void getBuilderMoving_square20EqualsSpeed() {
        ParametersMoving expectedParametersMoving = builderTestSquare20.getParametersMoving();
        ParametersMoving actualParametersMoving = actualBuilder.getParametersMoving();
        assertEquals(actualParametersMoving.getSpeed(), expectedParametersMoving.getSpeed());
    }

    @Test
    void getBuilderMoving_square20EqualsType() {
        ParametersMoving expectedParametersMoving = builderTestSquare20.getParametersMoving();
        ParametersMoving actualParametersMoving = actualBuilder.getParametersMoving();
        assertEquals(actualParametersMoving.getTypeTitle(), expectedParametersMoving.getTypeTitle());
    }

    @Test
    void getBuilderMoving_square20EqualsTypeInLevel() {
        ParametersMoving expectedParametersMoving = builderTestSquare20.getParametersMoving();
        ParametersMoving actualParametersMoving = actualBuilder.getParametersMoving();
        assertEquals(actualParametersMoving.getTypeInLevel(), expectedParametersMoving.getTypeInLevel());
    }

    @Test
    void getBuilderMoving_square20EqualsRadius() {
        ParametersMoving expectedParametersMoving = builderTestSquare20.getParametersMoving();
        ParametersMoving actualParametersMoving = actualBuilder.getParametersMoving();
        assertEquals(actualParametersMoving.getRadius(), expectedParametersMoving.getRadius());
    }

    @Test
    void getBuilderMoving_square20EqualsVectorFromTopLEftToAppliedCoordinates() {
        ParametersMoving expectedParametersMoving = builderTestSquare20.getParametersMoving();
        ParametersMoving actualParametersMoving = actualBuilder.getParametersMoving();
        assertEquals(actualParametersMoving.getRadius(), expectedParametersMoving.getRadius());
    }

    @Test
    void getBuilderMoving_square20EqualsPointWhereCoordinatesAreApplied() {
        ParametersMoving expectedParametersMoving = builderTestSquare20.getParametersMoving();
        ParametersMoving actualParametersMoving = actualBuilder.getParametersMoving();
        assertEquals(
                actualParametersMoving.getPointWhereCoordinatesAreApplied(),
                expectedParametersMoving.getPointWhereCoordinatesAreApplied()
        );
    }
}