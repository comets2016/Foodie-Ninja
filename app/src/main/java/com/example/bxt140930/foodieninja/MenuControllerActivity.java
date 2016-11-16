package com.example.bxt140930.foodieninja;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;

public class MenuControllerActivity extends AppCompatActivity {
    Order Order = new Order();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent I = getIntent();
        int ID = I.getIntExtra("ID", -1);

        if(ID < 0)
            finish();

        JsonParser JP = new JsonParser(this);

        ListView LVOrderItems = (ListView) findViewById(R.id.OrderList);
        OrderItemListAdapter OILA = new OrderItemListAdapter(this, Order.getOrderItems());
        LVOrderItems.setAdapter(OILA);

        ListView listview = (ListView) findViewById(R.id.MenuList);
        listview.setAdapter(new MenuListAdapter(this, JP.GetMenu(ID), OILA));
        listview.deferNotifyDataSetChanged();

        Button Fin = (Button)findViewById(R.id.BTNFinalize);
        Fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(getBaseContext(), PaymentControllerActivity.class);
                I.putExtra("Order", Order);
                startActivity(I);
                finish();
            }
        });
    }
}
