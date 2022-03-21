package com.alamutra.ccoa.Core.Logic.MovingBody;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.FootprintsSpaceTime;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.ccoa.Core.Logic.IndexLayer;
import com.alamutra.ccoa.Core.Logic.TypesInLevel;
import com.alamutra.ccoa.Core.SettingRenderingTasks.SkinsCapacitor;
import com.alamutra.ccoa.Core.SettingRenderingTasks.TypeMachinesBody;
import com.alamutra.ccoa.Core.Wrappers.RandomWrapperClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParametersMovingUniqueClass implements ParametersMovingUnique {
    private static final Logger LOGGER = LogManager.getLogger(ParametersMovingUniqueClass.class);

    private int id;
    private PolygonCCoA polygonCCoA;
    private TypeMachinesBody typeMachinesBody;
    private double speed;
    private TypesInLevel typeInLevel = TypesInLevel.OBJECT;

    public ParametersMovingUniqueClass(double speed, PolygonCCoA polygonCCoA, TypeMachinesBody typeMachinesBody) {
        testCorrectnessIncomingData(speed, polygonCCoA);
        this.id = new RandomWrapperClass(835).nextInt();
        this.polygonCCoA = renderingShapeAreVectorsFromCoordinateApplicationPoints(polygonCCoA);
        this.typeMachinesBody = typeMachinesBody;
        this.speed = speed;

        LOGGER.debug("create new car with id {}, {}", this.id, this);
    }


    @Override
    public void mark(
            FootprintsSpaceTime footprintsSpaceTime,
            PathCCoA pathCCoA,
            double timeAdding,
            IndexLayer indexLayer
    ) throws СrashIntoAnImpassableObjectException {
        assert (indexLayer != null);

        footprintsSpaceTime.addFootprintsPath(
                this,
                pathCCoA,
                timeAdding,
                indexLayer
        );
    }

    @Override
    public void markWithoutStandingUntilEndTime(
            FootprintsSpaceTime footprintsSpaceTime,
            PathCCoA pathCCoA,
            double timeAdding,
            IndexLayer indexLayer) throws СrashIntoAnImpassableObjectException {
        assert (indexLayer != null);

        footprintsSpaceTime.addFootprintsPathWithoutEndStandingUntilEndTime(
                this,
                pathCCoA,
                timeAdding,
                indexLayer
        );
    }

    @Override
    public String toString() {
        return "type: " + this.getSkin() + " id: " + this.getID() + " speed: " + this.getSpeed();
    }

    @Override
    public PolygonCCoA getShape() {
        return this.polygonCCoA;
    }

    @Override
    public double getSpeed() {
        return this.speed;
    }



    @Override
    public double getTimeTravel(double distance) {
        return distance / this.getSpeed();
    }


    @Override
    public SkinsCapacitor getSkin() {
        return SkinsCapacitor.convertFrom(this.typeMachinesBody);
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public TypesInLevel getTypeInLevel() {
        return typeInLevel;
    }

    @Override
    public TypeMachinesBody getTypeMachinesBody() {
        return this.typeMachinesBody;
    }

    @Override
    public double getLengthStep() {
        double maxCoordinatX = Double.MIN_VALUE;

        for (int i = 0; i < this.polygonCCoA.getCountPoints(); i++) {
            PointCCoA currentPointCCoA = this.polygonCCoA.getPoint(i);

            if (currentPointCCoA.getX() > maxCoordinatX) {
                maxCoordinatX = currentPointCCoA.getX();
            }
        }

        return maxCoordinatX;
    }

    private PointCCoA getCenterMovingObject() {
        return this.getShape().getCenterAverage();
    }

    @Override
    public PointCCoA getPointWhereCoordinatesAreApplied() {
        return this.getCenterMovingObject();
    }

    @Override
    public double getRadius() { //FIXME ADD TEST NOW
        PointCCoA center = this.getPointWhereCoordinatesAreApplied();

        PolygonCCoA polygonCCoA = this.getShape();

        double maxRadius = Double.MIN_VALUE;

        for (int i = 0; i < polygonCCoA.getCountPoints(); i++) {
            double currentRadius = polygonCCoA.getPoint(i).getDistanceToPoint(center);
            if (currentRadius > maxRadius) {
                maxRadius = currentRadius;
            }
        }
        return maxRadius;
    }

    @Override
    public PointCCoA getVectorFromTopLeftToAppliedCoordinates() {
        PointCCoA fristPointCCoA = this.getShape().getPoint(0); //FIXME finding
        return this.getPointWhereCoordinatesAreApplied().getVector(fristPointCCoA);
    }
    //==== <start> <Getter_and_Setter> ==================================================


    //==== <end> <Getter_and_Setter> ==================================================

    private PolygonCCoA renderingShapeAreVectorsFromCoordinateApplicationPoints(PolygonCCoA polygon) {
        polygon.deposeOn(polygon.getCenterAverage().getInversion());
        return polygon;
    }

    private void testCorrectnessIncomingData(double speed, PolygonCCoA shape) {
        if (speed < 0) {
            throw new IllegalArgumentException("speed is negative");
        }

        if (shape.getCountPoints() < 3) {
            throw new IllegalArgumentException("the area of the shape is 0");
        }
    }
}

