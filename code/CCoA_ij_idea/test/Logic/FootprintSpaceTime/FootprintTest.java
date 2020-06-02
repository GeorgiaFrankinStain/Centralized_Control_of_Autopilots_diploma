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

    @Test
    public void getOccupiedLocation() {
        {
            PolygonExtended formMachine = new PolygonExtendedClass(); //FIXME IMITATION
            Point[] arrayPoints = {
                    new PointClass(10, 10),
                    new PointClass(10, 20),
                    new PointClass(20, 20),
                    new PointClass(20, 10),
            };

            for (Point point : arrayPoints) {
                formMachine.addPoint(point);
            }

            MovingObject movingObject = new MovingObjectClass(formMachine, TypeMachinesBody.PASSENGER_CAR);

            Point margin = new PointClass(10, 10);
            Position position = new PositionClass(margin, 0.0);
            Footprint footprint = new FootprintClass(
                    0,
                    position,
                    1,
                    movingObject
            );

            PolygonExtended actualOccupiedLocation = footprint.getOccupiedLocation();
            PolygonExtended expectedPolygonExtended = new PolygonExtendedClass();

            for (Point point : arrayPoints) {
                Point marginedPoint = new PointClass(
                        point.getX() + margin.getX(),
                        point.getY() + margin.getY()
                );
                expectedPolygonExtended.addPoint(marginedPoint);
            }

            assertTrue(expectedPolygonExtended.equals(actualOccupiedLocation));
        }
        {
            PolygonExtended formMachine = new PolygonExtendedClass(); //FIXME IMITATION
            Point[] arrayPoints = {
                    new PointClass(10, 10),
                    new PointClass(10, 20),
                    new PointClass(20, 20),
                    new PointClass(20, 10),
            };

            for (Point point : arrayPoints) {
                formMachine.addPoint(point);
            }

            MovingObject movingObject = new MovingObjectClass(formMachine, TypeMachinesBody.PASSENGER_CAR);

            Point margin = new PointClass(10, 10);
            Position position = new PositionClass(margin, -PI / 2);
            Footprint footprint = new FootprintClass(
                    0,
                    position,
                    1,
                    movingObject
            );

            PolygonExtended actualOccupiedLocation = footprint.getOccupiedLocation();
            PolygonExtended expectedPolygonExtended = new PolygonExtendedClass();

            for (int i = 0; i < arrayPoints.length; i++) {

                int indexNextPoint = i + 1;
                int endIndex = arrayPoints.length - 1;
                Point nextCiclePoint;
                if (indexNextPoint <= endIndex) {
                    nextCiclePoint = arrayPoints[indexNextPoint];
                } else {
                    nextCiclePoint = arrayPoints[0];
                }
                Point marginedPoint = new PointClass(
                        nextCiclePoint.getX() + margin.getX(),
                        nextCiclePoint.getY() + margin.getY()
                );
                expectedPolygonExtended.addPoint(marginedPoint);
            }



            assertTrue(expectedPolygonExtended.equals(actualOccupiedLocation));
        }
    }
}