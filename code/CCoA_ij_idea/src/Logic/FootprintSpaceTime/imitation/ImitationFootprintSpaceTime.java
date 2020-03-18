package Logic.FootprintSpaceTime.imitation;

import Logic.FootprintSpaceTime.FootprintSpaceTime;
import Logic.FootprintSpaceTime.Point;
import Logic.FootprintSpaceTime.Polygon;

import java.util.List;

public class ImitationFootprintSpaceTime implements FootprintSpaceTime {
    @Override
    public List<Polygon> getAreaFromWhen(Polygon areaFind, long when) { //используется рендерингом, тут бы желательно оберзать большие зоны;


        //венуть список всех полигонов из смежных областей (итератор)
        //  определение областей, зацепленных полигоном
        //  вернуть все полигоны из зацепленных областей
        //пересечение полигонов
        //возвращаем список всех пересекающихся полигонов


        return null;
    }




    //TODO: add more difficult determitaion the level (https://habr.com/ru/post/122919/)//TODO: return id of poligons returned getAreaFromWhen  используется выделителем юнитов, тут не требуется возвращать полигоны, можно просто айдишники вернуть
    @Override
    public List<Polygon> getAreaFromWhen(Polygon areaFind, long when, int level) {

        return null;

    }

    //TODO: return id of poligons returned getAreaFromWhen  используется выделителем юнитов, тут не требуется возвращать полигоны, можно просто айдишники вернуть

    @Override
    public void addPointRadius(int ID, Polygon Place, long time) {

    }

    @Override
    public void addPointRadius(int ID, Polygon Place) {

    }

    @Override
    public void deleteFootprints(int ID) {

    }

    @Override
    public boolean getAccessPlace(Polygon place, long time, int level) {
        return false;
    }
}
