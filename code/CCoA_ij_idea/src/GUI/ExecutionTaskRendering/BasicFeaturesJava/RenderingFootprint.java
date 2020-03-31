package GUI.ExecutionTaskRendering.BasicFeaturesJava;

import GUI.StatementTaskRendering.TypeLandscapeBody;

import java.awt.*;

public interface RenderingFootprint {
    TypeLandscapeBody getTypePhisicalBody();
    int getID();
    int getLevel();
    void renderingYourself(Graphics g);
}
