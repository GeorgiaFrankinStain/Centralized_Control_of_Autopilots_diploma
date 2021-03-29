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
import Logic.MovingObjects.*;
import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import Logic.FootprintSpaceTime.FootprintsSpaceTime;
import Logic.FootprintSpaceTime.FootprintsSpaceTimeClass;
import Logic.FootprintSpaceTime.Point;
import Logic.IndexLayerClass;
import Logic.FootprintSpaceTime.PointClass;
import Logic.IndexLayer;

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
        //FIXME codestule

        FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass(); //create FootprintsSpaceTime (Landscape) //PUNKT_1


        PoolDataFootprintForRendering poolDataFootprintForRendering = new PoolDataFootprintForRenderingClass(onlyFootprintsSpaceTime);
        MapRender subwindowMapRendering = new MapRenderClass(poolDataFootprintForRendering); //create MapRender (FootprintsSpaceTime)

        //FIXME MOVE imitation in
//            onlyFootprintsSpaceTime.addFootprint();

        IndexLayer indexLayer = new IndexLayerClass(0);

        NetworkNodes networkNodesFabrica = new SquareNetworkNodes();
        AlhorithmFastFindPath fastFinderPath = new AStarSpaceTimePlanarGraphClass(networkNodesFabrica, onlyFootprintsSpaceTime);

        FabricParametersMoving fabricParametersMoving = new FabricParametersMovingClass();




        ParametersMoving wall = fabricParametersMoving.getMoving(TypeMachinesBody.WALL_CAR);
        Path wallPath = new PathClass();
        wallPath.addPoint(new PointClass(120, 160));
        wallPath.deposeOn(wall.getVectorFromTopLeftToAppliedCoordinates());
        try {
            wall.mark(onlyFootprintsSpaceTime, wallPath, 0.0, indexLayer);
        } catch (СrashIntoAnImpassableObjectExeption ex) {
        }

        {
            Point from = new PointClass(80, 60); //FIXME BAG don't multipoly 20 (size car)
            Point to = new PointClass(60, 300);


            double timeAdding = 0.0;
            ParametersMoving parametersMoving = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
            Path actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
            try {
                parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer); //FIXME bag sequense time adding
            } catch (СrashIntoAnImpassableObjectExeption ex) {
            }
        }

//FIXME почему у длинной стены нету огромного радиуса? как это работает?

        {

            Point from = new PointClass(120, 60);
            Point to = new PointClass(180, 260);

            double timeAdding = 0.0;

            ParametersMoving parametersMoving = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
            Path actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
            try {
                parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer);
            } catch (СrashIntoAnImpassableObjectExeption ex) {
            }
        }
        {

            Point from = new PointClass(220, 400);
            Point to = new PointClass(100, 20);

            double timeAdding = 10.0; //FIXME don't work more 20 время добавления новой машины должно быть позже, чем остановка предыдущей. Хз почему

            ParametersMoving parametersMoving = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
            Path actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
            try {
                parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer);
            } catch (СrashIntoAnImpassableObjectExeption ex) {
            }
        }
        {

            Point from = new PointClass(200, 360);
//            Point from = new PointClass(20, 20);
            Point to = new PointClass(60, 20);

            double timeAdding = 10.0; //FIXME don't work more 20 время добавления новой машины должно быть позже, чем остановка предыдущей. Хз почему

            ParametersMoving parametersMoving = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
            Path actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
            try {
                parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer);
            } catch (СrashIntoAnImpassableObjectExeption ex) {
            }
        }
        {

            Point from = new PointClass(400, 140);
//            Point from = new PointClass(20, 20);
            Point to = new PointClass(180, 300);

            double timeAdding = 10.0; //FIXME don't work more 20 время добавления новой машины должно быть позже, чем остановка предыдущей. Хз почему

            ParametersMoving parametersMoving = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
            Path actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
            try {
                parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer);
            } catch (СrashIntoAnImpassableObjectExeption ex) {
            }
        }
        {

            Point from = new PointClass(440, 140);
//            Point from = new PointClass(20, 20);
            Point to = new PointClass(140, 300);

            double timeAdding = 10.0; //FIXME don't work more 20 время добавления новой машины должно быть позже, чем остановка предыдущей. Хз почему

            ParametersMoving parametersMoving = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
            Path actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
            try {
                parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer);
            } catch (СrashIntoAnImpassableObjectExeption ex) {
            }
        }
        {

            Point from = new PointClass(540, 140);
//            Point from = new PointClass(20, 20);
            Point to = new PointClass(220, 300);

            double timeAdding = 10.0; //FIXME don't work more 20 время добавления новой машины должно быть позже, чем остановка предыдущей. Хз почему

            ParametersMoving parametersMoving = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
            Path actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
            try {
                parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer);
            } catch (СrashIntoAnImpassableObjectExeption ex) {
            }
        }
        {

            Point from = new PointClass(20, 20);
//            Point from = new PointClass(20, 20);
            Point to = new PointClass(100, 300);

            double timeAdding = 10.0; //FIXME don't work more 20 время добавления новой машины должно быть позже, чем остановка предыдущей. Хз почему

            ParametersMoving parametersMoving = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
            Path actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
            try {
                parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer);
            } catch (СrashIntoAnImpassableObjectExeption ex) {
            }
        }
        {

            Point from = new PointClass(60, 560);
//            Point from = new PointClass(20, 20);
            Point to = new PointClass(20, 20);

            double timeAdding = 20.0; //FIXME don't work more 20 время добавления новой машины должно быть позже, чем остановка предыдущей. Хз почему

            ParametersMoving parametersMoving = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
            Path actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
            try {
                parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer);
            } catch (СrashIntoAnImpassableObjectExeption ex) {
            }
        }
        {

            Point from = new PointClass(600, 100);
//            Point from = new PointClass(20, 20);
            Point to = new PointClass(20, 300);

            double timeAdding = 30.0; //FIXME don't work more 20 время добавления новой машины должно быть позже, чем остановка предыдущей. Хз почему

            BuilderParametersMoving builder = fabricParametersMoving.getBuilderMoving(TypeMachinesBody.TEST_SQUARE_20);
            builder.setSpeed(30);
            ParametersMoving parametersMoving = builder.getParametersMoving();
            Path actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
            try {
                parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer);
            } catch (СrashIntoAnImpassableObjectExeption ex) {
            }
        }
        {

            Point from = new PointClass(660, 100);
//            Point from = new PointClass(20, 20);
            Point to = new PointClass(40, 200);

            double timeAdding = 30.0; //FIXME don't work more 20 время добавления новой машины должно быть позже, чем остановка предыдущей. Хз почему

            BuilderParametersMoving builder = fabricParametersMoving.getBuilderMoving(TypeMachinesBody.TEST_SQUARE_20);
            builder.setSpeed(60);
            ParametersMoving parametersMoving = builder.getParametersMoving();
            Path actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
            try {
                parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer);
            } catch (СrashIntoAnImpassableObjectExeption ex) {
            }
        }
        {

//            Point from = new PointClass(20, 20);
            Point from = new PointClass(600, 40);
            Point to = new PointClass(20, 100);

            double timeAdding = 30.0; //FIXME don't work more 20 время добавления новой машины должно быть позже, чем остановка предыдущей. Хз почему

            ParametersMoving parametersMoving = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);
            Path actualPath = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
            try {
                parametersMoving.mark(onlyFootprintsSpaceTime, actualPath, timeAdding, indexLayer);
            } catch (СrashIntoAnImpassableObjectExeption ex) {
            }
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
