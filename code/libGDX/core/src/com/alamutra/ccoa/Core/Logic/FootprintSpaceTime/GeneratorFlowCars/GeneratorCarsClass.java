package com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.GeneratorFlowCars;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.CreatorMarksOfPathClass;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.FootprintsSpaceTime;
import com.alamutra.ccoa.Core.Logic.IndexLayer;
import com.alamutra.ccoa.Core.Logic.MovingBody.ParametersMoving;
import com.alamutra.ccoa.Core.Logic.MovingBody.PathCCoA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GeneratorCarsClass implements GeneratorCars {
    private static final Logger LOGGER = LogManager.getLogger(GeneratorCarsClass.class);

    private ParametersMoving parametersMoving;
    private PathCCoA pathCCoA;
    private double interval;
    private double startTime;
    private double timeGeneration;
    private IndexLayer indexLayer;

    private int exceptCarNumber = -1;

    private Double nextStartInterval;
    private Double nextEndInterval;


    public GeneratorCarsClass(
            ParametersMoving parametersMoving,
            PathCCoA pathCCoA,
            double interval,
            double startTime,
            double timeGeneration,
            IndexLayer indexLayer
    ) {
        this.parametersMoving = parametersMoving;
        this.pathCCoA = pathCCoA;
        this.interval = interval;
        this.startTime = startTime;
        this.timeGeneration = timeGeneration;
        this.indexLayer = indexLayer;


        nextStartInterval = startTime;
        nextEndInterval = startTime + interval;
    }

    @Override
    public boolean hasNextCar() {
        return this.nextStartInterval != null;
    }

    @Override
    public void markNextCarIfPossible(FootprintsSpaceTime footprintsSpaceTime, double currentTime) {
        boolean isGeneratorAlreadyFinished = nextStartInterval == null;
        if (isGeneratorAlreadyFinished) {
            return;
        }


        boolean isNowTimeMark = nextStartInterval <= currentTime && currentTime < nextEndInterval;

        if (isNowTimeMark) {
            try {
                LOGGER.debug("generatorCarsClass markNextCarIfPossible nextStartInterval: {}", nextStartInterval);
                parametersMoving.markWithoutStandingUntilEndTime(
                        footprintsSpaceTime,
                        pathCCoA,
                        nextStartInterval,
                        indexLayer
                );
            } catch (СrashIntoAnImpassableObjectException e) {
                LOGGER.debug("generatorCarsClass markNextCarIfPossible СrashIntoAnImpassableObjectException");
                e.printStackTrace();
            }

            double endTime = startTime + timeGeneration;
            boolean isGeneratorFinished = endTime <= nextEndInterval;
            if (isGeneratorFinished) {
                nextStartInterval = null;
                nextEndInterval = null;
                return;
            }
        }

        nextStartInterval += interval;
        nextEndInterval += interval;
    }

    @Override
    public double getInterval() {
        return this.interval;
    }

    @Override
    public double getStartTime() {
        return this.startTime;
    }

    private int detectNumberInterval(double currentTime) {
        if (currentTime < startTime) {
            return -1;
        }

        double endTime = startTime + timeGeneration;
        int i = 0;
        for (double startIntervalTest = startTime; startIntervalTest < endTime; startIntervalTest += interval) {
            double endIntervalTest = startIntervalTest += interval;

            if (startIntervalTest <= currentTime && currentTime < endIntervalTest) {
                return i;
            }

            i++;
        }

        return -1;
    }
}
