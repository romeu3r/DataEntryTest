package util;

import entities.Client;
import entities.Order;
import entities.Product;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Util {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    BufferedReader br = null;
    String line = null;

    public List<Order> loadOrders(String filePath) {
        try {
            br = new BufferedReader(new FileReader(filePath));
            line = br.readLine();
            List<Order> listOrder = new ArrayList<>();
            while (line != null) {
                if (countChar(line) == 2) {
                    String[] fields = line.split(",");
                    String name = fields[0];
                    int id = Integer.parseInt(fields[1]);
                    Date date = sdf.parse(fields[2]);
                    List<Product> listProduct = readingOrderItems();
                    listOrder.add(new Order(id, date, new Client(name), listProduct));
                }
            }
            return listOrder;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private int countChar(String msg) {
        try {
            int count = 0;
            for (int x = 0; x < msg.length(); x++) {
                if (msg.charAt(x) == ',') count++;
            }
            return count;
        } catch (Exception e) {
            return 0;
        }
    }

    private List<Product> readingOrderItems() {
        try {
            line = br.readLine();
            List<Product> list = new ArrayList<>();
            while (countChar(line) == 1) {
                String[] fields = line.split(",");
                String name = fields[0];
                double price = Double.parseDouble(fields[1]);
                list.add(new Product(name, price));
                line = br.readLine();
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Properties loadProperties() {
        try (FileInputStream fis = new FileInputStream("environment.properties")) {
            Properties props = new Properties();
            props.load(fis);
            return props;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
