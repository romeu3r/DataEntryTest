package application;

import support.Actions;
import support.FactoryActions;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Actions am = FactoryActions.Actions();

        System.out.println("Welcome to our Pseudocode, i'm glad to receive you here!");
        System.out.println("Before all, what kind of action you want make? (Chose one, please.)");
        System.out.print("[1] Show all data request? \n[2] Search Order by name? \n[3] Show client most spend money? \n[4] Show client with more orders? ");
        if (sc.nextInt() == 1)
            am.printAllOrders().forEach(System.out::println);

        sc.close();
    }
}
