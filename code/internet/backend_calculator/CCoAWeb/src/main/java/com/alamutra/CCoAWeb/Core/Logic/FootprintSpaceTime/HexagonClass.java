package com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime;

import com.alamutra.CCoAWeb.Core.Logic.GlobalVariable;

import java.util.ArrayList;
import java.util.List;

public class HexagonClass implements PolygonCCoA, HexagonVectors {
    private PolygonCCoA hexagon = new PolygonCCoAClass();

    private double lengthSide;

    private PointCCoA vector1InTop;
    private PointCCoA vector2InTopRight;
    private PointCCoA vector3FromTopInBottomRight;
    private PointCCoA vector4InBottom;
    private PointCCoA vector5InLeftBottom;
    private PointCCoA vector6FromBottomInLeft;

    private List<PointCCoA> vectorSide = new ArrayList<>();

    private static final int NUMBER_SIDE_HEXAGON = 6;

    /*
     *
     *
     *              ╱ 2 ╲
     *           ╱         ╲
     *         1            3
     *         |            |
     *         |     *      |
     *         |            |
     *         0            4
     *           ╲         /
     *              ╲ 5 /
     *
     *
     */

    public HexagonClass(int numberStartPoint, PointCCoA startPoint, double lengthSide) {
        constructor(numberStartPoint, startPoint, lengthSide);
    }


    /**
     * @param littleRadius is r
     *
     *              ╱ 2 ╲
     *           ╱         ╲
     *         1            3
     *         |            |
     *         |     *--r---|
     *         |            |
     *         0            4
     *           ╲         /
     *              ╲ 5 /
     *
     * a = 2r/√3 length side
     */
    public HexagonClass(double littleRadius, int numberStartPoint, PointCCoA startPoint) { //FIXME Add tests
        assert (littleRadius != Double.POSITIVE_INFINITY);
        assert (littleRadius != Double.NEGATIVE_INFINITY);
        assert (littleRadius != Double.NaN);
        assert (0 < littleRadius);

        double lengthSide = littleRadius * 2 / Math.sqrt(3);
        constructor(numberStartPoint, startPoint, lengthSide);
    }


    public HexagonClass(PointCCoA center, double lengthSide) {
        double littleRadius = (lengthSide * Math.sqrt(3)) / 2;
        PointCCoA vectorTo0Point = new PointCCoAClass(-littleRadius, -(lengthSide / 2));
        PointCCoA pointNumber0 = center.getDeposeOn(vectorTo0Point);
        constructor(0, pointNumber0, lengthSide);
    }

    public HexagonClass(double littleRadius, PointCCoA center) {
        double lengthSide = littleRadius * 2 / Math.sqrt(3);
        PointCCoA vectorTo0Point = new PointCCoAClass(-littleRadius, -(lengthSide / 2));
        PointCCoA pointNumber0 = center.getDeposeOn(vectorTo0Point);
        constructor(0, pointNumber0, lengthSide);
    }

    private void constructor(int numberStartPoint, PointCCoA startPoint, double lengthSide) {
        assert(0 <= numberStartPoint && numberStartPoint < 6);
        assert (lengthSide != Double.POSITIVE_INFINITY);
        assert (lengthSide != Double.NEGATIVE_INFINITY);
        assert (lengthSide != Double.NaN);
        assert (0 < lengthSide);
        this.lengthSide = lengthSide;
        createVectorsBetweenPoints();

        PointCCoA currentPoint = detectedLeftBottomStartPoint(numberStartPoint, startPoint);

        for (int i = 0; i < this.NUMBER_SIDE_HEXAGON; i++) {
            this.hexagon.addPoint(currentPoint);
            currentPoint.deposeOn(vectorSide.get(i));
        }

        validationResultHexagon();
    }

    private void validationResultHexagon() {

        PointCCoA current = this.getPoint(0);
        int indexOppositePoint = 3;
        PointCCoA oppositePoint = this.getPoint(indexOppositePoint);
        double firstDistance = current.getDistanceToPoint(oppositePoint);

        for (int i = 1; i < NUMBER_SIDE_HEXAGON; i++) {
            current = this.getPoint(i);
            indexOppositePoint = (i + 3) % NUMBER_SIDE_HEXAGON;
            oppositePoint = this.getPoint(indexOppositePoint);
            double distance = current.getDistanceToPoint(oppositePoint);
            assert (GlobalVariable.equalsNumber(distance, firstDistance));
        }
    }

    private PointCCoA detectedLeftBottomStartPoint(int numberStartPoint, PointCCoA startPoint) {
        PointCCoA currentPoint = startPoint.clone();

        for (int i = numberStartPoint; i < this.NUMBER_SIDE_HEXAGON; i++) {
            currentPoint.deposeOn(vectorSide.get(i));
        }

        return currentPoint;
    }

    @Override
    public int getCountPoints() {
        return this.hexagon.getCountPoints();
    }

    @Override
    public PointCCoA getPoint(int index) {
        return this.hexagon.getPoint(index);
    }

    @Override
    public void addPoint(PointCCoA newPointCCoA) {
        this.hexagon.addPoint(newPointCCoA);
    }

    @Override
    public void addPoint(PointCCoA[] pointCCoAS) {
        this.hexagon.addPoint(pointCCoAS);
    }

    @Override
    public void addPoint(List<PointCCoA> pointCCoAS) {
        this.hexagon.addPoint(pointCCoAS);
    }

    @Override
    public void addAllPoint(List<PointCCoA> newPointCCoAS) {
        this.hexagon.addAllPoint(newPointCCoAS);
    }

    @Override
    public void insertPoint(int index, PointCCoA newPointCCoA) {
        this.hexagon.insertPoint(index, newPointCCoA);
    }

    @Override
    public void setPoint(int index, PointCCoA newPointCCoA) {
        this.hexagon.setPoint(index, newPointCCoA);
    }

    @Override
    public PointCCoA getVector(int numberPointStartVector) {
        assert(0 <= numberPointStartVector && numberPointStartVector < 6);

        PointCCoA startOfVector = this.hexagon.getPoint(numberPointStartVector);
        int indexEndPoint = (numberPointStartVector + 1) % 6;
        PointCCoA endOfVector = this.hexagon.getPoint(indexEndPoint);

        PointCCoA vector = endOfVector.getVector(startOfVector);

        return vector;
    }

    @Override
    public void rotateRelative(PointCCoA origin, double angle) {
        this.hexagon.rotateRelative(origin, angle);
    }

    @Override
    public void deposeOn(PointCCoA vector) {
        this.hexagon.deposeOn(vector);
    }

    @Override
    public PolygonCCoA getDeposeOn(PointCCoA vector) {
        return this.getDeposeOn(vector);
    }

    @Override
    public boolean contains(PointCCoA desirededPointCCoA) {
        return this.hexagon.contains(desirededPointCCoA);
    }

    @Override
    public boolean enteringPoint(PointCCoA position) {
        return this.hexagon.enteringPoint(position);
    }

    @Override
    public boolean intersectionPolygon(PolygonCCoA secondPolygon) {
        return this.hexagon.intersectionPolygon(secondPolygon);
    }

    @Override
    public boolean intersectionLine(PointCCoA startLine, PointCCoA endLine) {
        return this.hexagon.intersectionLine(startLine, endLine);
    }

    @Override
    public boolean intersectionLine(LineCut lineCut) {
        return this.hexagon.intersectionLine(lineCut);
    }

    @Override
    public boolean isLiesInsideThe(Round round) {
        return this.hexagon.isLiesInsideThe(round);
    }

    @Override
    public PointCCoA getCenterAverage() {
        return this.hexagon.getCenterAverage();
    }

    @Override
    public Double[] getFormatDoubleArray() {
        return this.hexagon.getFormatDoubleArray();
    }

    @Override
    public double getArea() {
        return this.hexagon.getArea();
    }

    @Override
    public PolygonCCoA clone() {
        return this.hexagon.clone();
    }


    private void createVectorsBetweenPoints() {
        this.vector1InTop = getVector1InTop();
        this.vector2InTopRight = getVector2TopRight();
        this.vector3FromTopInBottomRight = getVector3FromTopInBottomRight();
        this.vector4InBottom = getVector4InBottom();
        this.vector5InLeftBottom = getVector5InLeftBottom();
        this.vector6FromBottomInLeft = getVector6FromBottomInLeft();

        this.vectorSide.add(vector1InTop);
        this.vectorSide.add(vector2InTopRight);
        this.vectorSide.add(vector3FromTopInBottomRight);
        this.vectorSide.add(vector4InBottom);
        this.vectorSide.add(vector5InLeftBottom);
        this.vectorSide.add(vector6FromBottomInLeft);
    }

    @Override
    public PointCCoA getVectorHexagonWithBottomLeftStart(int number) {
        assert(0 <= number && number < 6);
        return this.vectorSide.get(number);
    }

    @Override
    public PointCCoA getVector1InTop() {
        PointCCoA vectorVertical = new PointCCoAClass(0, 1);
        return vectorVertical.getMultipliedVector(lengthSide);
    }

    @Override
    public PointCCoA getVector2TopRight() {
        PointCCoA vectorHorizontal = new PointCCoAClass(1, 0);
        PointCCoA origin = new PointCCoAClass(0, 0);
        double degree30 = 0.523599;
        PointCCoA vector2InTopRight = vectorHorizontal.getRotateRelative(origin, degree30);
        return vector2InTopRight.getMultipliedVector(lengthSide);
    }

    @Override
    public PointCCoA getVector3FromTopInBottomRight() {
        PointCCoA vectorHorizontal = new PointCCoAClass(1, 0);
        PointCCoA origin = new PointCCoAClass(0, 0);
        double degree30 = 0.523599;
        PointCCoA vector2InTopRight = vectorHorizontal.getRotateRelative(origin, -degree30);
        return vector2InTopRight.getMultipliedVector(lengthSide);
    }

    @Override
    public PointCCoA getVector4InBottom() {
        PointCCoA vectorVertical = new PointCCoAClass(0, -1);
        return vectorVertical.getMultipliedVector(lengthSide);
    }

    @Override
    public PointCCoA getVector5InLeftBottom() {
        PointCCoA vectorVertical = new PointCCoAClass(-1, 0);
        PointCCoA origin = new PointCCoAClass(0, 0);
        double degree30 = 0.523599;
        PointCCoA vector1InTop = vectorVertical.getRotateRelative(origin, degree30);
        return vector1InTop.getMultipliedVector(lengthSide);
    }

    @Override
    public PointCCoA getVector6FromBottomInLeft() {
        PointCCoA vectorVertical = new PointCCoAClass(-1, 0);
        PointCCoA origin = new PointCCoAClass(0, 0);
        double degree30 = 0.523599;
        PointCCoA vector1InTop = vectorVertical.getRotateRelative(origin, -degree30);
        return vector1InTop.getMultipliedVector(lengthSide);
    }

    @Override
    public PointCCoA getVector1InTopСounterclockwise() {
        return getVector1InTop().getInversion();
    }

    @Override
    public PointCCoA getVector2TopRightСounterclockwise() {
        return getVector2TopRight().getInversion();
    }

    @Override
    public PointCCoA getVector3FromTopInBottomRightСounterclockwise() {
        return getVector3FromTopInBottomRight().getInversion();
    }

    @Override
    public PointCCoA getVector4InBottomСounterclockwise() {
        return getVector4InBottom().getInversion();
    }

    @Override
    public PointCCoA getVector5InLeftBottomСounterclockwise() {
        return getVector5InLeftBottom().getInversion();
    }

    @Override
    public PointCCoA getVector6FromBottomInLeftСounterclockwise() {
        return getVector6FromBottomInLeft().getInversion();
    }
}
