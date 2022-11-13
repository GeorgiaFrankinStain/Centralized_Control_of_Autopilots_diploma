package com.alamutra.CCoAWeb.Core.Logic.MovingBody;

import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.FootprintsSpaceTime;
import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.CCoAWeb.Core.Logic.IndexLayer;
import com.alamutra.CCoAWeb.Core.Logic.TypesInLevel;
import com.alamutra.CCoAWeb.Core.SettingRenderingTasks.SkinsCapacitor;
import com.alamutra.CCoAWeb.Core.SettingRenderingTasks.TypeMachinesBody;

public interface ParametersMovingUnique {
    public void mark(
            FootprintsSpaceTime footprintsSpaceTime,
            PathCCoA pathCCoA,
            double timeAdding,
            IndexLayer indexLayer
    ) throws СrashIntoAnImpassableObjectException;

    public void markWithoutStandingUntilEndTime(
            FootprintsSpaceTime footprintsSpaceTime,
            PathCCoA pathCCoA,
            double timeAdding,
            IndexLayer indexLayer
    ) throws СrashIntoAnImpassableObjectException;

    public PolygonCCoA getShape();

    public double getSpeed();

    public double getTimeTravel(double distance);

    public SkinsCapacitor getSkin();

    public TypesInLevel getTypeInLevel();

    public TypeMachinesBody getTypeMachinesBody();

    public double getLengthStep();

    public PointCCoA getPointWhereCoordinatesAreApplied();

    public double getRadius();

    public PointCCoA getVectorFromTopLeftToAppliedCoordinates();

    public String toString();

    public int getID();

    public boolean equalsWithoutUniqueId(Object other);
}
