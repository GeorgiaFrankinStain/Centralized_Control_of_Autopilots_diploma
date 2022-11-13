package com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineSegment;

import java.awt.geom.Line2D;


public class LineCutClass implements LineCut {
    private PointCCoA start;
    private PointCCoA end;

    public LineCutClass(PointCCoA start, PointCCoA end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public PointCCoA getStart() {
        return start;
    }

    @Override
    public PointCCoA getEnd() {
        return end;
    }

    @Override
    public boolean intersectionLineCut(LineCut secondLineCut) {
        PointCCoA bStartLine = secondLineCut.getStart();
        PointCCoA bEndLine = secondLineCut.getEnd();
        return Line2D.linesIntersect(
                start.getX(),
                start.getY(),
                end.getX(),
                end.getY(),
                bStartLine.getX(),
                bStartLine.getY(),
                bEndLine.getX(),
                bEndLine.getY()

        );
    }

    @Override
    public boolean intersection(PointCCoA testedPointCCoA) {
        LineSegment lineAsPoint = new LineSegment(
                new Coordinate(testedPointCCoA.getX(), testedPointCCoA.getY()),
                new Coordinate(testedPointCCoA.getX(), testedPointCCoA.getY())
        );
        LineSegment test = new LineSegment(
                new Coordinate(start.getX(), start.getY()),
                new Coordinate(end.getX(), end.getY())
        );

        boolean lineCutContainsPoint = test.intersection(lineAsPoint) != null;
        return lineCutContainsPoint;
    }

    @Override
    public double length() {
        return end.getDistanceToPoint(start);
    }
}
