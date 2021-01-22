package Logic.FootprintSpaceTime;

import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.*;
import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.Path;
import Logic.MovingObjects.PathClass;
import Logic.PathsMachines.PositionClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.PI;
import static org.junit.Assert.*;

public class FootprintsSpaceTimeTest {


    private LevelLayer defaultLevelLayer = new LevelLayerClass(0);

    private interface AverageTimeMeter {
        Double getTimeMovingToNextPointPath();
    }

    private class AverageTimeMeterClass implements AverageTimeMeter {
        private FootprintsSpaceTime onlyFootprintsSpaceTime;
        private double timeAddingPath = 0.0;

        public AverageTimeMeterClass(Path resPath) {
            onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass();

            FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();
            MovingObject movingObject = fabricMovingObjects.getMachine(TypeMachinesBody.PASSENGER_CAR);

            try {
                movingObject.mark(onlyFootprintsSpaceTime, resPath, timeAddingPath, defaultLevelLayer);
            } catch (СrashIntoAnImpassableObjectExeption ex) {
            }
        }

        @Override
        public Double getTimeMovingToNextPointPath() {
            return onlyFootprintsSpaceTime.averageTimeMovingToNextPointOfPath();
        }
    }

    @Test
    public void averageTimeMovingToNextPointOfPath_1() {
        AverageTimeMeter tester = new AverageTimeMeterClass(new PathClass(Arrays.asList(new PointClass[]{
                new PointClass(0, 0),
                new PointClass(10, 0)
        })));
        Double expected = 1.0;
        assertEquals(expected, tester.getTimeMovingToNextPointPath(), GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @Test
    public void averageTimeMovingToNextPointOfPath_2() {
        AverageTimeMeter tester = new AverageTimeMeterClass(new PathClass(Arrays.asList(new PointClass[]{
                new PointClass(0, 0),
                new PointClass(20, 0)
        })));
        Double expected = 2.0;
        assertEquals(expected, tester.getTimeMovingToNextPointPath(), GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }


}