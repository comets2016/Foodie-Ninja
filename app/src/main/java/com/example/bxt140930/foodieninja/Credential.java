package com.example.bxt140930.foodieninja;

/**
 * Created by jxj050100 on 11/15/2016.
 */

public class Credential {

    String username;
    String password;
    // Empty constructor
    public Credential(){

    }

    // constructor
    public Credential(String id, String password){
        this.username = id;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Credential{" +
                "password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
