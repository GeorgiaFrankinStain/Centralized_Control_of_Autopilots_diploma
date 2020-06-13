package Logic.FootprintSpaceTime;

import GUI.StatementTaskRendering.DataFootprintForRendering;
import Logic.GlobalVariable;
import Logic.MovingObjects.MovingObject;
import Logic.PathsMachines.PositionClass;
import Logic.Position;

public class FootprintClass implements Footprint, DataFootprintForRendering {
    private int idTrack;
    private Position position;
    private double timeStanding;
    private MovingObject movingObject;


    public FootprintClass(int idTrack, Position position, double timeStanding, MovingObject movingObject) {
        this.idTrack = idTrack;
        this.position = position;
        this.timeStanding = timeStanding;
        this.movingObject = movingObject;
    }


    @Override
    public PolygonExtended getOccupiedLocation() { //FIXME ADD_TEST
        PolygonExtended rotatePolygon = new PolygonExtendedClass();
        PolygonExtended formMovingObject = this.movingObject.getPolygonExtended();
        Point averageCenterFormMovingObject = formMovingObject.getCenterAverage();

        Point vectorApplicateCoordinat = this.movingObject.getPointWhereCoordinatesAreApplied().getInversion(); //FIXME long calls //FIXME move functino in footprint

        for (int i = 0; i < formMovingObject.getCountPoints(); i++) {
            Point currentPoint = formMovingObject.getPoint(i);
            Point rotarePoint =
                    currentPoint.getRotateRelative(averageCenterFormMovingObject, this.position.getRotation());

            Point coordinatMovingObject = this.position.getCoordinats();
            rotarePoint.setX(
                    rotarePoint.getX()
                            + coordinatMovingObject.getX()
                            + vectorApplicateCoordinat.getX()
            );
            rotarePoint.setY(
                    rotarePoint.getY()
                            + coordinatMovingObject.getY()
                            + vectorApplicateCoordinat.getY()
            );

            rotatePolygon.addPoint(rotarePoint);
        }


        return rotatePolygon;
    }

    @Override
    public String toString() {
        return this.position.toString()
                       + " MovObj" + this.movingObject.toString()
                       + " timeStanding:" + this.getTimeStanding();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;


        Footprint other = (Footprint) obj;

        int oneId = this.getIdObject();
        int twoId = other.getIdObject();
        boolean idTrackEquals = oneId == twoId;
        boolean positionEquals = this.getLocalOriginForPointMovingObject().equals(other.getPosition());
        boolean timeStandingEquals = GlobalVariable.equalsNumber(this.getTimeStanding(), other.getTimeStanding());
        boolean movingEquals = this.getMovingObject().equals(other.getMovingObject());

        return idTrackEquals && positionEquals && timeStandingEquals && movingEquals;
    }
    //==== <start> <Getter_and_Setter> ==================================================

    @Override
    public int getIdTrack() {
        return idTrack;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }


    //==== <start> <Implements RenderingFootprint> ==================================================
    @Override
    public Position getLocalOriginForPointMovingObject() {
        Point margin = this.movingObject.getVectorFromTopLeftToAppliedCoordinates().getInversion();
        Point newCoordinat = this.position.getCoordinats().getDeposeOn(margin);
        Position newPosition = new PositionClass(newCoordinat, this.position.getRotation());
        return newPosition;
    }

    @Override
    public Point getCoordinat() {
        return this.position.getCoordinats();
    }

    @Override
    public double getTimeStanding() {
        return this.timeStanding;
    }

    @Override
    public void setTimeStanding(double newTimeStanding) {
        this.timeStanding = newTimeStanding;
    }


    @Override
    public String getType() {
        return this.movingObject.getType();
    }

    @Override
    public MovingObject getMovingObject() {
        return this.movingObject;
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
