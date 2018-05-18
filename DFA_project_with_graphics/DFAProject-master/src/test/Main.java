package test;


import com.google.gson.*;
import core.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;



import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    public static  Scanner scan = new Scanner(System.in);


    public static void main(String args[]){

       // for specifying a renderer in  the gs-ui library

       System.setProperty("org.graphstream.ui.renderer","org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        DFA automaton = null;

        StateSet q = null;
        Alphabet sigma;
        State initialState;
        StateSet finalStates = null;
        TransitionTable delta;


        try{
            q = new StateSet("A", "B", "C","D","E","F");
        }
        catch(InputMismatchException e){
           e.printStackTrace();
        }

        sigma = new Alphabet('0', '1');

        initialState = new State("A");

        try{
            finalStates = new StateSet("C");
        }
        catch(InputMismatchException e){
            e.printStackTrace();
        }

          delta = new TransitionTable();
          delta.addTransition('0', "A", "B");
          delta.addTransition('1', "A", "B");
          delta.addTransition('0', "C", "A");
          delta.addTransition('1', "D", "E");
          delta.addTransition('0', "F", "D");
          delta.addTransition('1', "B", "E");
          delta.addTransition('1', "A", "A");











       // delta.addTransition('0', "B", "B");
     /*   delta.addTransition('1', "B", "B");
        delta.addTransition('0', "C", "C");
        delta.addTransition('1', "C", "C");*/

        automaton = new DFA(q, sigma, initialState, finalStates, delta);

        saveToFile(automaton, "savedDFA.json");

        automaton = loadFromFile("savedDFA.json");

        System.out.println(automaton);

        //DFA automaton2 = new DFA();

        //System.out.println(automaton2);
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

            delta.drawDFA(q);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dfa;
    }

}

