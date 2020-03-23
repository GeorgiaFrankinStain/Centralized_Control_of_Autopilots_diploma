import GUI.StatementTaskRendering.MapRender;
import GUI.StatementTaskRendering.MapRenderClass;
import GUI.StatementTaskRendering.PhisicalBodysFromWhen;
import GUI.Window.Windows;
import GUI.Window.WindowsClass;
import Logic.FootprintSpaceTime.FootprintSpaceTime;
import Logic.FootprintSpaceTime.FootprintSpaceTimeClass;
import Logic.Landscape.Landscape;
import Logic.Landscape.LandscapeClass;

public class Main {

    public static void main(String[] args) {


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





        MapRender subwindowMapRendering = new MapRenderClass((PhisicalBodysFromWhen) onlyFootprintSpaceTime); //create MapRender (FootprintSpaceTime) //PUNKT_0 NOW


        //create UserCommandInterface (ConsoleManagement) //in future may need +(FootprintSpaceTime, MapRender) //создавать после создания полноценного ConsoleManagement //PUNKT_4

        Windows allWindows = new WindowsClass();
        allWindows.createGeneralWindow(subwindowMapRendering); //create Windows (MapRender, UserCommandInterface) //FIXME add UserCommandInterface




        System.out.println("Hello World!");
    }

    public void processing() {

    }
}
