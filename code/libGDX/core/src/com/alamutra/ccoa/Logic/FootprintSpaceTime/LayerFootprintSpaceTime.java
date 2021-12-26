package com.alamutra.ccoa.Logic.FootprintSpaceTime;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectExeption;
import com.alamutra.ccoa.Logic.MovingObjects.ParametersMoving;
import com.alamutra.ccoa.Logic.MovingObjects.Path;
import com.alamutra.ccoa.Logic.Position;

import java.util.List;

public interface LayerFootprintSpaceTime {

    public List<Footprint> getRenderingFootprintsFromWhen(PolygonExtended areaFind, double time);


    public void addFootprint(
            ParametersMoving parametersMoving,
            Path path,
            double startTime
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


    public boolean getIsSeatTaken(PolygonExtended place, double time);

    public Position getPosition(ParametersMoving parametersMovingWithID, double time);

    public Double getAverageTimeMovingToNextPointOfPath();

    public Double totalTimeAllMoving();

    public Double getTimeAddingLastFootprints();

    public boolean isPathMovingObjectEnteringCorridor(ParametersMoving parametersMoving, Corridor corridor);
}
