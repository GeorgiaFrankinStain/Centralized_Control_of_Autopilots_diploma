package GUI.StatementTaskRendering.ConvertersTime;

import GUI.StatementTaskRendering.ConverterTime;
import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.*;
import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import Logic.FootprintSpaceTime.FootprintsSpaceTime;
import Logic.FootprintSpaceTime.FootprintsSpaceTimeClass;
import Logic.FootprintSpaceTime.Point;
import Logic.FootprintSpaceTime.PointClass;
import Logic.MovingObjects.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class DemonstartionAlhorithTest {

    IndexLayer defaultIndexLayer = new IndexLayerClass(0);
    private ConverterTime converterTime = createConverterTime();

    private ConverterTime createConverterTime() {
        FootprintsSpaceTime onlyFootprintsSpaceTime = new FootprintsSpaceTimeClass();

        /*
         * - this is second
         * 0-1-2
         */
        Path resPath = new PathClass();
        resPath.addPoint(new PointClass(0, 0));
        Point endPoint = new PointClass(20, 0);
        resPath.addPoint(endPoint);


        FabricParametersMoving fabricParametersMoving = new FabricParametersMovingClass();
        ParametersMoving parametersMoving = fabricParametersMoving.getMoving(TypeMachinesBody.TEST_SQUARE_20);


        double timeAddingPath = 0.0;
        try {
            parametersMoving.mark(onlyFootprintsSpaceTime, resPath, timeAddingPath, defaultIndexLayer);
        } catch (СrashIntoAnImpassableObjectExeption ex) {
        }


        ConverterTime converterTime = new DemonstartionAlhorith(onlyFootprintsSpaceTime);
        return converterTime;
    }

    @Test
    public void convert() {
        double requstedTime = 0.0;

        Double expected = -3.0;
        Double actual = converterTime.convert(requstedTime, defaultIndexLayer);

        assertEquals(expected, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @Test
    public void convert_0() {
        double requstedTime = 0.0;

        IndexLayer levelWithAlhorithInformation = new IndexLayerClass(1);
        Double expected = requstedTime;
        Double actual = converterTime.convert(requstedTime, levelWithAlhorithInformation);

        assertEquals(expected, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

}