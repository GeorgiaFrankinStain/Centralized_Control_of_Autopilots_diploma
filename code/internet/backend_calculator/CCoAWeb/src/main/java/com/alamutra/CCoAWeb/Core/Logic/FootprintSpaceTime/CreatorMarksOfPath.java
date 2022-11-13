package com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime;

import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.alamutra.CCoAWeb.Core.Logic.MovingBody.PathCCoA;

public interface CreatorMarksOfPath {
    public void addFootprint(
            PathCCoA pathCCoA,
            double startTime
    ) throws СrashIntoAnImpassableObjectException;
}
