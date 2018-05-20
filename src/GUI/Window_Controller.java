package GUI;
import com.google.gson.Gson;
import core.DFA;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import core.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Window_Controller {
    public void new_DFA(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("automation menu.fxml"));
        Parent root=loader.load();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    TextField name;
    @FXML
    public void load_DFA(ActionEvent event) throws IOException {

        DFA dfa=loadFromFile("C:\\Users\\ichigo\\Documents\\DFAProject\\"+name.getText()+".json");

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Testing Window.fxml"));
        Parent root=loader.load();
        TestingWindow testingWindow = loader.getController();
        testingWindow.setDfa(dfa);
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
        dfa.delta.drawDFA(dfa.q);
    }

    @FXML
    public void phponclick(ActionEvent event) throws IOException {
        Window.savephp();
        DFA dfa=loadFromFile("C:\\Users\\ichigo\\Documents\\DFAProject\\savedDFA.json");

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Testing Window.fxml"));
        Parent root=loader.load();
        TestingWindow testingWindow = loader.getController();
        testingWindow.setDfa(dfa);
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
        dfa.delta.drawDFA(dfa.q);
        System.out.println(dfa.sigma);
        System.out.println(dfa.q);
        System.out.println(dfa.delta);

    }
    public static void saveToFile(Object obj, String fileName){
        Gson gson = new Gson();
        DFA dfa = (DFA)obj;
        try {
            FileWriter writer = new FileWriter(fileName);
            gson.toJson(dfa, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static DFA loadFromFile(String fileName){
        JsonParser parser = new JsonParser();
        DFA dfa = null;
        try{
            Object obj = parser.parse(new FileReader(fileName));
            JsonObject jsonObject = (JsonObject)obj;

            //parsing the States
            StateSet q = new StateSet();
            JsonObject qObj = (JsonObject) jsonObject.get("q");
            JsonArray qArray = (JsonArray) qObj.get("states");
            for(int i = 0; i < qArray.size(); i++){
                String s = qArray.get(i).getAsString();
                q.addState(s);
            }

            //parsing the Alphabet
            Alphabet sigma = new Alphabet();
            JsonObject sigmaObj = (JsonObject) jsonObject.get("sigma");
            JsonArray sigmaArray = (JsonArray) sigmaObj.get("charSet");
            for(int i = 0; i < sigmaArray.size(); i++){
                String s = sigmaArray.get(i).getAsString();
                sigma.addSymbol(s.charAt(0));
            }

            //parsing the InitialState
            State initialState = null;
            JsonObject initialStateObj = (JsonObject) jsonObject.get("initialState");
            String initialStateString = (String) initialStateObj.get("stateName").getAsString();
            initialState = new State(initialStateString);

            //parsing the FinalStates
            StateSet finalStates = new StateSet();
            JsonObject finalStatesObj = (JsonObject) jsonObject.get("finalStates");
            JsonArray finalStatesArray = (JsonArray) finalStatesObj.get("states");
            for(int i = 0; i < finalStatesArray.size(); i++){
                String s = finalStatesArray.get(i).getAsString();
                finalStates.addState(s);
            }

            //Parsing the TransitionTable
            TransitionTable delta = new TransitionTable();
            JsonObject deltaObj = (JsonObject) ((JsonObject) jsonObject.get("delta")).get("table");
            for(char c : sigma.getCharSet()){
                for(String s : q.getStates()){
                    String key = "(" + String.valueOf(c) + "|" + s + ")";
                    if(deltaObj.has(key)){
                        JsonObject value = (JsonObject) deltaObj.get(key);
                        String stateName = value.get("stateName").getAsString();
                        delta.addTransition(c, s, stateName);
                    }
                }
            }

            //create the new DFA
            dfa = new DFA(q, sigma, initialState, finalStates, delta);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dfa;
    }
}
