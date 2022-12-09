package com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime;

import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.alamutra.CCoAWeb.Core.ModelLogic.MovingBody.PathCCoA;

public interface CreatorMarksOfPath {
    public void addFootprint(
            PathCCoA pathCCoA,
            double startTime
    ) throws СrashIntoAnImpassableObjectException;
}
