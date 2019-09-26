package com.tks;


import java.io.*;
import java.util.Scanner;

public class App
{
    public static void main( String[] args ) throws IOException
    {
        PrintWriter pw = new PrintWriter(System.out, true);
        Scanner scan = new Scanner(System.in);
        pw.println("Choose: ");
        int choice;
        do {
            pw.println("1: Create new Mealy Machine. ");
            pw.println("2: Create new Moore Machine. ");
            pw.println("3: Open a Mealy Machine. ");
            pw.println("4: Open a Moore Machine. ");
            pw.println("5: Exit. ");
            choice = Integer.parseInt(scan.nextLine());
        } while (choice < 1 || choice > 5);
        switch (choice) {
            case 1:
                pw.println("Creating new Mealy machine");
                mealyMachineRun(scan);
                break;
            case 2:
                pw.println("Creating new Moore machine");
                mooreMachineRun(scan);
                break;
            case 3:
                mealyMachineOpen(scan);
                break;
            case 4:
                mooreMachineOpen(scan);
                break;
            case 5:
                pw.println("Thank you for using this project: ");
                System.exit(0);
            default:
                pw.println("Wrong choice");
        }

    }

    private static void mealyMachineOpen(Scanner scan)throws IOException {
        System.out.println("Enter filename: ");
        String filename = scan.nextLine();
        MealyMachine mm;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            mm = (MealyMachine)ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Machine Loaded");
        System.out.println("Machine name: "+mm.name);
        System.out.println("Machine description: ");
        System.out.println(mm.description);
        StringBuilder inputs = new StringBuilder();
        String prefix = "";
        for (char term :
                mm.terminals) {
            inputs.append(prefix);
            prefix = ",";
            inputs.append(term);
        }
        System.out.println("Terminals: "+inputs.toString());
        while (true) {
            System.out.println("Enter input string: ");
            String input = scan.nextLine();
            System.out.println("Output: "+mm.run(input));
            System.out.println("Input again? (y/n)");
            String res = scan.nextLine();
            if (res.equalsIgnoreCase("y") || res.equalsIgnoreCase("yes")) continue;
            break;
        }
    }
    private static void mooreMachineOpen(Scanner scan)throws IOException {
        System.out.println("Enter filename: ");
        String filename = scan.nextLine();
        MooreMachine mm;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            mm = (MooreMachine)ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Machine Loaded");
        System.out.println("Machine name: "+mm.name);
        System.out.println("Machine description: ");
        System.out.println(mm.description);
        StringBuilder inputs = new StringBuilder();
        String prefix = "";
        for (char term :
                mm.terminals) {
            inputs.append(prefix);
            prefix = ",";
            inputs.append(term);
        }
        System.out.println("Terminals: "+inputs.toString());
        while (true) {
            System.out.println("Enter input string: ");
            String input = scan.nextLine();
            System.out.println("Output: "+mm.run(input));
            System.out.println("Input again? (y/n)");
            String res = scan.nextLine();
            if (res.equalsIgnoreCase("y") || res.equalsIgnoreCase("yes")) continue;
            break;
        }
    }

    private static void mealyMachineRun(Scanner scan)throws IOException {
        System.out.println("Create Mealy machine from user or from file: ");
        System.out.println("1: From User");
        System.out.println("2: From File");
        System.out.print("Choice: ");
        int choice = Integer.parseInt(scan.nextLine());
        MealyMachine mm;
        if (choice == 1) {
            mm = MealyMachine.fromUser(scan);
        }
        else if (choice == 2) {
            System.out.println("Enter filename: ");
            String filename = scan.nextLine();
            mm = MealyMachine.fromFile(filename);
        }
        else {
            System.out.println("Wrong Choice");
            return;
        }
        String outFilename = mm.name.length() == 0? "mealy_machine": mm.name;
        System.out.println("Saving Mealy machine as file "+outFilename);
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(outFilename))) {
            os.writeObject(mm);
        }
        StringBuilder inputs = new StringBuilder();
        String prefix = "";
        for (char term :
                mm.terminals) {
            inputs.append(prefix);
            prefix = ",";
            inputs.append(term);
        }
        String terminals = inputs.toString();
        while (true) {
            System.out.println("Terminals: "+terminals);
            System.out.println("Enter input string: ");
            String input = scan.nextLine();
            System.out.println("Output: "+mm.run(input));
            System.out.println("Input again? (y/n)");
            String res = scan.nextLine();
            if (res.equalsIgnoreCase("y") || res.equalsIgnoreCase("yes")) continue;
            break;
        }
    }

    private static void mooreMachineRun(Scanner scan)throws IOException {
        System.out.println("Create Moore machine from user or from file: ");
        System.out.println("1: From User");
        System.out.println("2: From File");
        System.out.print("Choice: ");
        int choice = Integer.parseInt(scan.nextLine());
        MooreMachine mm;
        if (choice == 1) {
            mm = MooreMachine.fromUser(scan);
        }
        else if (choice == 2) {
            System.out.println("Enter filename: ");
            String filename = scan.nextLine();
            mm = MooreMachine.fromFile(filename);
        }
        else {
            System.out.println("Wrong Choice");
            return;
        }
        String outFilename = mm.name.length() == 0? "moore_machine": mm.name;
        System.out.println("Saving Mealy machine as file "+outFilename);
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(outFilename))) {
            os.writeObject(mm);
        }
        StringBuilder inputs = new StringBuilder();
        String prefix = "";
        for (char term :
                mm.terminals) {
            inputs.append(prefix);
            prefix = ",";
            inputs.append(term);
        }
        String terminals = inputs.toString();
        while (true) {
            System.out.println("Terminals: "+terminals);
            System.out.println("Enter input string: ");
            String input = scan.nextLine();
            System.out.println("Output: "+mm.run(input));
            System.out.println("Input again? (y/n)");
            String res = scan.nextLine();
            if (res.equalsIgnoreCase("y") || res.equalsIgnoreCase("yes")) continue;
            break;
        }
    }
}
