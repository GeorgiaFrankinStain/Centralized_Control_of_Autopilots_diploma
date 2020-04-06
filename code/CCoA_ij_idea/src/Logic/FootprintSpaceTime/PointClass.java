package Logic.FootprintSpaceTime;

public class PointClass implements Point {
    private double x;
    private double y;

    public PointClass(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public void setX(double  x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public Point clone() {
        return new PointClass(this.getX(), this.getY());
    }


    @Override
    public int hashCode() {
        int  twoPow32 = 2147483647;
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



    /**
     * (https://en.wikipedia.org/wiki/Cross_product)
     * (https://stackoverflow.com/questions/1560492/how-to-tell-whether-a-point-is-to-the-right-or-left-side-of-a-line)
     * Use the sign of the determinant of vectors (AB,AM), where M(X,Y) is the query point:
     * position = sign((Bx - Ax) * (Y - Ay) - (By - Ay) * (X - Ax))
     */
    @Override
    public boolean isLeftRelative(Point startLine, Point endLine){
        return ((endLine.getX() - startLine.getX())*(this.getY() - startLine.getY()) -
                        (endLine.getY() - startLine.getY())*(this.getX() - startLine.getX())) > 0;
    }

    @Override
    public double getAngleRotareRelative(Point origin) {
        Point vector = new PointClass(
                this.getX() - origin.getX(),
                this.getY() - origin.getY()
        );
        return Math.atan2(vector.getY(), vector.getX());
    }

    @Override
    public Point getRotareRelative(Point origin, double angle) {
        double rotatedX = Math.cos(angle) * (this.getX() - origin.getX()) - Math.sin(angle) * (this.getY()-origin.getY()) + origin.getX();
        double rotatedY = Math.sin(angle) * (this.getX() - origin.getX()) + Math.cos(angle) * (this.getY() - origin.getY()) + origin.getY();

        return new PointClass((int) rotatedX, (int) rotatedY);
    }

    @Override
    public int getQuarter(Point origin) {
        Point radiusVertor = new PointClass(
                this.getX() - origin.getX(),
                this.getY() - origin.getY()
        );

        boolean isRight = radiusVertor.getX() >= 0; //FIXME situation point on line
        boolean isLeft = !isRight;
        boolean isTop = radiusVertor.getY() >= 0;
        boolean isBottom = !isTop;

        if (isRight && isTop) {
            return 0;
        } else if (isLeft && isTop) {
            return 1;
        } else if (isLeft && isBottom) {
            return  2;
        } else if (isRight && isBottom) {
            return 3;
        }

        assert (false);
        return -1;
    }
}
