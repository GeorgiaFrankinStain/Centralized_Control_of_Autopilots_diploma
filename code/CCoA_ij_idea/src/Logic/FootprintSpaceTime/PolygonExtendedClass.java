package Logic.FootprintSpaceTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PolygonExtendedClass implements PolygonExtended {
    private List<Point> points = new ArrayList<Point>();


    //FIXME rule of create polygon (lines don't intersect; prohibit narrow polygons, no use polygon without points)


    public PolygonExtendedClass() {
    }

    public PolygonExtendedClass(String polygonTextFormat) {
        this(getPointsForCreate(polygonTextFormat));
    }
    private static List<Point> getPointsForCreate(String polygonTextFormat) {
        ConstructorTextFormat constructor = new ConstructorTextFormatClass();
        List<Point> points = constructor.getPointsForCreate(polygonTextFormat);
        return points;
    }

    public PolygonExtendedClass(List<Point> points) {
        this.points = points;
    }

    private interface ConstructorTextFormat {
        public List<Point> getPointsForCreate(String textFormatPolygon);
    }

    private static class ConstructorTextFormatClass implements ConstructorTextFormat {
        private Map<Integer, Point> points = new TreeMap<>();
        private int x = 0;
        private int y = 0;
        private Point vectorDeposeRelativeNewOrigin = null;

        @Override
        public List<Point> getPointsForCreate(String polygonTextFormat) {
            for (char currentChar : polygonTextFormat.toCharArray()) {
                switchProcessing(currentChar);
            }
            List<Point> result = new ArrayList<Point>(points.values());
            applyNewOrigin(result);
            return result;
        }


        private void applyNewOrigin(List<Point> points) {
            if (vectorDeposeRelativeNewOrigin == null) {
                return;
            }

            for(Point point : points) {
                point.deposeOn(vectorDeposeRelativeNewOrigin);
            }
        }

        private void switchProcessing(char currentChar) {
            boolean isNumber = '0' <= currentChar && currentChar <= '9';
            if (isNumber) {
                addPoint(currentChar);
            } else if (currentChar == ' ') {
                x++;
            } else if (currentChar == '\n') {
                toNextRowY();
            } else if (currentChar == '+') {
                saveNewOrigin();
            } else {
                assert (false);
            }
        }

        private void saveNewOrigin() {
            boolean isOnlyOrigin = vectorDeposeRelativeNewOrigin == null;
            assert (isOnlyOrigin);
            vectorDeposeRelativeNewOrigin = new PointClass(-x, -y);
            x++;
        }

        private void addPoint(char currentChar) {
            int sequenceAddInPolygon = currentChar - '0';
            assert (0 <= sequenceAddInPolygon && sequenceAddInPolygon <= 9);
            points.put(sequenceAddInPolygon, new PointClass(x, y));
            x++;
        }

        private void toNextRowY() {
            y++;
            x = 0;
        }
    }

    @Override
    public int getCountPoints() {//TEST+
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
    public void addPoint(Point[] points) {
        for (Point point : points) {
            this.addPoint(point);
        }
    }

    @Override
    public void addPoint(List<Point> points) {
        for (Point point : points) {
            this.addPoint(point);
        }
    }

    @Override
    public void addAllPoint(List<Point> newPoints) {
        this.points.addAll(newPoints);
    }

    @Override
    public void insertPoint(int index, Point newPoint) {
        this.points.add(index, newPoint);
    }

    @Override
    public void setPoint(int index, Point newPoint) {
        this.points.set(index, newPoint);
    }

    @Override
    public void insertBeforetPoint(int index, Point newPoint) {
        //FIXME
    }

    @Override
    public void rotateRelative(Point origin, double angle) { //FIXME TEST ADD
        for (int i = 0; i < this.getCountPoints(); i++) {
            this.setPoint(i, this.getPoint(i).getRotateRelative(origin, angle));
        }
    }

    @Override
    public void deposeOn(Point vector) { //FIXME TEST ADD
        for (int i = 0; i < this.getCountPoints(); i++) {
            Point currentPoint = this.getPoint(i);
            Point newPoint = new PointClass(
                    currentPoint.getX() + vector.getX(),
                    currentPoint.getY() + vector.getY()
            );
            this.setPoint(i, newPoint);
        }
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < this.getCountPoints(); i++) {
            result += this.getPoint(i) + " ";
        }

        return result;
    }

    @Override
    public boolean equals(Object obj) { //FIXME ADD_TEST
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;


        PolygonExtended other = (PolygonExtended) obj;

        if (this.points.size() != other.getCountPoints()) {
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
    public PolygonExtended clone() {
        List<Point> cloneList = new ArrayList<Point>(this.points);
        return new PolygonExtendedClass(cloneList);
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
    public boolean intersectionLine(Point startLine, Point endLine) {//TEST+
        int indexLastPoint = this.getCountPoints() - 1;
        Point startLocalLine = this.getPoint(indexLastPoint);
        Point endLocalLine = this.getPoint(0);


        for (int i = 0; i < this.getCountPoints(); i++) {
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

    @Override
    public boolean intersectionLine(Line line) {
        return intersectionLine(line.getStart(), line.getEnd());
    }

    @Override
    public boolean isLiesInsideThe(Round round) {
        for (int i = 0; i < this.getCountPoints(); i++) {
            Point current = this.getPoint(i);
            if (!round.isIncludes(current)) {
                return false;
            }
        }
        return true;
    }


    private boolean intersectionLines(
            Point aStartLine,
            Point aEndLine,
            Point bStartLine,
            Point bEndLine
    ) {
        Line line = new LineClass(aStartLine, aEndLine);
        Line line2 = new LineClass(bStartLine, bEndLine);
        return line.intersectionLine(line2);
    }

    @Override
    public Point getCenterAverage() {//TEST+
        double xSum = 0;
        double ySum = 0;
        for (Point point : this.points) {
            xSum += point.getX();
            ySum += point.getY();
        }

        double xAverage = xSum / this.getCountPoints();
        double yAverage = ySum / this.getCountPoints();

        return new PointClass(xAverage, yAverage);
    }

    @Override
    public Double[] getFormatDoubleArray() { //TEST+
        Double[] arrayDouble = new Double[this.getCountPoints() * 2];

        int indexWriteInDoubleArray = 0;
        for (int i = 0; i < this.getCountPoints(); i++) {
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
        for (int i = 0; i < firstPolygon.getCountPoints(); i++) {
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
        int indexLastPoint = firstPolygon.getCountPoints() - 1;
        Point startLine1 = firstPolygon.getPoint(indexLastPoint);
        Point endLine1 = firstPolygon.getPoint(0);


        for (int i = 0; i < firstPolygon.getCountPoints(); i++) {

            boolean isIntersectionBOOL = secondPoylgon.intersectionLine(startLine1, endLine1);

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
