package Logic.FootprintSpaceTime;

import Logic.Landscape.ZonaLandscape;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.Path;
import Logic.PathsMachines.PositionClass;
import GUI.StatementTaskRendering.HistChangesFromWhen;
import Logic.Landscape.Landscape;
import Logic.Position;
import Logic.TypesInLevel;

import java.util.*;

public class FootprintSpaceTimeClass implements FootprintSpaceTime, HistChangesFromWhen {
    private Map<Integer, List<Footprint>> storage = new TreeMap<Integer, List<Footprint>>();
    List<MovingObject> imitationLadnscape = new ArrayList<MovingObject>(); //FIXME IMITATION Landscape
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
    public void addFootprint(int idTrack, MovingObject movingObject, Position position, int time) {


        if (!storage.containsKey(time)) {
            storage.put(time, new ArrayList<Footprint>());
        }


        storage.get(time).add(new FootprintClass(idTrack, position, movingObject));
    }


    @Override
    public void addFootprint(int idTrack, ZonaLandscape zonaLandscape, Position position) {

    }

    @Override
    public void addFootprint(int idTrack, MovingObject movingObject, Path path, int startTime) {

        Point vertorMarginRouteApplication = new PointClass(0, 0); //detection point route application at polygonExtender //FIXME find central point

        int timeAdding = startTime;

        /**
         * Landscape have getResistancePowerLandscape(pressurePaskaleOfMachine)
         * speed = MachinePower / ResistancePower
         */
        int speed = 10; //FIXME MAGIC NUMBER //FIXME IMITATION
        int currentMultiplicityStep = 1;
        double lengthStep = currentMultiplicityStep * speed;


        //processing 1 point
        if (path.getSize() == 0) {
            assert (false);
        } else if (path.getSize() == 1) {
            //FIXME add standing one the site of the machine
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
                        currentMultiplicityStep,
                        timeAdding
                );
            }
        }
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
    public List<Footprint> getRenderingFootprintsFromWhen(PolygonExtended areaVizibility, int time) {

        //FIXME take DataFootprintForRendering from the landscape


        //iteration all polygons
        //    add in resList, if intersection with areaVizibility

/*      program min:
            return a list of all polygons from a intersection table with areaVizibility (so far everything
                    is stored in a single table)


        program max:
            return a list of changed polygons from a intersection table with areaVizibility*/
        ArrayList newArrayList = (ArrayList) this.imitationLadnscape;
        //TODO add interpolation (LINK_RzRGrmTH)
        List<Footprint> resRendringFootpring = new ArrayList<Footprint>();
        for (Footprint current : storage.get(time)) {
            resRendringFootpring.add(current);
        }


        return resRendringFootpring;
    }

    @Override
    public List<Footprint> getRenderingFootprintsFromWhen(PolygonExtended areaVizibility, int time, TypesInLevel type) {
        return null;
    }

    @Override
    public void addFootprint(int idTrack, MovingObject movingObject, Position position, int time, int multiplycitySecond) {
        //TODO
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
    private Point stepVector(Point endLine, Point startLine) {
        Point origin = new PointClass(0, 0);
        int timeStep = 1; //FIXME
        double angleStepVector = endLine.getAngleRotareRelative(startLine); //FIXME dublication (LINK_RletVeVp)
        return new PointClass(10, 0).getRotareRelative(origin, angleStepVector); //FIXME MAGIC NUMBER
    }
    private int printEveryStepOnLine(
            Point startLine,
            Point endLine,
            int idTrack,
            MovingObject movingObject,
            int currentMultiplicityStep,
            int timeAdding
    ) {

        double angle = endLine.getAngleRotareRelative(startLine);
        double timeSum = 0;

        //determination time and size of step //FIXME (until every second (LINK_RzRGrmTH), and on good need to would in half lenght)
        //V = 10 px/sec //FIXME //    determination time insert (start + speed of passing throught the landscape)


        Point currentCoordinat = startLine.clone();
        Point stepVector = stepVector(endLine, startLine);

        double angleStepVector = endLine.getAngleRotareRelative(startLine); //FIXME dublication (LINK_RletVeVp)

        do {
            Position position = new PositionClass(currentCoordinat, angleStepVector);
            //add Footpint
            this.addFootprint(
                    idTrack,
                    movingObject,
                    position,
                    timeAdding
            );



            timeAdding += currentMultiplicityStep;
            timeSum += currentMultiplicityStep;



            currentCoordinat = new PointClass(
                    currentCoordinat.getX() + stepVector.getX(),
                    currentCoordinat.getY() + stepVector.getY()
            );
        } while (this.stepUnderTest(startLine, endLine, currentCoordinat));

        return (int) timeSum;
    }
    //==== <end> <Private_Methods> =========================================================================
}
