package com.alamutra.ccoa.Core.Wrappers;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomWrapperClass implements RandomWrapper {
    private Random random;
    //    private Random random = new Random();
    private static Map<Integer, Integer> counters = new HashMap<Integer, Integer>();


    public RandomWrapperClass(int seedDebug) {
        this.random = debug(seedDebug);
//        this.random = realise();
    }


    @Override
    public int nextInt() {
        return this.random.nextInt();
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

