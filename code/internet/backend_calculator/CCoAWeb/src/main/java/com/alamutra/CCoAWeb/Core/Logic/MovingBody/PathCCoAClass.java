package com.alamutra.CCoAWeb.Core.Logic.MovingBody;

import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.PointCCoA;

import java.util.ArrayList;
import java.util.List;

public class PathCCoAClass implements PathCCoA {
    private List<PointCCoA> pointCCoAS = new ArrayList<PointCCoA>();


    public PathCCoAClass() {
    }
    public PathCCoAClass(List<PointCCoA> pointCCoAS) {
        this.pointCCoAS = cloneArray(pointCCoAS);
    }


    @Override
    public int getSize() {
        return this.pointCCoAS.size();
    }

    @Override
    public PointCCoA getPoint(int index) {
        return this.pointCCoAS.get(index);
    }

    @Override
    public PointCCoA getPointLast() {
        return this.pointCCoAS.get(pointCCoAS.size() - 1);
    }

    @Override
    public void addPoint(PointCCoA pointCCoA) {
        this.pointCCoAS.add(pointCCoA.clone());
    }

    @Override
    public void addPoint(int index, PointCCoA pointCCoA) {
        this.pointCCoAS.add(index, pointCCoA.clone());
    }


    @Override
    public void deposeOn(PointCCoA vector) {
        for (int i = 0; i < pointCCoAS.size(); i++) {
            pointCCoAS.get(i).deposeOn(vector);
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


        PathCCoA other = (PathCCoA) obj;

        if (other.getSize() != this.getSize()) {
            return false;
        }

        for (int i = 0; i < this.pointCCoAS.size(); i++) {
            PointCCoA otherPointCCoA = other.getPoint(i);
            PointCCoA ourPointCCoA = this.getPoint(i);
            if (!ourPointCCoA.equals(otherPointCCoA)) {
                return false;
            }
        }

        return true;
    }
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < pointCCoAS.size(); i++) {
            res += pointCCoAS.get(i) + " ";
        }

        return res; //FIXME ADD TEST ON SEAD NOW NOW
    }

    private List<PointCCoA> cloneArray(List<PointCCoA> input) {
        List<PointCCoA> clone = new ArrayList<>();

        for (PointCCoA pointCCoA : input) {
            clone.add(pointCCoA.clone());
        }

        return clone;
    }

}

