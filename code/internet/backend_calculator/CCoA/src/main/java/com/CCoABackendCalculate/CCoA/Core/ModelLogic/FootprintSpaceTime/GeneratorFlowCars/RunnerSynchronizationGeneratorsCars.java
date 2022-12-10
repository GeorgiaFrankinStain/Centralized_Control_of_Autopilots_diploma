package com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.GeneratorFlowCars;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.FootprintsSpaceTime;

public interface RunnerSynchronizationGeneratorsCars {

    public void addGeneratorCars(GeneratorCars generatorCars);

    public void runIfPossible(FootprintsSpaceTime footprintsSpaceTime);
}
