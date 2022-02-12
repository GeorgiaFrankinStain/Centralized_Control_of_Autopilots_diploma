package com.alamutra.ccoa.Core.Logic.FootprintSpaceTime;

import com.alamutra.ccoa.Core.Logic.GlobalVariable;
import com.alamutra.ccoa.Core.Logic.MovingBody.ParametersMoving;
import com.alamutra.ccoa.Core.Logic.Position;
import com.alamutra.ccoa.Core.SettingRenderingTasks.DataFootprintForRendering;
import com.alamutra.ccoa.Core.SettingRenderingTasks.SkinsCapacitor;

public class FootprintClass implements Footprint, DataFootprintForRendering {
    private Position position;
    private double timeToNextFootprint;
    private ParametersMoving parametersMoving;
    private boolean isLastFootprintInPath = false;
    private Route route;


    public FootprintClass(
            Position position,
            double timeToNextFootprint,
            ParametersMoving parametersMoving,
            Route route) {
        this.route = route;
        this.position = position;
        this.timeToNextFootprint = timeToNextFootprint; //TODO: dubplicate information about time to next footprint
        this.parametersMoving = parametersMoving;
    }


    @Override
    public PolygonCCoA getOccupiedLocation() { //FIXME ADD_TEST
        PolygonCCoA formMovingObject = this.parametersMoving.getShape();
        PolygonCCoA resultPolygon = formMovingObject.clone();

        PointCCoA origin = this.parametersMoving.getPointWhereCoordinatesAreApplied();
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
    public PointCCoA getCoordinat() {
        return this.position.getCoordinates();
    }

    @Override
    public double getTimeToNextFootprint() {
        return this.timeToNextFootprint;
    }

    @Override
    public Footprint getApproximation(double timeFirst, Footprint second, double timeSecond, double timeApproximation) {
        assert (second.getIdMovingObject() == this.getIdMovingObject());

        Position position =
                this.getPosition().getApproximation(timeFirst, second.getPosition(), timeSecond, timeApproximation);
        Route route = new RouteClass();

        double timeToNextFootprint = Math.abs(timeSecond - timeFirst);

        return new FootprintClass(position, timeToNextFootprint, parametersMoving, route); //FIXME getTimeToNextFootprint always constant add tests
    }

    @Override
    public Footprint getApproximationWithNextFootprint(double timeApproximation) {
        NextFootprintAndTimes nextFootprintAndTimes = this.route.getNextFootprint(this);

        boolean isLastFootprint = nextFootprintAndTimes == null;
        if (isLastFootprint) {
            return getApproximationFromTimeStanding();
        }
        Footprint nextFootprint = nextFootprintAndTimes.getNextFootprint();


        double timeNextFootprint = nextFootprintAndTimes.getTimeOfNextFootprint();


        double timeThisFootprint = nextFootprintAndTimes.getTimeOfKeyFootprint();


        return getApproximation(timeThisFootprint, nextFootprint, timeNextFootprint, timeApproximation);
    }

    @Override
    public void setTimeToNextFootprint(double newTimeStanding) {
        this.timeToNextFootprint = newTimeStanding;
    }


    @Override
    public SkinsCapacitor getSkin() {
        return this.parametersMoving.getSkin();
    }

    @Override
    public ParametersMoving getMovingObject() {
        return this.parametersMoving;
    }

    @Override
    public double getRotationDegree() {
        return this.position.getRotationDegree();
    }

    @Override
    public PointCCoA getCoordinates() {
        return this.position.getCoordinates();
    }

    @Override
    public double getRotation() {
        return this.position.getRotation();
    }


    @Override
    public int getIdMovingObject() {
        return this.parametersMoving.getID();
    }

    //    ==== <end> <Implements RenderingFootprint> ==================================================

    //==== <end> <Getter_and_Setter> ==================================================


    //==== <start> <Private_Methods> =======================================================================
    private PointCCoA pointOfPolygonConsideringPosition(
            PointCCoA pointCCoA,
            PointCCoA generalPointCCoAPolygonExtender,
            Position position
    ) { //FIXME add origin of window


        PointCCoA theUseCoordinatesPolygon = position.getCoordinates();

        return new PointCCoAClass(
                pointCCoA.getX() + theUseCoordinatesPolygon.getX(),
                pointCCoA.getY() + theUseCoordinatesPolygon.getY()
        );
    }

    private Footprint getApproximationFromTimeStanding() {
        return this;
    }

    //==== <end> <Private_Methods> =========================================================================
}

