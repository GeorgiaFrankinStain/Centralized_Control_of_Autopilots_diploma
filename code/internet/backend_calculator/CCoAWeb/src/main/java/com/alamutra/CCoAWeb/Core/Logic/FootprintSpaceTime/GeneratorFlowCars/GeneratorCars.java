package com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.GeneratorFlowCars;

import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.FootprintsSpaceTime;

public interface GeneratorCars {

    public boolean hasNextCar();

    public void markNextCarIfPossible(FootprintsSpaceTime footprintsSpaceTime, double currentTime);

    public double getInterval();

    public double getStartTime();
}
