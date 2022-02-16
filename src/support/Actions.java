package support;

import entities.Client;
import entities.Order;
import entities.Product;

import java.util.List;

public interface Actions {
    List<Order> searchOrder(List<Order> list, String name);
    List<Order> printAllOrders();
    void makeANewOrder(Client client, List<Product> list);
}
