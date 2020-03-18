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



    @Override
    public int hashCode() {
        long twoPow32 = 2147483647;
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (this.getY() % twoPow32);
        result = prime * result + (int) (this.getY() / twoPow32);
        result = prime * result + (int) (this.getX() % twoPow32);
        result = prime * result + (int) (this.getX() / twoPow32);
        result = prime * result + prime;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;



        Point other = (Point) obj;
        return (this.getX() == other.getX())
                       && (this.getY() == other.getY());
    }
}
