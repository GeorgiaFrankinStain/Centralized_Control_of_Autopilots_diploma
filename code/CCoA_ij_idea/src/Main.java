import GUI.ExecutionTaskRendering.BasicFeaturesJava.MapRender;
import GUI.ExecutionTaskRendering.BasicFeaturesJava.MapRenderClass;
import GUI.StatementTaskRendering.PoolPhisicalBodysForRendering;
import GUI.StatementTaskRendering.PoolPhisicalBodysForRenderingClass;
import GUI.StatementTaskRendering.Windows;
import GUI.ExecutionTaskRendering.BasicFeaturesJava.WindowsClass;
import Logic.FootprintSpaceTime.FootprintSpaceTime;
import Logic.FootprintSpaceTime.FootprintSpaceTimeClass;
import Logic.Landscape.Landscape;
import Logic.Landscape.LandscapeClass;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.MovingObjectClass;

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
        FootprintSpaceTime onlyFootprintSpaceTime = new FootprintSpaceTimeClass(onlyLandscape); //create FootprintSpaceTime (Landscape) //PUNKT_1


        //PUNKT_2
        //create MovingObjects (FootprintSpaceTime)

        //create AreasBenchmarkPathsDijkstra (FootprintSpaceTime, Landscape)
        //create PathsMachines (FootprintSpaceTime, AreasBenchmarkPathsDijkstra) //class MovingObjects create new with (FootprintSpaceTime)


        //PUNKT_3
        //== все из данного блока создавать только после полноценного создания (следящих и расчитывающих) объектов, на которые мы будем вообще влять
        //create AutoDisainerMachines ()
        //create DisainerLandscape (Landscape)
        //create DriverMachine (MovingObjects)
        //create ObserverUpdate(AreasBenchmarkPathsDijkstra, PathsMachines)


        //create ConsoleManagement (AutoDisainerMachines, DisainerLandscape, DriverMachine, ObserverUpdate) //создавать только после полноценного создания входящих на вход объектов

        PoolPhisicalBodysForRendering poolPhisicalBodysForRendering = new PoolPhisicalBodysForRenderingClass(onlyFootprintSpaceTime);
        MapRender subwindowMapRendering = new MapRenderClass(poolPhisicalBodysForRendering); //create MapRender (FootprintSpaceTime) //PUNKT_0 NOW


        MovingObject movingObject = new MovingObjectClass();
        movingObject.mark(onlyFootprintSpaceTime);


        //create UserCommandInterface (ConsoleManagement) //in future may need +(FootprintSpaceTime, MapRender) //создавать после создания полноценного ConsoleManagement //PUNKT_4





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



        System.out.println("Hello World!");
    }

    //==== <start> <Private_Methods> =======================================================================
    private void update(long now) {
        generalWindows.update(now);
    }
    //==== <end> <Private_Methods> =========================================================================
}
