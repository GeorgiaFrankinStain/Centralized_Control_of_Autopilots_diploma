package GUI.StatementTaskRendering;

import GUI.ExecutionTaskRendering.BasicFeaturesJava.MapRender;

/**
 * Organization MapRender and UserCommandInterface ... in one window. Centeralized control.
 */
public interface Windows {
    public void createGeneralWindow(MapRender mapRender);
    public void update(long now);
//        void rendering(File settingFile, File mapFile); //TODO
}
