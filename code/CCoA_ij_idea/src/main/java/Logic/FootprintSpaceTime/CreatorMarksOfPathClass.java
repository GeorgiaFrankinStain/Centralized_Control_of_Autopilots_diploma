package Logic.FootprintSpaceTime;

import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import Logic.GlobalVariable;
import Logic.MovingObjects.ParametersMoving;
import Logic.MovingObjects.Path;
import Logic.PathsMachines.PositionClass;
import Logic.Position;

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
        lengthStep = parametersMoving.getLength();
        if (Math.abs(speed) < GlobalVariable.DOUBLE_COMPARISON_ACCURACY) {
            timeStanding = this.MAX_TIME_STANDING;
        } else {
            timeStanding = lengthStep / speed;
        }
    }

    @Override
    public void addFootprint(
            Path path,
            double startTime
    ) throws СrashIntoAnImpassableObjectExeption {

        try {
            addFootprinsBasedOnThePath(path, startTime);
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
            Path path,
            double startTime
    ) throws СrashIntoAnImpassableObjectExeption {

        double timeAdding = startTime;

        if (path.getSize() == 0) {
            assert (false);
        } else if (path.getSize() == 1) {
            Position position = new PositionClass(path.getPoint(0), 0.0);
            this.addFootprint(
                    position,
                    timeAdding,
                    this.MAX_TIME_STANDING
            );
        } else {
            timeAdding += processingCreateFootprintsOnRouteStraightLineFromPairPoints(path, timeAdding);
            //FIXME ADD_TEST BAG += equals on result = (add sumTime equal on result adding positionTime)
            processingCreateFoorprintEndRouteFromSinglePoint(path, timeAdding);
        }
    }

    private double processingCreateFootprintsOnRouteStraightLineFromPairPoints(
            Path path,
            double timeAdding
    ) throws СrashIntoAnImpassableObjectExeption {
        double sumTime = 0;
        int endIndex = path.getSize() - 1;
        for (int i = 0; i < endIndex; i++) {
            Point startLine = path.getPoint(i);
            Point endLine = path.getPoint(i + 1);

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
     * @param path
     * @param timeAdding
     * @throws СrashIntoAnImpassableObjectExeption
     */
    private void processingCreateFoorprintEndRouteFromSinglePoint(
            Path path,
            double timeAdding
    ) throws СrashIntoAnImpassableObjectExeption {
        int indexLastPoint = path.getSize() - 1;
        Point startLine = path.getPoint(indexLastPoint - 1);
        Point endlessPoint = path.getPoint(indexLastPoint);
        double angleStepVector = endlessPoint.getAngleRotareRelative(startLine); //FIXME dublication (LINK_RletVeVp)
        Position position = new PositionClass(endlessPoint, angleStepVector);
        this.addFootprint(
                position,
                timeAdding,
                this.MAX_TIME_STANDING
        );
    }


    private boolean stepUnderTest(Point startLine, Point endLine, Point stepUnderTest) {
        int quarterStartLine = startLine.getQuarter(endLine);
        int quarterstepUnderTest = stepUnderTest.getQuarter(endLine);

        return quarterStartLine == quarterstepUnderTest;
    }

    private Point stepVector(Point endLine, Point startLine, double lengthStep) {
        Point origin = new PointClass(0, 0);
        double angleStepVector = endLine.getAngleRotareRelative(startLine);
        return new PointClass(lengthStep, 0).getRotateRelative(origin, angleStepVector);
    }

    private double printEveryStepOnLine(
            Point startLine,
            Point endLine,
            double standingTime,
            double timeAdding
    ) throws СrashIntoAnImpassableObjectExeption {

        double angle = endLine.getAngleRotareRelative(startLine);
        double timeSum = 0;

        Point currentCoordinat = startLine.clone();
        Point stepVector = stepVector(endLine, startLine, parametersMoving.getLength());

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

            currentCoordinat = new PointClass(
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
            Point endLine,
            Point currentCoordinat,
            double angleStepVector,
            double timeAdding
    ) throws СrashIntoAnImpassableObjectExeption {
        double localTimeSum = 0;
        if (!endLine.equals(currentCoordinat)) {

            /*
             * |--|--|-
             *        ^
             * little step*/
            Point penultimatePoint = currentCoordinat;
            double lengthFinalStep = endLine.getDistanceToPoint(penultimatePoint);
            double standingTime = lengthFinalStep / parametersMoving.getSpeed();

            Position position = new PositionClass(penultimatePoint, angleStepVector);
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
