package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Window_Controller {
    public void new_DFA(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("automaton menu.fxml"));
        stage.setScene(new Scene(root));
    }
    public void load_DFA(ActionEvent event)
    {

    }
}
