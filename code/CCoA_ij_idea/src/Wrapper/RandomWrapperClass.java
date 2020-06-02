package Wrapper;

import java.util.Random;

public class RandomWrapperClass implements RandomWrapper {
    private Random random = new Random();


    @Override
    public int nextInt() {
        return this.random.nextInt();
    }
}
