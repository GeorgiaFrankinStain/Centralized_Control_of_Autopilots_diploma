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

import static java.lang.Math.PI;
import static org.junit.Assert.*;

public class FootprintSpaceTimeTest {

    @Test
    public void getPosition() {
        {
            Landscape onlyLandscape = new LandscapeClass();
            FootprintSpaceTime onlyFootprintSpaceTime = new FootprintSpaceTimeClass(onlyLandscape);

            Path resPath = new PathClass();
            resPath.addPoint(new PointClass(10, 100));
            Point endPoint = new PointClass(101, 100);
            resPath.addPoint(endPoint);


            FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();
            MovingObject movingObject = fabricMovingObjects.getMachine(TypeMachinesBody.PASSENGER_CAR);
            movingObject.mark(onlyFootprintSpaceTime, resPath);


            Position actualPosition = onlyFootprintSpaceTime.getPosition(movingObject.getID(), 9);

            double rotation = 0;
            Position expectedPosition = new PositionClass(endPoint, rotation);
            assertFalse(expectedPosition.equals(actualPosition)); //TODO add small steps


            Position expected2 = new PositionClass(new PointClass(100, 100), 0); //FIXME
            assertTrue(expected2.equals(actualPosition));
        }
    }
}