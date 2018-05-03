package core;


public class DFA {

    private StateSet q;
    private Alphabet sigma;
    private State initialState;
    private StateSet finalStates;
    private TransitionTable delta;

    public DFA(StateSet q, Alphabet sigma, State initialState, StateSet finalStates, TransitionTable delta){
        this.q = q;
        this.sigma = sigma;
        this.initialState = initialState;
        this.finalStates = finalStates;
        this.delta = delta;
    }

    public boolean start(String input){
        State currentState = this.initialState;

        for(int i = 0; i < input.length(); i++){

            char c = input.charAt(i);
            String s = currentState.toString();

            if(delta.containsKey(c, s)){
                currentState = new State(delta.getNextState(c, s));
            }
            else{
                //in case of dead state
                return false;
            }
        }

        if(finalStates.contains(currentState.toString())){
            //final state was reached
            return true;
        }
        //no final state was reached
        return false;
    }

    @Override
    public String toString(){
        return q + "\n" + sigma + "\n" + initialState + "\n" + finalStates + "\n" + delta;
    }

}
