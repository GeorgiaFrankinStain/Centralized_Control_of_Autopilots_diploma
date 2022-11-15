package com.alamutra.CCoAWeb.Core.Wrappers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomWrapperClassTest {

    @Test
    void nextString() {
        RandomWrapper random = new RandomWrapperClass(62467);
        String generatedRandom = random.nextString(10);
        assertEquals("NG0gkKe8yi", generatedRandom);
    }
}