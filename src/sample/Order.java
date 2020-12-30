package sample;

import java.util.ArrayList;

public class Order {
    private ArrayList<Title> titles;
    private Integer orderNumber;
    private Double total;

    Order()
    {
        titles = new ArrayList<>();
    }
    public ArrayList<Title> getTitles() {
        return titles;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}
