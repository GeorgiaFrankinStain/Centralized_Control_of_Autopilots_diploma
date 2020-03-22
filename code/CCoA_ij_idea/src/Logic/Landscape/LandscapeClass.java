package Logic.Landscape;

import GUI.Rendering.HistChangesFromWhen;
import GUI.Rendering.PhisicalBodysFromWhen;
import GUI.Rendering.TypePhisicalBody;
import Logic.FootprintSpaceTime.*;
import Logic.MovingObjects.Path;
import Logic.TypesInLevel;

import java.util.ArrayList;
import java.util.List;

public class LandscapeClass implements Landscape, PhisicalBodysFromWhen, HistChangesFromWhen {
    @Override
    public List<PhisicalBody> getPhisicalBodysFromWhen(PolygonExtended areaFind, int when) {
        //FIXME CRITICAL IMITATION

        PolygonExtended imitationPolygon = new PolygonExtendedClass();

        imitationPolygon.setPoint(new PointClass(0, 0));
        imitationPolygon.setPoint(new PointClass(900, 0));
        imitationPolygon.setPoint(new PointClass(100, 400));
        imitationPolygon.setPoint(new PointClass(0, 400));



        PolygonExtended imitationPolygon2 = new PolygonExtendedClass();

        imitationPolygon2.setPoint(new PointClass(0, 0));
        imitationPolygon2.setPoint(new PointClass(900, 0));
        imitationPolygon2.setPoint(new PointClass(900, 900));
        imitationPolygon2.setPoint(new PointClass(0, 900));

        List<PhisicalBody> resList = new ArrayList<PhisicalBody>();
        resList.add(new PhisicalBodyClass(imitationPolygon2, TypePhisicalBody.GRASS, 234568));
        resList.add(new PhisicalBodyClass(imitationPolygon, TypePhisicalBody.GRASS, 234567));

        return resList;
    }

    @Override
    public List<PhisicalBody> getPhisicalBodysFromWhen(PolygonExtended areaFind, int when, TypesInLevel type) {
        return null;
    }

    @Override
    public void fillArea() {

    }

    @Override
    public int speedInfluenceEnvironmentOnProperties(int speed) {
        return 0;
    }

    @Override
    public List<Point> getPointsDirectAccess(PolygonExtended area) {
        return null;
    }

    @Override
    public Path getDijkstraPath(Point from, Point to) {
        return null;
    }

    @Override
    public PolygonExtended getAreaChangesAfterBefore(int afterTime, int berforeTime) {
        return null;
    }

    @Override
    public int getTimeLastUpdate() {
        return 0;
    }
}
