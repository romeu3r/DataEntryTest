package entities;

import java.util.Date;
import java.util.Objects;

public class Client {
    private String name;
    private int totalOrders = 0;
    private Date firstOrder;
    private Double totalSpend = 0.0;

    public Client() {
    }

    public Client(String name) {
        this.name = name.replaceAll(",", ".");
    }

    public void updateClient(Double spendAdd) {
        this.totalSpend += spendAdd;
        this.totalOrders += 1;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
