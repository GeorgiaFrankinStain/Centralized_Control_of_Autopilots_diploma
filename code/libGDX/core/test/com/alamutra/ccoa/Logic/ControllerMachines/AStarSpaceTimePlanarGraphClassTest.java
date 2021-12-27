package com.alamutra.ccoa.Logic.ControllerMachines;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectExeption;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.FootprintsSpaceTime;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.FootprintsSpaceTimeClass;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.Point;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.PointClass;
import com.alamutra.ccoa.Logic.IndexLayer;
import com.alamutra.ccoa.Logic.IndexLayerClass;
import com.alamutra.ccoa.Logic.MovingObjects.*;
import com.alamutra.ccoa.StatementTaskRendering.TypeMachinesBody;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class AStarSpaceTimePlanarGraphClassTest {

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