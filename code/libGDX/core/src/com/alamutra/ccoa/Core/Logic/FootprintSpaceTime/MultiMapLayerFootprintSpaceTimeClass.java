package com.alamutra.ccoa.Core.Logic.FootprintSpaceTime;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.alamutra.ccoa.Core.Logic.GlobalVariable;
import com.alamutra.ccoa.Core.Logic.MovingBody.ParametersMovingUnique;
import com.alamutra.ccoa.Core.Logic.MovingBody.PathCCoA;
import com.alamutra.ccoa.Core.Logic.Position;
import com.alamutra.ccoa.Core.Wrappers.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MultiMapLayerFootprintSpaceTimeClass implements LayerFootprintSpaceTime {
    private static final Logger LOGGER = LogManager.getLogger(MultiMapLayerFootprintSpaceTimeClass.class);


    private MultiMap<Double, Footprint> storageAllFootprints =
            new MultiMapTree<Double, Footprint>();


    public MultiMapLayerFootprintSpaceTimeClass() {
    }


    @Override
    public void addFootprint(
            ParametersMovingUnique parametersMovingUnique,
            Position position,
            double time,
            double timeStanding
    ) throws СrashIntoAnImpassableObjectException { //FIXME ADD_TEST
        Route route = new RouteClass();

        Footprint newFootprint = new FootprintClass(position, timeStanding, parametersMovingUnique, route);

        this.addFootprint(newFootprint, time);
    }

    @Override
    public void addFootprint(Footprint footprint, double time) throws СrashIntoAnImpassableObjectException {

        boolean placeIsSeat = this.getIsSeatTaken(footprint.getOccupiedLocation(), time);

        if (!placeIsSeat) {
            storageAllFootprints.put(time, footprint);
        } else {
            throw new СrashIntoAnImpassableObjectException(time, footprint);
        }
    }


    @Override
    public void addFootprintsPath(
            ParametersMovingUnique parametersMovingUnique,
            PathCCoA pathCCoA,
            double startTime,
            Route route
    ) throws СrashIntoAnImpassableObjectException {
        CreatorMarksOfPath creatorMarksOfPath =
                new CreatorMarksOfPathClass(this, parametersMovingUnique, route, true);
        creatorMarksOfPath.addFootprint(pathCCoA, startTime);
    }

    @Override
    public void addFootprintsPathWithoutEndStandingUntilEndTime(
            ParametersMovingUnique parametersMovingUnique,
            PathCCoA pathCCoA,
            double startTime,
            Route route) throws СrashIntoAnImpassableObjectException {
        CreatorMarksOfPath creatorMarksOfPath =
                new CreatorMarksOfPathClass(this, parametersMovingUnique, route, false);
        creatorMarksOfPath.addFootprint(pathCCoA, startTime);
    }

    @Override
    public void deleteFootprints(int ID) {
        //FIXME
    }

    @Override
    public boolean getIsSeatTaken(PolygonCCoA place, double testedTime) {

        Iterator<PairCCoA<Double, Footprint>> iteratorEntryPair = storageAllFootprints.iteratorEntryPair();
        while (iteratorEntryPair.hasNext()) {
            PairCCoA<Double, Footprint> entry = iteratorEntryPair.next();
            Footprint footprint = entry.getValue();
            double timeStandingStart = entry.getKey();
            double timeStandingEnd = timeStandingStart + footprint.getTimeToNextFootprint();
            boolean timeStandingIncludeTestedTime = timeStandingStart <= testedTime && testedTime < timeStandingEnd;

            if (timeStandingIncludeTestedTime) {
                Footprint approximationFootprint = footprint.getApproximationWithNextFootprint(testedTime);

                PolygonCCoA locationMovingObject = approximationFootprint.getOccupiedLocation();
//                PolygonCCoA locationMovingObject = footprint.getOccupiedLocation();
                boolean placeIsSeat = place.intersectionPolygon(locationMovingObject);
                if (placeIsSeat) {
                    LOGGER.debug("place is seat taken. Place: {}, LocationMovingObject: {}", place, locationMovingObject);
                    return true;
                }
            }

        }


        return false;
    }

    @Override
    public Position getPosition(ParametersMovingUnique parametersMovingUniqueWithID, double time) {
        GetterPositionByID getter = new GetterPositionByIDClass(parametersMovingUniqueWithID, time);
        return getter.getPositionByID();

    }

    @Override
    public Double getAverageTimeMovingToNextPointOfPath() {
        Double averageRes = null;

        Iterator<PairCCoA<Double, Footprint>> iteratorEntryPair = storageAllFootprints.iteratorEntryPair();
        while (iteratorEntryPair.hasNext()) {
            PairCCoA<Double, Footprint> entry = iteratorEntryPair.next();
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

        Iterator<PairCCoA<Double, Footprint>> iteratorEntryPair = storageAllFootprints.iteratorEntryPair();
        while (iteratorEntryPair.hasNext()) {
            PairCCoA<Double, Footprint> entry = iteratorEntryPair.next();
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
        Iterator<PairCCoA<Double, Footprint>> iteratorEntryPair = storageAllFootprints.iteratorEntryPair();
        while (iteratorEntryPair.hasNext()) {
            PairCCoA<Double, Footprint> entry = iteratorEntryPair.next();
            Double timeAdding = entry.getKey();
            if (timeAdding > lastTime) {
                lastTime = timeAdding;
            }
        }

        return lastTime;
    }

    @Override
    public boolean isPathMovingObjectEnteringCorridor(ParametersMovingUnique parametersMovingUnique, Corridor corridor) { //FIXME codestyle

        TesterPathEnteringInCorridor tester = new OneTimeTesterPathEnteringCorridorClass(parametersMovingUnique, corridor);
        return tester.isPathMovingObjectEnteringCorridor();
    }


    //TODO: add more difficult determitaion the level (https://habr.com/ru/post/122919/)
    //TODO: return id of poligons returned getAreaFromWhen  используется выделителем юнитов, тут не требуется возвращать полигоны, можно просто айдишники вернуть
    @Override
    public List<Footprint> getRenderingFootprintsFromWhen(PolygonCCoA areaVizibility, double timeFind) {

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
        Iterator<PairCCoA<Double, Footprint>> iteratorEntryPair = storageAllFootprints.iteratorEntryPair();
        while (iteratorEntryPair.hasNext()) {
            PairCCoA<Double, Footprint> entry = iteratorEntryPair.next();

            Footprint currentFootprint = entry.getValue(); //FIXME NOW add test timeFind diapason intersection
            double timeStanding = currentFootprint.getTimeToNextFootprint();
            double startStanding = entry.getKey();
            double endStanding = startStanding + timeStanding;


            boolean footprintIndcludeFindTimePoint = startStanding <= timeFind && timeFind < endStanding;
            if (footprintIndcludeFindTimePoint) {
                LOGGER.debug("getRenderingFootprintsFromWhen: startStanding: {}, timeFind: {}, endStanding: {}", startStanding, timeFind, endStanding);
                Footprint approximationFootprint = currentFootprint.getApproximationWithNextFootprint(timeFind);
                LOGGER.debug("approximationFootprint: {}", approximationFootprint);
                resRendringFootpring.add(approximationFootprint);
            }
        }


        return resRendringFootpring;
    }

    private boolean isCorridorCoverTunnel(Corridor corridor,
                                          double timeStartFootprint, Footprint startFootprint,
                                          Footprint secondFootprint, double timeSecondFootprint) {
        boolean isCorridorCoverStartTunnel = corridor.isCoverPolygon(timeStartFootprint, startFootprint.getOccupiedLocation());
        PolygonCCoA occupiedSecondLocation = secondFootprint.getOccupiedLocation();
        boolean isCorridorCoverEndTunnel = corridor.isCoverPolygon(timeSecondFootprint, occupiedSecondLocation);
        boolean isCorridorCoverTunnelInTimestampsVertexesCorridor = isCorridorCoverTunnelInTimestampsVertexsCorridor(
                timeStartFootprint,
                startFootprint,
                secondFootprint,
                timeSecondFootprint,
                corridor
        );
        return isCorridorCoverStartTunnel && isCorridorCoverEndTunnel
                && isCorridorCoverTunnelInTimestampsVertexesCorridor;
    }


    private boolean isCorridorCoverTunnelInTimestampsVertexsCorridor(
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
                PolygonCCoA approximationOccupiedLocation = approximationFootprint.getOccupiedLocation();
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
        private ParametersMovingUnique parametersMovingUniqueWithID;
        private double timeApproximation;
        private Footprint previousFootprint = null;
        private Footprint currentFootprint = null;
        private Footprint startTunnel = null;
        private double timeStartTunnel;
        private Footprint endTunnel = null;
        private double timeEndTunnel;



        public GetterPositionByIDClass(ParametersMovingUnique parametersMovingUniqueWithID, double timeApproximation) {
            this.parametersMovingUniqueWithID = parametersMovingUniqueWithID;
            this.timeApproximation = timeApproximation;
        }

        @Override
        public Position getPositionByID() {
            while (iteratorID.hasNext(parametersMovingUniqueWithID)) {
                PairCCoA<Double, Footprint> entry = iteratorID.nextWithID(parametersMovingUniqueWithID);
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
            PairCCoA<Double, Footprint> next = iteratorID.nextWithID(parametersMovingUniqueWithID);
            if (next != null) {
                endTunnel = next.getValue();
            } else {
                endTunnel = imitationEndTunnel(); //TODO use new version function
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
        private ParametersMovingUnique parametersMovingUnique;
        private Corridor corridor;

        private Footprint startTunnel = null;
        private Double timeStartTunnel = null;
        private Footprint endTunnel = null;
        private Double timeEndTunnel = null;

        public OneTimeTesterPathEnteringCorridorClass(ParametersMovingUnique parametersMovingUnique, Corridor corridor) {
            this.parametersMovingUnique = parametersMovingUnique;
            this.corridor = corridor;
        }

        @Override
        public boolean isPathMovingObjectEnteringCorridor() {
            if (isEnoughFootprintsForCreateFirstTunnel()) {
                while (isPossibleNextFootprintToTurnInEndTunnel()) {
                    if (!isTunnelExistEndCorridorCoverTunnel()) {
                        return false;
                    }
                }
            }
            boolean isLastFootprintStandingUntilEndOfTimeCovered =
                    isCorridorCoverImitationEndLastTunnel();
            return isLastFootprintStandingUntilEndOfTimeCovered;
        }

        private boolean isEnoughFootprintsForCreateFirstTunnel() {
            return iteratorID.hasNextPairItem(parametersMovingUnique);
        }

        private boolean isPossibleNextFootprintToTurnInEndTunnel() {
            return iteratorID.hasNext(parametersMovingUnique);
        }

        private boolean isTunnelExistEndCorridorCoverTunnel() {
            prepareDataStartTunnel();
            boolean isDataPreparedSuccessfully = prepareEndTunnelForCycle(); //TODO codestyle get and change variation in one function
            if (!isDataPreparedSuccessfully) {
                return false;
            }

            return isCorridorCoverTunnel(corridor, timeStartTunnel, startTunnel, endTunnel, timeEndTunnel);
        }

        private boolean isCorridorCoverImitationEndLastTunnel() {
            boolean isDataPreparedSuccessfully = prepareDataStartTunnel();
            boolean isThereAreNoFootprintsOfThisMovingObjectAtAll = !isDataPreparedSuccessfully;
            if (isThereAreNoFootprintsOfThisMovingObjectAtAll) {
                return false;
            }
            prepareEndTunnelForLastTunnel();
            return isCorridorCoverTunnel(corridor, timeStartTunnel, startTunnel, endTunnel, timeEndTunnel);
        }


        private boolean prepareDataStartTunnel() {
            boolean isEmergensySituation = startTunnel != null && endTunnel == null;
            assert (!isEmergensySituation);

            boolean isFirstIteration = startTunnel == null && endTunnel == null;
            if (isFirstIteration) {
                PairCCoA<Double, Footprint> pair = iteratorID.nextWithID(parametersMovingUnique);
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

            PairCCoA<Double, Footprint> pair = iteratorID.nextWithID(parametersMovingUnique);

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
        public PairCCoA<Double, Footprint> nextWithID(ParametersMovingUnique parametersMovingUnique);

        public boolean hasNextPairItem(ParametersMovingUnique parametersMovingUnique);

        public boolean hasNext(ParametersMovingUnique parametersMovingUnique);
    }

    private class IteratorFootprintWithIDClass implements IteratorFootprintWithID { //FIXME add tests
        private Iterator<PairCCoA<Double, Footprint>> iteratorStorage = storageAllFootprints.iteratorEntryPair();
        private LinkedList<PairCCoA<Double, Footprint>> previouslyFound = new LinkedList<>();

        @Override
        public PairCCoA<Double, Footprint> nextWithID(ParametersMovingUnique parametersMovingUnique) {
            boolean isAvailablePreFoundItems = previouslyFound.size() > 0;
            if (!isAvailablePreFoundItems) {
                return getNextWithID(parametersMovingUnique);
            } else {
                return previouslyFound.pollFirst();
            }
        }

        @Override
        public boolean hasNextPairItem(ParametersMovingUnique parametersMovingUnique) {
            int numberOfRequiredElements = 2;
            return fillQueueToSize(parametersMovingUnique, numberOfRequiredElements);
        }

        private boolean fillQueueToSize(ParametersMovingUnique parametersMovingUnique, int countItems) {
            while (isNecessaryToContinueFind(countItems) && isPossibleToContinueFind()) {
                PairCCoA<Double, Footprint> preFound = getNextWithID(parametersMovingUnique);
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
        public boolean hasNext(ParametersMovingUnique parametersMovingUnique) {
            int numberOfRequiredElements = 1;
            return fillQueueToSize(parametersMovingUnique, numberOfRequiredElements);
        }

        private PairCCoA<Double, Footprint> getNextWithID(ParametersMovingUnique parametersMovingUnique) {
            while (iteratorStorage.hasNext()) {
                PairCCoA<Double, Footprint> entry = iteratorStorage.next();
                PairCCoA<Double, Footprint> result = new PairCCoAClass<>(entry.getKey(), entry.getValue());

                Footprint footprint = entry.getValue();
                boolean isSearchable = footprint.getMovingObject().getID() == parametersMovingUnique.getID();
                if (isSearchable) {
                    return result;
                }

            }


            return null;
        }
    }
}
