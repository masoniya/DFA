package GUI;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import core.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;


public class AutomationMenu {

    StateSet q;
    State intialstate;
    Alphabet sigma;
    StateSet finalstates;
    TransitionTable delta;

    int transition_number;

    @FXML
    AnchorPane anchorpane;

    @FXML
    TextField number_of_states;

    @FXML
    ComboBox<String> alpha_list;

    @FXML
    TextField custom_alpha;

    @FXML
    TextField initial_state;

    @FXML
    TextField number_of_final_states;

    @FXML
    TextField number_of_transition;

    @FXML
    TextField current_state;

    @FXML
    TextField transition;

    @FXML
    TextField next_state;


    @FXML
    public void onclick()
    {
        custom_alpha.setDisable(false);

    }
    @FXML
    public void okOncick(ActionEvent action)
    {
        transition_number=Integer.parseInt(number_of_transition.getText());
        current_state.setDisable(false);
        transition.setDisable(false);
        next_state.setDisable(false);
    }

    int counter=1;
    @FXML
    public void addonclick(ActionEvent action)
    {
        if(counter == transition_number)
        {
            current_state.setDisable(true);
            transition.setDisable(true);
            next_state.setDisable(true);
        }
        else
        {
            current_state.setText("");
            transition.setText("");
            next_state.setText("");

            current_state.getText();
            transition.getText();
            next_state.getText();
        }
        counter++;
    }

    @FXML
    public void nextonclick(ActionEvent event) throws IOException {
        int n = Integer.parseInt(number_of_states.getText());
        q = new StateSet();
        for(int i = 0; i < n; i++){
            q.addState("q" + String.valueOf(i));
        }


        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Testing Window.fxml"));
        Parent root=loader.load();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
