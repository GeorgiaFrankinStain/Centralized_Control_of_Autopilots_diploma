package GUI.StatementTaskRendering.ConvertersTime;

import GUI.StatementTaskRendering.ConverterTime;
import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.*;
import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObstacleExeption;
import Logic.FootprintSpaceTime.FootprintsSpaceTime;
import Logic.FootprintSpaceTime.FootprintsSpaceTimeClass;
import Logic.FootprintSpaceTime.Point;
import Logic.FootprintSpaceTime.PointClass;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.Path;
import Logic.MovingObjects.PathClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class DemonstartionAlhorithTest {

    @Test
    public void convert() {
        double timeAddingPath = 0.0;
        LevelLayer defaultLevelLayer = new LevelLayerClass(0);

        {
            FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass();

            /*
             * - this is second
             * 0-1-2
             */
            Path resPath = new PathClass();
            resPath.addPoint(new PointClass(0, 0));
            Point endPoint = new PointClass(20, 0);
            resPath.addPoint(endPoint);


            FabricMovingObjects fabricMovingObjects = new FabricMovingObjectsClass();
            MovingObject movingObject = fabricMovingObjects.getMachine(TypeMachinesBody.PASSENGER_CAR);


            try {
                movingObject.mark(onlyFootprintsSpaceTime, resPath, timeAddingPath, defaultLevelLayer);
            } catch (СrashIntoAnImpassableObstacleExeption ex) {
            }


            ConverterTime converterTime = new DemonstartionAlhorith(onlyFootprintsSpaceTime);

            {
                double requstedTime = 0.0;

                Double expected = -4.0;
                Double actual = converterTime.convert(requstedTime, defaultLevelLayer);
                assertEquals(expected, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
            }

            {
                double requstedTime = 0.0;

                Double expected = requstedTime;

                LevelLayer levelWithAlhorithInformation = new LevelLayerClass(1);
                Double actual = converterTime.convert(requstedTime, levelWithAlhorithInformation);
                assertEquals(expected, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
            }
        }
    }
}