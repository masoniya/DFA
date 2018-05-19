package GUI;

import core.DFA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TestingWindow {
    @FXML
    TextField checking_String;

    DFA dfa ;
    public void setDfa(DFA dfa1)
    {
        this.dfa = dfa1;
        System.out.println(dfa.delta);
    }
    @FXML
    public void check(ActionEvent event)
    {
        String checking = checking_String.getText();
        if(dfa.start(checking))
        {
            System.out.println("true");
        }
        else
            System.out.println("false");
    }

}
