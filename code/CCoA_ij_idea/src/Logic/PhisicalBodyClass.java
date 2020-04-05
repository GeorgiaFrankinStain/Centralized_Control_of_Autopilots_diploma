package Logic;

import GUI.StatementTaskRendering.TypeLandscapeBody;
import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.FootprintSpaceTime.PolygonExtended;

public class PhisicalBodyClass implements PhisicalBody {

    private PolygonExtended polygonExtended;
    private TypeMachinesBody typeMachinesBody;

    //FIXME create rand id

    public PhisicalBodyClass(PolygonExtended polygonExtended, TypeMachinesBody typeMachinesBody) {
        this.polygonExtended = polygonExtended;
        this.typeMachinesBody = typeMachinesBody;
    }


    @Override
    public String getType() {
        return this.typeMachinesBody.name();
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
