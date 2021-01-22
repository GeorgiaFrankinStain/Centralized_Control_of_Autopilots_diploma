package Logic.FootprintSpaceTime;

import java.awt.geom.Line2D;

public class LineClass implements Line {
    private Point start;
    private Point end;

    public LineClass(Point start, Point end) {
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
    public boolean intersectionLine(Line secondLine) {
        Point bStartLine = secondLine.getStart();
        Point bEndLine = secondLine.getEnd();
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
    public double length() {
        return end.getDistanceToPoint(start);
    }
}
