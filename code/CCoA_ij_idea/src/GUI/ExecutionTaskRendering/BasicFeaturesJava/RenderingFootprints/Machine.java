package GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingFootprints;

import GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingFootprint;
import GUI.StatementTaskRendering.DataFootprintForRendering;
import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.GlobalVariable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.io.File;

public class Machine extends Pane implements RenderingFootprint {
//    Rectangle rectangle;
    ImageView imageView;

    public Machine(DataFootprintForRendering newProperties) {
        setTranslateX(newProperties.getLocalOriginForPointMovingObject().getCoordinats().getX());
        setTranslateY(newProperties.getLocalOriginForPointMovingObject().getCoordinats().getY());

//        Image image = new Image("file:src.png",true);
//        ImageView imageView = new ImageView(image);



        this.imageView = createRectangle(newProperties.getMovingObject().getPolygonExtended());
        getChildren().addAll(imageView);
//        getChildren().addAll(this.rectangle);
    }


    @Override
    public void update(long now, DataFootprintForRendering newProperties) {
        setTranslateX(newProperties.getLocalOriginForPointMovingObject().getCoordinats().getX());
        setTranslateY(newProperties.getLocalOriginForPointMovingObject().getCoordinats().getY()); //FIXME long calls function
        setRotate(newProperties.getLocalOriginForPointMovingObject().getRotationDegree());
    }


    //==== <start> <Private_Methods> =======================================================================
    private ImageView createRectangle(PolygonExtended formMachine) {
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


        ImageView imageView = new ImageView();
        File file = new File("D:\\AF\\2019 - 11 - 03 - Centralized_Control_of_Autopilots__diploma\\code\\CCoA_ij_idea\\sourse_files\\machine.png");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }

    //==== <end> <Private_Methods> =======================================================================

}
