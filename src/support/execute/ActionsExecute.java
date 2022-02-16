package support.execute;

import entities.Order;
import support.Actions;
import util.Util;

import java.util.ArrayList;
import java.util.List;

public class ActionsExecute implements Actions {
    Util utils = null;
    String pathFile = null;
    List<Order> preLoadData;

    public ActionsExecute(Util utils) {
        this.pathFile = utils.loadProperties().getProperty("pathFile");
        this.utils = utils;
        this.preLoadData = printAllOrders();
    }

    @Override
    public List<Order> searchOrder(List<Order> list, String name) {
        try {
            List<Order> filter = new ArrayList<>();
            for (Order item : list) {
                if (item.getClient().getName().equalsIgnoreCase(name))
                    filter.add(item);
            }
            return filter;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Order> printAllOrders() {
        return utils.loadOrders(pathFile);
    }
}
