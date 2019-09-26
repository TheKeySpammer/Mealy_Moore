package com.tks;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DFA implements Serializable {
    
    private static final long serialVersionUID = 1L;
     int numberOfStates;
     int initialState;
     char[] terminals;
     int[][] stateTransition;
     String name;
     String description;
    
    DFA(int numberOfStates, char[] terminals, int[][] stateTransition) {
        this.numberOfStates = numberOfStates;
        this.terminals = terminals;
        this.stateTransition = stateTransition;
        this.name = "DFA1";
        this.description = "";
        this.initialState = 0;
    }

    DFA(int numberOfStates, char[] terminals, int[][] stateTransition, String name, String description) {
        this(numberOfStates, terminals, stateTransition);
        this.name = name;
        this.description = description;
    }

    public static DFA fromUser(Scanner scan) {
        String name = "";
        String description = "";
        System.out.print("Enter name: ");
        name = scan.nextLine();
        System.out.println("Enter description: ");
        description = scan.nextLine();
        System.out.println("Enter space or comma Separated terminals: ");
        String rawTerminals = scan.nextLine();
        char[] terminals = processRawInput(rawTerminals);
        System.out.print("Enter Number of states: ");
        int noStates = Integer.parseInt(scan.nextLine());
        int[][] transit = new int[noStates][terminals.length];
        System.out.println("Enter State Transition function");
        for (int i = 0; i < noStates; i++) {
            for (int j = 0; j < terminals.length; j++) {
                System.out.print("Q"+i+" --"+terminals[j]+"--> ");
                transit[i][j] = Integer.parseInt(scan.nextLine());
            }
        }
        return new DFA(noStates, terminals, transit, name, description);
    }


     static char[] processRawInput(String rawTerminals) {
        StringTokenizer st = new StringTokenizer(rawTerminals, " ,");
        char[] ch = new char[st.countTokens()];
        int i = 0;
        while(st.hasMoreTokens()) {
            ch[i++] = st.nextToken().charAt(0);
        }
        return ch;
        
    }
 
     int linearSearch (int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) return i;
        }
        return -1;
    }

     int linearSearch (char[] arr, char val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) return i;
        }
        return -1;
    }

}