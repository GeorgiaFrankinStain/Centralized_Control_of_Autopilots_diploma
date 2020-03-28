package Logic.FootprintSpaceTime;

import GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingBody;
import Logic.Position;

public interface Footprint {

    public int getIdTrack();
    public RenderingBody getRenderingBody();
    public Position getPosition();
}
