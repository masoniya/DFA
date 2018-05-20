package core;

public class Key {

    private char input;
    private String state;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Key key = (Key) o;

        if (input != key.input) return false;
        return state != null ? state.equals(key.state) : key.state == null;
    }

    @Override
    public int hashCode() {
        int result = (int) input;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    public Key(char input, String state){
        this.input = input;
        this.state = state;

    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Key key = (Key) o;

        if (input != key.input) return false;
        return state != null ? state.equals(key.state) : key.state == null;
    }

    @Override
    public int hashCode() {
        int result = (int) input;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }*/

    public String getState()
    {
        return this.state;
    }
    public  char getInput()
    {
        return this.input;
    }

    @Override
    public String toString(){
        return "(" + String.valueOf(input) + "|" + state + ")";
    }

}
