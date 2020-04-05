package Wrapper;

import java.util.Random;

public class RandowWrapperClass implements RandomWrapper {
    private Random random = new Random();


    @Override
    public int nextInt() {
        return this.random.nextInt();
    }
}
