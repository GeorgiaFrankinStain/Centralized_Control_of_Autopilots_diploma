package Logic.MovingObjects;

import Logic.FabricPhisicalBodys;
import Logic.FabricPhisicalBodysClass;
import Logic.PhisicalBody;
import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.FootprintSpaceTime.*;
import Logic.PathsMachines.PositionClass;
import Logic.Position;

public class MovingObjectClass implements MovingObject {

    public MovingObjectClass() {
    }



    @Override
    public void mark(FootprintSpaceTime footprintSpaceTime, Path path) {

        //run addSled

        FabricPhisicalBodys fabricPhisicalBodys = new FabricPhisicalBodysClass();


        PhisicalBody testBody = fabricPhisicalBodys.getMachineRenderingBody(TypeMachinesBody.PASSENGER_CAR);
        footprintSpaceTime.addFootprint(
                path.getIdTrack(),
                testBody,
                path,
                0
        );

/*        Position position1 = new PositionClass(new PointClass(100, 100), 0);
        footprintSpaceTime.addFootprint(13, testBody, position1, 1); //FIXME positions add


        Position position2 = new PositionClass(new PointClass(100, 300), 0);
        footprintSpaceTime.addFootprint(13, testBody, position2, 2);


        Position position3 = new PositionClass(new PointClass(100, 600), 0);
        footprintSpaceTime.addFootprint(13, testBody, position3, 3);


        Position position4 = new PositionClass(new PointClass(100, 800), 0);
        footprintSpaceTime.addFootprint(13, testBody, position4, 4);*/


    }
    //==== <start> <Getter_and_Setter> ==================================================


    //==== <end> <Getter_and_Setter> ==================================================
}
