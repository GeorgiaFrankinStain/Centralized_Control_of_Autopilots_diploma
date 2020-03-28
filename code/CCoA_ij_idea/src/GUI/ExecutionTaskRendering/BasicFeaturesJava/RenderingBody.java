package GUI.ExecutionTaskRendering.BasicFeaturesJava;

import GUI.StatementTaskRendering.TypeLandscapeBody;
import Logic.FootprintSpaceTime.PolygonExtended;

import java.awt.*;

public interface RenderingBody {
    TypeLandscapeBody getTypePhisicalBody();
    int getID();
    int getLevel();
    void renderingYourself(Graphics g);
}
