package Logic.FootprintSpaceTime;

import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObstacleExeption;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.Path;
import Logic.PathsMachines.PositionClass;
import GUI.StatementTaskRendering.HistChangesFromWhen;
import Logic.Landscape.Landscape;
import Logic.Position;
import Logic.TypesInLevel;
import Wrapper.EntryPair;
import Wrapper.MyMultiMap;
import Wrapper.MyMultiMapTree;

import java.util.*;

public class FootprintsSpaceTimeClass implements FootprintsSpaceTime, HistChangesFromWhen {


    private MyMultiMap<Double, Footprint> storageAllFootprints =
            new MyMultiMapTree<Double, Footprint>();


//    ListMultimap<Double, Footprint> storageAllFootprints = ArrayListMultimap.create();


    //    private Map<Double, List<Footprint>> storageAllFootprints = new TreeMap<Double, List<Footprint>>();
//    private List<MovingObject> imitationLadnscape = new ArrayList<MovingObject>(); //FIXME IMITATION Landscape
    private Landscape onlyLandscape;
/*
    program min:
        pollygons
    program max:
        rounds
 */


    public FootprintsSpaceTimeClass(Landscape onlyLandscape) {
        this.onlyLandscape = onlyLandscape;
    }


    @Override
    public void addFootprint(
            int idTrack,
            MovingObject movingObject,
            Position position,
            double time,
            double timeStanding
    ) throws СrashIntoAnImpassableObstacleExeption { //FIXME ADD_TEST


        Footprint newFootprint = new FootprintClass(idTrack, position, timeStanding, movingObject);

        this.addFootprint(newFootprint, time);
    }

    @Override
    public void addFootprint(Footprint footprint, double time) throws СrashIntoAnImpassableObstacleExeption {

        boolean placeIsSeat = this.getIsSeatTaken(footprint.getOccupiedLocation(), time, null);

        if (!placeIsSeat) {
            storageAllFootprints.put(time, footprint);
        } else {
            throw new СrashIntoAnImpassableObstacleExeption();
        }
    }


    @Override
    public void addFootprint(int idTrack, MovingObject movingObject, Path path, double startTime) throws СrashIntoAnImpassableObstacleExeption {
        CreatorMarksOfMovingObject creatorMarksOfMovingObject =
                new CreatorMarksOfMovingObjectClass(this, idTrack, movingObject);
        creatorMarksOfMovingObject.addFootprint(path, startTime);
    }

    @Override
    public void deleteFootprints(int ID) {
        //FIXME
    }

    @Override
    public boolean getIsSeatTaken(PolygonExtended place, double testedTime, TypesInLevel type) { //FIXME ADD_TEST

//FIXME NOW
        Iterator<EntryPair<Double, Footprint>> iteratorEntryPair = storageAllFootprints.iteratorEntryPair();
        while (iteratorEntryPair.hasNext()) {
            EntryPair<Double, Footprint> entry = iteratorEntryPair.next();
            Footprint footprint = entry.getValue();
            double timeStandingStart = entry.getKey();
            double timeStandingEnd = timeStandingStart + footprint.getTimeStanding();
            boolean timeStandingIncludeTestedTime = timeStandingStart < testedTime && testedTime < timeStandingEnd;

            if (timeStandingIncludeTestedTime) {
                PolygonExtended locationMovingObject = footprint.getOccupiedLocation();
                boolean placeIsSeat = place.intersectionPolygon(locationMovingObject);
                if (placeIsSeat) {
                    return true;
                }
            }

        }


        return false;
    }

    @Override
    public Position getPosition(int ID, double time) {
//FIXME NOW
        Iterator<EntryPair<Double, Footprint>> iteratorEntryPair = storageAllFootprints.iteratorEntryPair();
        while (iteratorEntryPair.hasNext()) {
            EntryPair<Double, Footprint> entry = iteratorEntryPair.next();
            Footprint currentFootprint = entry.getValue();
            double startStanding = entry.getKey();
            double endStanding = startStanding + currentFootprint.getTimeStanding();
            boolean timeStandingIncludeFindTime = startStanding <= time && time < endStanding;
            boolean isFindObject = currentFootprint.getIdObject() == ID && timeStandingIncludeFindTime;
            if (isFindObject) {
                return currentFootprint.getPosition();
            }
        }

        return null;
    }

    @Override
    public Landscape getLandscape() {
        return this.onlyLandscape;
    }


    //TODO: add more difficult determitaion the level (https://habr.com/ru/post/122919/)
    //TODO: return id of poligons returned getAreaFromWhen  используется выделителем юнитов, тут не требуется возвращать полигоны, можно просто айдишники вернуть
    @Override
    public List<Footprint> getRenderingFootprintsFromWhen(PolygonExtended areaVizibility, double timeFind) {

        //FIXME take DataFootprintForRendering from the landscape


        //iteration all polygons
        //    add in resList, if intersection with areaVizibility

/*      program min:
            return a list of all polygons from a intersection table with areaVizibility (so far everything
                    is stored in a single table)


        program max:
            return a list of changed polygons from a intersection table with areaVizibility*/
//        ArrayList newArrayList = (ArrayList) this.imitationLadnscape;
        //TODO add interpolation (LINK_RzRGrmTH)

        List<Footprint> resRendringFootpring = new ArrayList<Footprint>();
        Iterator<EntryPair<Double, Footprint>> iteratorEntryPair = storageAllFootprints.iteratorEntryPair();
        while (iteratorEntryPair.hasNext()) {
            EntryPair<Double, Footprint> entry = iteratorEntryPair.next();

            Footprint currentFootprint = entry.getValue(); //FIXME NOW add test timeFind diapason intersection
            double timeStanding = currentFootprint.getTimeStanding();
            double startStanding = entry.getKey();
            double endStanding = startStanding + timeStanding;

            boolean footprintIndcludeFindTimePoint = startStanding <= timeFind && timeFind < endStanding;
            if (footprintIndcludeFindTimePoint) {
                resRendringFootpring.add(currentFootprint);
            }
        }


        return resRendringFootpring;
    }

    @Override
    public List<Footprint> getRenderingFootprintsFromWhen(PolygonExtended areaVizibility, double time, TypesInLevel type) {
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







}
