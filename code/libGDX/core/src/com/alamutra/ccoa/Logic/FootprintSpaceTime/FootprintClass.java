package com.alamutra.ccoa.Logic.FootprintSpaceTime;

import com.alamutra.ccoa.GUI.StatementTaskRendering.DataFootprintForRendering;
import com.alamutra.ccoa.Logic.GlobalVariable;
import com.alamutra.ccoa.Logic.MovingObjects.ParametersMoving;
import com.alamutra.ccoa.Logic.Position;

public class FootprintClass implements Footprint, DataFootprintForRendering {
    private Position position;
    private double timeToNextFootprint;
    private ParametersMoving parametersMoving;


    public FootprintClass(Position position, double timeToNextFootprint, ParametersMoving parametersMoving) {
        this.position = position;
        this.timeToNextFootprint = timeToNextFootprint;
        this.parametersMoving = parametersMoving;
    }


    @Override
    public PolygonExtended getOccupiedLocation() { //FIXME ADD_TEST
        PolygonExtended formMovingObject = this.parametersMoving.getShape();
        PolygonExtended resultPolygon = formMovingObject.clone();

        Point origin = this.parametersMoving.getPointWhereCoordinatesAreApplied();
        resultPolygon.rotateRelative(origin, this.position.getRotation());
        resultPolygon.deposeOn(this.position.getCoordinates());

        return resultPolygon;
    }

    @Override
    public String toString() {
        return this.position.toString()
                + " MovObj" + this.parametersMoving.toString()
                + " timeStanding: " + this.getTimeToNextFootprint();
    }

    @Override
    public int hashCode() {
        int twoPow32 = 2147483647;
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (this.getTimeToNextFootprint() % twoPow32);
        result = prime * result + (int) (this.getMovingObject().hashCode());
        result = prime * result + (int) (this.getPosition().hashCode());
        result = prime * result + prime;
        return result;
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

        int oneId = this.getIdMovingObject();
        int twoId = other.getIdMovingObject();
        boolean idTrackEquals = oneId == twoId;
        Position thisPosition = this.getPosition();
        Position otherPosition = other.getPosition();
        boolean positionEquals = thisPosition.equals(otherPosition);
        boolean timeStandingEquals = GlobalVariable.equalsNumber(this.getTimeToNextFootprint(), other.getTimeToNextFootprint());
        boolean movingEquals = this.getMovingObject().equals(other.getMovingObject());

        return idTrackEquals && positionEquals && timeStandingEquals && movingEquals;
    }
    //==== <start> <Getter_and_Setter> ==================================================


    @Override
    public Position getPosition() {
        return this.position;
    }


    //==== <start> <Implements RenderingFootprint> ==================================================
    @Override
    public Position getLocalOriginForPointMovingObject() {/*
        Point margin = this.movingObject.getVectorFromTopLeftToAppliedCoordinates().getInversion();
        Point newCoordinat = this.position.getCoordinats().getDeposeOn(margin);
        Position newPosition = new PositionClass(newCoordinat, this.position.getRotation());
        return newPosition;*/

        return this.getPosition();
    }

    @Override
    public Point getCoordinat() {
        return this.position.getCoordinates();
    }

    @Override
    public double getTimeToNextFootprint() {
        return this.timeToNextFootprint;
    }

    @Override
    public Footprint getApproximation(double timeFirst, Footprint second, double timeSecond, double timeApproximation) {
        Position position =
                this.getPosition().getApproximation(timeFirst, second.getPosition(), timeSecond, timeApproximation);

        return new FootprintClass(position, this.getTimeToNextFootprint(), parametersMoving);
    }

    @Override
    public void setTimeToNextFootprint(double newTimeStanding) {
        this.timeToNextFootprint = newTimeStanding;
    }


    @Override
    public String getType() {
        return this.parametersMoving.getTypeTitle();
    }

    @Override
    public ParametersMoving getMovingObject() {
        return this.parametersMoving;
    }

    @Override
    public int getIdMovingObject() {
        return this.parametersMoving.getID();
    }

    //    ==== <end> <Implements RenderingFootprint> ==================================================

    //==== <end> <Getter_and_Setter> ==================================================


    //==== <start> <Private_Methods> =======================================================================
    private Point pointOfPolygonConsideringPosition(
            Point point,
            Point generalPointPolygonExtender,
            Position position
    ) { //FIXME add origin of window


        Point theUseCoordinatesPolygon = position.getCoordinates();

        return new PointClass(
                point.getX() + theUseCoordinatesPolygon.getX(),
                point.getY() + theUseCoordinatesPolygon.getY()
        );
    }

    //==== <end> <Private_Methods> =========================================================================
}
