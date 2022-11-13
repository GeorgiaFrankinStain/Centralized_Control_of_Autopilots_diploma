package com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.GeneratorFlowCars;

import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.FootprintsSpaceTime;

import java.util.ArrayList;
import java.util.List;

public class RunnerSynchronizationGeneratorsCarsClass implements RunnerSynchronizationGeneratorsCars {

    private List<GeneratorCars> generatorFlowsCars = new ArrayList<GeneratorCars>();

    @Override
    public void addGeneratorCars(GeneratorCars generatorCars) {
        this.generatorFlowsCars.add(generatorCars);
    }

    @Override
    public void runIfPossible(FootprintsSpaceTime footprintsSpaceTime) {

        double minInterval = minIntervalGenerators();
        double earliestStartTime = earliestStartTime();

        for (double time = earliestStartTime; ; time += minInterval) {

            boolean moreThan0UnfinishedGenerator = false;
            for (GeneratorCars generator : generatorFlowsCars) {
                boolean hasNext = generator.hasNextCar();
                moreThan0UnfinishedGenerator |= hasNext;

                if (hasNext) {
                    generator.markNextCarIfPossible(footprintsSpaceTime, time);
                }
            }

            if (!moreThan0UnfinishedGenerator) {
                break;
            }
        }
    }
    
    private double minIntervalGenerators() {
        double minInterval = Double.MAX_VALUE;

        for (GeneratorCars generator : this.generatorFlowsCars) {
            if (generator.getInterval() < minInterval) {
                minInterval = generator.getInterval();
            }
        }

        return minInterval;
    }

    private double earliestStartTime() {
        double earliestStartTime = Double.MAX_VALUE;

        for (GeneratorCars generator : this.generatorFlowsCars) {
            if (generator.getStartTime() < earliestStartTime) {
                earliestStartTime = generator.getStartTime();
            }
        }

        return earliestStartTime;
    }
}
