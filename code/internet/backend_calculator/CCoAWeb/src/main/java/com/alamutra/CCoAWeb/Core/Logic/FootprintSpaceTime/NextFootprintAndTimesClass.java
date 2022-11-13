package com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime;

public class NextFootprintAndTimesClass implements NextFootprintAndTimes { //FIXME add tests
    private double timeNextFootprint;
    private Footprint nextFootprint;
    private double timeKeyFootprint;

    public NextFootprintAndTimesClass(double timeNextFootprint, Footprint nextFootprint, double timeKeyFootprint) {
        this.timeKeyFootprint = timeKeyFootprint;
        this.timeNextFootprint = timeNextFootprint;
        this.nextFootprint = nextFootprint;
    }

    @Override
    public double getTimeOfKeyFootprint() {
        return this.timeKeyFootprint;
    }

    @Override
    public double getTimeOfNextFootprint() {
        return this.timeNextFootprint;
    }

    @Override
    public Footprint getNextFootprint() {
        return this.nextFootprint;
    }
}
