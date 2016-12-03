package com.example.bxt140930.Foodieninja.Entities;

import java.io.Serializable;
import java.util.Date;

public class ETicket implements Serializable {
    int ID;
    int Number;
    String QRCode;
    String Status;
    String Image;
    String CreateTime;
    int WaitTime;

    public int getWaitTime() {
        return WaitTime;
    }

    public void setWaitTime(int waitTime) {
        WaitTime = waitTime;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String getQRCode() {
        return QRCode;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
