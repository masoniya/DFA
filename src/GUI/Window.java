package GUI;

import core.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import scala.util.parsing.combinator.testing.Str;

import java.io.IOException;

public class Window extends Application {

    public static void main(String[] args) {
        System.setProperty("org.graphstream.ui.renderer","org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            primaryStage.setTitle("Registration Form FXML Application");
            Scene scene =new Scene(root, 800, 500);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static StateSet q;
    static State initialstate;
    static Alphabet sigma;
    static TransitionTable delta;
    static StateSet finalstates;
    public static void savephp()
    {
        //intiliaze the state set
        q = new StateSet();
        for(int i =0 ; i<= 22; i++)
        {
            q.addState("q"+ String.valueOf(i));
        }

        //intiliaze the initial state
        initialstate = new State("q0");

        //intiliaziethe alphabet
        sigma= new Alphabet();

        for(int i = (int)'a'; i < 26 + (int)'a'; i++){
            sigma.addSymbol((char)i);
        }
        for(int i = (int)'A'; i < 26 + (int)'A'; i++){
            sigma.addSymbol((char)i);
        }
        for( int i = 48; i<=57; i++)
        {
            sigma.addSymbol((char) i);
        }
        sigma.addSymbol('/');
        sigma.addSymbol('*');
        sigma.addSymbol('$');

        //intialize the final states set
        finalstates = new StateSet();

        finalstates.addState("q2"); finalstates.addState("q4"); finalstates.addState("q8");
        finalstates.addState("q13"); finalstates.addState("q15"); finalstates.addState("q18");
        finalstates.addState("q22");

        delta = new TransitionTable();

        delta.addTransition('$',"q0","q1");

        for (int i = 65; i <= 90; i++)
        {
            delta.addTransition((char)i,"q1","q2");
            delta.addTransition((char)i,"q2","q2");
            delta.addTransition((char)i,"q6","q6");
        }
        for(int i = 97; i <= 122; i++)
        {
            delta.addTransition((char)i, "q1","q2");
            delta.addTransition((char)i,"q2","q2");
            delta.addTransition((char)i,"q6","q6");
        }
        for( int i = 48; i<=57; i++)
        {
            delta.addTransition((char) i,"q2","q2");
            delta.addTransition((char)i, "q0","q3");
            delta.addTransition((char)i,"q3","q4");
            delta.addTransition((char)i, "q4","q4");
            delta.addTransition((char)i,"q6","q6");


        }
        delta.addTransition('.',"q3","q4");
        delta.addTransition('/',"q0","q5");
        delta.addTransition('*',"q5","q6");
        delta.addTransition('*',"q6","q7");
        delta.addTransition('/',"q7","q8");
        delta.addTransition('w',"q0","q9");
        delta.addTransition('h',"q9","q10");
        delta.addTransition('i',"q10","q11");
        delta.addTransition('l',"q11","q12");
        delta.addTransition('e',"q12","q13");
        delta.addTransition('c',"q0","q19");
        delta.addTransition('i',"q0","q14");
        delta.addTransition('f',"q14","q15");
        delta.addTransition('f',"q0","q16");
        delta.addTransition('o',"q16","q17");
        delta.addTransition('r',"q17","q18");
        delta.addTransition('a',"q19","q20");
        delta.addTransition('s',"q20","q21");
        delta.addTransition('e',"q21","q22");

        DFA phpdfa= new DFA(q,sigma,initialstate,finalstates,delta);
        Window_Controller.saveToFile(phpdfa,"savedDFA.json");


    }

}
