package com.example.bxt140930.foodieninja;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

public class Restaurant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        LinearLayout LL = (LinearLayout) findViewById(R.id.LLticket);
        LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent appInfo = new Intent(Restaurant.this, ETicket.class);
                startActivity(appInfo);
            }
        });

        LL = (LinearLayout) findViewById(R.id.LLMenu);
        LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent appInfo = new Intent(Restaurant.this, MenuActivity.class);
                startActivity(appInfo);
            }
        });
    }

}
