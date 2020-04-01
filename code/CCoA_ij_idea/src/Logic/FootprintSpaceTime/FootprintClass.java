package Logic.FootprintSpaceTime;

import GUI.StatementTaskRendering.DataFootprintForRendering;
import Logic.PhisicalBody;
import GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingFootprint;
import GUI.StatementTaskRendering.TypeLandscapeBody;
import Logic.Position;

import java.awt.*;

public class FootprintClass implements Footprint, DataFootprintForRendering{
    private int idTrack;
    private Position position;
    private PhisicalBody phisicalBody;

    public FootprintClass(int idTrack, Position position, PhisicalBody phisicalBody) {
        this.idTrack = idTrack;
        this.position = position;
        this.phisicalBody = phisicalBody;
    }


    //==== <start> <Getter_and_Setter> ==================================================

    @Override
    public int getIdTrack() {
        return idTrack;
    }

    @Override
    public PhisicalBody getPhisicalBody() {
        return this.phisicalBody;
    }

    //    ==== <start> <Implements RenderingFootprint> ==================================================
    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public TypeLandscapeBody getTypePhisicalBody() {
        return this.phisicalBody.getTypePhisicalBody();
    }

    @Override
    public int getIdObject() {
        return this.phisicalBody.getID();
    }

    //    ==== <end> <Implements RenderingFootprint> ==================================================

    //==== <end> <Getter_and_Setter> ==================================================


    //==== <start> <Private_Methods> =======================================================================
    private Point pointOfPolygonConsideringPosition(
            Point point,
            Point generalPointPhisicalBody,
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
