package Logic.FootprintSpaceTime;

import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.MovingObjectClass;
import Logic.PathsMachines.PositionClass;
import Logic.Position;
import org.junit.Test;

import static java.lang.Math.PI;
import static org.junit.Assert.*;

public class FootprintTest {

    @Test
    public void getIdObject() {
        //FIXME ADD_FORMALNOST_TEST
    }

    @Test
    public void getIdTrack() {
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


    private Point[] arrayPoints = {
            new PointClass(10, 10),
            new PointClass(10, 20),
            new PointClass(20, 20),
            new PointClass(20, 10),
    };

    private MovingObject getStandartMovingObjet() {
        MovingObject movingObject = new MovingObjectClass(getStandartFormMachine(), TypeMachinesBody.PASSENGER_CAR);
        return movingObject;
    }

    @Test
    public void getOccupiedLocation_margin() {
        Point margin = new PointClass(10, 10);
        Position position = new PositionClass(margin, 0.0);

        Footprint footprint = new FootprintClass(0, position, 1, getStandartMovingObjet());
        PolygonExtended actualOccupiedLocation = footprint.getOccupiedLocation();
        PolygonExtended expectedPolygonExtended = expectedAfterMargin(margin);

        assertTrue(expectedPolygonExtended.equals(actualOccupiedLocation));
    }

    private PolygonExtended expectedAfterMargin(Point margin) {
        PolygonExtended expectedPolygonExtended = new PolygonExtendedClass();

        for (Point point : arrayPoints) {
            Point marginedPoint = new PointClass(
                    point.getX() + margin.getX(),
                    point.getY() + margin.getY()
            );
            expectedPolygonExtended.addPoint(marginedPoint);
        }

        return expectedPolygonExtended;
    }

    private PolygonExtended getStandartFormMachine() {
        PolygonExtended formMachine = new PolygonExtendedClass(); //FIXME IMITATION


        for (Point point : arrayPoints) {
            formMachine.addPoint(point);
        }

        return formMachine;
    }

    @Test
    public void getOccupiedLocation_rotatedMargin() {
        Point margin = new PointClass(10, 10);
        Position position = new PositionClass(margin, -PI / 2);

        Footprint footprint = new FootprintClass(0, position, 1, getStandartMovingObjet());
        PolygonExtended actualOccupiedLocation = footprint.getOccupiedLocation();
        PolygonExtended expectedPolygonExtended = expectedRoteted(margin);


        assertTrue(expectedPolygonExtended.equals(actualOccupiedLocation));
    }

    private PolygonExtended expectedRoteted(Point margin) {
        PolygonExtended expectedPolygonExtended = new PolygonExtendedClass();

        int endIndex = arrayPoints.length - 1;
        for (int i = 0; i < arrayPoints.length; i++) {
            int indexNextPoint = i + 1;
            Point nextCyclePoint = nextCyclePoint(indexNextPoint, endIndex);

            Point marginedPoint = new PointClass(
                    nextCyclePoint.getX() + margin.getX(),
                    nextCyclePoint.getY() + margin.getY()
            );
            expectedPolygonExtended.addPoint(marginedPoint);
        }

        return expectedPolygonExtended;
    }
    private Point nextCyclePoint(int indexNextPoint, int endIndex) {
        Point nextPoint;
        if (indexNextPoint <= endIndex) {
            nextPoint = arrayPoints[indexNextPoint];
        } else {
            nextPoint = arrayPoints[0];
        }

        return nextPoint;
    }
}