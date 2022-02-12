package com.alamutra.ccoa.Core.Logic.Landscape;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.CreatorMarksOfPathClass;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.Footprint;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.ccoa.Core.Logic.MovingBody.ParametersMoving;
import com.alamutra.ccoa.Core.Logic.Position;
import com.alamutra.ccoa.Core.Wrappers.RandomWrapperClass;

 public class ZonaLandscapeClass implements ZonaLandscape, Footprint {
    private int idObject;
    private Position position;
    private PolygonCCoA polygonCCoA;
    private double timeStanding = CreatorMarksOfPathClass.MAX_TIME_STANDING;

    public ZonaLandscapeClass(Position position, PolygonCCoA polygonCCoA) {
        this.idObject = new RandomWrapperClass( 134).nextInt();
        this.position = position;
        this.polygonCCoA = polygonCCoA;
    }

    public ZonaLandscapeClass(int idObject, Position position, PolygonCCoA polygonCCoA) {
        this.idObject = idObject;
        this.position = position;
        this.polygonCCoA = polygonCCoA;
    }

    @Override
    public int getIdMovingObject() {
        return this.idObject;
    }


    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public PointCCoA getCoordinat() {
        return null;
    }

    @Override
    public double getTimeToNextFootprint() {
        return this.timeStanding;
    }

    @Override
    public Footprint getApproximation(double timeFirst, Footprint second, double timeSecond, double timeApproximation) {
        return null;
    }

     @Override
     public Footprint getApproximationWithNextFootprint(double timeApproximation) {
         return null;
     }


     @Override
    public ParametersMoving getMovingObject() {
        return null;
    }

    @Override
    public void setTimeToNextFootprint(double newTimeStanding) {
        this.timeStanding = newTimeStanding;
    }

    @Override
    public PolygonCCoA getOccupiedLocation() {
        return polygonCCoA;
    }

}

