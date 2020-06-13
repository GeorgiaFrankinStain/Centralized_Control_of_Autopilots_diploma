import GUI.ExecutionTaskRendering.BasicFeaturesJava.MapRender;
import GUI.ExecutionTaskRendering.BasicFeaturesJava.MapRenderClass;
import GUI.StatementTaskRendering.PoolDataFootprintForRendering;
import GUI.StatementTaskRendering.PoolDataFootprintForRenderingClass;
import GUI.StatementTaskRendering.TypeMachinesBody;
import GUI.StatementTaskRendering.Windows;
import GUI.ExecutionTaskRendering.BasicFeaturesJava.WindowsClass;
import Logic.AreasBenchmarkPaths.AreasBenchmarkPaths;
import Logic.AreasBenchmarkPaths.StraightLineEstimatedClass;
import Logic.ControllerMachines.*;
import Logic.FabricMovingObjects;
import Logic.FabricMovingObjectsClass;
import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObstacleExeption;
import Logic.FootprintSpaceTime.FootprintsSpaceTime;
import Logic.FootprintSpaceTime.FootprintsSpaceTimeClass;
import Logic.FootprintSpaceTime.Point;
import Logic.LevelLayerClass;
import Logic.FootprintSpaceTime.PointClass;
import Logic.LevelLayer;
import Logic.MovingObjects.MovingObject;

import Logic.MovingObjects.Path;
import Logic.MovingObjects.PathClass;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private Windows generalWindows;


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {


        FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass(); //create FootprintsSpaceTime (Landscape) //PUNKT_1


        PoolDataFootprintForRendering poolDataFootprintForRendering = new PoolDataFootprintForRenderingClass(onlyFootprintsSpaceTime);
        MapRender subwindowMapRendering = new MapRenderClass(poolDataFootprintForRendering); //create MapRender (FootprintsSpaceTime)

        //FIXME MOVE imitation in
//            onlyFootprintsSpaceTime.addFootprint();

        LevelLayer levelLayer = new LevelLayerClass(0);

        NetworkNodes networkNodesFabrica = new SquareNetworkNodes(13.3);//FIXME add fabric
        AlhorithmFastFindPath fastFinderPath = new AStarSpaceTime(networkNodesFabrica, onlyFootprintsSpaceTime);

        FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();
        MovingObject movingObject = fabricMovingObjects.getMachine(TypeMachinesBody.PASSENGER_CAR);




        MovingObject wall = fabricMovingObjects.getMachine(TypeMachinesBody.WALL_CAR);
        Path wallPath = new PathClass();
        wallPath.addPoint(new PointClass(50, 100));
        wallPath.deposeOn(wall.getVectorFromTopLeftToAppliedCoordinates());
        try {
            wall.mark(onlyFootprintsSpaceTime, wallPath, 0.0, levelLayer);
        } catch (СrashIntoAnImpassableObstacleExeption ex) {
        }





        Point from = new PointClass(60, 0);
        Point to = new PointClass(60, 200);

        Path actualPath = fastFinderPath.getPath(from, to, movingObject.getRadius(), movingObject);
        System.out.println("resPath: " + actualPath);
        try {
            movingObject.mark(onlyFootprintsSpaceTime, actualPath, 0.0, levelLayer);
        } catch (СrashIntoAnImpassableObstacleExeption ex) {
        }

/*
            MovingObject movingObject2 = fabricMovingObjects.getMachine(TypeMachinesBody.PASSENGER_CAR);
            LevelLayer levelLayer2 = new LevelLayerClass(1);
            try {

                Path resPath = new PathClass();
                resPath.addPoint(new PointClass(440, 440));
                resPath.addPoint(new PointClass(200, 200));
                movingObject2.mark(onlyFootprintsSpaceTime, resPath, 0.0, levelLayer2);
            } catch (СrashIntoAnImpassableObstacleExeption ex) {
            }*/


        //PUNKT_2
        //create MovingObjects (FootprintsSpaceTime)

        //create AreasBenchmarkPaths (FootprintsSpaceTime)
        AreasBenchmarkPaths areasBenchmarkPaths = new StraightLineEstimatedClass();

        //create ControlledMachines (FootprintsSpaceTime, AreasBenchmarkPathsDijkstra) //class MovingObjects create new with (FootprintsSpaceTime)
/*        ControllerMachines controllerMachines =
                new ControllerMachinesClass(onlyFootprintsSpaceTime, 20); //FIXME magic number
        controllerMachines.bringCarToEndOfRoad(
                new PointClass(0, 0),
                new PointClass(700, 700),
                movingObject,
                0.0
        );*/


        //PUNKT_3
        //== все из данного блока создавать только после полноценного создания (следящих и расчитывающих) объектов, на которые мы будем вообще влять
        //create AutoDisainerMachines ()
        //create DisainerLandscape (Landscape)
        //create DriverMachine (MovingObjects)
        //create ObserverUpdate(AreasBenchmarkPathsDijkstra, ControlledMachines)


        //create ConsoleManagement (AutoDisainerMachines, DisainerLandscape, DriverMachine, ObserverUpdate) //создавать только после полноценного создания входящих на вход объектов


        //create UserCommandInterface (ConsoleManagement) //in future may need +(FootprintsSpaceTime, MapRender) //создавать после создания полноценного ConsoleManagement //PUNKT_4


        this.generalWindows = new WindowsClass(primaryStage);
        generalWindows.createGeneralWindow(subwindowMapRendering); //create Windows (MapRender, UserCommandInterface) //FIXME add UserCommandInterface


        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
//                update(now);

                if (now - lastUpdate >= 280_000_000) {
                    update(now);
                    lastUpdate = now;
                }
            }
        };
        timer.start();
    }

    //==== <start> <Private_Methods> =======================================================================
    private Path createPath() { //FIXME IMITATION
        Path resPath = new PathClass();
        resPath.addPoint(new PointClass(10, 10));
        resPath.addPoint(new PointClass(15, 15));
        resPath.addPoint(new PointClass(200, 15));
        resPath.addPoint(new PointClass(20, 250));
        resPath.addPoint(new PointClass(30, 30));
        resPath.addPoint(new PointClass(35, 35));
        resPath.addPoint(new PointClass(40, 40));
        resPath.addPoint(new PointClass(400, 40));

        return resPath;
    }

    private Path createPathWall() { //FIXME IMITATION
        Path resPath = new PathClass();
        resPath.addPoint(new PointClass(60, 60));

        return resPath;
    }

    private void update(long now) {
        generalWindows.update(now);
    }
    //==== <end> <Private_Methods> =========================================================================
}
