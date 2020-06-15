package GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingFootprints;

import GUI.ExecutionTaskRendering.BasicFeaturesJava.DisplaingSpacesClass;
import GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingFootprint;
import GUI.StatementTaskRendering.DataFootprintForRendering;
import Logic.FootprintSpaceTime.PolygonExtended;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class WallSquare extends Pane implements RenderingFootprint {
    Polygon polygon;

    public WallSquare(DataFootprintForRendering newProperties) {
        setTranslateX(newProperties.getLocalOriginForPointMovingObject().getCoordinats().getX());
        setTranslateY(newProperties.getLocalOriginForPointMovingObject().getCoordinats().getY());


        this.polygon = createPolygonJavaFX(newProperties.getMovingObject().getPolygonExtended());
        getChildren().addAll(this.polygon);
    }


    @Override
    public void update(long now, DataFootprintForRendering newProperties) {
        setTranslateX(newProperties.getLocalOriginForPointMovingObject().getCoordinats().getX());
        setTranslateY(newProperties.getLocalOriginForPointMovingObject().getCoordinats().getY());
    }


    //==== <start> <Private_Methods> =======================================================================
    private Polygon createPolygonJavaFX(PolygonExtended formMachine) {
        Polygon resultPolygon = new Polygon();
        PolygonExtended resizeFormMachne = DisplaingSpacesClass.resiseOccupiedPlace(formMachine); //FIXME STATIC
        resultPolygon.getPoints().addAll(resizeFormMachne.getFormatDoubleArray());
        resultPolygon.setStroke(Color.BLACK);
        return resultPolygon;
    }

    //==== <end> <Private_Methods> =======================================================================

}
