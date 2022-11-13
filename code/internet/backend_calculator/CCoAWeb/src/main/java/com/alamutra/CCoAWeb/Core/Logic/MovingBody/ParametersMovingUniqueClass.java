package com.alamutra.CCoAWeb.Core.Logic.MovingBody;

import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.Footprint;
import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.FootprintsSpaceTime;
import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.CCoAWeb.Core.Logic.GlobalVariable;
import com.alamutra.CCoAWeb.Core.Logic.IndexLayer;
import com.alamutra.CCoAWeb.Core.Logic.TypesInLevel;
import com.alamutra.CCoAWeb.Core.SettingRenderingTasks.SkinsCapacitor;
import com.alamutra.CCoAWeb.Core.SettingRenderingTasks.TypeMachinesBody;
import com.alamutra.CCoAWeb.Core.Wrappers.RandomWrapperClass;
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
        return this.polygonCCoA.clone();
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
    public boolean equalsWithoutUniqueId(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;


        ParametersMovingUnique other = (ParametersMovingUniqueClass) obj;

        return other.getShape().equals(this.polygonCCoA)
                && other.getTypeMachinesBody().equals(this.typeMachinesBody)
                && GlobalVariable.equalsNumber(other.getSpeed(), this.getSpeed());
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
        double minCoordinateX = getMinCoordinateX();
        double maxCoordinateX = getMaxCoordinateX();

        return Math.abs(maxCoordinateX - minCoordinateX);
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


    private double getMaxCoordinateX() {
        double maxCoordinateX = Double.MIN_VALUE;

        for (int i = 0; i < this.polygonCCoA.getCountPoints(); i++) {
            PointCCoA currentPointCCoA = this.polygonCCoA.getPoint(i);

            if (currentPointCCoA.getX() > maxCoordinateX) {
                maxCoordinateX = currentPointCCoA.getX();
            }
        }

        return maxCoordinateX;
    }
    private double getMinCoordinateX() {
        double minCoordinateX = Double.MAX_VALUE;

        for (int i = 0; i < this.polygonCCoA.getCountPoints(); i++) {
            PointCCoA currentPointCCoA = this.polygonCCoA.getPoint(i);

            if (currentPointCCoA.getX() < minCoordinateX) {
                minCoordinateX = currentPointCCoA.getX();
            }
        }

        return minCoordinateX;
    }

    private PointCCoA getCenterMovingObject() {
        return this.getShape().getCenterAverage();
    }

}

