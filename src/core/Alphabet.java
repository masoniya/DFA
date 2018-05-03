package core;

import java.util.HashSet;

public class Alphabet {

    HashSet<Character> charSet;

    public Alphabet(char ... elems){

        charSet = new HashSet<>();
        for(char elem : elems){
            charSet.add(Character.valueOf(elem));
        }

    }

    public HashSet<Character> getCharSet() {
        return charSet;
    }

    public void display(){
        System.out.println(charSet);
    }


}
