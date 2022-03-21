package com.alamutra.ccoa.Core.Logic.FootprintSpaceTime;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.alamutra.ccoa.Core.Logic.MovingBody.ParametersMovingUnique;
import com.alamutra.ccoa.Core.Logic.MovingBody.PathCCoA;
import com.alamutra.ccoa.Core.Logic.Position;

import java.util.List;

public interface LayerFootprintSpaceTime {

    public List<Footprint> getRenderingFootprintsFromWhen(PolygonCCoA areaFind, double time);


    public void addFootprintsPath(
            ParametersMovingUnique parametersMovingUnique,
            PathCCoA pathCCoA,
            double startTime,
            Route route
    ) throws СrashIntoAnImpassableObjectException;


    public void addFootprintsPathWithoutEndStandingUntilEndTime(
            ParametersMovingUnique parametersMovingUnique,
            PathCCoA pathCCoA,
            double startTime,
            Route route
    ) throws СrashIntoAnImpassableObjectException;

    public void addFootprint(
            ParametersMovingUnique parametersMovingUnique,
            Position position,
            double time,
            double timeStanding
    ) throws СrashIntoAnImpassableObjectException;

    public void addFootprint(
            Footprint footprint,
            double time
    ) throws СrashIntoAnImpassableObjectException;


    public void deleteFootprints(int ID);


    public boolean getIsSeatTaken(PolygonCCoA place, double time);

    public Position getPosition(ParametersMovingUnique parametersMovingUniqueWithID, double time);

    public Double getAverageTimeMovingToNextPointOfPath();

    public Double totalTimeAllMoving();

    public Double getTimeAddingLastFootprints();

    public boolean isPathMovingObjectEnteringCorridor(ParametersMovingUnique parametersMovingUnique, Corridor corridor);
}
