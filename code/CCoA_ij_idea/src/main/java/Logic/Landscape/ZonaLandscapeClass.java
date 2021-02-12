package Logic.Landscape;

import Logic.FootprintSpaceTime.CreatorMarksOfPathClass;
import Logic.FootprintSpaceTime.Footprint;
import Logic.FootprintSpaceTime.Point;
import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.MovingObjects.MovingObject;
import Logic.Position;
import Logic.TypesInLevel;
import Wrapper.RandomWrapperClass;

public class ZonaLandscapeClass implements ZonaLandscape, Footprint {
    private int idObject;
    private Position position;
    private PolygonExtended polygonExtended;
    private double timeStanding = CreatorMarksOfPathClass.MAX_TIME_STANDING;

    public ZonaLandscapeClass(Position position, PolygonExtended polygonExtended) {
        this.idObject = new RandomWrapperClass( 134).nextInt();
        this.position = position;
        this.polygonExtended = polygonExtended;
    }

    public ZonaLandscapeClass(int idObject, Position position, PolygonExtended polygonExtended) {
        this.idObject = idObject;
        this.position = position;
        this.polygonExtended = polygonExtended;
    }

    @Override
    public int getIdObject() {
        return this.idObject;
    }

    @Override
    public int getIdTrack() {
        return this.idObject;
    }


    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Point getCoordinat() {
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
    public MovingObject getMovingObject() {
        return null;
    }

    @Override
    public void setTimeToNextFootprint(double newTimeStanding) {
        this.timeStanding = newTimeStanding;
    }

    @Override
    public PolygonExtended getOccupiedLocation() {
        return polygonExtended;
    }

    @Override
    public boolean getAccessPlace(PolygonExtended place, double time, TypesInLevel type) {
        return false;
    }
}
