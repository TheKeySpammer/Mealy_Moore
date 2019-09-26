package com.tks;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MooreMachine extends DFA{
    private char[] outputs;

    private MooreMachine(int noStates, char[] terminals, int[][] stateTransition,
                         char[] outputs){
        super(noStates, terminals, stateTransition);
        this.outputs = outputs;
    }

    private MooreMachine(int noStates, char[] terminals, int[][] stateTransition,
                         char[] outputs, String name, String description) {
        super (noStates, terminals, stateTransition, name, description);
        this.outputs = outputs;
    }

    public static MooreMachine fromUser() {
        return fromUser(new Scanner(System.in));
    }

    public static MooreMachine fromUser(Scanner scan) {
        System.out.println("Enter Values to create Moore machine");
        DFA dfa = DFA.fromUser(scan);
        System.out.println("Enter outputs Associated with each State: ");
        char[] outputs = new char[dfa.numberOfStates];
        for (int i = 0; i < dfa.numberOfStates; i++) {
            System.out.print("Q"+i+": ");
            outputs[i] = scan.nextLine().charAt(0);
        }
        return new MooreMachine(dfa.numberOfStates, dfa.terminals, dfa.stateTransition,
                outputs, dfa.name, dfa.description);
    }

    public static MooreMachine fromFile(String filename)throws IOException{
        Scanner scan = new Scanner(new FileInputStream(filename));
        String name = scan.nextLine();
        String description = scan.nextLine();
        char[] terminals = processRawInput(scan.nextLine());
        int noStates = Integer.parseInt(scan.nextLine());
        int[][] transit = new int[noStates][terminals.length];
        for (int i = 0; i < noStates; i++) {
            StringTokenizer st = new StringTokenizer(scan.nextLine());
            for (int j = 0; j < terminals.length; j++) {
                transit[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        char[] outputs = new char[noStates];
        for (int i = 0; i < outputs.length; i++) {
            outputs[i] = scan.nextLine().charAt(0);
        }
        scan.close();
        return new MooreMachine(noStates, terminals, transit,
                outputs, name, description);
    }

    public String run(String input) {
        char[] inputs = input.toCharArray();
        StringBuilder output = new StringBuilder();
        int currentState = this.initialState;
        for (char term : inputs) {
            int index = linearSearch(this.terminals, term);
            int nextState = this.stateTransition[currentState][index];
            int out = this.outputs[currentState];
            if (out != '@') {
                output.append(out);
            }
            currentState = nextState;
        }
        return output.toString();
    }
}
