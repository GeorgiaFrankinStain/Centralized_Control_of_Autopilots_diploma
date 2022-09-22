package com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.GeneratorFlowCars;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.FootprintsSpaceTime;

public interface GeneratorCars {

    public boolean hasNextCar();

    public void markNextCarIfPossible(FootprintsSpaceTime footprintsSpaceTime, double currentTime);

    public double getInterval();

    public double getStartTime();
}
