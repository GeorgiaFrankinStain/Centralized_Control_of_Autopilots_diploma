package com.alamutra.ccoa.Logic.MovingObjects;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectExeption;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.FootprintsSpaceTime;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.ccoa.Logic.IndexLayer;
import com.alamutra.ccoa.Logic.TypesInLevel;

public interface ParametersMoving {
    public void mark(
            FootprintsSpaceTime footprintsSpaceTime,
            PathCCoA pathCCoA,
            double timeAdding,
            IndexLayer indexLayer
    ) throws СrashIntoAnImpassableObjectExeption;

    public PolygonCCoA getShape();

    public double getSpeed();

    public double timeTravel(double distance);

    public String getTypeTitle();

    public TypesInLevel getTypeInLevel();

    public double getLengthStep();

    public PointCCoA getPointWhereCoordinatesAreApplied();

    public double getRadius();

    public PointCCoA getVectorFromTopLeftToAppliedCoordinates();

    public String toString();

    public int getID();
}
