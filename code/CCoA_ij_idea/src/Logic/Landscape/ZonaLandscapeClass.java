package Logic.Landscape;

import Logic.FootprintSpaceTime.Footprint;
import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.Position;
import Logic.TypesInLevel;
import Wrapper.RandowWrapperClass;

public class ZonaLandscapeClass implements ZonaLandscape, Footprint {
    private int idObject;
    private Position position;
    private PolygonExtended polygonExtended;

    public ZonaLandscapeClass(Position position, PolygonExtended polygonExtended) {
        this.idObject = new RandowWrapperClass().nextInt();
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
    public double getTimeStanding() {
        return Double.MAX_VALUE * 0.95;
    }

    @Override
    public PolygonExtended getLocation() {
        return polygonExtended;
    }

    @Override
    public boolean getAccessPlace(PolygonExtended place, double time, TypesInLevel type) {
        return false;
    }
}
