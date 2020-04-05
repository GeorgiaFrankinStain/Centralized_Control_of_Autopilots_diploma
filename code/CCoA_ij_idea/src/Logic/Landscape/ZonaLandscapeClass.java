package Logic.Landscape;

import Logic.FootprintSpaceTime.Footprint;
import Logic.PhisicalBody;
import Logic.Position;

public class ZonaLandscapeClass implements ZonaLandscape, Footprint {
    @Override
    public int getIdObject() {
        return 0;
    }

    @Override
    public int getIdTrack() {
        return 0;
    }

    @Override
    public PhisicalBody getPhisicalBody() {
        return null;
    }

    @Override
    public Position getPosition() {
        return null;
    }
}
