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
    final public static double MAX_TIME_STANDING = Double.MAX_VALUE * 0.95;

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

        boolean placeIsSeat = this.getIsSeatTaken(newFootprint.getOccupiedLocation(), time, null);

        if (!placeIsSeat) {
            storageAllFootprints.put(time, newFootprint);
        } else {
            throw new СrashIntoAnImpassableObstacleExeption();
        }
    }


    @Override
    public void addFootprint(int idTrack, MovingObject movingObject, Path path, double startTime) throws СrashIntoAnImpassableObstacleExeption {

        Point vertorMarginRouteApplication = new PointClass(0, 0); //detection point route application at polygonExtender //FIXME find central point

        double timeAdding = startTime;

        /**
         * Landscape have getResistancePowerLandscape(pressurePaskaleOfMachine)
         * speed = MachinePower / ResistancePower
         */
        double speed = movingObject.getSpeed(); //FIXME MAGIC NUMBER //FIXME IMITATION
        double lengthStep = movingObject.getLength();
        double timeStanding;
        if (Math.abs(speed) < 0.00000000001) { //FIXME MAGIC_NUMBER //FIXME == 0 error divide on speed res > double max number
            timeStanding = this.MAX_TIME_STANDING; //FIXME MAGIC_NUMBER
        } else {
            timeStanding = lengthStep / speed;
        }

        //FIXME REFACTORING
        //processing 1 point
        if (path.getSize() == 0) {
            assert (false);
        } else if (path.getSize() == 1) {
            //FIXME add standing one the site of the machine
            Position position = new PositionClass(path.getPoint(0), 0.0);
            this.addFootprint(
                    idTrack,
                    movingObject,
                    position,
                    timeAdding,
                    this.MAX_TIME_STANDING
            );
        } else {
            int endIndex = path.getSize() - 1;
            for (int i = 0; i < endIndex; i++) {
                Point startLine = path.getPoint(i);
                Point endLine = path.getPoint(i + 1);

                timeAdding += printEveryStepOnLine(
                        startLine,
                        endLine,
                        idTrack,
                        movingObject,
                        timeStanding,
                        timeAdding
                );

            }

            /*
             * number this is number interation cicle up
             * number this is point
             * -- this is time standing (Length equal length movingObject)
             * - this is lastLittleStep (last Little Time Standing)
             * 0--0--0--0-1
             *            |
             *            |
             *            1
             *            |
             *            |
             *            1
             *            |
             *            2--2--2--2--2-3
             *                          ^
             *               end point in endless
             *
             */
            int indexLastPoint = path.getSize() - 1;
            Point startLine = path.getPoint(indexLastPoint - 1);
            Point endlessPoint = path.getPoint(indexLastPoint);
            double angleStepVector = endlessPoint.getAngleRotareRelative(startLine); //FIXME dublication (LINK_RletVeVp)
            Position position = new PositionClass(endlessPoint, angleStepVector);
            this.addFootprint(
                    idTrack,
                    movingObject,
                    position,
                    timeAdding,
                    this.MAX_TIME_STANDING
            );
        }
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
    public List<Footprint> getRenderingFootprintsFromWhen(PolygonExtended areaVizibility, double timeFind) { //FIXME ADD_TEST

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

            boolean footprintIndcludeFindTimePoint = startStanding < timeFind && timeFind < endStanding;
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


    //==== <start> <Private_Methods> =======================================================================

    private boolean stepUnderTest(Point startLine, Point endLine, Point stepUnderTest) {
        int quarterStartLine = startLine.getQuarter(endLine);
        int quarterstepUnderTest = stepUnderTest.getQuarter(endLine);

        return quarterStartLine == quarterstepUnderTest;
    }

    private Point stepVector(Point endLine, Point startLine, double lengthStep) {
        Point origin = new PointClass(0, 0);
        double angleStepVector = endLine.getAngleRotareRelative(startLine); //FIXME dublication (LINK_RletVeVp)
        return new PointClass(lengthStep, 0).getRotareRelative(origin, angleStepVector); //FIXME MAGIC NUMBER
    }

    private int printEveryStepOnLine(
            Point startLine,
            Point endLine,
            int idTrack,
            MovingObject movingObject,
            double standingTime,
            double timeAdding
    ) throws СrashIntoAnImpassableObstacleExeption {

        double angle = endLine.getAngleRotareRelative(startLine);
        double timeSum = 0;

        //determination time and size of step //FIXME (until every second (LINK_RzRGrmTH), and on good need to would in half lenght)
        //V = 10 px/sec //FIXME //    determination time insert (start + speed of passing throught the landscape)


        Point currentCoordinat = startLine.clone();
        Point stepVector = stepVector(endLine, startLine, movingObject.getLength());

        double angleStepVector = endLine.getAngleRotareRelative(startLine); //FIXME dublication (LINK_RletVeVp)

        double lengthStep = stepVector.getLengthVector();
        double lengthStraightPath = endLine.getDistanceToPoint(startLine);
        int counterMaxSteps = (int) (lengthStraightPath / lengthStep);
//        counterMaxSteps++; //

        for (int i = 0; (i < counterMaxSteps) && this.stepUnderTest(startLine, endLine, currentCoordinat); i++) {

            Position position = new PositionClass(currentCoordinat, angleStepVector);
            //add Footpint
            this.addFootprint(
                    idTrack,
                    movingObject,
                    position,
                    timeAdding,
                    standingTime
            );


            timeAdding += standingTime;
            timeSum += standingTime;


            currentCoordinat = new PointClass(
                    currentCoordinat.getX() + stepVector.getX(),
                    currentCoordinat.getY() + stepVector.getY()
            );

        }

        if (!endLine.equals(currentCoordinat)) {

            /*
             * |--|--|-
             *        ^
             * little step*/
            Point penultimatePoint = currentCoordinat;
            double lengthFinalStep = endLine.getDistanceToPoint(penultimatePoint);
            standingTime = lengthFinalStep / movingObject.getSpeed();

            Position position = new PositionClass(penultimatePoint, angleStepVector);
            timeSum += standingTime;
            this.addFootprint(
                    idTrack,
                    movingObject,
                    position,
                    timeAdding,
                    standingTime
            );
        }



        return (int) timeSum;
    }


    //==== <end> <Private_Methods> =========================================================================
}
