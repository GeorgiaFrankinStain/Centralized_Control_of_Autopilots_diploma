package com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime;

import com.alamutra.CCoAWeb.Core.Logic.GlobalVariable;
import com.alamutra.CCoAWeb.Core.Logic.MovingBody.ParametersMovingUnique;
import com.alamutra.CCoAWeb.Core.Logic.Position;
import com.alamutra.CCoAWeb.Core.SettingRenderingTasks.DataFootprintForRendering;
import com.alamutra.CCoAWeb.Core.SettingRenderingTasks.SkinsCapacitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FootprintClass implements Footprint, DataFootprintForRendering {
    private static final Logger LOGGER = LogManager.getLogger(FootprintClass.class);

    private Position position;
    private double timeToNextFootprint;
    private ParametersMovingUnique parametersMovingUnique;
    private boolean isLastFootprintInPath = false;
    private Route route;


    public FootprintClass(
            Position position,
            double timeToNextFootprint,
            ParametersMovingUnique parametersMovingUnique,
            Route route) {
        this.route = route;
        this.position = position;
        this.timeToNextFootprint = timeToNextFootprint; //TODO: dubplicate information about time to next footprint
        this.parametersMovingUnique = parametersMovingUnique;
    }


    @Override
    public PolygonCCoA getOccupiedLocation() { //FIXME ADD_TEST
        PolygonCCoA formMovingObject = this.parametersMovingUnique.getShape();
        PolygonCCoA resultPolygon = formMovingObject.clone();

        LOGGER.debug("getOccupiedLocation shape: {}", resultPolygon);

        PointCCoA origin = this.parametersMovingUnique.getPointWhereCoordinatesAreApplied();
        resultPolygon.rotateRelative(origin, this.position.getRotation());

        LOGGER.debug("getOccupiedLocation shape after rotate: {}", resultPolygon);

        LOGGER.debug("getOccupiedLocation positionCoordinates: {}", this.position.getCoordinates());

        resultPolygon.deposeOn(this.position.getCoordinates());

        LOGGER.debug("getOccupiedLocation shape: after depose {}", resultPolygon);

        return resultPolygon;
    }

    @Override
    public String toString() {
        return this.position.toString()
                + " MovObj" + this.parametersMovingUnique.toString()
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

    @Override
    public boolean equalsWithoutUniqueId(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;


        Footprint other = (Footprint) obj;

        Position thisPosition = this.getPosition();
        Position otherPosition = other.getPosition();
        boolean positionEquals = thisPosition.equals(otherPosition);
        boolean timeStandingEquals = GlobalVariable.equalsNumber(this.getTimeToNextFootprint(), other.getTimeToNextFootprint());
        boolean movingEquals = this.getMovingObject().equalsWithoutUniqueId(other.getMovingObject());

        return positionEquals && timeStandingEquals && movingEquals;
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

        return new FootprintClass(position, timeToNextFootprint, parametersMovingUnique, route); //FIXME getTimeToNextFootprint always constant add tests
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
        return this.parametersMovingUnique.getSkin();
    }

    @Override
    public ParametersMovingUnique getMovingObject() {
        return this.parametersMovingUnique;
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
        return this.parametersMovingUnique.getID();
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

