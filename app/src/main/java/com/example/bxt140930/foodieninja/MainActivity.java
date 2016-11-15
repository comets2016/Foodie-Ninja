package com.example.bxt140930.foodieninja;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JsonParser JP = new JsonParser(this);
        ArrayList<Restaurants> ListOfRestaurants = JP.GetRestaurants();

        ListView listview = (ListView) findViewById(R.id.RestaurantList);
        listview.setAdapter(new RestaurantAdapter(this, ListOfRestaurants));
        /*listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent appInfo = new Intent(MainActivity.this, Restaurant.class);
                startActivity(appInfo);
                finish();
            }
        });*/
    }
}
