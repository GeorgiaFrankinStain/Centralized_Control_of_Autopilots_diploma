package Logic.FootprintSpaceTime;

import Logic.PhisicalBody;
import GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingFootprint;
import GUI.StatementTaskRendering.TypeLandscapeBody;
import Logic.Position;

import java.awt.*;

public class FootprintClass implements Footprint, RenderingFootprint {
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
    public int getID() {
        return this.phisicalBody.getID();
    }

    @Override
    public int getLevel() {
        return this.phisicalBody.getLevel();
    }

    @Override
    public void renderingYourself(Graphics g) {

        int[] xPoints = new int[this.phisicalBody.getPolygonExtended().countPoints()];
        int[] yPoints = new int[this.phisicalBody.getPolygonExtended().countPoints()];

        for (int i = 0; i < this.phisicalBody.getPolygonExtended().countPoints(); i++) {


            Point currentPoint = pointOfPolygonConsideringPosition(
                    this.phisicalBody.getPolygonExtended().getPoint(i),
                    this.phisicalBody.getPolygonExtended().getPoint(0),
                    this.position
            );

            xPoints[i] = currentPoint.getX();
            yPoints[i] = currentPoint.getY();
        }

        g.fillPolygon(xPoints, yPoints, xPoints.length);

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
