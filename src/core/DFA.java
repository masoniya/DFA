package core;


public class DFA {

    public StateSet q;
    public Alphabet sigma;
    public State initialState;
    public StateSet finalStates;
    public TransitionTable delta;

    public DFA(){

        /*
        //States Input
        int n;
        System.out.println("How Many States?");
        n = Main.scan.nextInt();
        q = new StateSet();
        for(int i = 0; i < n; i++){
            q.addState("q" + String.valueOf(i));
        }

        //Alphabet Input
        int h;
        System.out.println("Choose Alphabet");
        System.out.print("0 : Enter custom Alphabet.\n" +
                        "1 : Choose all English Characters\n" +
                        "2 : Choose all Numbers\n" +
                        "3 : Choose Symbols\n" +
                        "4 : Choose all Characters and Numbers\n");
        h = Main.scan.nextInt();
        sigma = new Alphabet();
        switch(h){
            case 0 :
                System.out.println("Enter Alphabet as String:");
                String inputAlphabet = Main.scan.next();
                for(int i = 0; i < inputAlphabet.length(); i++){
                    sigma.addSymbol(inputAlphabet.charAt(i));
                }
                break;
            case 1 :
                for(int i = (int)'a'; i < 26 + (int)'a'; i++){
                    sigma.addSymbol((char)i);
                }
                for(int i = (int)'A'; i < 26 + (int)'A'; i++){
                    sigma.addSymbol((char)i);
                }
                break;
            case 2 :
                for(int i = (int)'0'; i < 10 + (int)'0'; i++){
                    sigma.addSymbol((char)i);
                }
                break;
            case 3 :
                sigma.addSymbol('/');
                sigma.addSymbol('*');
                sigma.addSymbol('$');
                sigma.addSymbol('.');
                sigma.addSymbol('(');
                sigma.addSymbol(')');
                sigma.addSymbol('{');
                sigma.addSymbol('}');
                break;
            case 4 :
                for(int i = (int)'a'; i < 26 + (int)'a'; i++){
                    sigma.addSymbol((char)i);
                }
                for(int i = (int)'A'; i < 26 + (int)'A'; i++){
                    sigma.addSymbol((char)i);
                }
                for(int i = (int)'0'; i < 10 + (int)'0'; i++){
                    sigma.addSymbol((char)i);
                }
                break;
             default :
                 break;
        }

        //Initial State Input
        System.out.println("Select Initial State:");
        String inputInitialState = Main.scan.next();
        if(q.contains(inputInitialState)){
            initialState = new State(inputInitialState);
        }
        else{
            System.out.println("Invalid Initial State. Setting q0 as Initial State.");
            initialState = new State("q0");
        }

        //Final States Input
        int m;
        System.out.println("How Many Final States?");
        m = Main.scan.nextInt();
        finalStates = new StateSet();
        for(int i = 0; i < m; i++){
            String inputFinalState = Main.scan.next();
            if(q.contains(inputFinalState)){
                finalStates.addState(inputFinalState);
            }
        }

        //Transition Table Input
        System.out.println("Enter transitions : (q to quit)");
       delta = new TransitionTable();
        delta.addTransition('$', "q0", "q1");*/

    }

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
        return "q: " + q + "\n" +
                "sigma: " + sigma + "\n" +
                "initialState: " + initialState + "\n" +
                "finalStates: " + finalStates + "\n" +
                "delta: " + delta;
    }

}
