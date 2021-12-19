package com.alamutra.ccoa.Logic.MovingObjects;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.Point;
import com.alamutra.ccoa.Wrapper.RandomWrapperClass;

import java.util.ArrayList;
import java.util.List;

public class PathClass implements Path {
    private List<Point> points = new ArrayList<Point>();


    public PathClass() {
    }
    public PathClass(List<Point> points) {
        this.points = cloneArray(points);
    }


    @Override
    public int getSize() {
        return this.points.size();
    }

    @Override
    public Point getPoint(int index) {
        return this.points.get(index);
    }

    @Override
    public void addPoint(Point point) {
        this.points.add(point.clone());
    }

    @Override
    public void addPoint(int index, Point point) {
        this.points.add(index, point.clone());
    }


    @Override
    public void deposeOn(Point vector) {
        for (int i = 0; i < points.size(); i++) {
            points.get(i).deposeOn(vector);
        }
    }

    @Override
    public boolean equals(Object obj) { //FIXME ADD_TEST
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;


        Path other = (Path) obj;

        if (other.getSize() != this.getSize()) {
            return false;
        }

        for (int i = 0; i < this.points.size(); i++) {
            Point otherPoint = other.getPoint(i);
            Point ourPoint = this.getPoint(i);
            if (!ourPoint.equals(otherPoint)) {
                return false;
            }
        }

        return true;
    }
        @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < points.size(); i++) {
            res += points.get(i) + " ";
        }

        return res; //FIXME ADD TEST ON SEAD NOW NOW
    }

    private List<Point> cloneArray(List<Point> input) {
        List<Point> clone = new ArrayList<>();

        for (Point point : input) {
            clone.add(point.clone());
        }

        return clone;
    }

}
