package com.CCoABackendCalculate.CCoA.Core.Wrappers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomWrapperClassTest {

    private RandomWrapper random = new RandomWrapperClass(62467);
    @Test
    void nextString_1stString() {
        String generatedRandom = random.nextString(10);
        assertEquals("dTeT2omrOT", generatedRandom);
//        String generatedRandom2 = random.nextString(10);
//        assertEquals("VAsIOqbh7J", generatedRandom2);
    }
    @Test
    void nextString_2ndString() {
        String generatedRandom2 = random.nextString(10);
        assertEquals("dTeT2omrOT", generatedRandom2); //FIXME i don't now w
    }
}