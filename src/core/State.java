package core;


public class State {

    private String stateName;

    public State(String stateName){
        this.stateName = stateName;
    }

    @Override
    public String toString(){
        return stateName;
    }

}
