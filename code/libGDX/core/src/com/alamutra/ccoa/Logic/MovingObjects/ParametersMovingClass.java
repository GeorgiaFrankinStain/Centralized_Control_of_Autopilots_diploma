package com.alamutra.ccoa.Logic.MovingObjects;

import com.alamutra.ccoa.GUI.StatementTaskRendering.TypeMachinesBody;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.*;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import com.alamutra.ccoa.Logic.IndexLayer;
import com.alamutra.ccoa.Logic.TypesInLevel;
import com.alamutra.ccoa.Wrapper.RandomWrapperClass;

public class ParametersMovingClass implements ParametersMoving {

    private int id;
    private PolygonExtended polygonExtended;
    private TypeMachinesBody typeMachinesBody;
    private double speed;
    private TypesInLevel typeInLevel = TypesInLevel.OBJECT;

    public ParametersMovingClass(double speed, PolygonExtended polygonExtended, TypeMachinesBody typeMachinesBody) {
        testCorrectnessIncomingData(speed, polygonExtended);
        this.id = new RandomWrapperClass(835).nextInt();
        this.polygonExtended = renderingShapeAreVectorsFromCoordinateApplicationPoints(polygonExtended);
        this.typeMachinesBody = typeMachinesBody;
        this.speed = speed;
    }
    public ParametersMovingClass(PolygonExtended polygonExtended, TypeMachinesBody typeMachinesBody, int movingObject) {
        this.id = movingObject;
        this.polygonExtended = renderingShapeAreVectorsFromCoordinateApplicationPoints(polygonExtended);
        this.typeMachinesBody = typeMachinesBody;
    }


    @Override
    public void mark(
            FootprintsSpaceTime footprintsSpaceTime,
            Path path,
            double timeAdding,
            IndexLayer indexLayer
    ) throws СrashIntoAnImpassableObjectExeption {
        assert (indexLayer != null);

        footprintsSpaceTime.addFootprint(
                this,
                path,
                timeAdding,
                indexLayer
        );
    }

    @Override
    public String toString() {
        return "type: " + this.getTypeTitle() + " id: " + this.getID() + " speed: " + this.getSpeed();
    }

    @Override
    public PolygonExtended getShape() {
        return this.polygonExtended;
    }

    @Override
    public double getSpeed() {
        return this.speed;
    }



    @Override
    public double timeTravel(double distance) {
        return distance / this.getSpeed();
    }


    @Override
    public String getTypeTitle() {
        return this.typeMachinesBody.name();
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

        for (int i = 0; i < this.polygonExtended.getCountPoints(); i++) {
            Point currentPoint = this.polygonExtended.getPoint(i);

            if (currentPoint.getX() > maxCoordinatX) {
                maxCoordinatX = currentPoint.getX();
            }
        }

        return maxCoordinatX;
    }

    private Point getCenterMovingObject() {
        return this.getShape().getCenterAverage();
    }

    @Override
    public Point getPointWhereCoordinatesAreApplied() {
        return this.getCenterMovingObject();
    }

    @Override
    public double getRadius() { //FIXME ADD TEST NOW
        Point center = this.getPointWhereCoordinatesAreApplied();

        PolygonExtended polygonExtended = this.getShape();

        double maxRadius = Double.MIN_VALUE;

        for (int i = 0; i < polygonExtended.getCountPoints(); i++) {
            double currentRadius = polygonExtended.getPoint(i).getDistanceToPoint(center);
            if (currentRadius > maxRadius) {
                maxRadius = currentRadius;
            }
        }
        return maxRadius;
    }

    @Override
    public Point getVectorFromTopLeftToAppliedCoordinates() {
        Point fristPoint = this.getShape().getPoint(0); //FIXME finding
        return this.getPointWhereCoordinatesAreApplied().getVector(fristPoint);
    }
    //==== <start> <Getter_and_Setter> ==================================================


    //==== <end> <Getter_and_Setter> ==================================================

    private PolygonExtended renderingShapeAreVectorsFromCoordinateApplicationPoints(PolygonExtended polygon) {
        polygon.deposeOn(polygon.getCenterAverage().getInversion());
        return polygon;
    }

    private void testCorrectnessIncomingData(double speed, PolygonExtended shape) {
        if (speed < 0) {
            throw new IllegalArgumentException("speed is negative");
        }

        if (shape.getCountPoints() < 3) {
            throw new IllegalArgumentException("the area of the shape is 0");
        }
    }
}
