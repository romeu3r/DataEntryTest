package support.execute;

import entities.Client;
import entities.Order;
import entities.Product;
import support.Actions;
import util.PersonalErrorTreated;
import util.Util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActionsExecute implements Actions {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    Util utils;
    String pathFile;
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
            throw new PersonalErrorTreated(e.getMessage());
        }
    }

    @Override
    public List<Order> printAllOrders() {
        return utils.loadOrders(pathFile);
    }

    @Override
    public void makeANewOrder(Client client, List<Product> list) {
        List<Integer> usedId = new ArrayList<>();
        for (Order item : preLoadData) {
            usedId.add(item.getIdRequest());
        }
        int id;
        for (id = 1; id < usedId.size(); id++) {
            if (!usedId.contains(id))
                break;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile, true))) {
            bw.newLine();
            bw.write(client.getName() + "," + id + "," + sdf.format(new Date()));
            bw.newLine();
            for (Product item : list) {
                bw.write(item.getName() + "," + String.format("%.2f", item.getPrice()));
                bw.newLine();
            }
        } catch (Exception e) {
            throw new PersonalErrorTreated(e.getMessage());
        }
    }
}
