package GUI.ExecutionTaskRendering.BasicFeaturesJava;

import GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingBody;
import GUI.StatementTaskRendering.TypeLandscapeBody;
import Logic.FootprintSpaceTime.PolygonExtended;

import java.awt.*;

public class RenderingBodyClass implements RenderingBody {

    private PolygonExtended polygonExtended;

    //FIXME create rand id

    public RenderingBodyClass(PolygonExtended polygonExtended) {
        this.polygonExtended = polygonExtended;
    }


    @Override
    public TypeLandscapeBody getTypePhisicalBody() {
        return null;
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public void renderingYourself(Graphics g) {
        int[] xPoints = new int[this.polygonExtended.countPoints()];
        int[] yPoints = new int[this.polygonExtended.countPoints()];

        for (int i = 0; i < this.polygonExtended.countPoints(); i++) {
            xPoints[i] = this.polygonExtended.getPoint(i).getX();
            yPoints[i] = this.polygonExtended.getPoint(i).getY();
        }

        g.fillPolygon(xPoints, yPoints, xPoints.length);
    }
}
