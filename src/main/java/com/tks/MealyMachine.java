package com.tks;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class MealyMachine extends DFA {
    private char[] outputs;
    private int[][] outputFunction;

    private MealyMachine(int noStates, char[] terminals, int[][] stateTransition,
                 char[] outputs, int [][] outputFunction) {
        super(noStates, terminals, stateTransition);
        this.outputs = outputs;
        this.outputFunction = outputFunction;
    }

    private MealyMachine(int noStates, char[] terminals, int[][] stateTransition,
                 char[] outputs, int [][] outputFunction, String name, String description) {
        super(noStates, terminals, stateTransition, name, description);
        this.outputs = outputs;
        this.outputFunction = outputFunction;
    }

    public static MealyMachine fromUser() {
        return fromUser(new Scanner(System.in));
    }

    public static MealyMachine fromUser(Scanner scan) {
//        TODO: Add extensive error checking to inputs
        System.out.println("Enter values to create your Mealy Machine");
        DFA dfa = DFA.fromUser(scan);
        System.out.println("Enter space separated outputs: ");
        String rawOutputs = scan.nextLine();
        char[] outputs = processRawInput(rawOutputs);
        int[][] outputFunction = new int[dfa.numberOfStates][dfa.terminals.length];
        System.out.println("Enter Output transition function: ");
        for (int i = 0; i < dfa.numberOfStates; i++) {
            for (int j = 0; j < dfa.terminals.length; j++) {
                System.out.print("Q"+i+" --"+dfa.terminals[j]+" --> ");
                char output = scan.nextLine().charAt(0);
                for (int k = 0; k < outputs.length; k++) {
                    if (output == outputs[k]) {
                        outputFunction[i][j]  = k;
                        break;
                    }
                }
            }
        }
        return new MealyMachine(dfa.numberOfStates, dfa.terminals, dfa.stateTransition,
                outputs, outputFunction, dfa.name, dfa.description);
    }

    public static MealyMachine fromFile(String filename)throws IOException {
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
        char[] outputs = processRawInput(scan.nextLine());
        int[][] outputFunc = new int[noStates][terminals.length];
        for (int i = 0; i < noStates; i++) {
            StringTokenizer st = new StringTokenizer(scan.nextLine());
            for (int j = 0; j < terminals.length; j++) {
                char output = st.nextToken().charAt(0);
                for (int k = 0; k < outputs.length; k++) {
                    if (output == outputs[k]) {
                        outputFunc[i][j]  = k;
                        break;
                    }
                }
            }
        }
        scan.close();
        return new MealyMachine(noStates, terminals, transit,
                outputs, outputFunc, name, description);
    }

    public String run(String input) {
        char[] inputs = input.toCharArray();
        StringBuilder output = new StringBuilder();
        int currentState = this.initialState;
        for (char term : inputs) {
            int index = linearSearch(this.terminals, term);
            int nextState = this.stateTransition[currentState][index];
            char out = this.outputs[this.outputFunction[currentState][index]];
            if (out != '@') {
                output.append(out);
            }
            currentState = nextState;
        }
        return output.toString();
    }

}
