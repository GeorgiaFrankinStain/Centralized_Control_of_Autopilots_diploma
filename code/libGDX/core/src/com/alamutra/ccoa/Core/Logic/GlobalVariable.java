package com.alamutra.ccoa.Core.Logic;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GlobalVariable { //FIXME is good? (global variable in java is good?)
    public static final double DOUBLE_COMPARISON_ACCURACY = 0.001; //mm
    public static final double LENGTH_DIAPASON_MAGNET = GlobalVariable.DOUBLE_COMPARISON_ACCURACY * 4;

    public static boolean equalsNumber(double one, double two) {
        double difference = Math.abs(one - two);
        return difference <= GlobalVariable.DOUBLE_COMPARISON_ACCURACY;
    }

    public static boolean equalsNumber(double one, double two, double accuracy) {
        double difference = Math.abs(one - two);
        return difference <= accuracy;
    }

    public static double roundHalfUp(double number) {

        double accuracy = DOUBLE_COMPARISON_ACCURACY;
        int numberZerosAfterDecimalPoint = 0;
        for (; accuracy < 1; numberZerosAfterDecimalPoint++) {
            accuracy *= 10;
        }
        int newScale = numberZerosAfterDecimalPoint;

        BigDecimal result = new BigDecimal(number);
        result = result.setScale(newScale, RoundingMode.HALF_UP);

        return result.doubleValue();
    }

    /*
     *
     *      -3       -2        -1         0         1         2        3
     * |---------|---------|---------|---------|---------|---------|---------|
     *                               0         l
     *           ^
     *        the vertices belong to the range on the right
     * 0l - length diapason
     */
    public static int getNumberDiapason(double coordinate, double lengthDiapason) {
        int numberLeftBorder = (int) (coordinate / lengthDiapason);


        boolean isTheVertexBelongingToTheRightRange = (Math.abs(coordinate) % lengthDiapason) == 0;
        if (coordinate < 0 && !isTheVertexBelongingToTheRightRange) {
                numberLeftBorder--;
        }

        return numberLeftBorder;
    }

    /*
     *                 n is first diapason
     *                         V
     *              n-1          n          n+1          n+2          n+3          n+4          n+5
     *       |------------|------------|------------|------------|------------|------------|------------|
     *                    0
     *
     *       n-1          n          n+1          n+2          n+3          n+4          n+5
     * |-----------|------------|------------|------------|------------|------------|------------|
     *            0-offset
     *
     *
     *
     *
     *
     *            n-2           n-1          n          n+1          n+2          n+3          n+4          n+5
     *       |------------|------------|--*------*--|------------|------------|------------|------------|
     *                                 0  1      2  |
     *                                       |offset|
     *                                       |      | <- point end offset diapason
     *       point start offset diapason  -> |
     *                                       |
     *                                       |
     *                                       |
     *       n-2          n-1          n     |    n+1          n+2          n+3          n+4          n+5
     * |-----------|------------|------*--*--|---*--------|------------|------------|------------|
     *                             -1  0  1      2
     *                          ^
     *                          |
     *                         0-offset
     *
     */
    public static int getNumberOffsetDiapason(double coordinate, double lengthDiapason, double offset) {
        assert (offset <= 0);
        assert (Math.abs(offset) < lengthDiapason);

        int numberDiapason = GlobalVariable.getNumberDiapason(coordinate, lengthDiapason);

        if (isInOffsetDiapasonCoordinateOccupiedNextDiapason(coordinate, lengthDiapason, offset)) {
            numberDiapason++;
        }

        return numberDiapason;
    }

    private static boolean isInOffsetDiapasonCoordinateOccupiedNextDiapason(
            double coordinate, double lengthDiapason, double offset) {

        double leftBorder = GlobalVariable.getLeftBorderDiapason(coordinate, lengthDiapason);

        assert (leftBorder <= coordinate);

        double distanceFrom0ToCoordinateInFirstDiapason = coordinate - leftBorder;
        double projectionCoordinateInFirstDiapason = distanceFrom0ToCoordinateInFirstDiapason;

        double startOffsetDiapason = 0 + (lengthDiapason - Math.abs(offset));
        double endOffsetDiapason = 0 + lengthDiapason;

        boolean isInOffsetDiapasonCoordinateOccupiedNextDiapason =
                isIncludeInDiapason(startOffsetDiapason, projectionCoordinateInFirstDiapason, endOffsetDiapason);

        return isInOffsetDiapasonCoordinateOccupiedNextDiapason;
    }

    public static double getLeftBorderOffsetDiapason(double coordinate, double lengthDiapason, double offset) {
        assert (offset <= 0);
        if (Math.abs(offset) == lengthDiapason) {
            offset = 0;
        }
        assert (Math.abs(offset) < lengthDiapason);

        int numberOffsetDiapason = getNumberOffsetDiapason(coordinate, lengthDiapason, offset);
        double leftBorderMappedDiapason = lengthDiapason * numberOffsetDiapason;
        double leftBorderOffsetDiapason = leftBorderMappedDiapason - Math.abs(offset);

        return leftBorderOffsetDiapason;
    }

    public static double getLeftBorderDiapason(double coordinate, double lengthDiapason) {
        long numberLeftBorder = GlobalVariable.getNumberDiapason(coordinate, lengthDiapason);
        double leftBorder = lengthDiapason * numberLeftBorder;
        return leftBorder;
    }

    private static boolean isIncludeInDiapason(double startDiapason, double coordinate, double endDiapason) {
        return startDiapason <= coordinate && coordinate < endDiapason;
    }

}
