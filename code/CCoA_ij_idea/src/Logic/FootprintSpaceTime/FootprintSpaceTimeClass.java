package Logic.FootprintSpaceTime;

import GUI.StatementTaskRendering.HistChangesFromWhen;
import Logic.Landscape.Landscape;
import Logic.TypesInLevel;

import java.util.List;

public class FootprintSpaceTimeClass implements FootprintSpaceTime, HistChangesFromWhen {

    List<PolygonExtended> xranilishe; //STACK_NOW
    Landscape onlyLandscape;


    public FootprintSpaceTimeClass(Landscape onlyLandscape) {
        this.onlyLandscape = onlyLandscape;
    }


    @Override
    public void addPointRadius(int ID, PolygonExtended Place, int time) {

    }

    @Override
    public void addPointRadius(int ID, PolygonExtended Place) {

    }

    @Override
    public void deleteFootprints(int ID) {

    }

    @Override
    public boolean getAccessPlace(PolygonExtended place, int time, TypesInLevel type) {
        return false;
    }


    //TODO: add more difficult determitaion the level (https://habr.com/ru/post/122919/)
    //TODO: return id of poligons returned getAreaFromWhen  используется выделителем юнитов, тут не требуется возвращать полигоны, можно просто айдишники вернуть
    @Override
    public List<PhisicalBody> getPhisicalBodysFromWhen(PolygonExtended areaVizibility, int when) {

        //FIXME take PhisicalBodys from the landscape


        //iteration all polygons
        //    add in resList, if intersection with areaVizibility

/*      program min:
            return a list of all intersection polygons (iterate throught all polygons)

        program max:
            return a list of all PhisicalBodys from adjacent areas
                return all PhisicalBodys from determination square areas (hash tables), cling polygon
            return a list of all intersection polygons*/
        return null;
    }

    @Override
    public List<PhisicalBody> getPhisicalBodysFromWhen(PolygonExtended areaVizibility, int when, TypesInLevel type) {
        return null;
    }

    @Override
    public PolygonExtended getAreaChangesAfterBefore(int afterTime, int berforeTime) {
        return null;
    }

    @Override
    public int getTimeLastUpdate() {
        return 0;
    }


    //==== <start> <Private_Methods> =======================================================================

    //==== <end> <Private_Methods> =========================================================================
}
