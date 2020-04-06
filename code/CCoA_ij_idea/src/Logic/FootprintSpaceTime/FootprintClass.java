package Logic.FootprintSpaceTime;

import GUI.StatementTaskRendering.DataFootprintForRendering;
import Logic.MovingObjects.MovingObject;
import Logic.Position;

public class FootprintClass implements Footprint, DataFootprintForRendering {
    private int idTrack;
    private Position position;
    private MovingObject movingObject;

    public FootprintClass(int idTrack, Position position, MovingObject movingObject) {
        this.idTrack = idTrack;
        this.position = position;
        this.movingObject = movingObject;
    }


    //==== <start> <Getter_and_Setter> ==================================================

    @Override
    public int getIdTrack() {
        return idTrack;
    }


    //    ==== <start> <Implements RenderingFootprint> ==================================================
    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public String getType() {
        return this.movingObject.getType();
    }

    @Override
    public int getIdObject() {
        return this.movingObject.getID();
    }

    //    ==== <end> <Implements RenderingFootprint> ==================================================

    //==== <end> <Getter_and_Setter> ==================================================


    //==== <start> <Private_Methods> =======================================================================
    private Point pointOfPolygonConsideringPosition(
            Point point,
            Point generalPointPolygonExtender,
            Position position
    ) { //FIXME add origin of window


        Point theUseCoordinatesPolygon = position.getCoordinats();

        return new PointClass(
                point.getX() + theUseCoordinatesPolygon.getX(),
                point.getY() + theUseCoordinatesPolygon.getY()
        );
    }

    //==== <end> <Private_Methods> =========================================================================
}
