package com.CCoABackendCalculate.CCoA.Core.Wrappers;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomWrapperClass implements RandomWrapper {
    private char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    private boolean isNowDebugMode = true;
    private Random random;
    private static Map<Integer, Integer> counters = new HashMap<Integer, Integer>();


    public RandomWrapperClass(int seedDebug) {
        if (this.isNowDebugMode) {
            this.random = debug(seedDebug);
        } else {
            this.random = realise();
        }
    }


    @Override
    public int nextInt() {
        return this.random.nextInt();
    }

    @Override
    public String nextString(int length) {
        String generatedString;
        if (this.isNowDebugMode) {
            generatedString =
                    RandomStringUtils.random(length, 0, 0, false, false, this.chars, this.random);
        } else {
            generatedString = RandomStringUtils.randomAlphanumeric(length);
        }
        return generatedString;
    }

    private Random debug(int seedDebug) {
        increment(seedDebug);
        return new Random(this.counters.get(seedDebug));
    }
    private void increment(int seedDebug) {
        int currentValue = 0;
        if (this.counters.containsKey(seedDebug)) {
            currentValue = this.counters.get(seedDebug);
        }

        this.counters.put(seedDebug, currentValue + 1);
    }

    private Random realise() {
        return new Random();
    }
}

