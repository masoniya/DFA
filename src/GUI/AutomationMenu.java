package GUI;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import core.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AutomationMenu implements Initializable {

    StateSet q;
    State intialstate;
    Alphabet sigma;
    StateSet finalstates = new StateSet();;
    TransitionTable delta = new TransitionTable();

    int transition_number,alpha_picker;

    @FXML
    AnchorPane anchorpane;

    @FXML
    TextField number_of_states;


    ObservableList<String> states = FXCollections.observableArrayList();


    @FXML
    ComboBox<String> alpha_list;

    ObservableList<String> alphabet_observablelist = FXCollections.observableArrayList("Custom","Binary","English","Numbers",
            "Symbols", "PHP Alphabet");

    @FXML
    TextField custom_alpha;


    @FXML
    TextField initial_state;

    @FXML
    TextField number_of_final_states;

    @FXML
    ComboBox<String> final_states_menu;

    @FXML
    TextField number_of_transition;

    @FXML
    TextField current_state;

    @FXML
    TextField transition;

    @FXML
    TextField next_state;



    @FXML
    public void numberofstatesconfirmonclick(ActionEvent action)
    {
        int n = Integer.parseInt(number_of_states.getText());
        q = new StateSet();
        for(int i = 0; i < n; i++){
            q.addState("q" + String.valueOf(i));
            states.add("q"+ String.valueOf(i));
        }

    }

    @FXML
    public void onclick()
    {
        if(alpha_list.getValue() == "Custom")
        {
            custom_alpha.setDisable(false);
        }
        if(alpha_list.getValue() == "Binary")
                alpha_picker = 0;
        if(alpha_list.getValue() == "English")
                alpha_picker = 1;
        if(alpha_list.getValue() == "Numbers")
                alpha_picker = 2;
        if(alpha_list.getValue() == "Symbols")
                alpha_picker = 3;
        if(alpha_list.getValue() == "PHP Alphabet")
                alpha_picker = 4;

        sigma = new Alphabet();
        switch(alpha_picker){
            case 0 :
                sigma.addSymbol('0');
                sigma.addSymbol('1');
                break;
            case 1 :
                for(int i = (int)'a'; i < 26 + (int)'a'; i++){
                    sigma.addSymbol((char)i);
                }
                for(int i = (int)'A'; i < 26 + (int)'A'; i++){
                    sigma.addSymbol((char)i);
                }
                break;
            case 2 :
                for(int i = (int)'0'; i < 10 + (int)'0'; i++){
                    sigma.addSymbol((char)i);
                }
                break;
            case 3 :
                sigma.addSymbol('/');
                sigma.addSymbol('*');
                sigma.addSymbol('$');
                sigma.addSymbol('.');
                sigma.addSymbol('(');
                sigma.addSymbol(')');
                sigma.addSymbol('{');
                sigma.addSymbol('}');
                break;
            case 4 :
                for(int i = (int)'a'; i < 26 + (int)'a'; i++){
                    sigma.addSymbol((char)i);
                }
                for(int i = (int)'A'; i < 26 + (int)'A'; i++){
                    sigma.addSymbol((char)i);
                }
                for(int i = (int)'0'; i < 10 + (int)'0'; i++){
                    sigma.addSymbol((char)i);
                }
                sigma.addSymbol('/');
                sigma.addSymbol('*');
                sigma.addSymbol('$');
                sigma.addSymbol('.');
                sigma.addSymbol('(');
                sigma.addSymbol(')');
                sigma.addSymbol('{');
                sigma.addSymbol('}');
                break;
            default :
                break;
        }

    }
    @FXML
    public void EnterOnClick(ActionEvent action)
    {
        String inputAlphabet = custom_alpha.getText();
        for(int i = 0; i < inputAlphabet.length(); i++){
            sigma.addSymbol(inputAlphabet.charAt(i));
        }

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

            String temp =  transition.getText() + " ";
            char input = temp.charAt(0);
            String current =current_state.getText();
            String next = next_state.getText();
            delta.addTransition(input,current,next);

        counter++;
        current_state.setText("");
        next_state.setText("");
        transition.setText("");
    }

    int counter1 = 0;
    @FXML
    public void finalstatesOnclick(ActionEvent event)
    {
        int inputfinalstates= Integer.parseInt(number_of_final_states.getText());
        if(counter1 < inputfinalstates)
        {
        String inputfinalstate = final_states_menu.getValue();
        if(q.contains(inputfinalstate)){
            finalstates.addState(inputfinalstate);
        }
        }
    }
    @FXML
    TextField save;
    @FXML
    public void nextonclick(ActionEvent event) throws IOException {


        String inputInitialState = initial_state.getText();
        if( q.contains(inputInitialState))
            intialstate = new State(inputInitialState);
        else
            intialstate = new State("q0");




        DFA dfa=new DFA(q,sigma,intialstate,finalstates,delta);
        System.out.println(dfa.q);
        System.out.println(dfa.initialState);
        System.out.println(dfa.sigma);
        System.out.println(dfa.finalStates);
        System.out.println(dfa.delta);

        Window_Controller.saveToFile(dfa,save.getText()+".json");

        delta.drawDFA(q);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Testing Window.fxml"));
        Parent root=loader.load();
        TestingWindow testingWindow = loader.getController();
        testingWindow.setDfa(dfa);
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alpha_list.setItems(alphabet_observablelist);
        final_states_menu.setItems(states);
    }
}
