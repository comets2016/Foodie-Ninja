package com.example.bxt140930.Foodieninja;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bxt140930.Foodieninja.Entities.FoodJoint;
import com.example.bxt140930.Foodieninja.Entities.Order;
import com.example.bxt140930.Foodieninja.Other.ServerFacade;

import java.util.ArrayList;

class FoodjointListAdapter extends BaseAdapter {

    Context context;
    ArrayList<FoodJoint> FoodJointList;
    private static LayoutInflater inflater = null;

    public FoodjointListAdapter(Context context, ArrayList<FoodJoint> FoodJointList) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.FoodJointList = FoodJointList;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return FoodJointList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return FoodJointList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.restaurant_row, null);

        TextView text = (TextView) vi.findViewById(R.id.ResName);
        text.setText(FoodJointList.get(position).getName());

        text = (TextView) vi.findViewById(R.id.ResWhour);
        text.setText(FoodJointList.get(position).getWorkingHours());

        text = (TextView) vi.findViewById(R.id.ResWaitTime);
        text.setText(context.getString(R.string.EstimatedWaitTime) + " " + FoodJointList.get(position).getEstimatedWait());

        byte[] decodedString = Base64.decode(FoodJointList.get(position).getImageUrl(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        ImageView IV = (ImageView) vi.findViewById(R.id.IVLogo);
        IV.setImageBitmap(decodedByte);

        Button IMGBTN = (Button) vi.findViewById(R.id.BTNGetMenu);
        IMGBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(context, MenuControllerActivity.class);
                I.putExtra("FoodJoint", FoodJointList.get(position));
                context.startActivity(I);
                ((Activity) context).overridePendingTransition(R.anim.rtl_slide_in, R.anim.rtl_slide_out);
            }
        });

        IMGBTN = (Button) vi.findViewById(R.id.BTNGetTicket);
        IMGBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServerFacade SF = new ServerFacade(context);
                Order O = new Order(FoodJointList.get(position));
                O.setET(SF.GetETicket(FoodJointList.get(position)));
                Intent intent = new Intent(context, ETicketControllerActivity.class);
                intent.putExtra("Order", O);
                context.startActivity(intent);
            }
        });

        return vi;
    }
}