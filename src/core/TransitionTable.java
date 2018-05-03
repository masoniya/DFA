package core;

import java.util.HashMap;

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

}
