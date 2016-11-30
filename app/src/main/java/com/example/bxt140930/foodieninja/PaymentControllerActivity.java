package com.example.bxt140930.Foodieninja;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bxt140930.Foodieninja.Entities.Order;
import com.example.bxt140930.Foodieninja.Other.ServerFacade;

public class PaymentControllerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Order O = (Order)getIntent().getSerializableExtra("Order");
        ServerFacade JP = new ServerFacade(this);
        O.setTotal(JP.GetTotal(O));

        TextView TVTotal = (TextView) findViewById(R.id.TVPayment);
        TVTotal.setText(getString(R.string.PaymentAmount) + " " + O.getTotal());

        LinearLayout LL = (LinearLayout) findViewById(R.id.LLComet);
        LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent appInfo = new Intent(PaymentControllerActivity.this, ETicketWithOrderControllerActivity.class);
                startActivity(appInfo);
            }
        });

        Button Pay = (Button)findViewById(R.id.BTNPay1);
        Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ETicketWithOrderControllerActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Pay = (Button)findViewById(R.id.BTNPay2);
        Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ETicketWithOrderControllerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
