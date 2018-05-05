package core;


import java.util.HashSet;

public class Alphabet {

    private HashSet<Character> charSet;

    public Alphabet(char ... elems){

        charSet = new HashSet<>();
        for(char elem : elems){
            charSet.add(Character.valueOf(elem));
        }
    }

    public void addSymbol(char elem) {
        charSet.add(elem);
    }

    public HashSet<Character> getCharSet() {
        return charSet;
    }

    @Override
    public String toString(){
        return charSet.toString();
    }


}
