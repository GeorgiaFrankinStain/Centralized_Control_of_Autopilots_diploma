package Logic.FootprintSpaceTime;

import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObstacleExeption;
import Logic.Landscape.Landscape;
import Logic.LevelLayer;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.Path;
import Logic.Position;
import Logic.TypesInLevel;

import java.util.List;

public interface LayerFootprintSpaceTime {

    public List<Footprint> getRenderingFootprintsFromWhen(PolygonExtended areaFind, double time);


    public void addFootprint(
            int idTrack,
            MovingObject movingObject,
            Path path,
            double startTime
    ) throws СrashIntoAnImpassableObstacleExeption;

    public void addFootprint(
            int idTrack,
            MovingObject movingObject,
            Position position,
            double time,
            double timeStanding
    ) throws СrashIntoAnImpassableObstacleExeption;

    public void addFootprint(
            Footprint footprint,
            double time
    ) throws СrashIntoAnImpassableObstacleExeption;

    public void deleteFootprints(int ID);


    public boolean getIsSeatTaken(PolygonExtended place, double time);

    public Position getPosition(int ID, double time);

    public Double getAverageTimeMovingToNextPointOfPath();

    public Double getTimeAddingLastFootprints();
}
