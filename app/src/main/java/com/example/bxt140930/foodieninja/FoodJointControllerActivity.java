package com.example.bxt140930.Foodieninja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.bxt140930.Foodieninja.Entities.Restaurants;
import com.example.bxt140930.Foodieninja.Other.LoginSinglton;
import com.example.bxt140930.Foodieninja.Other.ServerFacade;

import java.util.ArrayList;

public class FoodJointControllerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginSinglton.getInstance().validateUser(this);

        ServerFacade JP = new ServerFacade(this);
        ArrayList<Restaurants> ListOfRestaurants = JP.GetRestaurants();

        ListView listview = (ListView) findViewById(R.id.RestaurantList);
        listview.setAdapter(new RestaurantListAdapter(this, ListOfRestaurants));
    }
}
