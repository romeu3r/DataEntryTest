package support;

import entities.Order;

import java.util.List;

public interface Actions {
    List<Order> searchOrder(List<Order> list, String name);
    List<Order> printAllOrders();
}
