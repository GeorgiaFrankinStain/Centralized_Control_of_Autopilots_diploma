package Logic.MovingObjects;

import GUI.ExecutionTaskRendering.BasicFeaturesJava.FabricRederingBodys;
import GUI.ExecutionTaskRendering.BasicFeaturesJava.FabricRenderingBodysClass;
import GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingBodyClass;
import GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingBody;
import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.FootprintSpaceTime.*;
import Logic.PathsMachines.PositionClass;
import Logic.Position;

public class MovingObjectClass implements MovingObject {
    public MovingObjectClass() {
    }


    //TODO create track


    @Override
    public void mark(FootprintSpaceTime footprintSpaceTime) {
        //run addSled

        FabricRederingBodys fabricRederingBodys = new FabricRenderingBodysClass();


        RenderingBody testBody = fabricRederingBodys.getMachineRenderingBody(TypeMachinesBody.PASSENGER_CAR);


        Position position1 = new PositionClass(new PointClass(100, 100), 0);
        footprintSpaceTime.addFootprint(13, testBody, position1, 1); //FIXME positions add


        Position position2 = new PositionClass(new PointClass(100, 150), 0);
        footprintSpaceTime.addFootprint(13, testBody, position2, 2);


        Position position3 = new PositionClass(new PointClass(100, 200), 0);
        footprintSpaceTime.addFootprint(13, testBody, position1, 3);
        footprintSpaceTime.addFootprint(13, testBody, position1, 4);
    }
}
