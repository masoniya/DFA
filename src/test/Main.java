package test;

import core.*;

import java.util.InputMismatchException;

public class Main {

    public static void main(String args[]){

        DFA automaton;

        StateSet q = null;
        Alphabet sigma;
        State initialState;
        StateSet finalStates = null;
        TransitionTable delta;


        try{
            q = new StateSet("A", "B", "C");
        }
        catch(InputMismatchException e){
           e.printStackTrace();
        }

        sigma = new Alphabet('0', '1');

        initialState = new State("A");

        try{
            finalStates = new StateSet("C");
        }
        catch(InputMismatchException e){
            e.printStackTrace();
        }

        delta = new TransitionTable();
        delta.addTransition('0', "A", "B");
        delta.addTransition('1', "A", "C");
        delta.addTransition('0', "B", "B");
        delta.addTransition('1', "B", "B");
        delta.addTransition('0', "C", "C");
        delta.addTransition('1', "C", "C");

        automaton = new DFA(q, sigma, initialState, finalStates, delta);

        System.out.println(automaton);

        System.out.println(automaton.start("101000100101"));

    }
}

