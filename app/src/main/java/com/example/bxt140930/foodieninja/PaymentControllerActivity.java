package com.example.bxt140930.Foodieninja;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bxt140930.Foodieninja.Entities.ETicket;
import com.example.bxt140930.Foodieninja.Entities.Order;
import com.example.bxt140930.Foodieninja.Entities.Payment;
import com.example.bxt140930.Foodieninja.Other.ServerFacade;

public class PaymentControllerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        final Order O = (Order) getIntent().getSerializableExtra("Order");
        final ServerFacade SF = new ServerFacade(this);
        O.setTotal(SF.GetTotal(O));

        if (O.getTotal() < 0)
            Toast.makeText(getApplicationContext(), getString(R.string.exceed_items), Toast.LENGTH_LONG);

        TextView TVTotal = (TextView) findViewById(R.id.TVPayment);
        TVTotal.setText(getString(R.string.PaymentAmount) + " " + O.getTotal());

        Button Pay = (Button)findViewById(R.id.BTNPay1);
        Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Payment P = new Payment();
                P.setCardNumber(((EditText) findViewById(R.id.EDCometID)).getText().toString());
                O.setP(P);
                PlaceOrder(O);
            }
        });

        Pay = (Button)findViewById(R.id.BTNPay2);
        Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Payment P = new Payment();
                P.setCardNumber(((EditText) findViewById(R.id.ETCreditNumber)).getText().toString());
                O.setP(P);
                PlaceOrder(O);
            }
        });
    }

    private void PlaceOrder(Order O) {
        ServerFacade SF = new ServerFacade(this);
        ETicket ET = SF.PlaceOrder(O);
        if (ET == null) {
            O.RemovePaymet();
            Toast.makeText(getApplicationContext(), getString(R.string.payment_failed), Toast.LENGTH_LONG);
            return;
        }
        O.setET(ET);
        Intent intent = new Intent(getBaseContext(), ETicketControllerActivity.class);
        intent.putExtra("Order", O);
        startActivity(intent);
        finish();
    }

}
