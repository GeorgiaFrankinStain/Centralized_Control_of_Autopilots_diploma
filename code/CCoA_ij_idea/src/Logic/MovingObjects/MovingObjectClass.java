package Logic.MovingObjects;

import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.FootprintSpaceTime.*;
import Wrapper.RandowWrapperClass;

public class MovingObjectClass implements MovingObject {

    private PolygonExtended polygonExtended;
    private TypeMachinesBody typeMachinesBody;
    private double speed;
    private int idObject = new RandowWrapperClass().nextInt();

    public MovingObjectClass(PolygonExtended polygonExtended, TypeMachinesBody typeMachinesBody) {
        this.polygonExtended = polygonExtended;
        this.typeMachinesBody = typeMachinesBody;
    }



    @Override
    public void mark(FootprintsSpaceTime footprintsSpaceTime, Path path) {

        //run addSled

        footprintsSpaceTime.addFootprint(
                path.getIdTrack(),
                this,
                path,
                0
        );

/*        Position position1 = new PositionClass(new PointClass(100, 100), 0);
        footprintsSpaceTime.addFootprint(13, testBody, position1, 1); //FIXME positions add


        Position position2 = new PositionClass(new PointClass(100, 300), 0);
        footprintsSpaceTime.addFootprint(13, testBody, position2, 2);


        Position position3 = new PositionClass(new PointClass(100, 600), 0);
        footprintsSpaceTime.addFootprint(13, testBody, position3, 3);


        Position position4 = new PositionClass(new PointClass(100, 800), 0);
        footprintsSpaceTime.addFootprint(13, testBody, position4, 4);*/


    }

    @Override
    public PolygonExtended getPolygonExtended() {
        return this.polygonExtended;
    }

    @Override
    public double getSpeed() {
        return this.speed;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }


    @Override
    public String getType() {
        return this.typeMachinesBody.name();
    }

    @Override
    public int getID() {
        return this.idObject;
    }

    @Override
    public int getLevel() {
        return 0;
    }
    //==== <start> <Getter_and_Setter> ==================================================


    //==== <end> <Getter_and_Setter> ==================================================
}
