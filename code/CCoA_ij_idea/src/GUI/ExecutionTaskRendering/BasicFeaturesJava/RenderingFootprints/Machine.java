package GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingFootprints;

import GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingFootprint;
import GUI.StatementTaskRendering.DataFootprintForRendering;
import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.GlobalVariable;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Machine extends Pane implements RenderingFootprint {
    Rectangle rectangle;

    public Machine(DataFootprintForRendering newProperties) {
        setTranslateX(newProperties.getPosition().getCoordinats().getX());
        setTranslateY(newProperties.getPosition().getCoordinats().getY());
        this.rectangle = createRectangle(newProperties.getMovingObject().getPolygonExtended());
        getChildren().addAll(this.rectangle);
    }


    @Override
    public void update(long now, DataFootprintForRendering newProperties) {
        setTranslateX(newProperties.getPosition().getCoordinats().getX());
        setTranslateY(newProperties.getPosition().getCoordinats().getY());
    }


    //==== <start> <Private_Methods> =======================================================================
    private Rectangle createRectangle(PolygonExtended formMachine) {
        if (formMachine.getCountPoints() != 4) {
            assert(false);
            return null;
        }


        double width = formMachine.getPoint(1).getX() - formMachine.getPoint(0).getX();
        double height = formMachine.getPoint(3).getY() - formMachine.getPoint(0).getY();

        if (GlobalVariable.equalsNumber(width, 0)
                    || GlobalVariable.equalsNumber(height, 0)) {
            assert(false);
            return null;
        }

        return new Rectangle(width, height, Color.RED);
    }

    //==== <end> <Private_Methods> =======================================================================

}
