package Logic.ControllerMachines;

import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.MovingObjects.FabricParametersMoving;
import Logic.MovingObjects.FabricParametersMovingClass;
import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import Logic.FootprintSpaceTime.FootprintsSpaceTime;
import Logic.FootprintSpaceTime.FootprintsSpaceTimeClass;
import Logic.FootprintSpaceTime.Point;
import Logic.FootprintSpaceTime.PointClass;
import Logic.IndexLayer;
import Logic.IndexLayerClass;
import Logic.MovingObjects.ParametersMoving;
import Logic.MovingObjects.Path;
import Logic.MovingObjects.PathClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class AStarSpaceTimePlanarGraphClassTest {

    private AlhorithmFastFindPath fastFinderPath = pathCreatedFastFingerPath();

    private AlhorithmFastFindPath pathCreatedFastFingerPath() {
        FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass();


        NetworkNodes networkNodesFabrica = new SquareNetworkNodes();
        AlhorithmFastFindPath fastFinderPath = new AStarSpaceTimePlanarGraphClass(networkNodesFabrica, onlyFootprintsSpaceTime);

        return fastFinderPath;
    }

    private ParametersMoving squareParametersMoving = createSquareMovingObject();

    private ParametersMoving createSquareMovingObject() {
        FabricParametersMoving fabricParametersMoving = new FabricParametersMovingClass();
        ParametersMoving parametersMoving = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
        return parametersMoving;
    }

    private double timeAddingPath = 0.0;
    ;

    @Test
    public void getPath_gorizontal() throws СrashIntoAnImpassableObjectExeption {
        IndexLayer defaultIndexLayer = new IndexLayerClass(0);
        {


            Point from = new PointClass(0, 0);
            Point to = new PointClass(100, 0);

            Path actualPath = fastFinderPath.getPath(from, to, squareParametersMoving.getRadius(), squareParametersMoving, timeAddingPath);

            Path expectedPath = new PathClass();
            expectedPath.addPoint(new PointClass(0.0, 0.0));
            expectedPath.addPoint(new PointClass(20.0, 0.0));
            expectedPath.addPoint(new PointClass(40.0, 0.0));
            expectedPath.addPoint(new PointClass(60.0, 0.0));
            expectedPath.addPoint(new PointClass(80.0, 0.0));
            expectedPath.addPoint(new PointClass(100.0, 0.0));

            assertEquals(expectedPath, actualPath);
        }
    }

    @Test
    public void getPath_vertical() throws СrashIntoAnImpassableObjectExeption {
        {

            FabricParametersMoving fabricParametersMoving = new FabricParametersMovingClass();

            IndexLayer indexLayer = new IndexLayerClass(0);


            ParametersMoving wall = fabricParametersMoving.getMoving(TypeMachinesBody.WALL_CAR);


            Point from = new PointClass(60, 0);
            Point to = new PointClass(60, 200);

            Path actualPath = fastFinderPath.getPath(from, to, squareParametersMoving.getRadius(), squareParametersMoving, timeAddingPath);

            Path expectedPath = new PathClass();
            expectedPath.addPoint(new PointClass(60.0, 0.0));
            expectedPath.addPoint(new PointClass(60.0, 20.0));
            expectedPath.addPoint(new PointClass(60.0, 40.0));
            expectedPath.addPoint(new PointClass(60.0, 60.0));
            expectedPath.addPoint(new PointClass(60.0, 80.0));
            expectedPath.addPoint(new PointClass(60.0, 100.0));
            expectedPath.addPoint(new PointClass(60.0, 120.0));
            expectedPath.addPoint(new PointClass(60.0, 140.0));
            expectedPath.addPoint(new PointClass(60.0, 160.0));
            expectedPath.addPoint(new PointClass(60.0, 180.0));
            expectedPath.addPoint(new PointClass(60.0, 200.0));

            assertEquals(expectedPath, actualPath);
        }
    }
}