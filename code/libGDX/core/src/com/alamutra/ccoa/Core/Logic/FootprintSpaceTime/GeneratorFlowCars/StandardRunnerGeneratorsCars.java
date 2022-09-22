package com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.GeneratorFlowCars;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.FootprintsSpaceTime;

public class StandardRunnerGeneratorsCars implements RunnerSynchronizationGeneratorsCars {
    RunnerSynchronizationGeneratorsCars runner;

    public StandardRunnerGeneratorsCars() {
        this.runner = new RunnerSynchronizationGeneratorsCarsClass();
        this.runner.addGeneratorCars(new StandardGeneratorCars());
    }

    @Override
    public void addGeneratorCars(GeneratorCars generatorCars) {
        this.runner.addGeneratorCars(generatorCars);
    }

    @Override
    public void runIfPossible(FootprintsSpaceTime footprintsSpaceTime) {
        this.runner.runIfPossible(footprintsSpaceTime);
    }
}
