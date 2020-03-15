import GUI.Window.Windows;
import GUI.Window.WindowsClass;

public class Main {

    public static void main(String[] args) {


        //create FootprintSpaceTime ()




        //create Landscape (FootprintSpaceTime)
        //create MovingObjects (FootprintSpaceTime)

        //create AreasBenchmarkPathsDijkstra (FootprintSpaceTime, Landscape)
        //create PathsMachines (FootprintSpaceTime, AreasBenchmarkPathsDijkstra) //class MovingObjects create new with (FootprintSpaceTime)











        //create AutoDisainerMachines ()
        //create DisainerLandscape (Landscape)
        //create DriverMachine (MovingObjects)
        //create ObserverUpdate(AreasBenchmarkPathsDijkstra, PathsMachines)

        //create ConsoleManagement (AutoDisainerMachines, DisainerLandscape, DriverMachine, ObserverUpdate)


        //create Rendering (FootprintSpaceTime)
        //create UserCommandInterface (ConsoleManagement) //in future may need +(FootprintSpaceTime, Rendering)
        //create Windows (Rendering, UserCommandInterface)
        Windows allWindows = new WindowsClass();
        allWindows.createGeneralWindow(); //FIXME add Rendering, UserCommandInterface




        System.out.println("Hello World!");
    }

    public void processing() {

    }
}
