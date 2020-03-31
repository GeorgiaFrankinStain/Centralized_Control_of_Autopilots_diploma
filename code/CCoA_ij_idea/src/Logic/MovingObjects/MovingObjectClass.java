package Logic.MovingObjects;

import GUI.ExecutionTaskRendering.BasicFeaturesJava.FabricRederingBodys;
import GUI.ExecutionTaskRendering.BasicFeaturesJava.FabricRenderingBodysClass;
import Logic.PhisicalBody;
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


        PhisicalBody testBody = fabricRederingBodys.getMachineRenderingBody(TypeMachinesBody.PASSENGER_CAR);


        Position position1 = new PositionClass(new PointClass(100, 100), 0);
        footprintSpaceTime.addFootprint(13, testBody, position1, 1); //FIXME positions add


        Position position2 = new PositionClass(new PointClass(100, 300), 0);
        footprintSpaceTime.addFootprint(13, testBody, position2, 2);


        Position position3 = new PositionClass(new PointClass(100, 600), 0);
        footprintSpaceTime.addFootprint(13, testBody, position3, 3);


        Position position4 = new PositionClass(new PointClass(100, 800), 0);
        footprintSpaceTime.addFootprint(13, testBody, position4, 4);
    }
}
