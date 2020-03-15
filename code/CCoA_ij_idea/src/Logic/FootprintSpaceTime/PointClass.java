package Logic.FootprintSpaceTime;

public class PointClass implements Point {
    private long x;
    private long y;

    public PointClass(long x, long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public long getX() {
        return x;
    }

    @Override
    public void setX(long x) {
        this.x = x;
    }

    @Override
    public long getY() {
        return y;
    }

    @Override
    public void setY(long y) {
        this.y = y;
    }
}
