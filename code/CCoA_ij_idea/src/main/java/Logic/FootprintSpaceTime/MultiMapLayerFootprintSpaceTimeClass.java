package Logic.FootprintSpaceTime;

import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import Logic.GlobalVariable;
import Logic.MovingObjects.ParametersMoving;
import Logic.MovingObjects.Path;
import Logic.Position;
import Wrapper.EntryPair;
import Wrapper.MultiMap;
import Wrapper.MultiMapTree;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MultiMapLayerFootprintSpaceTimeClass implements LayerFootprintSpaceTime {


    private MultiMap<Double, Footprint> storageAllFootprints =
            new MultiMapTree<Double, Footprint>();


    public MultiMapLayerFootprintSpaceTimeClass() {
    }


    @Override
    public void addFootprint(
            ParametersMoving parametersMoving,
            Position position,
            double time,
            double timeStanding
    ) throws СrashIntoAnImpassableObjectExeption { //FIXME ADD_TEST


        Footprint newFootprint = new FootprintClass(position, timeStanding, parametersMoving);

        this.addFootprint(newFootprint, time);
    }

    @Override
    public void addFootprint(Footprint footprint, double time) throws СrashIntoAnImpassableObjectExeption {

        boolean placeIsSeat = this.getIsSeatTaken(footprint.getOccupiedLocation(), time);

        if (!placeIsSeat) {
            storageAllFootprints.put(time, footprint);
        } else {
            throw new СrashIntoAnImpassableObjectExeption();
        }
    }


    @Override
    public void addFootprint(
            ParametersMoving parametersMoving,
            Path path,
            double startTime
    ) throws СrashIntoAnImpassableObjectExeption {
        CreatorMarksOfPath creatorMarksOfPath =
                new CreatorMarksOfPathClass(this, parametersMoving);
        creatorMarksOfPath.addFootprint(path, startTime);
    }

    @Override
    public void deleteFootprints(int ID) {
        //FIXME
    }

    @Override
    public boolean getIsSeatTaken(PolygonExtended place, double testedTime) { //FIXME ADD_TEST

//FIXME NOW

        Iterator<EntryPair<Double, Footprint>> iteratorEntryPair = storageAllFootprints.iteratorEntryPair();
        while (iteratorEntryPair.hasNext()) {
            EntryPair<Double, Footprint> entry = iteratorEntryPair.next();
            Footprint footprint = entry.getValue();
            double timeStandingStart = entry.getKey();
            double timeStandingEnd = timeStandingStart + footprint.getTimeToNextFootprint();
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
    public Position getPosition(ParametersMoving parametersMovingWithID, double time) {
        GetterPositionByID getter = new GetterPositionByIDClass(parametersMovingWithID, time);
        return getter.getPositionByID();

    }

    @Override
    public Double getAverageTimeMovingToNextPointOfPath() {
        Double averageRes = null;

        Iterator<EntryPair<Double, Footprint>> iteratorEntryPair = storageAllFootprints.iteratorEntryPair();
        while (iteratorEntryPair.hasNext()) {
            EntryPair<Double, Footprint> entry = iteratorEntryPair.next();
            Footprint footprint = entry.getValue();
            boolean isEndOfPath = GlobalVariable.equalsNumber(
                    footprint.getTimeToNextFootprint(),
                    CreatorMarksOfPathClass.MAX_TIME_STANDING
            );
            if (!isEndOfPath) {
                if (averageRes == null) {
                    averageRes = footprint.getTimeToNextFootprint();
                } else {
                    averageRes += footprint.getTimeToNextFootprint();
                    averageRes /= 2;
                }
            }

        }
        return averageRes;
    }


    @Override
    public Double totalTimeAllMoving() {

        double averageRes = 0;

        Iterator<EntryPair<Double, Footprint>> iteratorEntryPair = storageAllFootprints.iteratorEntryPair();
        while (iteratorEntryPair.hasNext()) {
            EntryPair<Double, Footprint> entry = iteratorEntryPair.next();
            Footprint footprint = entry.getValue();
            boolean isEndOfPath = GlobalVariable.equalsNumber(
                    footprint.getTimeToNextFootprint(),
                    CreatorMarksOfPathClass.MAX_TIME_STANDING
            );
            if (!isEndOfPath) {
                averageRes += footprint.getTimeToNextFootprint();
            }

        }
        return averageRes;
    }

    @Override
    public Double getTimeAddingLastFootprints() { //FIXME ADD_TEST
        Double lastTime = Double.MIN_VALUE;
        Iterator<EntryPair<Double, Footprint>> iteratorEntryPair = storageAllFootprints.iteratorEntryPair();
        while (iteratorEntryPair.hasNext()) {
            EntryPair<Double, Footprint> entry = iteratorEntryPair.next();
            Double timeAdding = entry.getKey();
            if (timeAdding > lastTime) {
                lastTime = timeAdding;
            }
        }

        return lastTime;
    }

    @Override
    public boolean isPathMovingObjectEnteringCorridor(ParametersMoving parametersMoving, Corridor corridor) { //FIXME codestyle

        TesterPathEnteringInCorridor tester = new OneTimeTesterPathEnteringCorridorClass(parametersMoving, corridor);
        return tester.isPathMovingObjectEnteringCorridor();
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
            double timeStanding = currentFootprint.getTimeToNextFootprint();
            double startStanding = entry.getKey();
            double endStanding = startStanding + timeStanding;

            boolean footprintIndcludeFindTimePoint = startStanding <= timeFind && timeFind < endStanding;
            if (footprintIndcludeFindTimePoint) {
                resRendringFootpring.add(currentFootprint);
            }
        }


        return resRendringFootpring;
    }

    private boolean isCorridorMutuallyIncludesPath(Corridor corridor, double timeAdding, Footprint footprint,
                                                   Footprint secondFootprint,
                                                   double timeSecondFootprint) {
        PolygonExtended occupiedLocation = footprint.getOccupiedLocation();
        boolean isCorridorCoverVertexPath = corridor.isCoverPolygon(timeSecondFootprint, occupiedLocation);
        boolean isTunnelCoveredCorridor = isVertexCorridorFromThisTimeDiapasonCoverPath(
                timeAdding,
                footprint,
                secondFootprint,
                timeSecondFootprint,
                corridor
        );
        return isCorridorCoverVertexPath && isTunnelCoveredCorridor;
    }


    private boolean isVertexCorridorFromThisTimeDiapasonCoverPath(
            double timeAdding,
            Footprint firstFootprint,
            Footprint secondFootprint,
            double timeSecondFootprint,
            Corridor corridor
    ) {
        List<Double> timestampsVertexCorridor = corridor.timestampsVertexCorridor();

        for (Double timeTested : timestampsVertexCorridor) {
            assert (timeTested != null);
            double fromTime = timeAdding;
            double toTime = timeAdding + firstFootprint.getTimeToNextFootprint();
            boolean isTimeDiapasonIncludeCurrentVertex = fromTime < timeTested && timeTested < toTime;
            if (isTimeDiapasonIncludeCurrentVertex) {
                Footprint approximationFootprint =
                        firstFootprint.getApproximation(fromTime, secondFootprint, toTime, timeTested);
                PolygonExtended approximationOccupiedLocation = approximationFootprint.getOccupiedLocation();
                if (!corridor.isCoverPolygon(timeTested, approximationOccupiedLocation)) {
                    return false;
                }
            }
        }

        return true;
    }


    private interface GetterPositionByID {
        public Position getPositionByID();
    }

    private class GetterPositionByIDClass implements GetterPositionByID {
        private IteratorFootprintWithID iteratorID = new IteratorFootprintWithIDClass();
        private ParametersMoving parametersMovingWithID;
        private double timeApproximation;
        private Footprint previousFootprint = null;
        private Footprint currentFootprint = null;
        private Footprint startTunnel = null;
        private double timeStartTunnel;
        private Footprint endTunnel = null;
        private double timeEndTunnel;



        public GetterPositionByIDClass(ParametersMoving parametersMovingWithID, double timeApproximation) {
            this.parametersMovingWithID = parametersMovingWithID;
            this.timeApproximation = timeApproximation;
        }

        @Override
        public Position getPositionByID() {
            while (iteratorID.hasNext(parametersMovingWithID)) {
                Pair<Double, Footprint> entry = iteratorID.nextWithID(parametersMovingWithID);
                currentFootprint = entry.getValue();
                timeStartTunnel = entry.getKey();
                timeEndTunnel = timeStartTunnel + currentFootprint.getTimeToNextFootprint();

                boolean timeStandingIncludeFindTime =
                        timeStartTunnel <= timeApproximation && timeApproximation < timeEndTunnel;
                if (timeStandingIncludeFindTime) {
                    Footprint approximation = approximationFootprint();
                    return approximation.getPosition();
                }
            }

            return null;
        }

        private Footprint approximationFootprint() {
            startFootprint();
            endFootprint();
            return startTunnel.getApproximation(timeStartTunnel, endTunnel, timeEndTunnel, timeApproximation);
        }

        private void startFootprint() {
            startTunnel = currentFootprint;
        }

        private void endFootprint() {
            Pair<Double, Footprint> next = iteratorID.nextWithID(parametersMovingWithID);
            if (next != null) {
                endTunnel = next.getValue();
            } else {
                endTunnel = imitationEndTunnel();
            }
        }

        private Footprint imitationEndTunnel() {
            return currentFootprint;
        }

    }

    private interface TesterPathEnteringInCorridor {
        public boolean isPathMovingObjectEnteringCorridor();
    }

    private class OneTimeTesterPathEnteringCorridorClass implements TesterPathEnteringInCorridor {
        private IteratorFootprintWithID iteratorID = new IteratorFootprintWithIDClass();
        private ParametersMoving parametersMoving;
        private Corridor corridor;

        private Footprint startTunnel = null;
        private Double timeStartTunnel = null;
        private Footprint endTunnel = null;
        private Double timeEndTunnel = null;

        public OneTimeTesterPathEnteringCorridorClass(ParametersMoving parametersMoving, Corridor corridor) {
            this.parametersMoving = parametersMoving;
            this.corridor = corridor;
        }

        @Override
        public boolean isPathMovingObjectEnteringCorridor() {
            if (isEnoughFootprintsForCreateFirstTunnel()) {
                while (isPossibleNextFootprintToTurnInEndTunnel()) {
                    if (!isCorridorMutuallyIncludesPathThisMachine()) {
                        return false;
                    }
                }
            }
            boolean isLastFootprintStandingUntilEndOfTimeMutuallyIncludes =
                    isImitationEndLastTunnelCorridorMutuallyIncludesPathThisMachine();
            return isLastFootprintStandingUntilEndOfTimeMutuallyIncludes;
        }

        private boolean isEnoughFootprintsForCreateFirstTunnel() {
            return iteratorID.hasNextPairItem(parametersMoving);
        }

        private boolean isPossibleNextFootprintToTurnInEndTunnel() {
            return iteratorID.hasNext(parametersMoving);
        }

        private boolean isCorridorMutuallyIncludesPathThisMachine() {
            prepareDataStartTunnel();
            boolean isDataPreparedSuccessfully = prepareEndTunnelForCycle();
            if (!isDataPreparedSuccessfully) {
                return false;
            }

            return isCorridorMutuallyIncludesPath(corridor, timeStartTunnel, startTunnel, endTunnel, timeEndTunnel);
        }

        private boolean isImitationEndLastTunnelCorridorMutuallyIncludesPathThisMachine() {
            boolean isDataPreparedSuccessfully = prepareDataStartTunnel();
            boolean isThereAreNoFootprintsOfThisMovingObjectAtAll = !isDataPreparedSuccessfully;
            if (isThereAreNoFootprintsOfThisMovingObjectAtAll) {
                return false;
            }
            prepareEndTunnelForLastTunnel();
            return isCorridorMutuallyIncludesPath(corridor, timeStartTunnel, startTunnel, endTunnel, timeEndTunnel);
        }


        private boolean prepareDataStartTunnel() { //FIXME codestyle
            boolean isEmergensySituation = startTunnel != null && endTunnel == null;
            assert (!isEmergensySituation);

            boolean isFirstIteration = startTunnel == null && endTunnel == null;
            if (isFirstIteration) {
                Pair<Double, Footprint> pair = iteratorID.nextWithID(parametersMoving);
                if (pair == null) {
                    return false;
                }
                startTunnel = pair.getValue();
                timeStartTunnel = pair.getKey();
                assert (timeStartTunnel != null);
            } else {
                assert (endTunnel != null && timeEndTunnel != null);
                startTunnel = endTunnel;
                timeStartTunnel = timeEndTunnel;
            }

            return true;
        }

        private boolean prepareEndTunnelForCycle() {

            Pair<Double, Footprint> pair = iteratorID.nextWithID(parametersMoving);

            if (pair == null) {
                return false;
            }
            endTunnel = pair.getValue();
            timeEndTunnel = pair.getKey();
            assert (timeEndTunnel != null);


            return true;
        }

        private void prepareEndTunnelForLastTunnel() {
            endTunnel = startTunnel;
            timeEndTunnel = timeStartTunnel + startTunnel.getTimeToNextFootprint();
        }
    }


    private interface IteratorFootprintWithID {
        public Pair<Double, Footprint> nextWithID(ParametersMoving parametersMoving);

        public boolean hasNextPairItem(ParametersMoving parametersMoving);

        public boolean hasNext(ParametersMoving parametersMoving);
    }

    private class IteratorFootprintWithIDClass implements IteratorFootprintWithID { //FIXME add tests
        private Iterator<EntryPair<Double, Footprint>> iteratorStorage = storageAllFootprints.iteratorEntryPair();
        private LinkedList<Pair<Double, Footprint>> previouslyFound = new LinkedList<>();

        @Override
        public Pair<Double, Footprint> nextWithID(ParametersMoving parametersMoving) {
            boolean isAvailablePreFoundItems = previouslyFound.size() > 0;
            if (!isAvailablePreFoundItems) {
                return getNextWithID(parametersMoving);
            } else {
                return previouslyFound.pollFirst();
            }
        }

        @Override
        public boolean hasNextPairItem(ParametersMoving parametersMoving) {
            return fillQueueToSize(parametersMoving, 2);
        }

        private boolean fillQueueToSize(ParametersMoving parametersMoving, int countItems) {
            while (isNecessaryToContinueFind(countItems) && isPossibleToContinueFind()) {
                Pair<Double, Footprint> preFound = getNextWithID(parametersMoving);
                boolean isFoundFootprintWithTheID = preFound != null;
                if (isFoundFootprintWithTheID) {
                    previouslyFound.addLast(preFound);
                } else {
                    break;
                }
            }

            return previouslyFound.size() >= countItems;
        }

        private boolean isNecessaryToContinueFind(int countItems) {
            return previouslyFound.size() < countItems;
        }

        private boolean isPossibleToContinueFind() {
            return iteratorStorage.hasNext();
        }

        @Override
        public boolean hasNext(ParametersMoving parametersMoving) {
            return fillQueueToSize(parametersMoving, 1);
        }

        private Pair<Double, Footprint> getNextWithID(ParametersMoving parametersMoving) {
            while (iteratorStorage.hasNext()) {
                EntryPair<Double, Footprint> entry = iteratorStorage.next();
                Pair<Double, Footprint> result = new Pair<>(entry.getKey(), entry.getValue());

                Footprint footprint = entry.getValue();
                boolean isSearchable = footprint.getMovingObject().getID() == parametersMoving.getID();
                if (isSearchable) {
                    return result;
                }

            }

            return null;
        }
    }
}
