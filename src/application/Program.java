package application;

import entities.Order;
import util.Util;

import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        String pathFile = "extraFiles/request.csv";
        Scanner sc = new Scanner(System.in);

        Util utils = new Util();
        List<Order> list = utils.loadOrders(pathFile);

        list.forEach(System.out::println);
        sc.close();
    }
}
