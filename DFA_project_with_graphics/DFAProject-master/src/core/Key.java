package core;

public class Key {

    private char input;
    private String state;

    public Key(char input, String state){
        this.input = input;
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key = (Key) o;
        return input == key.input && state == key.state;
    }

    @Override
    public int hashCode() {
        int result = Character.hashCode(input);
        result = 31 * result + state.hashCode();
        return result;
    }
    public String getState()
    {
        return this.state;
    }
    public char getInput()
    {
        return this.input;
    }

    @Override
    public String toString(){
        return "(" + String.valueOf(input) + "|" + state + ")";
    }

}
