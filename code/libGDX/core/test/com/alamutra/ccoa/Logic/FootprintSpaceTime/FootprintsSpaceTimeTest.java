package com.alamutra.ccoa.Logic.FootprintSpaceTime;

import com.alamutra.ccoa.GUI.StatementTaskRendering.TypeMachinesBody;
import com.alamutra.ccoa.Logic.*;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import com.alamutra.ccoa.Logic.MovingObjects.*;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class FootprintsSpaceTimeTest {


    private IndexLayer defaultIndexLayer = new IndexLayerClass(0);

    private interface AverageTimeMeter {
        Double getTimeMovingToNextPointPath();
    }

    private class AverageTimeMeterClass implements AverageTimeMeter {
        private FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass();
        private double timeAddingPath = 0.0;

        public AverageTimeMeterClass(Path resPath) {
            FabricParametersMoving fabricParametersMoving = new FabricParametersMovingClass();
            ParametersMoving parametersMoving = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);

            try {
                parametersMoving.mark(onlyFootprintsSpaceTime, resPath, timeAddingPath, defaultIndexLayer);
            } catch (СrashIntoAnImpassableObjectExeption ex) {
            }
        }

        @Override
        public Double getTimeMovingToNextPointPath() {
            return onlyFootprintsSpaceTime.totalTimeAllMoving();
        }
    }

    @Test
    public void averageTimeMovingToNextPointOfPath_1() {
        AverageTimeMeter tester = new AverageTimeMeterClass(new PathClass(Arrays.asList(new Point[]{
                new PointClass(0, 0),
                new PointClass(10, 0)
        })));
        Double expected = 1.0;
        assertEquals(expected, tester.getTimeMovingToNextPointPath(), GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @Test
    public void averageTimeMovingToNextPointOfPath_2() {
        AverageTimeMeter tester = new AverageTimeMeterClass(new PathClass(Arrays.asList(new Point[]{
                new PointClass(0, 0),
                new PointClass(20, 0)
        })));
        Double expected = 2.0;
        double actual = tester.getTimeMovingToNextPointPath();
        assertEquals(expected, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }


}