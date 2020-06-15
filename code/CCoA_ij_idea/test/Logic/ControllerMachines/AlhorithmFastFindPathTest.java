package Logic.ControllerMachines;

import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.FabricMovingObjects;
import Logic.FabricMovingObjectsClass;
import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObstacleExeption;
import Logic.FootprintSpaceTime.FootprintsSpaceTime;
import Logic.FootprintSpaceTime.FootprintsSpaceTimeClass;
import Logic.FootprintSpaceTime.Point;
import Logic.FootprintSpaceTime.PointClass;
import Logic.LevelLayer;
import Logic.LevelLayerClass;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.Path;
import Logic.MovingObjects.PathClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlhorithmFastFindPathTest {

    @Test
    public void getPath() throws СrashIntoAnImpassableObstacleExeption {
        double timeAddingPath = 0.0;
        LevelLayer defaultLevelLayer = new LevelLayerClass(0);
/*
        {
            FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass();

            NetworkNodes networkNodesFabrica = new SquareNetworkNodes(13.3);//FIXME add fabric
            AlhorithmFastFindPath fastFinderPath = new AStarSpaceTime(networkNodesFabrica);

            FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();
            MovingObject movingObject = fabricMovingObjects.getMachine(TypeMachinesBody.PASSENGER_CAR);

            Point from = new PointClass(0, 0);
            Point to = new PointClass(100, 0);

            Path actualPath = fastFinderPath.getPath(from, to, movingObject.getRadius());
            movingObject.mark(
                    onlyFootprintsSpaceTime,
                    actualPath,
                    timeAddingPath,
                    defaultLevelLayer
            );
            Path expectedPath = new PathClass();
            expectedPath.addPoint(new PointClass(0.0, 0.0));
            expectedPath.addPoint(new PointClass(20.0, 0.0));
            expectedPath.addPoint(new PointClass(40.0, 0.0));
            expectedPath.addPoint(new PointClass(60.0, 0.0));
            expectedPath.addPoint(new PointClass(80.0, 0.0));
            expectedPath.addPoint(new PointClass(100.0, 0.0));

            assertEquals(expectedPath, actualPath);
        }*/
/*        {
            FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass();

            NetworkNodes networkNodesFabrica = new SquareNetworkNodes(13.3);//FIXME add fabric
            AlhorithmFastFindPath fastFinderPath = new AStarSpaceTime(networkNodesFabrica, onlyFootprintsSpaceTime);

            FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();
            MovingObject movingObject = fabricMovingObjects.getMachine(TypeMachinesBody.PASSENGER_CAR);




            LevelLayer levelLayer = new LevelLayerClass(0);


            MovingObject wall = fabricMovingObjects.getMachine(TypeMachinesBody.WALL_CAR);






            Point from = new PointClass(60, 0);
            Point to = new PointClass(60, 200);

            Path actualPath = fastFinderPath.getPath(from, to, movingObject.getRadius(), movingObject);

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
            boolean anExceptionWasTriggered = false;
            try {
                movingObject.mark(
                        onlyFootprintsSpaceTime,
                        actualPath,
                        timeAddingPath,
                        defaultLevelLayer
                );
            } catch (СrashIntoAnImpassableObstacleExeption ex) {
                anExceptionWasTriggered = true;
            }

            assertFalse(anExceptionWasTriggered);

            assertEquals(expectedPath, actualPath);



        }*/
        {
            FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass();

            NetworkNodes networkNodesFabrica = new SquareNetworkNodes(13.3);//FIXME add fabric
            AlhorithmFastFindPath fastFinderPath = new AStarSpaceTime(networkNodesFabrica, onlyFootprintsSpaceTime);

            FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();
            MovingObject movingObject = fabricMovingObjects.getMachine(TypeMachinesBody.PASSENGER_CAR);




            LevelLayer levelLayer = new LevelLayerClass(0);


            MovingObject wall = fabricMovingObjects.getMachine(TypeMachinesBody.WALL_CAR);
            Path wallPath = new PathClass();
            wallPath.addPoint(new PointClass(50, 100));
            double timeAdding = 0.0;
            try {
                wall.mark(onlyFootprintsSpaceTime, wallPath, timeAdding, levelLayer);
            } catch (СrashIntoAnImpassableObstacleExeption ex) {
            }





            Point from = new PointClass(60, 0);
            Point to = new PointClass(60, 200);

            Path actualPath = fastFinderPath.getPath(from, to, movingObject.getRadius(), movingObject, timeAdding);

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

            System.out.println(actualPath);
            assertEquals(expectedPath, actualPath);


            boolean anExceptionWasTriggered = false;
            try {
                movingObject.mark(
                        onlyFootprintsSpaceTime,
                        actualPath,
                        timeAddingPath,
                        defaultLevelLayer
                );
            } catch (СrashIntoAnImpassableObstacleExeption ex) {
                anExceptionWasTriggered = true;
            }

            assertTrue(anExceptionWasTriggered);
        }
    }
}