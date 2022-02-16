package application;

import entities.Client;
import entities.Product;
import support.Actions;
import support.FactoryActions;
import util.PersonalErrorTreated;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        Actions am = FactoryActions.Actions();
        try {
            System.out.println("Welcome to our Pseudocode, i'm glad to receive you here!");
            System.out.println("Before all, what kind of action you want make? (Chose one, please.)");
            System.out.print("[1] Show all data request? \n[2] Make a new order? \n[3] Show client that most spend money? \n[4] Show client with more orders? ");
            int chose = sc.nextInt();
            if (chose == 1)
                am.printAllOrders().forEach(System.out::println);
            if (chose == 2) {
                System.out.print("Please, type your name: ");
                sc.nextLine();
                String name = sc.nextLine();
                String more = "y";
                List<Product> listItems = new ArrayList<>();
                while (more.equalsIgnoreCase("y")) {
                    System.out.print("Type name of your item: ");
                    String itemName = sc.nextLine();
                    System.out.print("Type price of your item: ");
                    double price = sc.nextDouble();
                    listItems.add(new Product(itemName, price));
                    System.out.print("You wish add more items(y/n)? ");
                    sc.nextLine();
                    more = sc.nextLine();
                }
                am.makeANewOrder(new Client(name), listItems);
                System.out.println("You order will received! " + name + ", Please check below:\n" + listItems);
            }
            if (chose == 3) {
                Client client = am.mostInvestedClient();
                System.out.println("");
                System.out.println("The best client is: " + client.getName() + ",\nActually he makes a total orders: " + client.getTotalOrders() + "\nAnd spend a total: " + client.getTotalSpend());
            }
        } catch (Exception e) {
            throw new PersonalErrorTreated(e.getMessage());
        }
        sc.close();
    }
}
