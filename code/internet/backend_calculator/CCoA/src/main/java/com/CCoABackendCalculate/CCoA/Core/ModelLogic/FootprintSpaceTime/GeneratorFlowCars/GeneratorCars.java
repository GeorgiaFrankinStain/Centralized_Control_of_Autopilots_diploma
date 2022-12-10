package com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.GeneratorFlowCars;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.FootprintsSpaceTime;

public interface GeneratorCars {

    public boolean hasNextCar();

    public void markNextCarIfPossible(FootprintsSpaceTime footprintsSpaceTime, double currentTime);

    public double getInterval();

    public double getStartTime();
}
