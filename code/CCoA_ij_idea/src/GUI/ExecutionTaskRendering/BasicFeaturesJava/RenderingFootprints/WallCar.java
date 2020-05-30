package GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingFootprints;

import GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingFootprint;
import GUI.StatementTaskRendering.DataFootprintForRendering;
import Logic.FootprintSpaceTime.PolygonExtended;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class WallCar extends Pane implements RenderingFootprint {
    Polygon polygon;

    public WallCar(DataFootprintForRendering newProperties) {
        setTranslateX(newProperties.getPosition().getCoordinats().getX());
        setTranslateY(newProperties.getPosition().getCoordinats().getY());


        this.polygon = createPolygonJavaFX(newProperties.getMovingObject().getPolygonExtended());
        getChildren().addAll(this.polygon);
    }


    @Override
    public void update(long now, DataFootprintForRendering newProperties) {
        setTranslateX(newProperties.getPosition().getCoordinats().getX());
        setTranslateY(newProperties.getPosition().getCoordinats().getY());
    }


    //==== <start> <Private_Methods> =======================================================================
    private Polygon createPolygonJavaFX(PolygonExtended formMachine) {
        Polygon resultPolygon = new Polygon();
        resultPolygon.getPoints().addAll(formMachine.getFormatDoubleArray());
        resultPolygon.setStroke(Color.BLACK);
        return resultPolygon;
    }

    //==== <end> <Private_Methods> =======================================================================

}

