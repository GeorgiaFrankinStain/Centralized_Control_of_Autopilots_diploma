package Logic.FootprintSpaceTime;

import GUI.StatementTaskRendering.TypePhisicalBody;

public class PhisicalBodyClass implements PhisicalBody {
    private PolygonExtended polygonExtended;

    public PhisicalBodyClass(PolygonExtended polygonExtended) {
        this.polygonExtended = polygonExtended;
    }

    @Override
    public PolygonExtended getArea() {
        return this.polygonExtended;
    }

    @Override
    public TypePhisicalBody getTypePhisicalBody() {
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
}
