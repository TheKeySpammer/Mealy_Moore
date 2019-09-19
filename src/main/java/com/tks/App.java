package com.tks;

import java.io.PrintWriter;
import java.util.Scanner;

public class App
{
    public static void main( String[] args )
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
//                TODO: Add code to Create a new Mealy machine
                pw.println("Creating new Mealy machine");
                mealyMachineRun(scan);
                break;
            case 2:
//                TODO: Add code to create new Moore machine
                pw.println("Creating new Moore machine");
                break;
            case 3:
//                TODO: Add code to parse saved mealy machine and take input
                break;
            case 4:
//                TODO: Add code to parse saved moore machine an take input
                break;
            case 5:
                pw.println("Thank you for using this project: ");
                System.exit(0);
            default:
                pw.println("Wrong choice");
        }

    }

    private static void mealyMachineRun(Scanner scan) {
        MealyMachine mm = MealyMachine.fromUser(scan);
        System.out.println("Enter input string: ");
        String input = scan.nextLine();
        System.out.println("Output: "+mm.run(input));
    }
}
