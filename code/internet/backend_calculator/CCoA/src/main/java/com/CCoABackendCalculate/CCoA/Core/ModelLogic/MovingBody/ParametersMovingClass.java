package com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.FootprintsSpaceTime;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PolygonCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.IndexLayer;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.TypesInLevel;
import com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.SkinsCapacitor;
import com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.TypeMachinesBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParametersMovingClass implements ParametersMoving {
    private static final Logger LOGGER = LogManager.getLogger(ParametersMovingClass.class);

    private double speed;
    private PolygonCCoA polygonCCoA;
    private TypeMachinesBody typeMachinesBody;

    private ParametersMovingUnique parametersMovingUnique;

    public ParametersMovingClass(double speed, PolygonCCoA polygonCCoA, TypeMachinesBody typeMachinesBody) {
        this.speed = speed;
        this.polygonCCoA = polygonCCoA;
        this.typeMachinesBody = typeMachinesBody;

        updateUniqueParametersMoving();
    }

    public ParametersMovingClass(ParametersMovingUnique parametersMovingUnique) {
        this.speed = parametersMovingUnique.getSpeed();
        this.polygonCCoA = parametersMovingUnique.getShape();
        this.typeMachinesBody = parametersMovingUnique.getTypeMachinesBody();

        updateUniqueParametersMoving();
    }

    @Override
    public void mark(
            FootprintsSpaceTime footprintsSpaceTime,
            PathCCoA pathCCoA,
            double timeAdding,
            IndexLayer indexLayer
    ) throws СrashIntoAnImpassableObjectException {
        LOGGER.debug("noUnique mark {}", this.parametersMovingUnique);
        this.parametersMovingUnique.mark(footprintsSpaceTime, pathCCoA, timeAdding, indexLayer);
        updateUniqueParametersMoving();
    }

    @Override
    public void markWithoutStandingUntilEndTime(
            FootprintsSpaceTime footprintsSpaceTime,
            PathCCoA pathCCoA,
            double timeAdding,
            IndexLayer indexLayer
    ) throws СrashIntoAnImpassableObjectException {
        LOGGER.debug("noUnique markWithoutStandingUntilEndTime {}", this.parametersMovingUnique);
        this.parametersMovingUnique.markWithoutStandingUntilEndTime(
                footprintsSpaceTime,
                pathCCoA,
                timeAdding,
                indexLayer
        );
        updateUniqueParametersMoving();
    }

    @Override
    public PolygonCCoA getShape() {
        return this.parametersMovingUnique.getShape();
    }

    @Override
    public double getSpeed() {
        return this.parametersMovingUnique.getSpeed();
    }

    @Override
    public double getTimeTravel(double distance) {
        return this.parametersMovingUnique.getTimeTravel(distance);
    }

    @Override
    public SkinsCapacitor getSkin() {
        return this.parametersMovingUnique.getSkin();
    }

    @Override
    public TypesInLevel getTypeInLevel() {
        return this.parametersMovingUnique.getTypeInLevel();
    }

    @Override
    public double getLengthStep() {
        return this.parametersMovingUnique.getLengthStep();
    }

    @Override
    public PointCCoA getPointWhereCoordinatesAreApplied() {
        return this.parametersMovingUnique.getPointWhereCoordinatesAreApplied();
    }

    @Override
    public double getRadius() {
        return this.parametersMovingUnique.getRadius();
    }

    @Override
    public PointCCoA getVectorFromTopLeftToAppliedCoordinates() {
        return this.parametersMovingUnique.getVectorFromTopLeftToAppliedCoordinates();
    }

    private void updateUniqueParametersMoving() {
        this.parametersMovingUnique = new ParametersMovingUniqueClass(
                this.speed,
                this.polygonCCoA.clone(),
                this.typeMachinesBody
        );
    }
}
