package com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.GeneratorFlowCars;

import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.FootprintsSpaceTime;

public interface RunnerSynchronizationGeneratorsCars {

    public void addGeneratorCars(GeneratorCars generatorCars);

    public void runIfPossible(FootprintsSpaceTime footprintsSpaceTime);
}
