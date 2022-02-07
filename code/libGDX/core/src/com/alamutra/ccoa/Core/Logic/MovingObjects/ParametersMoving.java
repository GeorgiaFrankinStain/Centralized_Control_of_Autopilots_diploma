package com.alamutra.ccoa.Core.Logic.MovingObjects;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectExeption;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.FootprintsSpaceTime;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.ccoa.Core.Logic.IndexLayer;
import com.alamutra.ccoa.Core.Logic.TypesInLevel;
import com.alamutra.ccoa.Core.SettingRenderingTasks.Skins;

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

    public Skins getSkin();

    public TypesInLevel getTypeInLevel();

    public double getLengthStep();

    public PointCCoA getPointWhereCoordinatesAreApplied();

    public double getRadius();

    public PointCCoA getVectorFromTopLeftToAppliedCoordinates();

    public String toString();

    public int getID();
}
