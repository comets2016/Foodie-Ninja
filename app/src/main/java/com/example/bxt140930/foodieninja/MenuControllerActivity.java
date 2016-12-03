package com.example.bxt140930.Foodieninja;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bxt140930.Foodieninja.Entities.Order;
import com.example.bxt140930.Foodieninja.Entities.FoodJoint;
import com.example.bxt140930.Foodieninja.Other.ServerFacade;

public class MenuControllerActivity extends AppCompatActivity {
    com.example.bxt140930.Foodieninja.Entities.Order Order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        FoodJoint FJ = (FoodJoint) getIntent().getSerializableExtra("FoodJoint");


        TextView text = (TextView) findViewById(R.id.TVMenuHeader);
        text.setText(FJ.getName() + " Menu");

        byte[] decodedString = Base64.decode(FJ.getImageUrl(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        ImageView IV = (ImageView) findViewById(R.id.IVLogo);
        IV.setImageBitmap(decodedByte);

        if (FJ == null)
            finish();
        Order = new Order(FJ);
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
        final OrderItemListAdapter OILA = new OrderItemListAdapter(this, Order);
        LVOrderItems.setAdapter(OILA);


        ListView listview = (ListView) findViewById(R.id.MenuList);
        listview.setAdapter(new MenuListAdapter(this, JP.GetMenu(FJ.getId()), OILA));
        listview.deferNotifyDataSetChanged();
    }
}
