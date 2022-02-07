package com.alamutra.ccoa.Core.Logic.MovingObjects;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoAClass;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PathCCoAClassTest {

    @Test
    void getSize_constructor1() {
        List<PointCCoA> pointCCoAS = new ArrayList<>();
        pointCCoAS.add(new PointCCoAClass(0, 0));
        pointCCoAS.add(new PointCCoAClass(1, 1));

        PathCCoA pathCCoA = new PathCCoAClass(pointCCoAS);
        PointCCoA expected = new PointCCoAClass(0, 0);
        assertEquals(expected, pathCCoA.getPoint(0));
    }

    @Test
    void getSize_constructor2() {
        List<PointCCoA> pointCCoAS = new ArrayList<>();
        pointCCoAS.add(new PointCCoAClass(0, 0));
        pointCCoAS.add(new PointCCoAClass(1, 1));

        PathCCoA pathCCoA = new PathCCoAClass(pointCCoAS);
        PointCCoA expected = new PointCCoAClass(1, 1);
        assertEquals(expected, pathCCoA.getPoint(1));
    }

    @Test
    void getPoint_1() {
        PathCCoA pathCCoA = new PathCCoAClass();
        pathCCoA.addPoint(new PointCCoAClass(1, 1));
        PointCCoA expected = new PointCCoAClass(1, 1);
        assertEquals(expected, pathCCoA.getPoint(0));
    }

    @Test
    void getPoint_protectionFromEditingSourceListPoints() {
        List<PointCCoA> pointCCoAS = new ArrayList<>();
        pointCCoAS.add(new PointCCoAClass(0, 0));
        pointCCoAS.add(new PointCCoAClass(1, 1));

        PathCCoA pathCCoA = new PathCCoAClass(pointCCoAS);

        pointCCoAS.remove(1);

        PointCCoA expected = new PointCCoAClass(1, 1);
        assertEquals(expected, pathCCoA.getPoint(1));
    }

    @Test
    void getPoint_protectionFromEditingSourcePoint() {
        PathCCoA pathCCoA = new PathCCoAClass();
        PointCCoA sourcePointCCoA = new PointCCoAClass(1, 1);
        pathCCoA.addPoint(sourcePointCCoA);
        sourcePointCCoA.setX(13);
        sourcePointCCoA.setY(13);
        PointCCoA expected = new PointCCoAClass(1, 1);
        assertEquals(expected, pathCCoA.getPoint(0));
    }

    @Test
    void addPoint() {
        PathCCoA pathCCoA = new PathCCoAClass();
        pathCCoA.addPoint(new PointCCoAClass(1, 1));
        PointCCoA expected = new PointCCoAClass(1, 1);
        assertEquals(expected, pathCCoA.getPoint(0));
    }

    @Test
    void deposeOn_1st() {
        PathCCoA pathCCoA = new PathCCoAClass();
        pathCCoA.addPoint(new PointCCoAClass(0, 0));
        pathCCoA.addPoint(new PointCCoAClass(1, 1));
        PointCCoA vector = new PointCCoAClass(1, 1);
        pathCCoA.deposeOn(vector);

        assertEquals(new PointCCoAClass(1, 1), pathCCoA.getPoint(0));
    }

    @Test
    void deposeOn_2nd() {
        PathCCoA pathCCoA = new PathCCoAClass();
        pathCCoA.addPoint(new PointCCoAClass(0, 0));
        pathCCoA.addPoint(new PointCCoAClass(1, 1));
        PointCCoA vector = new PointCCoAClass(1, 1);
        pathCCoA.deposeOn(vector);

        assertEquals(new PointCCoAClass(2, 2), pathCCoA.getPoint(1));
    }

    @Test
    void testEquals_empty() {
        PathCCoA first = new PathCCoAClass();
        PathCCoA second = new PathCCoAClass();

        assertEquals(first, second);
    }

    @Test
    void testEquals_1And1() {
        PathCCoA first = new PathCCoAClass();
        first.addPoint(new PointCCoAClass(1, 1));
        PathCCoA second = new PathCCoAClass();
        second.addPoint(new PointCCoAClass(1, 1));

        assertEquals(first, second);
    }

    @Test
    void testEquals_notEquals_order() {
        PathCCoA first = new PathCCoAClass();
        first.addPoint(new PointCCoAClass(0, 0));
        first.addPoint(new PointCCoAClass(1, 1));

        PathCCoA second = new PathCCoAClass();
        second.addPoint(new PointCCoAClass(0, 0));
        second.addPoint(new PointCCoAClass(1, 1));

        assertEquals(first, second);
    }

    @Test
    void testEquals_notEquals_0And1() {
        PathCCoA first = new PathCCoAClass();
        first.addPoint(new PointCCoAClass(0, 0));
        PathCCoA second = new PathCCoAClass();
        second.addPoint(new PointCCoAClass(1, 1));

        assertNotEquals(first, second);
    }

    @Test
    void testEquals_reverseOrder() {
        PathCCoA first = new PathCCoAClass();
        first.addPoint(new PointCCoAClass(0, 0));
        first.addPoint(new PointCCoAClass(1, 1));

        PathCCoA second = new PathCCoAClass();
        second.addPoint(new PointCCoAClass(1, 1));
        second.addPoint(new PointCCoAClass(0, 0));

        assertNotEquals(first, second);
    }

    @Test
    void testEquals_notEquals_emptyAndOnePoint() {
        PathCCoA first = new PathCCoAClass();
        PathCCoA second = new PathCCoAClass();
        second.addPoint(new PointCCoAClass(1, 1));

        assertNotEquals(first, second);
    }

    @Test
    void testEquals_yourself() {
        PathCCoA first = new PathCCoAClass();

        assertEquals(first, first);
    }

    @Test
    void testToString_0Points() {
        PathCCoA first = new PathCCoAClass();
        String actual = first.toString();
        String expected = "";

        assertEquals(expected, actual);
    }

    @Test
    void testToString_1() {
        PathCCoA first = new PathCCoAClass();
        first.addPoint(new PointCCoAClass(1, 1));

        String actual = first.toString();
        String expected = "(x: 1.0   y: 1.0) ";

        assertEquals(expected, actual);
    }

    @Test
    void testToString_2Points() {
        PathCCoA first = new PathCCoAClass();
        first.addPoint(new PointCCoAClass(1, 1));
        first.addPoint(new PointCCoAClass(2, 2));

        String actual = first.toString();
        String expected = "(x: 1.0   y: 1.0) (x: 2.0   y: 2.0) ";

        assertEquals(expected, actual);
    }
}