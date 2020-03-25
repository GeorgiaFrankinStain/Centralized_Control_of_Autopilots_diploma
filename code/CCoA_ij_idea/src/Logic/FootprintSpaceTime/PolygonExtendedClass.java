package Logic.FootprintSpaceTime;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class PolygonExtendedClass implements PolygonExtended {
    private List<Point> points = new ArrayList<Point>();
    ;

    //FIXME rule of create polygon (lines don't intersect; prohibit narrow polygons and polygon without points)


    public PolygonExtendedClass() {
    }

    public PolygonExtendedClass(List<Point> points) {
        this.points = points;
    }

    @Override
    public int countPoints() {
        return points.size();
    }

    @Override
    public Point getPoint(int index) {
        return this.points.get(index);
    }

    @Override
    public void setPoint(Point newPoint) {
        this.points.add(newPoint);
    }

    @Override
    public void setPoint(int index, Point newPoint) {
        this.points.add(index, newPoint);
    }

    @Override
    public void setInsertBeforetPoint(int index, Point newPoint) {
        //FIXME
    }


    @Override
    public boolean contains(Point desirededPoint) {
        return this.points.contains(desirededPoint);
    }


    public boolean enteringPoint(Point testPoint) {

        //FIXME использовать алгоримт зональной декйкстры с алгоритмом создания точек прямого доступа около каждого вектора


        if (this.contains(testPoint)) {
            return true;
        }


        int indexLastPoint = this.points.size() - 1;
        Point startLine = this.points.get(indexLastPoint);
        Point endLine = this.points.get(0);

        boolean pointLeftLastLineBOOL = testPoint.isLeftRelative(startLine, endLine);

        for (int i = 0; ; i++) {
            int indexEndLine = i + 1;
            startLine = this.points.get(i);
            endLine = this.points.get(indexEndLine);

            boolean pointLeftCurrentLineBOOL = testPoint.isLeftRelative(startLine, endLine);

            boolean testPointOnDifferentSidesLines = pointLeftLastLineBOOL != pointLeftCurrentLineBOOL;
            if (testPointOnDifferentSidesLines) {
                return false;
            }


            boolean thisLastIteration = indexEndLine == indexLastPoint;
            if (thisLastIteration) {
                break;
            }
        }


        return true;
    }

    @Override
    public boolean intersectionPolygon(PolygonExtended secondPolygon) {

        boolean mutualEnterPointOfPolygonsBOOL =
                enteringMinimumOnePointOfFirstPolygonInSecondPolygon(this, secondPolygon)
                        || enteringMinimumOnePointOfFirstPolygonInSecondPolygon(secondPolygon, this)
                        || intersectionMinumumTwoLineSegmentOfTwoPolygons(this, secondPolygon);

        if (mutualEnterPointOfPolygonsBOOL) {
            return true;
        }


        return false;
    }


    @Override
    public boolean intersecionLine(Point startLine, Point endLine) {
        int indexLastPoint = this.countPoints() - 1;
        Point startLocalLine = this.getPoint(indexLastPoint);
        Point endLocalLine = this.getPoint(0);


        for (int i = 0; i < this.countPoints(); i++) {
            boolean isIntersectionBOOL = this.intersectionLines(
                    startLocalLine,
                    endLocalLine,
                    startLine,
                    endLine);

            if (isIntersectionBOOL) {
                return true;
            }


            boolean lastLineIsMissingBOOL = i == indexLastPoint;
            if (lastLineIsMissingBOOL) {
                break;
            }

            startLocalLine = this.getPoint(i);
            endLocalLine = this.getPoint(i + 1);
        }

        return false;
    }


    /**
     * (https://martin-thoma.com/how-to-check-if-two-line-segments-intersect/)
     * Check if bounding boxes do intersect. If one bounding box
     * touches the other, they do intersect.
     */
    @Override
    public boolean intersectionLines(
            Point aStartLine,
            Point aEndLine,
            Point bStartLine,
            Point bEndLine
    ) {
        return Line2D.linesIntersect(
                aStartLine.getX(),
                aStartLine.getY(),
                aEndLine.getX(),
                aEndLine.getY(),
                bStartLine.getX(),
                bStartLine.getY(),
                bEndLine.getX(),
                bEndLine.getY()

        );
    }


    //==== <start> <Private_Methods> =======================================================================


    private boolean enteringMinimumOnePointOfFirstPolygonInSecondPolygon(
            PolygonExtended firstPolygon,
            PolygonExtended secondPolygon
    ) {
        for (int i = 0; i < firstPolygon.countPoints(); i++) {
            Point currentPoint = firstPolygon.getPoint(i);

            if (secondPolygon.enteringPoint(currentPoint)) {
                return true;
            }
        }

        return false;
    }

    private boolean intersectionMinumumTwoLineSegmentOfTwoPolygons(
            PolygonExtended firstPolygon,
            PolygonExtended secondPoylgon
    ) {
        int indexLastPoint = firstPolygon.countPoints() - 1;
        Point startLine1 = firstPolygon.getPoint(indexLastPoint);
        Point endLine1 = firstPolygon.getPoint(0);


        for (int i = 0; i < firstPolygon.countPoints(); i++) {

            boolean isIntersectionBOOL = secondPoylgon.intersecionLine(startLine1, endLine1);

            if (isIntersectionBOOL) {
                return true;
            }


            boolean lastLineIsMissingBOOL = i == indexLastPoint;
            if (lastLineIsMissingBOOL) {
                break;
            }

            startLine1 = firstPolygon.getPoint(i);
            endLine1 = firstPolygon.getPoint(i + 1);
        }


        return false;
    }

    //==== <end> <Private_Methods> =========================================================================

}
