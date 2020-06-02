package Logic.FootprintSpaceTime;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class PolygonExtendedClass implements PolygonExtended {
    private List<Point> points = new ArrayList<Point>();


    //FIXME rule of create polygon (lines don't intersect; prohibit narrow polygons, no use polygon without points)


    public PolygonExtendedClass(List<Point> points) {
        this.points = points;
    }
    public PolygonExtendedClass() {
    }

    @Override
    public int countPoints() {//TEST+
        return points.size();
    }

    @Override
    public Point getPoint(int index) {//TEST+
        return this.points.get(index);
    }

    @Override
    public void addPoint(Point newPoint) {
        this.points.add(newPoint);
    }

    @Override
    public void addAllPoint(List<Point> newPoints) {
        this.points.addAll(newPoints);
    }

    @Override
    public void setPoint(int index, Point newPoint) {
        this.points.add(index, newPoint);
    }

    @Override
    public void insertBeforetPoint(int index, Point newPoint) {
        //FIXME
    }

    public boolean equals(Object obj) { //FIXME ADD_TEST
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;



        PolygonExtended other = (PolygonExtended) obj;

        if (this.points.size() != other.countPoints()) {
            return false;
        }

        for (int i = 0; i < this.points.size(); i++) {
            Point pointFromThis = this.points.get(i);
            Point pointFromOther = other.getPoint(i);
            if (!pointFromThis.equals(pointFromOther)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean contains(Point desirededPoint) {//TEST+
        return this.points.contains(desirededPoint);
    }


    public boolean enteringPoint(Point testPoint) {//TEST+

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
    public boolean intersectionPolygon(PolygonExtended secondPolygon) {//TEST+

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
    public boolean intersecionLine(Point startLine, Point endLine) {//TEST+
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
    ) {//TEST+
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

    @Override
    public Point getCenterAverage() {//TEST+
        double xSum = 0;
        double ySum = 0;
        for (Point point : this.points) {
            xSum += point.getX();
            ySum += point.getY();
        }

        double xAverage = xSum / this.countPoints();
        double yAverage = ySum / this.countPoints();

        return new PointClass(xAverage, yAverage);
    }

    @Override
    public Double[] getFormatDoubleArray() { //TEST+
        Double[] arrayDouble = new Double[this.countPoints() * 2];

        int indexWriteInDoubleArray = 0;
        for (int i = 0; i < this.countPoints(); i++) {
            Point currentPoint = this.getPoint(i);


            arrayDouble[indexWriteInDoubleArray] = currentPoint.getX();
            indexWriteInDoubleArray++;
            arrayDouble[indexWriteInDoubleArray] = currentPoint.getY();
            indexWriteInDoubleArray++;
        }

        return arrayDouble;
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
