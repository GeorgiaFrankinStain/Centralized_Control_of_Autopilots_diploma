package Logic.FootprintSpaceTime;

import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObstacleExeption;
import Logic.Landscape.Landscape;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.Path;
import Logic.Position;
import Logic.TypesInLevel;

import java.util.List;

public interface FootprintsSpaceTime {

    public List<Footprint> getRenderingFootprintsFromWhen(PolygonExtended areaFind, double time);

    public List<Footprint> getRenderingFootprintsFromWhen(PolygonExtended areaFind, double time, TypesInLevel type);


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


    public boolean getIsSeatTaken(PolygonExtended place, double time, TypesInLevel type);

    public Position getPosition(int ID, double time);

    public Landscape getLandscape();



    /*TODO: влажные мечты: здесь можно сделать получение утромбованной точки размещения полигона и узнавание важности точки*/

    //TODO: critical получить собирательный образ среды (собирательные свойства, влияющих слоев; свойства эти будут переданые в общем виде, ибо мы не знаем на какие расстояния их ПЛАНИРУЮТ (этим и занимаются алгоритмы) вычиывать)

}