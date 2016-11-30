package com.example.bxt140930.Foodieninja.Entities;

/**
 * Created by jxj050100 on 11/15/2016.
 */

public class Credential {

    String username;
    String password;
    String firstName;
    String lastName;
    String email;

    // Empty constructor
    public Credential(){

    }

    // constructor
    public Credential(String id, String password){
        this.username = id;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return firstName;
    }

    public void setLastName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return firstName;
    }

    public void setEmail(String firstName) {
        this.firstName = firstName;
    }

    // constructor
    public Credential(String id, String firstName, String lastName, String email, String password){
        this.username = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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
