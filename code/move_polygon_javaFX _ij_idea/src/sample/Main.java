package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    private Pane gameRoot = new Pane();
    private Pane applicationRoot = new Pane();

    private Parent createContent() {
        gameRoot.setPrefSize(300, 300);
        applicationRoot.getChildren().addAll(gameRoot);

        return applicationRoot;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
