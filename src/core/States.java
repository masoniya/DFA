package core;

import java.util.InputMismatchException;
import java.util.HashSet;
import java.util.regex.Pattern;

public class States {

    HashSet<String> charSet;

    public static String statePattern = "q[0-9]*";

    public States(String ... elems) throws InputMismatchException {

        charSet = new HashSet<>();
        for(String elem : elems){
            if(!Pattern.matches("q[0-9]*", "q0")){
                throw new InputMismatchException();
            }
            charSet.add(elem);
        }

    }

}
