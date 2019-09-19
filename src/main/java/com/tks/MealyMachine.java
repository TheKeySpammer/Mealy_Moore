package com.tks;


import java.util.Scanner;
import java.util.StringTokenizer;

public class MealyMachine {
    private int[] states;
    private char[] inputs;
    private int[][] stateTransition;
    private char[] outputs;
    private int[][] outputFunction;
    private int initialState;
    private int numberOfStates;
    private String name;
    private String description;

    public static class Builder {
        private char[] inputs;
        private int[][] stateTransition;
        private char[] outputs;
        private int[][] outputFunction;
        private int numberOfStates;

//        Optional fields
        private int initialState = 0;
        private int[] states = null;
        private String name = "mealy_machine";
        private String description = "";

        public Builder(char[] inputs, char[] outputs, int numberOfStates, int[][] stateTransition, int[][] outputFunction) {
            this.inputs = inputs;
            this.outputs = outputs;
            this.numberOfStates = numberOfStates;
            this.stateTransition = stateTransition;
            this.outputFunction = outputFunction;
            states = new int[this.numberOfStates];
        }

        public Builder name(String val) {
            this.name = val;
            return this;
        }

        public Builder description(String val) {
            this.description = val;
            return this;
        }

        public Builder initialState(int val) {
            this.initialState = val;
            return this;
        }

        public MealyMachine build() {
            return new MealyMachine(this);
        }
    }

    private MealyMachine(Builder builder) {
        this.description = builder.description;
        this.initialState = builder.initialState;
        this.inputs = builder.inputs;
        this.name = builder.name;
        this.numberOfStates = builder.numberOfStates;
        this.states = builder.states;
        this.stateTransition = builder.stateTransition;
        this.outputFunction = builder.outputFunction;
        this.outputs = builder.outputs;
    }

    public static MealyMachine fromUser() {
        return fromUser(new Scanner(System.in));
    }

    public static MealyMachine fromUser(Scanner scan) {
//        TODO: Add extensive error checking to inputs
        System.out.println("Enter values to create your Mealy Machine");
        System.out.print("Enter name: ");
        String name = scan.nextLine();
        System.out.println("Enter description");
        String description = scan.nextLine();
        System.out.println("Enter space separated terminals: ");
        String rawTerminals = scan.nextLine();
        char[] terminals = processRawInput(rawTerminals);
        System.out.println("Enter space separated outputs: ");
        String rawOutputs = scan.nextLine();
        char[] outputs = processRawInput(rawOutputs);
        System.out.print("Enter number of states: ");
        int noStates = Integer.parseInt(scan.nextLine());
        System.out.println("Enter State transition function: ");
        int[][] stateTransition = new int[noStates][terminals.length];
        int[][] outputFunction = new int[noStates][terminals.length];
        for (int i = 0; i < noStates; i++) {
            for (int j = 0; j < terminals.length; j++) {
                System.out.print("Q"+i+" --"+terminals[j]+"--> ");
//                TODO: Take q0 input instead of index
                stateTransition[i][j] = Integer.parseInt(scan.nextLine());
            }
        }
        System.out.println("Enter Output transition function: ");
        for (int i = 0; i < noStates; i++) {
            for (int j = 0; j < terminals.length; j++) {
                System.out.print("Q"+i+" --"+terminals[j]+" --> ");
                char output = scan.nextLine().charAt(0);
                for (int k = 0; k < outputs.length; k++) {
                    if (output == outputs[k]) {
                        outputFunction[i][j]  = k;
                        break;
                    }
                }
            }
        }
        return new Builder(terminals, outputs, noStates, stateTransition, outputFunction).description(description).name(name).build();
    }

    private static char[] processRawInput(String terminal) {
        StringTokenizer st = new StringTokenizer(terminal, " ,");
        char[] terminals = new char[st.countTokens()];
        int i = 0;
        while(st.hasMoreTokens()) {
            terminals[i++] = st.nextToken().charAt(0);
        }
        return terminals;
    }

    public String run(String input) {
        char[] inputs = input.toCharArray();
        StringBuilder output = new StringBuilder();
        int currentState = this.initialState;
        for (char term : inputs) {
            int index = linearSearch(this.inputs, term);
            int nextState = this.stateTransition[currentState][index];
            output.append(this.outputs[this.outputFunction[currentState][index]]);
            currentState = nextState;
        }
        return output.toString();
    }

    private int linearSearch(char arr[], int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) return i;
        }
        return -1;
    }
}
