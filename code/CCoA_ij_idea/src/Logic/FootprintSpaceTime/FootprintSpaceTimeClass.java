package Logic.FootprintSpaceTime;

import GUI.StatementTaskRendering.HistChangesFromWhen;
import GUI.StatementTaskRendering.PhisicalBodysFromWhen;
import Logic.Landscape.Landscape;
import Logic.TypesInLevel;

import java.util.List;

public class FootprintSpaceTimeClass implements FootprintSpaceTime, PhisicalBodysFromWhen, HistChangesFromWhen {

    List<PolygonExtended> xranilishe; //STACK_NOW
    Landscape onlyLandscape;


    public FootprintSpaceTimeClass(Landscape onlyLandscape) {
        this.onlyLandscape = onlyLandscape;
    }

    @Override
    public List<PolygonExtended> getAreaFromWhen(PolygonExtended areaFind, int  when) { //используется рендерингом, тут бы желательно оберзать большие зоны; рендеринг сам обрежет большие зоны


        //венуть список всех полигонов из смежных областей (итератор)
        //  определение областей, зацепленных полигоном
        //  вернуть все полигоны из зацепленных областей
        //пересечение полигонов
        //возвращаем список всех пересекающихся полигонов


        return null;
    }




    //TODO: add more difficult determitaion the level (https://habr.com/ru/post/122919/)
    //TODO: return id of poligons returned getAreaFromWhen  используется выделителем юнитов, тут не требуется возвращать полигоны, можно просто айдишники вернуть

    @Override
    public List<PolygonExtended> getAreaFromWhen(PolygonExtended areaFind, int  when, TypesInLevel type) {
        return null;
    }

    @Override
    public void addPointRadius(int ID, PolygonExtended Place, int  time) {

    }

    @Override
    public void addPointRadius(int ID, PolygonExtended Place) {

    }

    @Override
    public void deleteFootprints(int ID) {

    }

    @Override
    public boolean getAccessPlace(PolygonExtended place, int  time, TypesInLevel type) {
        return false;
    }

    @Override
    public List<PhisicalBody> getRenderingPolygonsFromWhen(PolygonExtended areaFind, int  when) {
        return null;
    }

    @Override
    public List<PhisicalBody> getRenderingPolygonsFromWhen(PolygonExtended areaFind, int  when, TypesInLevel type) {
        return null;
    }

    @Override
    public PolygonExtended getAreaChangesAfterBefore(int  afterTime, int  berforeTime) {
        return null;
    }

    @Override
    public int  getTimeLastUpdate() {
        return 0;
    }
}
