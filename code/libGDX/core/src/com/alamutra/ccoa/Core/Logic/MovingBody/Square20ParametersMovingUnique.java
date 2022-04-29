package com.alamutra.ccoa.Core.Logic.MovingBody;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.FootprintsSpaceTime;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.ccoa.Core.Logic.IndexLayer;
import com.alamutra.ccoa.Core.Logic.TypesInLevel;
import com.alamutra.ccoa.Core.SettingRenderingTasks.SkinsCapacitor;
import com.alamutra.ccoa.Core.SettingRenderingTasks.TypeMachinesBody;

public class Square20ParametersMovingUnique implements ParametersMovingUnique {

    private ParametersMovingUnique car20;

    public Square20ParametersMovingUnique() {
        FabricParametersMovingUnique fabric = new FabricParametersMovingUniqueClass();
        this.car20 = fabric.getMoving(TypeMachinesBody.TEST_SQUARE_20);
    }

    @Override
    public void mark(FootprintsSpaceTime footprintsSpaceTime, PathCCoA pathCCoA, double timeAdding, IndexLayer indexLayer) throws СrashIntoAnImpassableObjectException {
        this.car20.mark(footprintsSpaceTime, pathCCoA, timeAdding, indexLayer);
    }

    @Override
    public void markWithoutStandingUntilEndTime(FootprintsSpaceTime footprintsSpaceTime, PathCCoA pathCCoA, double timeAdding, IndexLayer indexLayer) throws СrashIntoAnImpassableObjectException {
        this.markWithoutStandingUntilEndTime(footprintsSpaceTime, pathCCoA, timeAdding, indexLayer);
    }

    @Override
    public PolygonCCoA getShape() {
        return this.car20.getShape();
    }

    @Override
    public double getSpeed() {
        return this.car20.getSpeed();
    }

    @Override
    public double getTimeTravel(double distance) {
        return this.car20.getTimeTravel(distance);
    }

    @Override
    public SkinsCapacitor getSkin() {
        return this.car20.getSkin();
    }

    @Override
    public TypesInLevel getTypeInLevel() {
        return this.car20.getTypeInLevel();
    }

    @Override
    public TypeMachinesBody getTypeMachinesBody() {
        return this.car20.getTypeMachinesBody();
    }

    @Override
    public double getLengthStep() {
        return this.car20.getLengthStep();
    }

    @Override
    public PointCCoA getPointWhereCoordinatesAreApplied() {
        return this.car20.getPointWhereCoordinatesAreApplied();
    }

    @Override
    public double getRadius() {
        return this.car20.getRadius();
    }

    @Override
    public PointCCoA getVectorFromTopLeftToAppliedCoordinates() {
        return this.car20.getVectorFromTopLeftToAppliedCoordinates();
    }

    @Override
    public int getID() {
        return this.car20.getID();
    }
}
