package Logic.FootprintSpaceTime;

import GUI.Rendering.TypePhisicalBody;

public class PhisicalBodyClass implements PhisicalBody {
    PolygonExtended polygon;
    TypePhisicalBody type;
    int ID;
    int level;



    public PhisicalBodyClass(PolygonExtended polygon, TypePhisicalBody type, int ID) {
        this.polygon = polygon;
        this.type = type;
        this.ID = ID;
    }

    @Override
    public PolygonExtended getPolygonExtended() {
        return polygon;
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
