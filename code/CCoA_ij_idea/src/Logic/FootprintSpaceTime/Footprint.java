package Logic.FootprintSpaceTime;

import Logic.PhisicalBody;
import Logic.Position;

public interface Footprint {
    public int getIdObject();
    public int getIdTrack();
    public PhisicalBody getPhisicalBody();
    public Position getPosition();

}
