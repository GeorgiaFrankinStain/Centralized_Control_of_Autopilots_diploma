package Logic.FootprintSpaceTime;

import Logic.TypesInLevel;

import java.util.List;

public interface FootprintSpaceTime {
    public List<Polygon> getAreaFromWhen(Polygon areaFind, long when);
    public List<Polygon> getAreaFromWhen(Polygon areaFind, long when, TypesInLevel type);

    public void addPointRadius(int ID, Polygon Place, long time);
    public void addPointRadius(int ID, Polygon Place);
    public void deleteFootprints(int ID);

    public boolean getAccessPlace(Polygon place, long time, TypesInLevel type);

    /*TODO: влажные мечты: здесь можно сделать получение утромбованной точки размещения полигона и узнавание важности точки*/

    //TODO: critical получить собирательный образ среды (собирательные свойства, влияющих слоев; свойства эти будут переданые в общем виде, ибо мы не знаем на какие расстояния их ПЛАНИРУЮТ (этим и занимаются алгоритмы) вычиывать)

}
