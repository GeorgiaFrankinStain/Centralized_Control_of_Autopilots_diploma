package com.alamutra.ccoa.Logic.FootprintSpaceTime;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectExeption;
import com.alamutra.ccoa.Logic.MovingObjects.Path;

public interface CreatorMarksOfPath {
    public void addFootprint(
            Path path,
            double startTime
    ) throws СrashIntoAnImpassableObjectExeption;
}
