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
import java.lang.invoke.SwitchPoint;

public class TestingWindow {
    @FXML
    TextField checking_String;

    @FXML
    Label truthlabel;

    @FXML
    Label trueorfalse;

    DFA dfa;
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
            trueorfalse.setText("belonging");

        }
        else
            trueorfalse.setText("not belonging");
    }
    @FXML
    public void checkphp(ActionEvent event)
    {

        String checking = checking_String.getText();
        if(dfa.start(checking))
        {
            trueorfalse.setText("Accepted");
            if (isNumeric(checking)) {
                truthlabel.setText("number");
            }
            char c = 0, b = 0, a = 0, d = 0;
            if (checking.length() > 1) {
                c = checking.charAt(0);
                int n = checking.length();
                b = checking.charAt(n - 1);
                a = checking.charAt(1);
                d = checking.charAt(n - 2);
            }
            if (c == '$') {
                truthlabel.setText("Variable");
            }
            if (checking.equals("while") || checking.equals("case") || checking.equals("if") || checking.equals("for")) {
                truthlabel.setText("Key word");
            }
            if (c == '/' && a == '*' && d == '*' && b == '/') {
                truthlabel.setText("Comment");
            }

        }
        else {
            trueorfalse.setText("Error , not in the PhP Syntax");
            truthlabel.setText("");
        }
    }


    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
