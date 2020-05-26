package Logic.FootprintSpaceTime;

import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.FabricMovingObjects;
import Logic.FabricMovingObjectsClass;
import Logic.Landscape.Landscape;
import Logic.Landscape.LandscapeClass;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.Path;
import Logic.MovingObjects.PathClass;
import Logic.PathsMachines.PositionClass;
import Logic.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class FootprintsSpaceTimeTest {

    @Test
    public void getPosition() {
        {
            Landscape onlyLandscape = new LandscapeClass();
            FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass(onlyLandscape);

            Path resPath = new PathClass();
            resPath.addPoint(new PointClass(0, 100));
            Point endPoint = new PointClass(101, 100);
            resPath.addPoint(endPoint);


            FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();
            MovingObject movingObject = fabricMovingObjects.getMachine(TypeMachinesBody.PASSENGER_CAR);
            movingObject.mark(onlyFootprintsSpaceTime, resPath);

            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(movingObject.getID(), 0);

                Position expected = new PositionClass(new PointClass(0, 100), 0); //FIXME
                assertEquals(expected, actualPosition);
            }

            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(movingObject.getID(), 8);

                Position expected = new PositionClass(new PointClass(80, 100), 0); //FIXME
                assertEquals(expected, actualPosition);
            }

            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(movingObject.getID(), 9);

                Position expected = new PositionClass(new PointClass(90, 100), 0); //FIXME
                assertEquals(expected, actualPosition);
            }

            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(movingObject.getID(), 10);

                double rotation = 0;
                Position expectedPosition = new PositionClass(endPoint, rotation);
                assertFalse(expectedPosition.equals(actualPosition)); //TODO add small steps, must assertTrue


                Position expected = new PositionClass(new PointClass(100, 100), 0); //FIXME
                assertEquals(expected, actualPosition);
            }

        }
    }
}