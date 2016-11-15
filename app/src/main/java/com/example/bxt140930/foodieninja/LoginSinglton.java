package com.example.bxt140930.foodieninja;

/**
 * Created by jxj050100 on 11/14/2016.
 */

public class LoginSinglton {
    //create an object of SingleObject
    private static LoginSinglton instance = new LoginSinglton();

    //make the constructor private so that this class cannot be
    //instantiated
    private LoginSinglton(){}

    //Get the only object available
    public static LoginSinglton getInstance(){
        return instance;
    }

    public void validateUser(String user, String password){

    }



}
