package entities;

import java.util.Date;

public class Client {
    private String name;
    private int totalOrders;
    private Date firstOrder;
    private Double totalSpend;

    public Client() {
    }

    public Client(String name) {
        this.name = name.replaceAll(",", ".");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public Date getFirstOrder() {
        return firstOrder;
    }

    public void setFirstOrder(Date firstOrder) {
        this.firstOrder = firstOrder;
    }

    public Double getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(Double totalSpend) {
        this.totalSpend = totalSpend;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", totalOrders=" + totalOrders +
                ", firstOrder=" + firstOrder +
                ", totalSpend=" + totalSpend +
                '}';
    }
}
