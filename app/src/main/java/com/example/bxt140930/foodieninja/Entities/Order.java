package com.example.bxt140930.Foodieninja.Entities;

import com.example.bxt140930.Foodieninja.Other.OrderValidator;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bxt140930 on 11/15/2016.
 */

public class Order implements Serializable {
    double Total;

    public ETicket getET() {
        return ET;
    }

    public void setET(ETicket ET) {
        this.ET = ET;
    }

    ETicket ET;

    public Payment getP() {
        return P;
    }

    public void setP(Payment p) {
        P = p;
    }

    public void RemovePaymet() {
        P = null;
    }

    Payment P;

    public com.example.bxt140930.Foodieninja.Entities.FoodJoint getFoodJoint() {
        return FoodJoint;
    }

    public void setFoodJoint(com.example.bxt140930.Foodieninja.Entities.FoodJoint foodJoint) {
        FoodJoint = foodJoint;
    }

    com.example.bxt140930.Foodieninja.Entities.FoodJoint FoodJoint;

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    ArrayList<OrderItem> OrderItems;

    public Order(com.example.bxt140930.Foodieninja.Entities.FoodJoint R)
    {
        this.FoodJoint = R;
        OrderItems = new ArrayList<>();
    }

    public ArrayList<OrderItem> getOrderItems() {
        return OrderItems;
    }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        OrderItems = orderItems;
    }

    public boolean AddOrderItems(Item M) {
        OrderValidator OV = new OrderValidator();
        if (!OV.Validate(this))
            return false;
        for (OrderItem I : OrderItems)
            if (I.getItem().getId() == M.getId()) {
                I.setQuantuty(I.getQuantuty() + 1);
                return true;
            }
        OrderItem OI = new OrderItem();
        OI.setItem(M);
        OI.setQuantuty(1);
        OrderItems.add(OI);
        return true;
    }


}
