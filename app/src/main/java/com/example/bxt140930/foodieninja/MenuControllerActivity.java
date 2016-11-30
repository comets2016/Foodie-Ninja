package com.example.bxt140930.Foodieninja;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.bxt140930.Foodieninja.Entities.Order;
import com.example.bxt140930.Foodieninja.Other.ServerFacade;

public class MenuControllerActivity extends AppCompatActivity {
    com.example.bxt140930.Foodieninja.Entities.Order Order = new Order();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent I = getIntent();
        int ID = I.getIntExtra("ID", -1);

        if(ID < 0)
            finish();

        ServerFacade JP = new ServerFacade(this);



        final Button Fin = (Button)findViewById(R.id.BTNFinalize);
        Fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(getBaseContext(), PaymentControllerActivity.class);
                I.putExtra("Order", Order);
                startActivity(I);
                finish();
            }
        });


        ListView LVOrderItems = (ListView) findViewById(R.id.OrderList);
        final OrderItemListAdapter OILA = new OrderItemListAdapter(this, Order.getOrderItems());
        LVOrderItems.setAdapter(OILA);


        //ListView listview = (ListView) findViewById(R.id.MenuList);
        //listview.setAdapter(new MenuListAdapter(this, JP.GetMenu(ID), OILA, (Button)findViewById(R.id.BTNFinalize)));
        //listview.deferNotifyDataSetChanged();
    }
}
