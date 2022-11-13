package com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime;

public interface HexagonVectors {

    public PointCCoA getVector(int number);
    public void rotateRelative(PointCCoA origin, double angle);

    public PointCCoA getVectorHexagonWithBottomLeftStart(int number);

    public PointCCoA getVector1InTop();

    public PointCCoA getVector2TopRight();

    public PointCCoA getVector3FromTopInBottomRight();

    public PointCCoA getVector4InBottom();

    public PointCCoA getVector5InLeftBottom();

    public PointCCoA getVector6FromBottomInLeft();

    public PointCCoA getVector1InTopСounterclockwise();

    public PointCCoA getVector2TopRightСounterclockwise();

    public PointCCoA getVector3FromTopInBottomRightСounterclockwise();

    public PointCCoA getVector4InBottomСounterclockwise();

    public PointCCoA getVector5InLeftBottomСounterclockwise();

    public PointCCoA getVector6FromBottomInLeftСounterclockwise();
}
