package com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.GeneratorFlowCars;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.FootprintsSpaceTime;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.GlobalVariable;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.IndexLayer;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.IndexLayerClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.*;

public class StandardGeneratorCars implements GeneratorCars {

    private ParametersMoving square20Car = new Square20ParametersMoving();

    private PathCCoA path = new StandardPathX0Y100ToX100Y100();
    private double heightSquare20Car = 20;
    private double protectionAccuracyError = GlobalVariable.DOUBLE_COMPARISON_ACCURACY * 5;
    private double timeToDriveYourLength = heightSquare20Car / square20Car.getSpeed();
    private double interval = timeToDriveYourLength + protectionAccuracyError;
    private IndexLayer defaultLayer = new IndexLayerClass(0);
    private GeneratorCars generatorCars =
            new GeneratorCarsClass(square20Car, path, interval, 0, 9, defaultLayer);

    @Override
    public boolean hasNextCar() {
        return generatorCars.hasNextCar();
    }

    @Override
    public void markNextCarIfPossible(FootprintsSpaceTime footprintsSpaceTime, double currentTime) {
        generatorCars.markNextCarIfPossible(footprintsSpaceTime, currentTime);
    }

    @Override
    public double getInterval() {
        return generatorCars.getInterval();
    }

    @Override
    public double getStartTime() {
        return generatorCars.getStartTime();
    }
}
