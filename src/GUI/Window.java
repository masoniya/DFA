package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Window extends Application {

    public static void main(String[] args) {
        System.setProperty("org.graphstream.ui.renderer","org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            primaryStage.setTitle("Registration Form FXML Application");
            Scene scene =new Scene(root, 800, 500);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
