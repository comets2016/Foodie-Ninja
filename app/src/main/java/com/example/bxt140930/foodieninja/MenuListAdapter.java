package com.example.bxt140930.Foodieninja;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bxt140930.Foodieninja.Entities.Item;

import java.util.ArrayList;

class MenuListAdapter extends BaseAdapter {

    Context context;
    ArrayList<Item> Menu;
    OrderItemListAdapter OILA;
    private static LayoutInflater inflater = null;

    public MenuListAdapter(Context context, ArrayList<Item> Item, OrderItemListAdapter OILA) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.Menu = Item;
        this.OILA = OILA;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Menu.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return Menu.get(position);
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
            vi = inflater.inflate(R.layout.menu_row, null);


        TextView text = (TextView) vi.findViewById(R.id.Itemname);
        text.setText(Menu.get(position).getName());

        text = (TextView) vi.findViewById(R.id.ItemPrice);
        text.setText(String.valueOf(Menu.get(position).getPrice()));

        byte[] decodedString = Base64.decode(Menu.get(position).getImageUrl(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        ImageView IV = (ImageView) vi.findViewById(R.id.IVLogo);
        IV.setImageBitmap(decodedByte);

        Button B = (Button)vi.findViewById(R.id.BTNAdd);
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OILA.AddItem(Menu.get(position));
            }
        });

        return vi;
    }
}