package com.example.bxt140930.Foodieninja.Other;

import com.example.bxt140930.Foodieninja.Entities.Order;
import com.example.bxt140930.Foodieninja.Entities.OrderItem;

public class OrderValidator {
    public Boolean Validate(Order O) {
        int Totalitems = 0;
        for (OrderItem OI : O.getOrderItems())
            Totalitems += OI.getQuantuty();
        return Totalitems < 5;
    }
}
