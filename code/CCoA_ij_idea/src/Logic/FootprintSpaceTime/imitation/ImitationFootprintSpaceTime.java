package Logic.FootprintSpaceTime.imitation;

import Logic.FootprintSpaceTime.FootprintSpaceTime;
import Logic.FootprintSpaceTime.Point;
import Logic.FootprintSpaceTime.Polygon;
import Logic.TypesInLevel;

import java.util.List;

public class ImitationFootprintSpaceTime implements FootprintSpaceTime {
    @Override
    public List<Polygon> getAreaFromWhen(Polygon areaFind, long when) { return null;}
    @Override
    public List<Polygon> getAreaFromWhen(Polygon areaFind, long when,  TypesInLevel type) {

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
    public boolean getAccessPlace(Polygon place, long time,  TypesInLevel type) {
        return false;
    }
}
