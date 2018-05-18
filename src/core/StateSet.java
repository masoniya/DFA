package core;


import java.util.InputMismatchException;
import java.util.HashSet;
import java.util.regex.Pattern;

public class StateSet {

    private HashSet<String> states;

    //public static String statePattern = ".";
    public static String statePattern = "q[0-9]*";

    public StateSet(String ... elems) throws InputMismatchException {

        states = new HashSet<>();
        for(String elem : elems){
            if(!Pattern.matches(statePattern, elem)){
                throw new InputMismatchException();
            }
            states.add(elem);
        }
    }

    public void addState(String elem) throws InputMismatchException {
            if(!Pattern.matches(statePattern, elem)){
                //throw new InputMismatchException();
            }
            states.add(elem);
    }

    public boolean contains(String s){
        if(states.contains(s)){
            return true;
        }
        return false;
    }

    public HashSet<String> getStates(){
        return states;
    }

    @Override
    public String toString(){
        return states.toString();
    }

}
