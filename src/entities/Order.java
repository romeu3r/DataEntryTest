package entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Order {
    private int idRequest;
    private Date date;
    private Client client;
    private List<Product> items;

    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    public Order() {
    }

    public Order(int idRequest, Date date, Client client, List<Product> items) {
        this.idRequest = idRequest;
        this.date = date;
        this.client = client;
        this.items = items;
    }

    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idRequest=" + idRequest +
                ", date=" + sdf.format(date) +
                ", client=" + client +
                ", items=" + items +
                '}';
    }
}
