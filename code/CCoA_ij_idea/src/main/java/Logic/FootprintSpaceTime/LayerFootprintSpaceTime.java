package Logic.FootprintSpaceTime;

import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.Path;
import Logic.Position;

import java.util.List;

public interface LayerFootprintSpaceTime {

    public List<Footprint> getRenderingFootprintsFromWhen(PolygonExtended areaFind, double time);


    public void addFootprint(
            int idTrack,
            MovingObject movingObject,
            Path path,
            double startTime
    ) throws СrashIntoAnImpassableObjectExeption;

    public void addFootprint(
            int idTrack,
            MovingObject movingObject,
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

    public Position getPosition(MovingObject movingObjectWithID, double time);

    public Double getAverageTimeMovingToNextPointOfPath();

    public Double totalTimeAllMoving();

    public Double getTimeAddingLastFootprints();

    public boolean isPathMovingObjectEnteringCorridor(MovingObject movingObject, Corridor corridor);
}
