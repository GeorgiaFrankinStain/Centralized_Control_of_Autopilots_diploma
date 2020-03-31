package Logic;

import GUI.StatementTaskRendering.TypeLandscapeBody;
import Logic.FootprintSpaceTime.PolygonExtended;

public class PhisicalBodyClass implements PhisicalBody {

    private PolygonExtended polygonExtended;

    //FIXME create rand id

    public PhisicalBodyClass(PolygonExtended polygonExtended) {
        this.polygonExtended = polygonExtended;
    }


    @Override
    public TypeLandscapeBody getTypePhisicalBody() {
        return null;
    } //FIXME

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public PolygonExtended getPolygonExtended() {
        return this.polygonExtended;
    }
}
