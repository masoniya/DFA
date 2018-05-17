package core;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import java.util.HashMap;
import java.util.HashSet;

public class TransitionTable {

    private HashMap<Key, State> table;

    public TransitionTable(){
        table = new HashMap<>();
    }

    public void addTransition(char input, String currentState, String nextState){
        Key k = new Key(input, currentState);
        State s = new State(nextState);
        table.put(k, s);
    }

    public String getNextState(char input, String currentState){
        Key k = new Key(input, currentState);
        return table.get(k).toString();
    }

    //check if key is in the map
    public boolean containsKey(char input, String currentState){
        Key k = new Key(input, currentState);
        if(table.containsKey(k)){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return table.toString();
    }


    public void drawDFA(StateSet states)
    {

        Graph graph = new SingleGraph("DFA");
        HashSet <String> stateSet = states.getStates();
        for(String s  : stateSet)
        {
            graph.addNode(s);
        }
        for (HashMap.Entry<Key, State> entry : table.entrySet()) {
            Key key = entry.getKey();
            State value = entry.getValue();

            try{
                graph.addEdge(Character.toString(key.getInput()),key.getState(),value.toString());
            }
            catch(Exception e)
            {

            }

        }
        graph.display();

    }

}
