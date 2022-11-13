package com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.GeneratorFlowCars;

import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.FootprintsSpaceTime;

public interface RunnerSynchronizationGeneratorsCars {

    public void addGeneratorCars(GeneratorCars generatorCars);

    public void runIfPossible(FootprintsSpaceTime footprintsSpaceTime);
}
