package Logic.FootprintSpaceTime;

import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObstacleExeption;
import Logic.Landscape.Landscape;
import Logic.LevelLayer;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.Path;
import Logic.Position;
import Logic.TypesInLevel;

import java.util.List;

public interface FootprintsSpaceTime {

    public List<Footprint> getRenderingFootprintsFromWhen(
            PolygonExtended areaFind,
            double time,
            LevelLayer levelLayer
    );

    public void addFootprint(
            int idTrack,
            MovingObject movingObject,
            Path path,
            double startTime,
            LevelLayer levelLayer
    ) throws СrashIntoAnImpassableObstacleExeption;

    public void addFootprint(
            Footprint footprint,
            double time,
            LevelLayer levelLayer
    ) throws СrashIntoAnImpassableObstacleExeption;

    public void deleteFootprints(int ID);

    public boolean getIsSeatTaken(PolygonExtended place, double time, LevelLayer levelLayer);

    public boolean getIsSeatTakenSpaceTime(
            PolygonExtended place,
            double fromTime,
            double toTime,
            LevelLayer levelLayer
    );

    public Double averageTimeMovingToNextPointOfPath();

    public Double getTimeAddingLastFootprints(LevelLayer levelLayer);

    public Position getPositionInDefaultLevel(int ID, double time);





    /*TODO: влажные мечты: здесь можно сделать получение утромбованной точки размещения полигона и узнавание важности точки*/

    //TODO: critical получить собирательный образ среды (собирательные свойства, влияющих слоев; свойства эти будут переданые в общем виде, ибо мы не знаем на какие расстояния их ПЛАНИРУЮТ (этим и занимаются алгоритмы) вычиывать)

}