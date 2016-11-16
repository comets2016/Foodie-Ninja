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

    LoginSinglton loginSinglton = LoginSinglton.getInstance();
    String tableName="credential";
    SQLiteJDBCforCredential sqlite = new SQLiteJDBCforCredential(this, tableName);

    //getting data from the sqlite db
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Credential credential = sqlite.getAllContacts();
        int returnCode = loginSinglton.validateUser(this, credential.getUsername(),credential.getPassword());
        if (returnCode == 200) {
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
        else
        {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }
}
