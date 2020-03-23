package GUI.ExecutionTaskRendering;

import Logic.FootprintSpaceTime.PhisicalBody;

public interface PoolPhisicalBodys {
    public void addPhisicalBody(PhisicalBody phisicalBody);
    public PhisicalBody getPhisicalBody(int ID);
}
