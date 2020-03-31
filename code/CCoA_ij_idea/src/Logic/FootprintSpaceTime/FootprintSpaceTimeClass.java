package Logic.FootprintSpaceTime;

import Logic.PhisicalBody;
import GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingFootprint;
import GUI.StatementTaskRendering.HistChangesFromWhen;
import Logic.Landscape.Landscape;
import Logic.Position;
import Logic.TypesInLevel;

import java.util.*;

public class FootprintSpaceTimeClass implements FootprintSpaceTime, HistChangesFromWhen {
    private Map<Integer, List<Footprint>> storage = new TreeMap<Integer, List<Footprint>>();
    List<PhisicalBody> imitationLadnscape = new ArrayList<PhisicalBody>(); //FIXME IMITATION Landscape
/*
    program min:
        pollygons
    program max:
        rounds
 */

    Landscape onlyLandscape;


    public FootprintSpaceTimeClass(Landscape onlyLandscape) {
        this.onlyLandscape = onlyLandscape;
    }


    @Override
    public void addFootprint(int idTrack, PhisicalBody movingObject, Position position, int time) {


        if (!storage.containsKey(time)) {
            storage.put(time, new ArrayList<Footprint>());
        }


        storage.get(time).add(new FootprintClass(idTrack, position, movingObject));
    }




    @Override
    public void addFootprint(int idTrack, PhisicalBody movingObject, Position position) {

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
    public List<RenderingFootprint> getRenderingFootprintsFromWhen(PolygonExtended areaVizibility, int time) {

        //FIXME take PhisicalBodys from the landscape


        //iteration all polygons
        //    add in resList, if intersection with areaVizibility

/*      program min:
            return a list of all polygons from a intersection table with areaVizibility (so far everything
                    is stored in a single table)


        program max:
            return a list of changed polygons from a intersection table with areaVizibility*/
        ArrayList newArrayList = (ArrayList) this.imitationLadnscape;

//        List<RenderingFootprint> cloneLandscape = (List<RenderingFootprint>) newArrayList.clone();
        List<RenderingFootprint> resRendringFootpring = new ArrayList<RenderingFootprint>();
        for (Footprint current : storage.get(time)) {
            resRendringFootpring.add((RenderingFootprint) current);
        }

        return resRendringFootpring;
    }

    @Override
    public List<RenderingFootprint> getRenderingFootprintsFromWhen(PolygonExtended areaVizibility, int time, TypesInLevel type) {
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
