package com.example.bxt140930.foodieninja;

/**
 * Created by jxj050100 on 11/15/2016.
 */

public class Credential {

    String _id;
    String password;

    // Empty constructor
    public Credential(){

    }

    // constructor
    public Credential(String id, String password){
        this._id = id;
        this.password = password;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
