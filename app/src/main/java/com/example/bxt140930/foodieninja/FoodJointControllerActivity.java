package com.example.bxt140930.foodieninja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class FoodJointControllerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginSinglton.getInstance().validateUser(this);

        JsonParser JP = new JsonParser(this);
        ArrayList<Restaurants> ListOfRestaurants = JP.GetRestaurants();

        ListView listview = (ListView) findViewById(R.id.RestaurantList);
        listview.setAdapter(new RestaurantListAdapter(this, ListOfRestaurants));
    }
}
