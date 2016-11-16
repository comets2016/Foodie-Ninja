package com.example.bxt140930.foodieninja;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;

public class PaymentControllerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Order O = (Order)getIntent().getSerializableExtra("Order");
        JsonParser JP = new JsonParser(this);
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
