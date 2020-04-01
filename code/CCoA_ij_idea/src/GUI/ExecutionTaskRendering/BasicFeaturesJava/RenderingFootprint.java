package GUI.ExecutionTaskRendering.BasicFeaturesJava;

import GUI.StatementTaskRendering.DataFootprintForRendering;
import GUI.StatementTaskRendering.TypeLandscapeBody;


public interface RenderingFootprint {
    TypeLandscapeBody getTypePhisicalBody();
    int getID();
    int getLevel();
    void renderingYourself();
    void update(long now, DataFootprintForRendering newProperties);
}
