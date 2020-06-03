package Wrapper;

import java.util.Random;

public class RandomWrapperClass implements RandomWrapper {
    private static int counterRandom = 0;
    private Random random = new Random(this.counterRandom); //seed for debug, don't delete seed for debug
//    private Random random = new Random();


    public RandomWrapperClass() {
        this.counterRandom++;
    }


    @Override
    public int nextInt() {
        return this.random.nextInt();
    }
}
