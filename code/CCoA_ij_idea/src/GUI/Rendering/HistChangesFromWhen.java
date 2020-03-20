package GUI.Rendering;

import Logic.FootprintSpaceTime.Polygon;

/**
 * Need for Rendering and change path for cars (, due to the appearance of viscous and impassable obstacles
 */
public interface HistChangesFromWhen {
    public Polygon getAreaChangesAfterBefore(long afterTime, long berforeTime); //for 
    public long getTimeLastUpdate(); //it is necessary for rendering, so that it can update the currently open map section if necessary (if it changed)
}
