package com.alamutra.ccoa.Core.Logic.MovingBody;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.FootprintsSpaceTime;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.ccoa.Core.Logic.IndexLayer;
import com.alamutra.ccoa.Core.Logic.TypesInLevel;
import com.alamutra.ccoa.Core.SettingRenderingTasks.SkinsCapacitor;
import com.alamutra.ccoa.Core.SettingRenderingTasks.TypeMachinesBody;

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
