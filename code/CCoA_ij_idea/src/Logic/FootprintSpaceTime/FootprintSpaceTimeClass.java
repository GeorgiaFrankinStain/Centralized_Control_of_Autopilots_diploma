package Logic.FootprintSpaceTime;

import GUI.Rendering.HistChangesFromWhen;

import java.util.List;

public class FootprintSpaceTimeClass implements FootprintSpaceTime, HistChangesFromWhen {
    @Override
    public List<Polygon> getAreaFromWhen(Polygon areaFind, long when) {
        return null;
    }

    @Override
    public List<Polygon> getAreaFromWhen(Polygon areaFind, long when, int level) {
        return null;
    }

    @Override
    public void addPointRadius(int ID, Polygon Place, long time) {

    }

    @Override
    public void addPointRadius(int ID, Polygon Place) {

    }

    @Override
    public void deleteFootprints(int ID) {

    }

    @Override
    public boolean getAccessPlace(Polygon place, long time, int level) {
        return false;
    }

    @Override
    public List<PhisicalBody> getPhysicalBodysFromWhen(Polygon areaFind, long when) {
        return null;
    }

    @Override
    public List<PhisicalBody> getPhysicalBodysFromWhen(Polygon areaFind, long when, int level) {
        return null;
    }
}
