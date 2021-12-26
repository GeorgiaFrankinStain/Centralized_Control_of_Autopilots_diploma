package com.alamutra.ccoa.Logic.FootprintSpaceTime;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineSegment;

import java.awt.geom.Line2D;


public class LineCutClass implements LineCut {
    private Point start;
    private Point end;

    public LineCutClass(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Point getStart() {
        return start;
    }

    @Override
    public Point getEnd() {
        return end;
    }

    @Override
    public boolean intersectionLine(LineCut secondLineCut) {
        Point bStartLine = secondLineCut.getStart();
        Point bEndLine = secondLineCut.getEnd();
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
    public boolean intersection(Point testedPoint) {
        LineSegment lineAsPoint = new LineSegment(
                new Coordinate(testedPoint.getX(), testedPoint.getY()),
                new Coordinate(testedPoint.getX(), testedPoint.getY())
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
