package GUI.ExecutionTaskRendering.BasicFeaturesJava.ObjectsOfMapRender;

import GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingFootprint;
import GUI.StatementTaskRendering.DataFootprintForRendering;
import GUI.StatementTaskRendering.TypeLandscapeBody;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Machine extends Pane implements RenderingFootprint {
    Rectangle rectangle = new Rectangle(20, 20, Color.RED);

    public Machine (DataFootprintForRendering newProperties) {

        setTranslateX(newProperties.getPosition().getCoordinats().getX());
        setTranslateY(newProperties.getPosition().getCoordinats().getY());
        getChildren().addAll(rectangle);
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
    public void renderingYourself() {
        //FIXME need?
    }

    @Override
    public void update(long now, DataFootprintForRendering newProperties) {
        setTranslateX(newProperties.getPosition().getCoordinats().getX());
        setTranslateY(newProperties.getPosition().getCoordinats().getY());
    }

}
