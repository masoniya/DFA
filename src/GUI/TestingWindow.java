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
            if(isNumeric(checking))
            {
                truthlabel.setText("number");
            }
            char c = checking.charAt(0);
            int n=checking.length();
            char b = checking.charAt(n-1);
            if( c == '$')
            {
                truthlabel.setText("Variable");
            }
            if(checking.equals("while") || checking.equals("case") || checking.equals("if") || checking.equals("for"))
            {
                truthlabel.setText("Key word");
            }
            if(c == '/' && b =='/')
            {
                truthlabel.setText("Comment");
            }

            System.out.println("true");
        }
        else
            System.out.println("false");
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
