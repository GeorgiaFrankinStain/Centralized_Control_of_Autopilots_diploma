package Logic;

public class GlobalVariable { //FIXME is good? (global variable in java is good?)
    public static final double DOUBLE_COMPARISON_ACCURACY = 0.000000001;
    public static boolean equalsNumber(double one, double two) {
        return Math.abs(one - two) < GlobalVariable.DOUBLE_COMPARISON_ACCURACY;
    }
}
