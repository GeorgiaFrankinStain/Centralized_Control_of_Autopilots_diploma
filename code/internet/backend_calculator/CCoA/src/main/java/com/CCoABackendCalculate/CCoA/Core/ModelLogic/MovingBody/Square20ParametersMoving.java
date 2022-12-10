package com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.FootprintsSpaceTime;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PolygonCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.IndexLayer;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.TypesInLevel;
import com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.SkinsCapacitor;
import com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.TypeMachinesBody;

public class Square20ParametersMoving implements ParametersMoving {

    private ParametersMoving parametersMoving;

    public Square20ParametersMoving() {
        FabricParametersMovingUnique fabric = new FabricParametersMovingUniqueClass();
        ParametersMovingUnique parametersMovingUnique = fabric.getMoving(TypeMachinesBody.TEST_SQUARE_20);
        this.parametersMoving = new ParametersMovingClass(parametersMovingUnique);
    }

    @Override
    public void mark(
            FootprintsSpaceTime footprintsSpaceTime,
            PathCCoA pathCCoA,
            double timeAdding,
            IndexLayer indexLayer
    ) throws СrashIntoAnImpassableObjectException {
        parametersMoving.mark(footprintsSpaceTime, pathCCoA, timeAdding, indexLayer);
    }

    @Override
    public void markWithoutStandingUntilEndTime(
            FootprintsSpaceTime footprintsSpaceTime,
            PathCCoA pathCCoA,
            double timeAdding,
            IndexLayer indexLayer
    ) throws СrashIntoAnImpassableObjectException {
        parametersMoving.markWithoutStandingUntilEndTime(footprintsSpaceTime, pathCCoA, timeAdding, indexLayer);
    }

    @Override
    public PolygonCCoA getShape() {
        return parametersMoving.getShape();
    }

    @Override
    public double getSpeed() {
        return parametersMoving.getSpeed();
    }

    @Override
    public double getTimeTravel(double distance) {
        return parametersMoving.getTimeTravel(distance);
    }

    @Override
    public SkinsCapacitor getSkin() {
        return parametersMoving.getSkin();
    }

    @Override
    public TypesInLevel getTypeInLevel() {
        return parametersMoving.getTypeInLevel();
    }

    @Override
    public double getLengthStep() {
        return parametersMoving.getLengthStep();
    }

    @Override
    public PointCCoA getPointWhereCoordinatesAreApplied() {
        return parametersMoving.getPointWhereCoordinatesAreApplied();
    }

    @Override
    public double getRadius() {
        return parametersMoving.getRadius();
    }

    @Override
    public PointCCoA getVectorFromTopLeftToAppliedCoordinates() {
        return parametersMoving.getVectorFromTopLeftToAppliedCoordinates();
    }
}
