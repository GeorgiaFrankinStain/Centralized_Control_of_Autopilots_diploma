package com.alamutra.ccoa.Core.Logic.MovingObjects;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectExeption;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.FootprintsSpaceTime;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.ccoa.Core.Logic.IndexLayer;
import com.alamutra.ccoa.Core.Logic.TypesInLevel;
import com.alamutra.ccoa.Core.SettingRenderingTasks.Skins;
import com.alamutra.ccoa.Core.SettingRenderingTasks.TypeMachinesBody;
import com.alamutra.ccoa.Core.Wrappers.RandomWrapperClass;

public class ParametersMovingClass implements ParametersMoving {

    private int id;
    private PolygonCCoA polygonCCoA;
    private TypeMachinesBody typeMachinesBody;
    private double speed;
    private TypesInLevel typeInLevel = TypesInLevel.OBJECT;

    public ParametersMovingClass(double speed, PolygonCCoA polygonCCoA, TypeMachinesBody typeMachinesBody) {
        testCorrectnessIncomingData(speed, polygonCCoA);
        this.id = new RandomWrapperClass(835).nextInt();
        this.polygonCCoA = renderingShapeAreVectorsFromCoordinateApplicationPoints(polygonCCoA);
        this.typeMachinesBody = typeMachinesBody;
        this.speed = speed;
    }
    public ParametersMovingClass(PolygonCCoA polygonCCoA, TypeMachinesBody typeMachinesBody, int movingObject) {
        this.id = movingObject;
        this.polygonCCoA = renderingShapeAreVectorsFromCoordinateApplicationPoints(polygonCCoA);
        this.typeMachinesBody = typeMachinesBody;
    }


    @Override
    public void mark(
            FootprintsSpaceTime footprintsSpaceTime,
            PathCCoA pathCCoA,
            double timeAdding,
            IndexLayer indexLayer
    ) throws СrashIntoAnImpassableObjectExeption {
        assert (indexLayer != null);

        footprintsSpaceTime.addFootprint(
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
    public Skins getSkin() {
        return Skins.convertFrom(this.typeMachinesBody);
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

