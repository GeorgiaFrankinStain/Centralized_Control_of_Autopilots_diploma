package com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.GlobalVariable;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.ParametersMovingUnique;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.PathCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.PathsMachines.PositionClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.Position;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreatorMarksOfPathClass implements CreatorMarksOfPath {
    private static final Logger LOGGER = LogManager.getLogger(CreatorMarksOfPathClass.class);

    private double timeStandingInLastPath;
    private LayerFootprintSpaceTime footprintsSpaceTime;
    private ParametersMovingUnique parametersMovingUnique;
    private Route route;

    private double speed;
    private double lengthStep;
    double timeStanding;
    private Footprint penultimateFootprintInPath = null;
    private Footprint lastFootprintInPath = null;

    public CreatorMarksOfPathClass(
            LayerFootprintSpaceTime footprintsSpaceTime,
            ParametersMovingUnique parametersMovingUnique,
            Route route,
            boolean isStandingTimeEndPathIsUntilEndTime
    ) {
        this.footprintsSpaceTime = footprintsSpaceTime;
        this.parametersMovingUnique = parametersMovingUnique;
        this.route = route;

        if (isStandingTimeEndPathIsUntilEndTime) {
            this.timeStandingInLastPath = GlobalVariable.MAX_TIME_STANDING;
        } else {
            this.timeStandingInLastPath = GlobalVariable.MIN_TIME_STANDING;
        }


        /**
         * Landscape have getResistancePowerLandscape(pressurePaskaleOfMachine)
         * speed = MachinePower / ResistancePower
         */
        speed = parametersMovingUnique.getSpeed();
        lengthStep = parametersMovingUnique.getLengthStep();
        if (Math.abs(speed) < GlobalVariable.DOUBLE_COMPARISON_ACCURACY) {
            timeStanding = GlobalVariable.MAX_TIME_STANDING;
        } else {
            timeStanding = lengthStep / speed;
        }
    }

    @Override
    public void addFootprint(
            PathCCoA pathCCoA,
            double startTime
    ) throws СrashIntoAnImpassableObjectException {

        try {
            addFootprintsBasedOnThePath(pathCCoA, startTime);
        } catch (СrashIntoAnImpassableObjectException ex) {
            setTheStandingTimeUntilTheEndOfTimeInCaseOfAnAccident();
            LOGGER.debug("path crash: {}", pathCCoA);
            throw new СrashIntoAnImpassableObjectException();
        }

    }

    //==== <start> <Private_Methods> =======================================================================
    private void setTheStandingTimeUntilTheEndOfTimeInCaseOfAnAccident() {
        if (this.penultimateFootprintInPath != null) {
            this.penultimateFootprintInPath.setTimeToNextFootprint(GlobalVariable.MAX_TIME_STANDING);
        }
    }

    private void addFootprintsBasedOnThePath(
            PathCCoA pathCCoA,
            double startTime
    ) throws СrashIntoAnImpassableObjectException {

        double timeAdding = startTime;

        if (pathCCoA.getSize() == 0) {
            assert (false);
        } else if (pathCCoA.getSize() == 1) {
            Position position = new PositionClass(pathCCoA.getPoint(0), 0.0);
            this.addFootprint(
                    position,
                    timeAdding,
                    this.timeStandingInLastPath
            );
        } else {
            timeAdding += processingCreateFootprintsOnRouteStraightLinesFromPairsPoints(pathCCoA, timeAdding);
            //FIXME ADD_TEST BAG += equals on result = (add sumTime equal on result adding positionTime)
            processingCreateFootprintEndRouteFromSinglePoint(pathCCoA, timeAdding);
        }
    }


    private double processingCreateFootprintsOnRouteStraightLinesFromPairsPoints(
            PathCCoA pathCCoA,
            double timeAdding
    ) throws СrashIntoAnImpassableObjectException {
        double sumTime = 0;
        int endIndex = pathCCoA.getSize() - 1;
        for (int i = 0; i < endIndex; i++) {
            PointCCoA startLine = pathCCoA.getPoint(i);
            PointCCoA endLine = pathCCoA.getPoint(i + 1);

            double lastSumTime = printEveryStepOnLine(
                    startLine,
                    endLine,
                    timeStanding,
                    timeAdding
            );

            sumTime += lastSumTime;
            timeAdding += lastSumTime;
        }

        return sumTime;
    }

    /**
     * number this is number interation cicle up
     * number this is point
     * -- this is time standing (Length equal length movingObject)
     * - this is lastLittleStep (last Little Time Standing)
     * 0--0--0--0-1
     * |
     * |
     * 1
     * |
     * |
     * 1
     * |
     * 2--2--2--2--2-3
     * ^
     * end point in endless
     *
     * @param pathCCoA
     * @param timeAdding
     * @throws СrashIntoAnImpassableObjectException
     */
    private void processingCreateFootprintEndRouteFromSinglePoint(
            PathCCoA pathCCoA,
            double timeAdding
    ) throws СrashIntoAnImpassableObjectException {
        int indexLastPoint = pathCCoA.getSize() - 1;
        PointCCoA startLine = pathCCoA.getPoint(indexLastPoint - 1);
        PointCCoA endlessPointCCoA = pathCCoA.getPoint(indexLastPoint);
        double angleStepVector = endlessPointCCoA.getAngleRotareRelative(startLine); //FIXME dublication (LINK_RletVeVp)
        Position position = new PositionClass(endlessPointCCoA, angleStepVector);
        this.addFootprint(
                position,
                timeAdding,
                this.timeStandingInLastPath
        );
    }


    private boolean stepUnderTest(PointCCoA startLine, PointCCoA endLine, PointCCoA stepUnderTest) {
        int quarterStartLine = startLine.getQuarter(endLine);
        int quarterstepUnderTest = stepUnderTest.getQuarter(endLine);

        return quarterStartLine == quarterstepUnderTest;
    }

    private PointCCoA stepVector(PointCCoA endLine, PointCCoA startLine, double lengthStep) {
        PointCCoA origin = new PointCCoAClass(0, 0);
        double angleStepVector = endLine.getAngleRotareRelative(startLine);
        return new PointCCoAClass(lengthStep, 0).getRotateRelative(origin, angleStepVector);
    }

    private double printEveryStepOnLine(
            PointCCoA startLine,
            PointCCoA endLine,
            double standingTime,
            double timeAdding
    ) throws СrashIntoAnImpassableObjectException {

        double angle = endLine.getAngleRotareRelative(startLine);
        double timeSum = 0;


        PointCCoA currentCoordinat = startLine.clone();
        PointCCoA stepVector = stepVector(endLine, startLine, parametersMovingUnique.getLengthStep());

        LOGGER.debug(
                "printEveryStepOnLine, startLine: {}, endLine: {}, stepVector: {}",
                startLine, endLine, stepVector
        );

        double angleStepVector = endLine.getAngleRotareRelative(startLine);

        double lengthStep = stepVector.getLengthVector();
        double lengthStraightPath = endLine.getDistanceToPoint(startLine);
        int counterMaxSteps = (int) (lengthStraightPath / lengthStep);
        for (int i = 0; (i < counterMaxSteps) && this.stepUnderTest(startLine, endLine, currentCoordinat); i++) {
            Position position = new PositionClass(currentCoordinat, angleStepVector);
            this.addFootprint(
                    position,
                    timeAdding,
                    standingTime
            );


            timeAdding += standingTime;
            timeSum += standingTime;

            LOGGER.debug(
                    "printEveryStepOnLine currentCoordinate: {}, ID: {}",
                    currentCoordinat, this.parametersMovingUnique.getID()
            );

            currentCoordinat = new PointCCoAClass(
                    currentCoordinat.getX() + stepVector.getX(),
                    currentCoordinat.getY() + stepVector.getY()
            );
        }

        timeSum += littleStepInEndStraightLineBetweenTwoNeightborePointPaths(
                endLine,
                currentCoordinat,
                angleStepVector,
                timeAdding
        );


        return timeSum;
    }

    private double littleStepInEndStraightLineBetweenTwoNeightborePointPaths(
            PointCCoA endLine,
            PointCCoA currentCoordinat,
            double angleStepVector,
            double timeAdding
    ) throws СrashIntoAnImpassableObjectException {
        double localTimeSum = 0;
        if (!endLine.equals(currentCoordinat)) {

            /*
             * |--|--|-
             *        ^
             * little step*/
            PointCCoA penultimatePointCCoA = currentCoordinat;
            double lengthFinalStep = endLine.getDistanceToPoint(penultimatePointCCoA);
            double standingTime = lengthFinalStep / parametersMovingUnique.getSpeed();

            Position position = new PositionClass(penultimatePointCCoA, angleStepVector);
            localTimeSum = standingTime;
            this.addFootprint(
                    position,
                    timeAdding,
                    standingTime
            );

            return localTimeSum;
        }


        return localTimeSum;
    }

    private void addFootprint(
            Position position,
            double time,
            double timeStanding
    ) throws СrashIntoAnImpassableObjectException {
        this.penultimateFootprintInPath = this.lastFootprintInPath;
        this.lastFootprintInPath = new FootprintClass(position, timeStanding, parametersMovingUnique, this.route);
        route.addLastFootprint(time, this.lastFootprintInPath);

        this.footprintsSpaceTime.addFootprint(this.lastFootprintInPath, time);
    }
}
