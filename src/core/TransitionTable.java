package core;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;

import java.util.HashMap;
import java.util.HashSet;

public class TransitionTable {


    private static class GraphKey{

        private String currentState;
        private String nextState;

        public GraphKey(String currentState, String nextState){
            this.currentState = currentState;
            this.nextState = nextState;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            GraphKey graphKey = (GraphKey) o;

            if (currentState != null ? !currentState.equals(graphKey.currentState) : graphKey.currentState != null)
                return false;
            return nextState != null ? nextState.equals(graphKey.nextState) : graphKey.nextState == null;
        }

        @Override
        public int hashCode() {
            int result = currentState != null ? currentState.hashCode() : 0;
            result = 31 * result + (nextState != null ? nextState.hashCode() : 0);
            return result;
        }

        public String getCurrentState() {
            return currentState;
        }

        public String getNextState() {
            return nextState;
        }
        @Override
        public String toString(){
            return "(" + currentState + "|" + nextState + ")";
        }


    }

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
        HashMap<GraphKey,String> helperMap = new HashMap<GraphKey,String>();



        Graph graph = new MultiGraph("DFA");


        HashSet <String> stateSet = states.getStates();

        graph.addAttribute("ui.stylesheet", "node {" +
                "fill-color: #008B8B;" +
                "fill-mode: gradient-diagonal1;"+
                "text-size:20;" +
                "shape: circle;" +
                "size: 40px,40px,40px;" + "}" +
                "edge {"+
                "text-alignment: center;" +
                "text-color:#008B8B;" +
                "text-style:bold-italic;" +
                "text-size:20;" +
                "}"
        );


        for(String s  : stateSet)
        {
            graph.addNode(s);
        }

        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }


        for (HashMap.Entry<Key, State> entry : table.entrySet()) {

            Key key = entry.getKey();
            State value = entry.getValue();



            GraphKey Gkey = new GraphKey(key.getState(),value.toString());

            if(helperMap.containsKey(Gkey)){

                String oldValue = helperMap.get(Gkey);
                helperMap.replace(Gkey,oldValue +"," + String.valueOf(key.getInput()));
            }
            else
            {
                helperMap.put(Gkey,String.valueOf(key.getInput()));
            }



            String id =key.getState()+value.toString();
            Edge edge = graph.getEdge(id);

            if(edge==null) {


                graph.addEdge(id, key.getState(), value.toString(), true);
            }



        }
        for (HashMap.Entry<GraphKey,String> entry : helperMap.entrySet()) {
            String currentState = entry.getKey().getCurrentState();
            String nextState = entry.getKey().getNextState();
            String value = entry.getValue();

            System.out.println(entry.getKey()+ entry.getValue());

        }
        for (HashMap.Entry<GraphKey,String> entry : helperMap.entrySet()) {
            String currentState = entry.getKey().getCurrentState();
            String nextState = entry.getKey().getNextState();
            String value = entry.getValue();

            Edge edge = graph.getEdge(currentState+nextState);
            edge.addAttribute("ui.label",value);




        }

        Viewer viewer = graph.display();



    }

}