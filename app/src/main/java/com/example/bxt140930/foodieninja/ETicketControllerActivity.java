package com.example.bxt140930.Foodieninja;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bxt140930.Foodieninja.Entities.ETicket;
import com.example.bxt140930.Foodieninja.Entities.Order;

import java.text.SimpleDateFormat;

public class ETicketControllerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eticket);

        final Order O = (Order) getIntent().getSerializableExtra("Order");

        TextView TV = (TextView) findViewById(R.id.TVNumber);
        TV.setText(String.valueOf(O.getET().getNumber()));

        TV = (TextView) findViewById(R.id.TVResName);
        TV.setText(String.valueOf(O.getFoodJoint().getName()));

        TV = (TextView) findViewById(R.id.TVWaitTime);
        TV.setText(String.valueOf("Estimated Wait Time: " + O.getET().getWaitTime()) + " Minutes");

        TV = (TextView) findViewById(R.id.TVDate);
        TV.setText(String.valueOf("Order Date and Time: " + O.getET().getCreateTime()));

        byte[] decodedString = Base64.decode(O.getFoodJoint().getImageUrl(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        ImageView IV = (ImageView) findViewById(R.id.IVLogo);
        IV.setImageBitmap(decodedByte);
    }
}
