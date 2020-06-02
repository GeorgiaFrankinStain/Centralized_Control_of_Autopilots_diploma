package Logic.FootprintSpaceTime;

import GUI.ExecutionTaskRendering.BasicFeaturesJava.MapRender;
import GUI.ExecutionTaskRendering.BasicFeaturesJava.MapRenderClass;
import GUI.StatementTaskRendering.PoolDataFootprintForRendering;
import GUI.StatementTaskRendering.PoolDataFootprintForRenderingClass;
import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.FabricMovingObjects;
import Logic.FabricMovingObjectsClass;
import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObstacleExeption;
import Logic.Landscape.Landscape;
import Logic.Landscape.LandscapeClass;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.Path;
import Logic.MovingObjects.PathClass;
import Logic.PathsMachines.PositionClass;
import Logic.Position;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static java.lang.Math.PI;
import static org.junit.Assert.*;

public class FootprintsSpaceTimeTest {

    @Test
    public void getRenderingFootprintsFromWhen() {
        {


            Landscape onlyLandscape = new LandscapeClass();
            FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass(onlyLandscape); //create FootprintsSpaceTime (Landscape) //PUNKT_1

            PoolDataFootprintForRendering poolDataFootprintForRendering = new PoolDataFootprintForRenderingClass(onlyFootprintsSpaceTime);
            MapRender subwindowMapRendering = new MapRenderClass(poolDataFootprintForRendering); //create MapRender (FootprintsSpaceTime)

            {
                FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();

                MovingObject wall = fabricMovingObjects.getMachine(TypeMachinesBody.WALL_CAR);
                try {
                    wall.mark(onlyFootprintsSpaceTime, createPathWall(), 0.0);
                } catch (СrashIntoAnImpassableObstacleExeption ex) {
                }


                MovingObject movingObject = fabricMovingObjects.getMachine(TypeMachinesBody.PASSENGER_CAR);
                try {
                    movingObject.mark(onlyFootprintsSpaceTime, createPath(), 0.0);
                } catch (СrashIntoAnImpassableObstacleExeption ex) {
                }
            }


            {
                PolygonExtended areaRendering = new PolygonExtendedClass();
                areaRendering.addPoint(new PointClass(0, 0));
                areaRendering.addPoint(new PointClass(900, 0));
                areaRendering.addPoint(new PointClass(900, 900));
                areaRendering.addPoint(new PointClass(0, 900));
                List<Footprint> footprints =
                        onlyFootprintsSpaceTime.getRenderingFootprintsFromWhen(areaRendering, 0.0);



/*                boolean weAreCurrentlyTestingSerializedObjectRecords = false;
                if (weAreCurrentlyTestingSerializedObjectRecords) {

                }*/

            }
        }
    }

    @Test
    public void getPosition() {


    }

    @Test
    public void addFootprint() {


        double timeAddingPath = 0.0;


        {// Test path from to
            Landscape onlyLandscape = new LandscapeClass();
            FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass(onlyLandscape);

            Path resPath = new PathClass();
            resPath.addPoint(new PointClass(0, 100));
            Point endPoint = new PointClass(100, 100);
            resPath.addPoint(endPoint);


            FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();
            MovingObject movingObject = fabricMovingObjects.getMachine(TypeMachinesBody.PASSENGER_CAR);


            double speed = 10;
            movingObject.setSpeed(speed);

            double lengthStep = 20; // movingObject.getLength();
            double timeStanding = 2;
/*            if (Math.abs(speed) < 0.00000000001) { //FIXME MAGIC_NUMBER
                timeStanding = Double.MAX_VALUE * 0.95; //FIXME MAGIC_NUMBER
            } else {
                timeStanding = lengthStep / speed;
            }*/


            try {
                movingObject.mark(onlyFootprintsSpaceTime, resPath, timeAddingPath);
            } catch (СrashIntoAnImpassableObstacleExeption ex) {
            }


            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(
                        movingObject.getID(),
                        timeStanding * 0
                );

                Position expected = new PositionClass(new PointClass(lengthStep * 0, 100), 0); //FIXME
                assertEquals(expected, actualPosition);
            }

            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(
                        movingObject.getID(),
                        timeStanding * 1
                );

                Position expected = new PositionClass(new PointClass(lengthStep * 1, 100), 0); //FIXME
                assertEquals(expected, actualPosition);
            }

            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(
                        movingObject.getID(),
                        timeStanding * 4
                );

                Position expected = new PositionClass(new PointClass(lengthStep * 4, 100), 0); //FIXME
                assertEquals(expected, actualPosition);
            }

            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(
                        movingObject.getID(),
                        timeStanding * 5
                );

                Position expected = new PositionClass(new PointClass(lengthStep * 5, 100), 0); //FIXME
                assertEquals(expected, actualPosition);
            }


            { //Test standing after stop machine
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(
                        movingObject.getID(),
                        timeStanding * 400
                );

                Position expected = new PositionClass(new PointClass(lengthStep * 5, 100), 0); //FIXME
                assertEquals(expected, actualPosition);
            }

            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(
                        movingObject.getID(),
                        10
                );

                double rotation = 0;
                Position expectedPosition = new PositionClass(endPoint, rotation);
                assertTrue(expectedPosition.equals(actualPosition)); //TODO add small steps, must assertTrue


                Position expected = new PositionClass(new PointClass(100, 100), 0); //FIXME
                assertEquals(expected, actualPosition);
            }


            for (int i = 0; i < 6; i++) {

                Position actualPosition = onlyFootprintsSpaceTime.getPosition(
                        movingObject.getID(),
                        timeStanding * i
                );

                Position expected = new PositionClass(new PointClass(lengthStep * i, 100), 0); //FIXME
                assertEquals(expected, actualPosition);
            }
        }


        {// Test path from to //Test last small step standing after stop machine
            Landscape onlyLandscape = new LandscapeClass();
            FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass(onlyLandscape);

            Path resPath = new PathClass();
            resPath.addPoint(new PointClass(0, 100));
            Point endPoint = new PointClass(110, 100);
            resPath.addPoint(endPoint);


            FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();
            MovingObject movingObject = fabricMovingObjects.getMachine(TypeMachinesBody.PASSENGER_CAR);


            double speed = 10;
            movingObject.setSpeed(speed);

            double lengthStep = 20; // movingObject.getLength();
            double timeStanding = 2;
/*            if (Math.abs(speed) < 0.00000000001) { //FIXME MAGIC_NUMBER
                timeStanding = Double.MAX_VALUE * 0.95; //FIXME MAGIC_NUMBER
            } else {
                timeStanding = lengthStep / speed;
            }*/


            try {
                movingObject.mark(onlyFootprintsSpaceTime, resPath, timeAddingPath);
            } catch (СrashIntoAnImpassableObstacleExeption ex) {
            }

            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(
                        movingObject.getID(),
                        timeStanding * 400
                );

                Position expected = new PositionClass(new PointClass(lengthStep * 5.5, 100), 0); //FIXME
                assertEquals(expected, actualPosition);
            }

        }


        {// Test path from to //Test last small step standing after stop machine
            Landscape onlyLandscape = new LandscapeClass();
            FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass(onlyLandscape);

            Path resPath = new PathClass();
            resPath.addPoint(new PointClass(0, 0));
            Point endPoint = new PointClass(0, 100);
            resPath.addPoint(endPoint);


            FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();
            MovingObject movingObject = fabricMovingObjects.getMachine(TypeMachinesBody.PASSENGER_CAR);


            double speed = 10;
            movingObject.setSpeed(speed);


            try {
                movingObject.mark(onlyFootprintsSpaceTime, resPath, timeAddingPath);
            } catch (СrashIntoAnImpassableObstacleExeption ex) {
            }

            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(
                        movingObject.getID(),
                        8
                );

                Position expected = new PositionClass(new PointClass(0, 80), PI / 2); //FIXME
                assertEquals(expected, actualPosition);
            }
            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(
                        movingObject.getID(),
                        10
                );

                Position expected = new PositionClass(new PointClass(0, 100), PI / 2); //FIXME
                assertEquals(expected, actualPosition);
            }

            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(
                        movingObject.getID(),
                        10000000
                );

                Position expected = new PositionClass(new PointClass(0, 100), PI / 2); //FIXME
                assertEquals(expected, actualPosition);
            }
        }


        {// Test standing
            Landscape onlyLandscape = new LandscapeClass();
            FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass(onlyLandscape);

            Path resPath = new PathClass();
            resPath.addPoint(new PointClass(0, 0));


            FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();
            MovingObject movingObject = fabricMovingObjects.getMachine(TypeMachinesBody.PASSENGER_CAR);


            double speed = 10;
            movingObject.setSpeed(speed);


            try {
                movingObject.mark(onlyFootprintsSpaceTime, resPath, timeAddingPath);
            } catch (СrashIntoAnImpassableObstacleExeption ex) {
            }

            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(
                        movingObject.getID(),
                        0
                );

                Position expected = new PositionClass(new PointClass(0, 0), 0.0); //FIXME
                assertEquals(expected, actualPosition);
            }
            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(
                        movingObject.getID(),
                        23456
                );

                Position expected = new PositionClass(new PointClass(0, 0), 0.0); //FIXME
                assertEquals(expected, actualPosition);
            }
            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(
                        movingObject.getID(),
                        234563456
                );

                Position expected = new PositionClass(new PointClass(0, 0), 0.0); //FIXME
                assertEquals(expected, actualPosition);
            }
        }

        {//Test zig-zag without last little step
            /*
             * number this is number interation cicle up
             * number this is point
             * -- this is time standing (Length equal length movingObject)
             * - this is lastLittleStep (last Little Time Standing)
             *
             *
             *
             * 0--0--0--1
             *          |
             *          |
             *          1
             *          |
             *          |
             *          1
             *          2--2--2--2--3
             *                      ^
             *               end point in endless
             *
             *
             * (next demonstration example with last little step
             *
             * 0--0--0--0-1
             *            |
             *            |
             *            1
             *            |
             *            |
             *            1
             *            |
             *            2--2--2--2--2-3
             *                          ^
             *               end point in endless
             *
             */

            Landscape onlyLandscape = new LandscapeClass();
            FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass(onlyLandscape);

            Path resPath = new PathClass();
            resPath.addPoint(new PointClass(0, 0));
            resPath.addPoint(new PointClass(100, 0));
            resPath.addPoint(new PointClass(100, 100));
            resPath.addPoint(new PointClass(200, 100));


            FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();
            MovingObject movingObject = fabricMovingObjects.getMachine(TypeMachinesBody.PASSENGER_CAR);


            double speed = 10;
            movingObject.setSpeed(speed);

            double lengthStep = 20; // movingObject.getLength();
            double timeStanding = 2;
/*            if (Math.abs(speed) < 0.00000000001) { //FIXME MAGIC_NUMBER
                timeStanding = Double.MAX_VALUE * 0.95; //FIXME MAGIC_NUMBER
            } else {
                timeStanding = lengthStep / speed;
            }*/


            try {
                movingObject.mark(onlyFootprintsSpaceTime, resPath, timeAddingPath);
            } catch (СrashIntoAnImpassableObstacleExeption ex) {
            }

            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(
                        movingObject.getID(),
                        timeStanding * 0
                );

                Position expected = new PositionClass(new PointClass(lengthStep * 0, 0), 0); //FIXME
                assertEquals(expected, actualPosition);
            }

            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(
                        movingObject.getID(),
                        timeStanding * 1
                );

                Position expected = new PositionClass(new PointClass(lengthStep * 1, 0), 0); //FIXME
                assertEquals(expected, actualPosition);
            }

            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(
                        movingObject.getID(),
                        timeStanding * 4
                );

                Position expected = new PositionClass(new PointClass(lengthStep * 4, 0), 0); //FIXME
                assertEquals(expected, actualPosition);
            }

            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(
                        movingObject.getID(),
                        timeStanding * 5
                );

                Position expected = new PositionClass(new PointClass(100, 0), 0); //FIXME
                assertEquals(expected, actualPosition);
            }


            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(
                        movingObject.getID(),
                        timeStanding * 5 * 2
                );

                Position expected = new PositionClass(new PointClass(100, 100), 0); //FIXME
                assertEquals(expected, actualPosition);
            }

            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(
                        movingObject.getID(),
                        timeStanding * 5 * 3
                );

                Position expected = new PositionClass(new PointClass(200, 100), 0); //FIXME
                assertEquals(expected, actualPosition);
            }
        }


        {
            Landscape onlyLandscape = new LandscapeClass();
            FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass(onlyLandscape);

            Path resPath = new PathClass();
            resPath.addPoint(new PointClass(0, 0));


            FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();
            MovingObject movingObject = fabricMovingObjects.getMachine(TypeMachinesBody.PASSENGER_CAR);


            double speed = 10;
            movingObject.setSpeed(speed);


            try {
                movingObject.mark(onlyFootprintsSpaceTime, resPath, timeAddingPath);
            } catch (СrashIntoAnImpassableObstacleExeption ex) {
            }

            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(
                        movingObject.getID(),
                        0
                );

                Position expected = new PositionClass(new PointClass(0, 0), 0.0); //FIXME
                assertEquals(expected, actualPosition);
            }
        }


        {//Test timeAdding is very big double
            timeAddingPath = FootprintsSpaceTimeClass.MAX_TIME_STANDING / 2;
            Landscape onlyLandscape = new LandscapeClass();
            FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass(onlyLandscape);

            Path resPath = new PathClass();
            resPath.addPoint(new PointClass(0, 100));
            Point endPoint = new PointClass(100, 100);
            resPath.addPoint(endPoint);


            FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();
            MovingObject movingObject = fabricMovingObjects.getMachine(TypeMachinesBody.PASSENGER_CAR);


            double speed = 10;
            movingObject.setSpeed(speed);

            double lengthStep = 20; // movingObject.getLength();
            double timeStanding = 2;
/*            if (Math.abs(speed) < 0.00000000001) { //FIXME MAGIC_NUMBER
                timeStanding = Double.MAX_VALUE * 0.95; //FIXME MAGIC_NUMBER
            } else {
                timeStanding = lengthStep / speed;
            }*/


            try {
                movingObject.mark(onlyFootprintsSpaceTime, resPath, timeAddingPath);
            } catch (СrashIntoAnImpassableObstacleExeption ex) {
            }

            {
                Position actualPosition = onlyFootprintsSpaceTime.getPosition(
                        movingObject.getID(),
                        timeAddingPath + timeStanding * 0
                );

                Position expected = new PositionClass(new PointClass(lengthStep * 0, 100), 0); //FIXME
//            assertEquals(expected, actualPosition);
                //FIXME TEST_FAILED_CONCEPTION mantica double number, += 2 no change number, mantica of double
            }
        }


        {//TEST Exeption situation
            Landscape onlyLandscape = new LandscapeClass();
            FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass(onlyLandscape);

            Path resPath = new PathClass();
            resPath.addPoint(new PointClass(0, 0));



            FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();

            MovingObject wall = fabricMovingObjects.getMachine(TypeMachinesBody.WALL_CAR);
            try {
                wall.mark(onlyFootprintsSpaceTime, createPathWall(), 0.0);
            } catch (СrashIntoAnImpassableObstacleExeption ex) {
            }


            boolean exeptionThrowed = false;
            MovingObject movingObject = fabricMovingObjects.getMachine(TypeMachinesBody.PASSENGER_CAR);
            try {
                movingObject.mark(onlyFootprintsSpaceTime, createPath(), 0.0);
            } catch (СrashIntoAnImpassableObstacleExeption ex) {
                exeptionThrowed = true;
            }

            assertTrue(exeptionThrowed);
        }
    }

    //==== <start> <Private_Methods> =======================================================================
    private Path createPath() { //FIXME IMITATION
        Path resPath = new PathClass();
        resPath.addPoint(new PointClass(10, 10));
        resPath.addPoint(new PointClass(15, 15));
        resPath.addPoint(new PointClass(200, 15));
        resPath.addPoint(new PointClass(20, 250));
        resPath.addPoint(new PointClass(30, 30));
        resPath.addPoint(new PointClass(35, 35));
        resPath.addPoint(new PointClass(40, 40));
        resPath.addPoint(new PointClass(400, 40));

        return resPath;
    }
    private Path createPathWall() { //FIXME IMITATION
        Path resPath = new PathClass();
        resPath.addPoint(new PointClass(60, 60));

        return resPath;
    }


    //==== <end> <Private_Methods> =========================================================================
}