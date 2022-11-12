package com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.GeneratorFlowCars;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.FootprintsSpaceTime;

public interface RunnerSynchronizationGeneratorsCars {

    public void addGeneratorCars(GeneratorCars generatorCars);

    public void runIfPossible(FootprintsSpaceTime footprintsSpaceTime);
}
