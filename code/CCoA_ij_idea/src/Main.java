import GUI.ExecutionTaskRendering.BasicFeaturesJava.MapRender;
import GUI.ExecutionTaskRendering.BasicFeaturesJava.MapRenderClass;
import GUI.StatementTaskRendering.PoolDataFootprintForRendering;
import GUI.StatementTaskRendering.PoolDataFootprintForRenderingClass;
import GUI.StatementTaskRendering.TypeMachinesBody;
import GUI.StatementTaskRendering.Windows;
import GUI.ExecutionTaskRendering.BasicFeaturesJava.WindowsClass;
import Logic.FabricMovingObjects;
import Logic.FabricMovingObjectsClass;
import Logic.FootprintSpaceTime.FootprintsSpaceTime;
import Logic.FootprintSpaceTime.FootprintsSpaceTimeClass;
import Logic.FootprintSpaceTime.PointClass;
import Logic.Landscape.Landscape;
import Logic.Landscape.LandscapeClass;
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




        //create Landscape ()
        Landscape onlyLandscape = new LandscapeClass();
        FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass(onlyLandscape); //create FootprintsSpaceTime (Landscape) //PUNKT_1


        //PUNKT_2
        //create MovingObjects (FootprintsSpaceTime)

        //create AreasBenchmarkPathsDijkstra (FootprintsSpaceTime, Landscape)
        //create PathsMachines (FootprintsSpaceTime, AreasBenchmarkPathsDijkstra) //class MovingObjects create new with (FootprintsSpaceTime)


        //PUNKT_3
        //== все из данного блока создавать только после полноценного создания (следящих и расчитывающих) объектов, на которые мы будем вообще влять
        //create AutoDisainerMachines ()
        //create DisainerLandscape (Landscape)
        //create DriverMachine (MovingObjects)
        //create ObserverUpdate(AreasBenchmarkPathsDijkstra, PathsMachines)


        //create ConsoleManagement (AutoDisainerMachines, DisainerLandscape, DriverMachine, ObserverUpdate) //создавать только после полноценного создания входящих на вход объектов

        PoolDataFootprintForRendering poolDataFootprintForRendering = new PoolDataFootprintForRenderingClass(onlyFootprintsSpaceTime);
        MapRender subwindowMapRendering = new MapRenderClass(poolDataFootprintForRendering); //create MapRender (FootprintsSpaceTime)

        { //FIXME MOVE imitation in
//            onlyFootprintsSpaceTime.addFootprint();

            FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();

            MovingObject wall = fabricMovingObjects.getMachine(TypeMachinesBody.WALL_CAR);
            wall.mark(onlyFootprintsSpaceTime, createPathWall());


            MovingObject movingObject = fabricMovingObjects.getMachine(TypeMachinesBody.PASSENGER_CAR);
            movingObject.mark(onlyFootprintsSpaceTime, createPath());



        }


        //create UserCommandInterface (ConsoleManagement) //in future may need +(FootprintsSpaceTime, MapRender) //создавать после создания полноценного ConsoleManagement //PUNKT_4





        this.generalWindows = new WindowsClass(primaryStage);
        generalWindows.createGeneralWindow(subwindowMapRendering); //create Windows (MapRender, UserCommandInterface) //FIXME add UserCommandInterface


        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0 ;
            @Override
            public void handle(long now) {
//                update(now);

                if (now - lastUpdate >= 280_000_000) {
                    update(now);
                    lastUpdate = now ;
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
        resPath.addPoint(new PointClass(60, 60));
        resPath.addPoint(new PointClass(60, 60));
        resPath.addPoint(new PointClass(60, 60));
        resPath.addPoint(new PointClass(60, 60));
        resPath.addPoint(new PointClass(60, 60));
        resPath.addPoint(new PointClass(60, 60));

        return resPath;
    }
    private void update(long now) {
        generalWindows.update(now);
    }
    //==== <end> <Private_Methods> =========================================================================
}
