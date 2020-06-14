package GUI.ExecutionTaskRendering.BasicFeaturesJava;

import Logic.FootprintSpaceTime.Point;
import Logic.FootprintSpaceTime.PointClass;
import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.FootprintSpaceTime.PolygonExtendedClass;

public class DisplaingSpacesClass {
    private static double kStrenchings = 1; //FIXME DON'T WORK with 2, collision image

    public static PolygonExtended resiseOccupiedPlace(PolygonExtended place) { //FIXME STATIC
        PolygonExtended resizePolygon = new PolygonExtendedClass();
        for (int i = 0; i < place.getCountPoints(); i++) {
            Point resizePoint = new PointClass(
                    place.getPoint(i).getX() * kStrenchings,
                    place.getPoint(i).getY() * kStrenchings
            );
            resizePolygon.addPoint(resizePoint);
        }

        return resizePolygon;
    }
}
