package GUI.ExecutionTaskRendering.BasicFeaturesJava;


import GUI.StatementTaskRendering.Windows;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WindowsClass implements Windows {

    private int heightGeneralWindowDefault = 600;
    private int widthGeneralWindowDefault = 800;
    private String titleGeneralWindowDefault = "Centralized Control of Autopilots";

    private Stage primaryStage;
    private MapRender mapRender;
    private Pane generalPane = new Pane();




    public WindowsClass(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void createGeneralWindow(MapRender mapRender) {
        //FIXME CRITICAL create panel buttons
        this.mapRender = mapRender;


        generalPane.setPrefSize(widthGeneralWindowDefault, heightGeneralWindowDefault);
        generalPane.getChildren().addAll((Pane) this.mapRender);


        primaryStage.setTitle(this.titleGeneralWindowDefault); //TODO: add title of room
        primaryStage.setScene(new Scene(generalPane));
        primaryStage.show();
    }

    @Override
    public void update(long now) {
        this.mapRender.update(now);
    }


    //==== <start> <Private_Methods> =======================================================================
    //==== <end> <Private_Methods> =========================================================================
}
