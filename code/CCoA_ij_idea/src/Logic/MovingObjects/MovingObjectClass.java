package Logic.MovingObjects;

import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.FootprintSpaceTime.*;
import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObstacleExeption;
import Logic.LevelLayer;
import Wrapper.RandomWrapperClass;

public class MovingObjectClass implements MovingObject {

    private PolygonExtended polygonExtended;
    private TypeMachinesBody typeMachinesBody;
    private double speed;
    private int idObject = new RandomWrapperClass(835).nextInt();

    public MovingObjectClass(PolygonExtended polygonExtended, TypeMachinesBody typeMachinesBody) {
        this.polygonExtended = polygonExtended;
        this.typeMachinesBody = typeMachinesBody;
    }


    @Override
    public void mark(
            FootprintsSpaceTime footprintsSpaceTime,
            Path path,
            double timeAdding,
            LevelLayer levelLayer
    ) throws СrashIntoAnImpassableObstacleExeption {


        footprintsSpaceTime.addFootprint(
                path.getIdTrack(),
                this,
                path,
                timeAdding,
                levelLayer
        );


/*        Position position1 = new PositionClass(new PointClass(100, 100), 0);
        footprintsSpaceTime.addFootprint(13, testBody, position1, 1); //FIXME positions add


        Position position2 = new PositionClass(new PointClass(100, 300), 0);
        footprintsSpaceTime.addFootprint(13, testBody, position2, 2);


        Position position3 = new PositionClass(new PointClass(100, 600), 0);
        footprintsSpaceTime.addFootprint(13, testBody, position3, 3);


        Position position4 = new PositionClass(new PointClass(100, 800), 0);
        footprintsSpaceTime.addFootprint(13, testBody, position4, 4);*/


    }

    @Override
    public String toString() {
        return "type: " + this.getType() + " id: " + this.getID() + " speed: " + this.getSpeed();
    }

    @Override
    public PolygonExtended getPolygonExtended() {
        return this.polygonExtended;
    }

    @Override
    public double getSpeed() {
        return this.speed;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
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
        return this.idObject;
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
        return this.getPolygonExtended().getCenterAverage();
    }

    @Override
    public Point getPointWhereCoordinatesAreApplied() {
        return this.getCenterMovingObject();
    }

    @Override
    public double getRadius() { //FIXME ADD TEST NOW
        Point center = this.getPointWhereCoordinatesAreApplied();

        PolygonExtended polygonExtended = this.getPolygonExtended();

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
        Point fristPoint = this.getPolygonExtended().getPoint(0); //FIXME finding
        return this.getPointWhereCoordinatesAreApplied().getVector(fristPoint);
    }
    //==== <start> <Getter_and_Setter> ==================================================


    //==== <end> <Getter_and_Setter> ==================================================
}
