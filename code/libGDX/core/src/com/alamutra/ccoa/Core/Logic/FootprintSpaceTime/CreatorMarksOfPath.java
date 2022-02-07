package com.alamutra.ccoa.Core.Logic.FootprintSpaceTime;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectExeption;
import com.alamutra.ccoa.Core.Logic.MovingObjects.PathCCoA;

public interface CreatorMarksOfPath {
    public void addFootprint(
            PathCCoA pathCCoA,
            double startTime
    ) throws СrashIntoAnImpassableObjectExeption;
}
