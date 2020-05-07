package GUI.StatementTaskRendering;

import Logic.FootprintSpaceTime.PolygonExtended;

/**
 * Need for Rendering and change path for cars (, due to the appearance of viscous and impassable obstacles
 */
public interface HistChangesFromWhen {
    public PolygonExtended getAreaChangesAfterBefore(int  afterTime, int  berforeTime); //for
    public int getTimeLastUpdate(); //it is necessary for rendering, so that it can update the currently open map section if necessary (if it changed)

    //TODO hash (LINK_GhyJnYIW)
}
