package com.example.bxt140930.Foodieninja.Entities;

import java.io.Serializable;

/**
 * Created by bxt140930 on 11/15/2016.
 */

public class OrderItem  implements Serializable {
    com.example.bxt140930.Foodieninja.Entities.Item Item;
    int Quantuty;

    public int getQuantuty() {
        return Quantuty;
    }

    public void setQuantuty(int quantuty) {
        Quantuty = quantuty;
    }

    public com.example.bxt140930.Foodieninja.Entities.Item getItem() {
        return Item;
    }

    public void setItem(com.example.bxt140930.Foodieninja.Entities.Item item) {
        Item = item;
    }
}
