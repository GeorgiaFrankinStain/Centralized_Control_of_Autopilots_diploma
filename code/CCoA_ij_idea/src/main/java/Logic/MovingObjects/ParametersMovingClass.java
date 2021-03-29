package Logic.MovingObjects;

import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.FootprintSpaceTime.*;
import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import Logic.IndexLayer;
import Wrapper.RandomWrapperClass;

public class ParametersMovingClass implements ParametersMoving {

    private PolygonExtended polygonExtended;
    private TypeMachinesBody typeMachinesBody;
    private double speed;
    private int id;

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
                path.getIdTrack(),
                this,
                path,
                timeAdding,
                indexLayer
        );
    }

    @Override
    public String toString() {
        return "type: " + this.getType() + " id: " + this.getID() + " speed: " + this.getSpeed();
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
    public String getType() {
        return this.typeMachinesBody.name();
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public double getLength() {
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
