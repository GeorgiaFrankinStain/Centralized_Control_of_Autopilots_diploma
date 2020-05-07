package Logic.FootprintSpaceTime;

import Logic.Landscape.ZonaLandscape;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.Path;
import Logic.Position;
import Logic.TypesInLevel;

import java.util.List;

public interface FootprintSpaceTime {

    public List<Footprint> getRenderingFootprintsFromWhen(PolygonExtended areaFind, int time);

    public List<Footprint> getRenderingFootprintsFromWhen(PolygonExtended areaFind, int time, TypesInLevel type);


    public void addFootprint(
            int idTrack,
            MovingObject movingObject,
            Position position,
            int time,
            int multiplycitySecond
    );

    public void addFootprint(int idTrack, MovingObject movingObject, Path path, int startTime);

    public void addFootprint(int idTrack, MovingObject movingObject, Position position, int time);

    public void addFootprint(int idTrack, ZonaLandscape zonaLandscape);

    public void deleteFootprints(int ID);

    public boolean getAccessPlace(PolygonExtended place, int time, TypesInLevel type);

    /*TODO: влажные мечты: здесь можно сделать получение утромбованной точки размещения полигона и узнавание важности точки*/

    //TODO: critical получить собирательный образ среды (собирательные свойства, влияющих слоев; свойства эти будут переданые в общем виде, ибо мы не знаем на какие расстояния их ПЛАНИРУЮТ (этим и занимаются алгоритмы) вычиывать)

}
