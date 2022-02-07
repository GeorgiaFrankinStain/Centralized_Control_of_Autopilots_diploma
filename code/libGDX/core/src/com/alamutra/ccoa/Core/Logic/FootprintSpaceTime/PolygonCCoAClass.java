package com.alamutra.ccoa.Core.Logic.FootprintSpaceTime;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PolygonCCoAClass implements PolygonCCoA {
    private List<PointCCoA> pointCCoAS = new ArrayList<PointCCoA>();


    //FIXME rule of create polygon (lines don't intersect; prohibit narrow polygons, no use polygon without points)


    public PolygonCCoAClass() {
    }

    public PolygonCCoAClass(String polygonTextFormat) {
        this(getPointsForCreate(polygonTextFormat));
    }
    private static List<PointCCoA> getPointsForCreate(String polygonTextFormat) {
        ConstructorTextFormat constructor = new ConstructorTextFormatClass();
        List<PointCCoA> pointCCoAS = constructor.getPointsForCreate(polygonTextFormat);
        return pointCCoAS;
    }

    public PolygonCCoAClass(List<PointCCoA> pointCCoAS) {
        addPoint(pointCCoAS);
    }

    private interface ConstructorTextFormat {
        public List<PointCCoA> getPointsForCreate(String textFormatPolygon);
    }

    private static class ConstructorTextFormatClass implements ConstructorTextFormat {
        private Map<Integer, PointCCoA> points = new TreeMap<>();
        private int x = 0;
        private int y = 0;
        private PointCCoA vectorDeposeRelativeNewOrigin = null;

        @Override
        public List<PointCCoA> getPointsForCreate(String polygonTextFormat) {
            for (char currentChar : polygonTextFormat.toCharArray()) {
                switchProcessing(currentChar);
            }
            List<PointCCoA> result = new ArrayList<PointCCoA>(points.values());
            applyNewOrigin(result);
            return result;
        }


        private void applyNewOrigin(List<PointCCoA> pointCCoAS) {
            if (vectorDeposeRelativeNewOrigin == null) {
                return;
            }

            for(PointCCoA pointCCoA : pointCCoAS) {
                pointCCoA.deposeOn(vectorDeposeRelativeNewOrigin);
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
            vectorDeposeRelativeNewOrigin = new PointCCoAClass(-x, -y);
            x++;
        }

        private void addPoint(char currentChar) {
            int sequenceAddInPolygon = currentChar - '0';
            assert (0 <= sequenceAddInPolygon && sequenceAddInPolygon <= 9);
            points.put(sequenceAddInPolygon, new PointCCoAClass(x, y));
            x++;
        }

        private void toNextRowY() {
            y++;
            x = 0;
        }
    }

    @Override
    public int getCountPoints() {
        return pointCCoAS.size();
    }

    @Override
    public PointCCoA getPoint(int index) {//TEST+
        return this.pointCCoAS.get(index);
    }

    @Override
    public void addPoint(PointCCoA newPointCCoA) {
        assert(newPointCCoA != null);
        this.pointCCoAS.add(newPointCCoA.clone());
    }

    @Override
    public void addPoint(PointCCoA[] pointCCoAS) {
        for (PointCCoA pointCCoA : pointCCoAS) {
            this.addPoint(pointCCoA);
        }
    }

    @Override
    public void addPoint(List<PointCCoA> pointCCoAS) {
        for (PointCCoA pointCCoA : pointCCoAS) {
            this.addPoint(pointCCoA);
        }
    }

    @Override
    public void addAllPoint(List<PointCCoA> newPointCCoAS) {
        assert(newPointCCoAS != null);
        this.pointCCoAS.addAll(newPointCCoAS);
    }

    @Override
    public void insertPoint(int index, PointCCoA newPointCCoA) {
        this.pointCCoAS.add(index, newPointCCoA);
    }

    @Override
    public void setPoint(int index, PointCCoA newPointCCoA) {
        this.pointCCoAS.set(index, newPointCCoA);
    }


    @Override
    public void rotateRelative(PointCCoA origin, double angle) { //FIXME TEST ADD
        for (int i = 0; i < this.getCountPoints(); i++) {
            this.setPoint(i, this.getPoint(i).getRotateRelative(origin, angle));
        }
    }

    @Override
    public void deposeOn(PointCCoA vector) { //FIXME TEST ADD
        for (int i = 0; i < this.getCountPoints(); i++) {
            PointCCoA currentPointCCoA = this.getPoint(i);
            PointCCoA newPointCCoA = new PointCCoAClass(
                    currentPointCCoA.getX() + vector.getX(),
                    currentPointCCoA.getY() + vector.getY()
            );
            this.setPoint(i, newPointCCoA);
        }
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < this.getCountPoints(); i++) {
            result += this.getPoint(i) + " ";
        }

        return "[" + result + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;


        PolygonCCoA other = (PolygonCCoA) obj;

        if (this.pointCCoAS.size() != other.getCountPoints()) {
            return false;
        }

        for (int i = 0; i < this.pointCCoAS.size(); i++) {
            PointCCoA pointCCoAFromThis = this.pointCCoAS.get(i);
            PointCCoA pointCCoAFromOther = other.getPoint(i);
            if (!pointCCoAFromThis.equals(pointCCoAFromOther)) {
                return false;
            }
        }

        return true;
    }


    @Override
    public int hashCode() {
        int twoPow32 = 2147483647;
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (this.pointCCoAS.hashCode() % twoPow32);
        result = prime * result + prime;
        return result;
    }


    @Override
    public PolygonCCoA clone() {
        List<PointCCoA> cloneList = new ArrayList<PointCCoA>(this.pointCCoAS);
        return new PolygonCCoAClass(cloneList);
    }

    @Override
    public boolean contains(PointCCoA desirededPointCCoA) {
        return this.pointCCoAS.contains(desirededPointCCoA);
    }

    @Override
    public boolean enteringPoint(PointCCoA testedPointCCoA) {//FIXME TEST+labirint + validate polygon
        boolean isPointIsVertex = this.contains(testedPointCCoA);
        if (isPointIsVertex || perimeterContainsPoint(testedPointCCoA)) {
            return true;
        }

        return jtc_contains_in_polygon(testedPointCCoA);
    }

    @Override
    public boolean intersectionPolygon(PolygonCCoA secondPolygon) {

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
    public boolean intersectionLine(PointCCoA startLine, PointCCoA endLine) {
        int indexLastPoint = this.getCountPoints() - 1;
        PointCCoA startLocalLine = this.getPoint(indexLastPoint);
        PointCCoA endLocalLine = this.getPoint(0);


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
    public boolean intersectionLine(LineCut lineCut) {
        return intersectionLine(lineCut.getStart(), lineCut.getEnd());
    }

    @Override
    public boolean isLiesInsideThe(Round round) {
        for (int i = 0; i < this.getCountPoints(); i++) {
            PointCCoA current = this.getPoint(i);
            if (!round.isIncludes(current)) {
                return false;
            }
        }
        return true;
    }


    private boolean intersectionLines(
            PointCCoA aStartLine,
            PointCCoA aEndLine,
            PointCCoA bStartLine,
            PointCCoA bEndLine
    ) {
        LineCut lineCut = new LineCutClass(aStartLine, aEndLine);
        LineCut lineCut2 = new LineCutClass(bStartLine, bEndLine);
        return lineCut.intersectionLine(lineCut2);
    }

    @Override
    public PointCCoA getCenterAverage() {
        double xSum = 0;
        double ySum = 0;
        for (PointCCoA pointCCoA : this.pointCCoAS) {
            xSum += pointCCoA.getX();
            ySum += pointCCoA.getY();
        }

        double xAverage = xSum / this.getCountPoints();
        double yAverage = ySum / this.getCountPoints();

        return new PointCCoAClass(xAverage, yAverage);
    }

    @Override
    public Double[] getFormatDoubleArray() {
        Double[] arrayDouble = new Double[this.getCountPoints() * 2];

        int indexWriteInDoubleArray = 0;
        for (int i = 0; i < this.getCountPoints(); i++) {
            PointCCoA currentPointCCoA = this.getPoint(i);


            arrayDouble[indexWriteInDoubleArray] = currentPointCCoA.getX();
            indexWriteInDoubleArray++;
            arrayDouble[indexWriteInDoubleArray] = currentPointCCoA.getY();
            indexWriteInDoubleArray++;
        }

        return arrayDouble;
    }


    //==== <start> <Private_Methods> =======================================================================
    private boolean perimeterContainsPoint(PointCCoA testedPointCCoA) {
        for (int i = 0; i < this.pointCCoAS.size(); i++) {
            int indexEndLine = indexEndLine(i);
            PointCCoA startLine = this.pointCCoAS.get(i);
            PointCCoA endLine = this.pointCCoAS.get(indexEndLine);
            LineCut lineCut = new LineCutClass(startLine, endLine);

            boolean lineContainPoint = lineCut.intersection(testedPointCCoA);
            if (lineContainPoint) {
                return true;
            }
        }

        return false;
    }

    private int indexEndLine(int i) {
        int indexLastItem = this.pointCCoAS.size() - 1;
        int indexEndLine = i + 1;
        boolean isGoingBeyondBoundary = indexEndLine > indexLastItem;
        if (isGoingBeyondBoundary) {
            indexEndLine = 0;
        }
        return indexEndLine;
    }

    private boolean jtc_contains_in_polygon(PointCCoA testedPointCCoA) {
        int sizeForClosedPolygon = this.pointCCoAS.size() + 1;
        Coordinate[] coordinates = new Coordinate[sizeForClosedPolygon];

        for (int i = 0; i < this.pointCCoAS.size(); i++) {
            PointCCoA current = this.pointCCoAS.get(i);
            coordinates[i] = new Coordinate(current.getX(), current.getY(), 0);
        }
        PointCCoA first = this.pointCCoAS.get(0);
        coordinates[sizeForClosedPolygon - 1] = new Coordinate(first.getX(), first.getY(), 0);

        Geometry polygonGeometry = new GeometryFactory().createPolygon(coordinates);
        Geometry point = new GeometryFactory().createPoint(new Coordinate(testedPointCCoA.getX(), testedPointCCoA.getY()));
        return polygonGeometry.contains(point);
    }

    private boolean enteringMinimumOnePointOfFirstPolygonInSecondPolygon(
            PolygonCCoA firstPolygon,
            PolygonCCoA secondPolygon
    ) {
        for (int i = 0; i < firstPolygon.getCountPoints(); i++) {
            PointCCoA currentPointCCoA = firstPolygon.getPoint(i);

            if (secondPolygon.enteringPoint(currentPointCCoA)) {
                return true;
            }
        }

        return false;
    }

    private boolean intersectionMinumumTwoLineSegmentOfTwoPolygons(
            PolygonCCoA firstPolygon,
            PolygonCCoA secondPoylgon
    ) { //FIXME codestyle
        int indexLastPoint = firstPolygon.getCountPoints() - 1;
        PointCCoA startLine1 = firstPolygon.getPoint(indexLastPoint);
        PointCCoA endLine1 = firstPolygon.getPoint(0);


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

