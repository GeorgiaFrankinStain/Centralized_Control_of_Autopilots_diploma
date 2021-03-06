package Logic.ControllerMachines;

import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import Logic.FootprintSpaceTime.FootprintsSpaceTime;
import Logic.FootprintSpaceTime.Point;
import Logic.IndexLayer;
import Logic.IndexLayerClass;
import Logic.MovingObjects.ParametersMoving;
import Logic.MovingObjects.Path;

import java.util.ArrayList;
import java.util.List;

public class ControllerMachinesClass implements ControllerMachines {
    private List<ParametersMoving> subordinateMachines = new ArrayList<ParametersMoving>();
    private NetworkNodes networkNodesFabrica;
    private AlhorithmFastFindPath fastFinderPath;
    private FootprintsSpaceTime footprintsSpaceTime;
    private IndexLayer defaultLevel = new IndexLayerClass(0);


    public ControllerMachinesClass(FootprintsSpaceTime footprintsSpaceTime, double dimension) {
        this.footprintsSpaceTime = footprintsSpaceTime;
        this.networkNodesFabrica = new SquareNetworkNodes();
        this.fastFinderPath = new AStarSpaceTimePlanarGraphClass(networkNodesFabrica, footprintsSpaceTime);
    }

    @Override
    public void bringCarToEndOfRoad(
            Point from,
            Point to,
            ParametersMoving parametersMoving,
            double timeAdding
    ) throws СrashIntoAnImpassableObjectExeption {
        this.subordinateMachines.add(parametersMoving);

        Path path = fastFinderPath.getPath(from, to, parametersMoving.getRadius(), parametersMoving, timeAdding);
        parametersMoving.mark(
                this.footprintsSpaceTime,
                path,
                timeAdding,
                this.defaultLevel
        );


        //FIXME add processing exeption accident
    }
}
