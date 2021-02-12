package Logic.ControllerMachines;

import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import Logic.FootprintSpaceTime.FootprintsSpaceTime;
import Logic.FootprintSpaceTime.Point;
import Logic.LevelLayer;
import Logic.LevelLayerClass;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.Path;

import java.util.ArrayList;
import java.util.List;

public class ControllerMachinesClass implements ControllerMachines {
    private List<MovingObject> subordinateMachines = new ArrayList<MovingObject>();
    private NetworkNodes networkNodesFabrica; //FIXME add fabric
    private AlhorithmFastFindPath fastFinderPath;
    private FootprintsSpaceTime footprintsSpaceTime;
    private LevelLayer defaultLevel = new LevelLayerClass(0);


    public ControllerMachinesClass(FootprintsSpaceTime footprintsSpaceTime, double dimension) {
        this.footprintsSpaceTime = footprintsSpaceTime;
        this.networkNodesFabrica = new SquareNetworkNodes(dimension); //FIXME add fabric
        this.fastFinderPath = new AStarSpaceTimePlanarGraphClass(networkNodesFabrica, footprintsSpaceTime);
    }

    @Override
    public void bringCarToEndOfRoad(
            Point from,
            Point to,
            MovingObject movingObject,
            double timeAdding
    ) throws СrashIntoAnImpassableObjectExeption {
        this.subordinateMachines.add(movingObject);

        Path path = fastFinderPath.getPath(from, to, movingObject.getRadius(), movingObject, timeAdding);
        movingObject.mark(
                this.footprintsSpaceTime,
                path,
                timeAdding,
                this.defaultLevel
        );


        //FIXME add processing exeption accident
    }
}
