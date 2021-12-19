package com.alamutra.ccoa.Logic.MovingObjects;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.FootprintsSpaceTime;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.Point;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.PolygonExtended;
import com.alamutra.ccoa.Logic.IndexLayer;
import com.alamutra.ccoa.Logic.TypesInLevel;

public interface ParametersMoving {
    public void mark(
            FootprintsSpaceTime footprintsSpaceTime,
            Path path,
            double timeAdding,
            IndexLayer indexLayer
    ) throws СrashIntoAnImpassableObjectExeption;

    public PolygonExtended getShape();

    public double getSpeed();

    public double timeTravel(double distance);

    public String getTypeTitle();

    public TypesInLevel getTypeInLevel();

    public double getLengthStep();

    public Point getPointWhereCoordinatesAreApplied();

    public double getRadius();

    public Point getVectorFromTopLeftToAppliedCoordinates();

    public String toString();

    public int getID();
}
