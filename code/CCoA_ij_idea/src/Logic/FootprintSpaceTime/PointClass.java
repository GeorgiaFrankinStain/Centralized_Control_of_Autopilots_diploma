package Logic.FootprintSpaceTime;

public class PointClass implements Point {
    private int  x;
    private int  y;

    public PointClass(int  x, int  y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int  getX() {
        return x;
    }

    @Override
    public void setX(int  x) {
        this.x = x;
    }

    @Override
    public int  getY() {
        return y;
    }

    @Override
    public void setY(int  y) {
        this.y = y;
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
}
