package com.example.bxt140930.foodieninja;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bxt140930 on 11/15/2016.
 */

public class Order implements Serializable {
    double Total;

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    ArrayList<OrderItem> OrderItems;
    public Order()
    {
        OrderItems = new ArrayList<>();
    }

    public ArrayList<OrderItem> getOrderItems() {
        return OrderItems;
    }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        OrderItems = orderItems;
    }
}
