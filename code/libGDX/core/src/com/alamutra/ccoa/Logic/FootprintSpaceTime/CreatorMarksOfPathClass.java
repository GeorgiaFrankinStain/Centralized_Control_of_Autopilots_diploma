package com.alamutra.ccoa.Logic.FootprintSpaceTime;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectExeption;
import com.alamutra.ccoa.Logic.GlobalVariable;
import com.alamutra.ccoa.Logic.MovingObjects.ParametersMoving;
import com.alamutra.ccoa.Logic.MovingObjects.PathCCoA;
import com.alamutra.ccoa.Logic.PathsMachines.PositionClass;
import com.alamutra.ccoa.Logic.Position;

public class CreatorMarksOfPathClass implements CreatorMarksOfPath {
    final public static double MAX_TIME_STANDING = Double.MAX_VALUE * 0.95;
    private LayerFootprintSpaceTime footprintsSpaceTime;
    private ParametersMoving parametersMoving;

    private double speed;
    private double lengthStep;
    double timeStanding;
    private Footprint penultimateFootprintInPath = null;
    private Footprint lastFootprintInPath = null;

    public CreatorMarksOfPathClass(
            LayerFootprintSpaceTime footprintsSpaceTime,
            ParametersMoving parametersMoving
    ) {
        this.footprintsSpaceTime = footprintsSpaceTime;
        this.parametersMoving = parametersMoving;


        /**
         * Landscape have getResistancePowerLandscape(pressurePaskaleOfMachine)
         * speed = MachinePower / ResistancePower
         */
        speed = parametersMoving.getSpeed();
        lengthStep = parametersMoving.getLengthStep();
        if (Math.abs(speed) < GlobalVariable.DOUBLE_COMPARISON_ACCURACY) {
            timeStanding = this.MAX_TIME_STANDING;
        } else {
            timeStanding = lengthStep / speed;
        }
    }

    @Override
    public void addFootprint(
            PathCCoA pathCCoA,
            double startTime
    ) throws СrashIntoAnImpassableObjectExeption {

        try {
            addFootprinsBasedOnThePath(pathCCoA, startTime);
        } catch (СrashIntoAnImpassableObjectExeption ex) {
            setTheStandingTimeUntilTheEndOfTimeInCaseOfAnAccident();
            throw new СrashIntoAnImpassableObjectExeption();
        }

    }

    //==== <start> <Private_Methods> =======================================================================
    private void setTheStandingTimeUntilTheEndOfTimeInCaseOfAnAccident() {
        if (this.penultimateFootprintInPath != null) {
            this.penultimateFootprintInPath.setTimeToNextFootprint(this.MAX_TIME_STANDING);
        }
    }

    private void addFootprinsBasedOnThePath(
            PathCCoA pathCCoA,
            double startTime
    ) throws СrashIntoAnImpassableObjectExeption {

        double timeAdding = startTime;

        if (pathCCoA.getSize() == 0) {
            assert (false);
        } else if (pathCCoA.getSize() == 1) {
            Position position = new PositionClass(pathCCoA.getPoint(0), 0.0);
            this.addFootprint(
                    position,
                    timeAdding,
                    this.MAX_TIME_STANDING
            );
        } else {
            timeAdding += processingCreateFootprintsOnRouteStraightLineFromPairPoints(pathCCoA, timeAdding);
            //FIXME ADD_TEST BAG += equals on result = (add sumTime equal on result adding positionTime)
            processingCreateFoorprintEndRouteFromSinglePoint(pathCCoA, timeAdding);
        }
    }

    private double processingCreateFootprintsOnRouteStraightLineFromPairPoints(
            PathCCoA pathCCoA,
            double timeAdding
    ) throws СrashIntoAnImpassableObjectExeption {
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
     * @throws СrashIntoAnImpassableObjectExeption
     */
    private void processingCreateFoorprintEndRouteFromSinglePoint(
            PathCCoA pathCCoA,
            double timeAdding
    ) throws СrashIntoAnImpassableObjectExeption {
        int indexLastPoint = pathCCoA.getSize() - 1;
        PointCCoA startLine = pathCCoA.getPoint(indexLastPoint - 1);
        PointCCoA endlessPointCCoA = pathCCoA.getPoint(indexLastPoint);
        double angleStepVector = endlessPointCCoA.getAngleRotareRelative(startLine); //FIXME dublication (LINK_RletVeVp)
        Position position = new PositionClass(endlessPointCCoA, angleStepVector);
        this.addFootprint(
                position,
                timeAdding,
                this.MAX_TIME_STANDING
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
    ) throws СrashIntoAnImpassableObjectExeption {

        double angle = endLine.getAngleRotareRelative(startLine);
        double timeSum = 0;

        PointCCoA currentCoordinat = startLine.clone();
        PointCCoA stepVector = stepVector(endLine, startLine, parametersMoving.getLengthStep());

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
    ) throws СrashIntoAnImpassableObjectExeption {
        double localTimeSum = 0;
        if (!endLine.equals(currentCoordinat)) {

            /*
             * |--|--|-
             *        ^
             * little step*/
            PointCCoA penultimatePointCCoA = currentCoordinat;
            double lengthFinalStep = endLine.getDistanceToPoint(penultimatePointCCoA);
            double standingTime = lengthFinalStep / parametersMoving.getSpeed();

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
    ) throws СrashIntoAnImpassableObjectExeption {
        this.penultimateFootprintInPath = this.lastFootprintInPath;
        this.lastFootprintInPath = new FootprintClass(position, timeStanding, parametersMoving);

        this.footprintsSpaceTime.addFootprint(this.lastFootprintInPath, time);
    }
}
