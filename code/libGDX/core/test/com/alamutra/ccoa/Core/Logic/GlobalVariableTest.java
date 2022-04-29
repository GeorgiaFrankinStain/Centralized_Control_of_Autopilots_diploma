package com.alamutra.ccoa.Core.Logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GlobalVariableTest {

    private double lengthDiapason = 10;
    private double offset = -5;

    @Test
    void getNumberDiapason_0_0() {
        assertEquals(0, GlobalVariable.getNumberDiapason(0, lengthDiapason));
    }

    @Test
    void getNumberDiapason_1_10() {
        assertEquals(1, GlobalVariable.getNumberDiapason(10, lengthDiapason));
    }

    @Test
    void getNumberDiapason_m1_m10() {
        assertEquals(-1, GlobalVariable.getNumberDiapason(-10, lengthDiapason));
    }

    @Test
    void getNumberDiapason_0_1() {
        assertEquals(0, GlobalVariable.getNumberDiapason(1, lengthDiapason));
    }

    @Test
    void getNumberDiapason_m1_m1() {
        assertEquals(-1, GlobalVariable.getNumberDiapason(-1, lengthDiapason));
    }

    @Test
    void getNumberDiapason_m2_m11() {
        assertEquals(-2, GlobalVariable.getNumberDiapason(-11, lengthDiapason));
    }

    @Test
    void leftBorderDiapason_0_0() {
        assertEquals(0, GlobalVariable.getLeftBorderDiapason(0, lengthDiapason));
    }

    @Test
    void leftBorderDiapason_0_1() {
        assertEquals(0, GlobalVariable.getLeftBorderDiapason(1, lengthDiapason));
    }

    @Test
    void leftBorderDiapason_m1_m10() {
        assertEquals(-10, GlobalVariable.getLeftBorderDiapason(-1, lengthDiapason));
    }

    @Test
    void leftBorderDiapason_m11_m20() {
        assertEquals(-20, GlobalVariable.getLeftBorderDiapason(-11, lengthDiapason));
    }

    @Test
    void leftBorderDiapason_11_10() {
        assertEquals(10, GlobalVariable.getLeftBorderDiapason(11, lengthDiapason));
    }

    /*
     *
     *              n-1          n          n+1          n+2          n+3          n+4          n+5
     *       |--------*---|--*------*--|--*------*--|------------|------------|------------|------------|
     *               -1      0      1     1      2  |
     *                                       |offset|
     *                                       |      | <- point end offset diapason
     *       point start offset diapason  -> |
     *                                       |
     *                                       |
     *                                       |
     *       n-1          n          n+1     |    n+2          n+3          n+4          n+5
     * |-----------|--*------*--|---*-----*--|---*--------|------------|------------|------------|
     *               -1      0      1     1      2
     *
     */


    @Test
    void getNumberOffsetDiapason_0_1() {
        assertEquals(0, GlobalVariable.getNumberOffsetDiapason(1, lengthDiapason, offset));
    }

    @Test
    void getNumberOffsetDiapason_0_m1() {
        assertEquals(0, GlobalVariable.getNumberOffsetDiapason(-1, lengthDiapason, offset));
    }

    @Test
    void getNumberOffsetDiapason_0_m5() {
        assertEquals(0, GlobalVariable.getNumberOffsetDiapason(-5, lengthDiapason, offset));
    }

    @Test
    void getNumberOffsetDiapason_1_6() {
        assertEquals(1, GlobalVariable.getNumberOffsetDiapason(6, lengthDiapason, offset));
    }

    @Test
    void getNumberOffsetDiapason_1_13() {
        assertEquals(1, GlobalVariable.getNumberOffsetDiapason(13, lengthDiapason, offset));
    }

    @Test
    void getNumberOffsetDiapason_2_16() {
        assertEquals(2, GlobalVariable.getNumberOffsetDiapason(16, lengthDiapason, offset));
    }

    @Test
    void getNumberOffsetDiapason_m1_m6() {
        assertEquals(-1, GlobalVariable.getNumberOffsetDiapason(-6, lengthDiapason, offset));
    }


    @Test
    void getNumberOffsetDiapason_m1_m9() {
        assertEquals(-1, GlobalVariable.getNumberOffsetDiapason(-9, lengthDiapason, offset));
    }

    @Test
    void getNumberOffsetDiapason_m1_m11() {
        assertEquals(-1, GlobalVariable.getNumberOffsetDiapason(-11, lengthDiapason, offset));
    }

    @Test
    void getNumberOffsetDiapason_m1_m10() {
        assertEquals(-1, GlobalVariable.getNumberOffsetDiapason(-10, lengthDiapason, offset));
    }

    @Test
    void getNumberOffsetDiapason_m1_m13() {
        assertEquals(-1, GlobalVariable.getNumberOffsetDiapason(-13, lengthDiapason, offset));
    }

    @Test
    void getNumberOffsetDiapason_m2_m16() {
        assertEquals(-2, GlobalVariable.getNumberOffsetDiapason(-16, lengthDiapason, offset));
    }

    @Test
    void getLeftBorderOffsetDiapason_m5_0() {
        assertEquals(-5, GlobalVariable.getLeftBorderOffsetDiapason(0, lengthDiapason, offset));
    }

    @Test
    void getLeftBorderOffsetDiapason_m5_m4() {
        assertEquals(-5, GlobalVariable.getLeftBorderOffsetDiapason(-4, lengthDiapason, offset));
    }

    @Test
    void getLeftBorderOffsetDiapason_m5_m5() {
        assertEquals(-5, GlobalVariable.getLeftBorderOffsetDiapason(-5, lengthDiapason, offset));
    }

    @Test
    void getLeftBorderOffsetDiapason_m15_m6() {
        assertEquals(-15, GlobalVariable.getLeftBorderOffsetDiapason(-6, lengthDiapason, offset));
    }

    @Test
    void getLeftBorderOffsetDiapason_m15_m15() {
        assertEquals(-15, GlobalVariable.getLeftBorderOffsetDiapason(-15, lengthDiapason, offset));
    }

}