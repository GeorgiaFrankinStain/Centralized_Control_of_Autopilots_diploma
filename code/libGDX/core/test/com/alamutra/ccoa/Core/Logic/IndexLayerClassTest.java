package com.alamutra.ccoa.Core.Logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexLayerClassTest {

    @Test
    void testToString() {

        IndexLayer indexLayer = new IndexLayerClass(0);
        String actual = indexLayer.toString();
        String expected = "zIndex: 0";
        assertEquals(expected, actual);
    }


    @Test
    void getZIndex() {
        for (int i = -10; i < 10; i++) {
            IndexLayer indexLayer = new IndexLayerClass(i);
            assertEquals(i, indexLayer.getZIndex());
        }
    }

    @Test
    void testEquals_0() {
        IndexLayer indexLayer = new IndexLayerClass(0);
        IndexLayer indexLayer2 = new IndexLayerClass(0);
        assertEquals(indexLayer, indexLayer2);
    }

    @Test
    void testEquals_1() {
        IndexLayer indexLayer = new IndexLayerClass(1);
        IndexLayer indexLayer2 = new IndexLayerClass(1);
        assertEquals(indexLayer, indexLayer2);
    }

    @Test
    void testEquals_yourself() {
        IndexLayer indexLayer = new IndexLayerClass(1);
        assertEquals(indexLayer, indexLayer);
    }

    @Test
    void testEquals_notEquals() {
        IndexLayer indexLayer = new IndexLayerClass(0);
        IndexLayer indexLayer2 = new IndexLayerClass(1);
        assertNotEquals(indexLayer, indexLayer2);
    }

    @Test
    void testEquals_notEqualsOtherOrder() {
        IndexLayer indexLayer = new IndexLayerClass(0);
        IndexLayer indexLayer2 = new IndexLayerClass(1);
        assertNotEquals(indexLayer2, indexLayer);
    }

    @Test
    void compareTo_firstIsSmallerThanSecond() {
        Comparable<IndexLayer> first = new IndexLayerClass(0);
        IndexLayer second = new IndexLayerClass(1);
        boolean isFirstIsSmallerThanSecond = first.compareTo(second) == 1;
        assert(isFirstIsSmallerThanSecond);
    }

    @Test
    void compareTo_0() {
        Comparable<IndexLayer> first = new IndexLayerClass(0);
        IndexLayer second = new IndexLayerClass(0);

        boolean isFirstIsEqualSecond = first.compareTo(second) == 0;
        assert(isFirstIsEqualSecond);
    }

    @Test
    void compareTo_1() {
        Comparable<IndexLayer> first = new IndexLayerClass(1);
        IndexLayer second = new IndexLayerClass(1);

        boolean isFirstIsEqualSecond = first.compareTo(second) == 0;
        assert(isFirstIsEqualSecond);
    }

    @Test
    void compareTo_yourself() {
        IndexLayer firstIndex = new IndexLayerClass(1);
        Comparable<IndexLayer> firstComparable = (Comparable<IndexLayer>) firstIndex;

        boolean isFirstIsEqualSecond = firstComparable.compareTo(firstIndex) == 0;
        assert(isFirstIsEqualSecond);
    }

    @Test
    void compareTo_firstIsBiggerThanSecond() {
        Comparable<IndexLayer> first = new IndexLayerClass(1);
        IndexLayer second = new IndexLayerClass(0);
        boolean isFirstIsBiggerThanSecond = first.compareTo(second) == -1;
        assert(isFirstIsBiggerThanSecond);
    }

    @Test
    void compareTo_notEqualsOtherOrder() {
        IndexLayer indexLayer = new IndexLayerClass(0);
        IndexLayer indexLayer2 = new IndexLayerClass(1);
        assertNotEquals(indexLayer2, indexLayer);
    }

    //=======================


    @Test
    void testHashCode_0() {
        IndexLayer indexLayer = new IndexLayerClass(0);
        IndexLayer indexLayer2 = new IndexLayerClass(0);
        assertEquals(indexLayer.hashCode(), indexLayer2.hashCode());
    }

    @Test
    void testHashCode_1() {
        IndexLayer indexLayer = new IndexLayerClass(1);
        IndexLayer indexLayer2 = new IndexLayerClass(1);
        assertEquals(indexLayer.hashCode(), indexLayer2.hashCode());
    }

    @Test
    void testHashCode_yourself() {
        IndexLayer indexLayer = new IndexLayerClass(1);
        assertEquals(indexLayer.hashCode(), indexLayer.hashCode());
    }

    @Test
    void testHashCode_notEquals() {
        IndexLayer indexLayer = new IndexLayerClass(0);
        IndexLayer indexLayer2 = new IndexLayerClass(1);
        assertNotEquals(indexLayer.hashCode(), indexLayer2.hashCode());
    }

    @Test
    void testHashCode_notEqualsOtherOrder() {
        IndexLayer indexLayer = new IndexLayerClass(0);
        IndexLayer indexLayer2 = new IndexLayerClass(1);
        assertNotEquals(indexLayer2.hashCode(), indexLayer.hashCode());
    }
}