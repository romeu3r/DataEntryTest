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
import java.util.*;
import java.util.stream.Collectors;

public class ActionsExecute implements Actions {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    Util utils;
    String pathFile;
    List<Order> preLoadData;
    List<Client> singularClients = new ArrayList<>();

    public ActionsExecute(Util utils) {
        this.pathFile = utils.loadProperties().getProperty("pathFile");
        this.utils = utils;
        this.preLoadData = printAllOrders();
    }

    @Override
    public List<Order> searchOrder(String name) {
        try {
            List<Order> filter = new ArrayList<>();
            for (Order item : preLoadData) {
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

    @Override
    public Client mostInvestedClient() {
        loadClientByOrder();
        return singularClients.stream().sorted((o1, o2) -> o2.getTotalSpend().compareTo(o1.getTotalSpend())).toList().get(0);
    }

    @Override
    public Client mostOrdersMake() {
        loadClientByOrder();
        return singularClients.stream().sorted((o1, o2) -> o2.getTotalOrders().compareTo(o1.getTotalOrders())).toList().get(0);
    }

    private void loadClientByOrder() {
        Map<Client, Double> totalClient = new HashMap<>();
        for (Order byOrder : preLoadData) {
            Client client = byOrder.getClient();
            double valueList = byOrder.getItems().stream().map(Product::getPrice).reduce(0.0, Double::sum);
            if (totalClient.containsKey(client)) {
                double value = totalClient.get(client);
                totalClient.put(client, value + valueList);
                singularClients.forEach(x -> {
                    if (x.equals(client)) x.updateClient(value);
                });
                client.updateClient(valueList);
            } else {
                totalClient.put(client, valueList);
                client.updateClient(valueList);
                singularClients.add(client);
            }
        }
    }
}
