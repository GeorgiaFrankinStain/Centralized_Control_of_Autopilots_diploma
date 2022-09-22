package com.alamutra.ccoa.Core.Logic.ControllerMachines.Hexagon;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.*;

public class HexagonVectorsToNeighborsHexagonTile implements HexagonVectors {
    private HexagonVectors hexagonVectors;

    public HexagonVectorsToNeighborsHexagonTile(double littleDiameter) {
        double lengthSideForBigHexagon = littleDiameter;
        double distanceToNeighborNode = lengthSideForBigHexagon;
        PolygonCCoA vectors = new HexagonClass(new PointCCoAClass(0, 0), distanceToNeighborNode);
        PointCCoA origin = new PointCCoAClass(0, 0);
        double angleTransformationVertexVectorsInNeighborsVectors = -0.523599;
        vectors.rotateRelative(origin, angleTransformationVertexVectorsInNeighborsVectors);
        this.hexagonVectors = (HexagonVectors) vectors;
    }

    @Override
    public PointCCoA getVector(int number) {
        return this.hexagonVectors.getVector(number);
    }

    @Override
    public void rotateRelative(PointCCoA origin, double angle) {
        this.hexagonVectors.rotateRelative(origin, angle);
    }

    @Override
    public PointCCoA getVectorHexagonWithBottomLeftStart(int number) {
        return this.hexagonVectors.getVectorHexagonWithBottomLeftStart(number);
    }

    @Override
    public PointCCoA getVector1InTop() {
        return this.hexagonVectors.getVector1InTop();
    }

    @Override
    public PointCCoA getVector2TopRight() {
        return this.hexagonVectors.getVector2TopRight();
    }

    @Override
    public PointCCoA getVector3FromTopInBottomRight() {
        return this.hexagonVectors.getVector3FromTopInBottomRight();
    }

    @Override
    public PointCCoA getVector4InBottom() {
        return this.hexagonVectors.getVector4InBottom();
    }

    @Override
    public PointCCoA getVector5InLeftBottom() {
        return this.hexagonVectors.getVector5InLeftBottom();
    }

    @Override
    public PointCCoA getVector6FromBottomInLeft() {
        return this.hexagonVectors.getVector6FromBottomInLeft();
    }

    @Override
    public PointCCoA getVector1InTopСounterclockwise() {
        return this.hexagonVectors.getVector1InTopСounterclockwise();
    }

    @Override
    public PointCCoA getVector2TopRightСounterclockwise() {
        return this.hexagonVectors.getVector2TopRightСounterclockwise();
    }

    @Override
    public PointCCoA getVector3FromTopInBottomRightСounterclockwise() {
        return this.hexagonVectors.getVector3FromTopInBottomRightСounterclockwise();
    }

    @Override
    public PointCCoA getVector4InBottomСounterclockwise() {
        return this.hexagonVectors.getVector4InBottomСounterclockwise();
    }

    @Override
    public PointCCoA getVector5InLeftBottomСounterclockwise() {
        return this.hexagonVectors.getVector5InLeftBottomСounterclockwise();
    }

    @Override
    public PointCCoA getVector6FromBottomInLeftСounterclockwise() {
        return this.hexagonVectors.getVector6FromBottomInLeftСounterclockwise();
    }
}
