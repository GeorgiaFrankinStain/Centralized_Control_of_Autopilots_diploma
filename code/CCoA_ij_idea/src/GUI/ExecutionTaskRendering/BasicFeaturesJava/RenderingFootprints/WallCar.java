package GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingFootprints;

import GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingFootprint;
import GUI.StatementTaskRendering.DataFootprintForRendering;
import Logic.FootprintSpaceTime.PolygonExtended;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class WallCar extends Pane implements RenderingFootprint {
    Rectangle rectangle;

    public WallCar(DataFootprintForRendering newProperties) {
        setTranslateX(newProperties.getPosition().getCoordinats().getX());
        setTranslateY(newProperties.getPosition().getCoordinats().getY());
        getChildren().addAll(rectangle);


        this.rectangle = createRectangle(newProperties.getMovingObject().getPolygonExtended());
    }


    @Override
    public void update(long now, DataFootprintForRendering newProperties) {
        setTranslateX(newProperties.getPosition().getCoordinats().getX());
        setTranslateY(newProperties.getPosition().getCoordinats().getY());
    }


    //==== <start> <Private_Methods> =======================================================================
    private Rectangle createRectangle(PolygonExtended formMachine) {




        double width = 0;
        double height = 0;
        return new Rectangle(width, height, Color.BLACK);
    }

    //==== <end> <Private_Methods> =======================================================================

}

