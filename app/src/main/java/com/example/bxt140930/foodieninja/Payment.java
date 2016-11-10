package com.example.bxt140930.foodieninja;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);


        LinearLayout LL = (LinearLayout) findViewById(R.id.LLComet);
        LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent appInfo = new Intent(Payment.this, ETicketWithOrder.class);
                startActivity(appInfo);
            }
        });

        Button Pay = (Button)findViewById(R.id.BTNPay1);
        Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ETicketWithOrder.class);
                startActivity(intent);
                finish();
            }
        });

        Pay = (Button)findViewById(R.id.BTNPay2);
        Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ETicketWithOrder.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
