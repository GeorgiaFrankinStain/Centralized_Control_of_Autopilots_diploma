package com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.FootprintsSpaceTime;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PolygonCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.IndexLayer;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.TypesInLevel;
import com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.SkinsCapacitor;
import com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.TypeMachinesBody;

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
