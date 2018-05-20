package GUI;

import core.DFA;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

import static GUI.Window_Controller.loadFromFile;

public class Loadwindow {

    @FXML
    TextField name;

    @FXML
    public void load(ActionEvent event) throws IOException {

        DFA dfa=loadFromFile("C:\\Users\\ichigo\\Documents\\DFAProject\\"+name.getText()+".json");

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Trsting Window.fxml"));
        Parent root=loader.load();
        TestingWindow testingWindow = loader.getController();
        testingWindow.setDfa(dfa);
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
        dfa.delta.drawDFA(dfa.q);
    }

}
