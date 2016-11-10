package com.example.bxt140930.foodieninja;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listview = (ListView) findViewById(R.id.RestaurantList);
        listview.setAdapter(new RestaurantAdapter(this, new String[] { "Subway",
                "Chick-fil-A" }, new String[] { "7:30am - 8:00pm",
                "7:30am - 8:00pm" }, new String[] { "Estimated wait time: 26 minutes",
                "Estimated wait time: 12 minutes"}, new int[] { R.mipmap.subway,R.mipmap.chick }));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent appInfo = new Intent(MainActivity.this, Restaurant.class);
                startActivity(appInfo);
                finish();
            }
        });
    }
}
