package GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingFootprints;

import GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingFootprint;
import GUI.StatementTaskRendering.DataFootprintForRendering;
import GUI.StatementTaskRendering.TypeLandscapeBody;
import Logic.FootprintSpaceTime.PolygonExtended;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Machine extends Pane implements RenderingFootprint {
    Rectangle rectangle;

    public Machine(DataFootprintForRendering newProperties) {
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
        if (formMachine.countPoints() != 4) {
            assert(false);
            return null;
        }


        double width = formMachine.getPoint(1).getX() - formMachine.getPoint(0).getX();
        double height = formMachine.getPoint(3).getY() - formMachine.getPoint(0).getY();

        if (Math.abs(width) < 0.001 || Math.abs(height) < 0.001) { //FIXME MAGIC_NUMBER
            assert(false);
            return null;
        }

        return new Rectangle(width, height, Color.RED);
    }

    //==== <end> <Private_Methods> =======================================================================

}
