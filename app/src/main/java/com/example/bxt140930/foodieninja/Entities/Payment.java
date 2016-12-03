package com.example.bxt140930.Foodieninja.Entities;

import java.io.Serializable;

/**
 * Created by bxt140930 on 12/2/2016.
 */

public class Payment implements Serializable {
    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    String CardNumber;
}
