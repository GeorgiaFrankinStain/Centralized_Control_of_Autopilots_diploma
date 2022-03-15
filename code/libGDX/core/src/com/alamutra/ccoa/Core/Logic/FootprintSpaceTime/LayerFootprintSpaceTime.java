package com.alamutra.ccoa.Core.Logic.FootprintSpaceTime;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectExeption;
import com.alamutra.ccoa.Core.Logic.MovingBody.ParametersMoving;
import com.alamutra.ccoa.Core.Logic.MovingBody.PathCCoA;
import com.alamutra.ccoa.Core.Logic.Position;

import java.util.List;

public interface LayerFootprintSpaceTime {

    public List<Footprint> getRenderingFootprintsFromWhen(PolygonCCoA areaFind, double time);


    public void addFootprintsPath(
            ParametersMoving parametersMoving,
            PathCCoA pathCCoA,
            double startTime,
            Route route
    ) throws СrashIntoAnImpassableObjectExeption;


    public void addFootprintsPathWithoutEndStandingUntilEndTime(
            ParametersMoving parametersMoving,
            PathCCoA pathCCoA,
            double startTime,
            Route route
    ) throws СrashIntoAnImpassableObjectExeption;



    public void addFootprint(
            ParametersMoving parametersMoving,
            Position position,
            double time,
            double timeStanding
    ) throws СrashIntoAnImpassableObjectExeption;

    public void addFootprint(
            Footprint footprint,
            double time
    ) throws СrashIntoAnImpassableObjectExeption;

    public void deleteFootprints(int ID);


    public boolean getIsSeatTaken(PolygonCCoA place, double time);

    public Position getPosition(ParametersMoving parametersMovingWithID, double time);

    public Double getAverageTimeMovingToNextPointOfPath();

    public Double totalTimeAllMoving();

    public Double getTimeAddingLastFootprints();

    public boolean isPathMovingObjectEnteringCorridor(ParametersMoving parametersMoving, Corridor corridor);
}
