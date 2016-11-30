package com.example.bxt140930.Foodieninja;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bxt140930.Foodieninja.Entities.Menu;
import com.example.bxt140930.Foodieninja.Entities.OrderItem;

import java.util.ArrayList;

class OrderItemListAdapter extends BaseAdapter {

    Context context;
    ArrayList<OrderItem> Items;
    private static LayoutInflater inflater = null;

    public OrderItemListAdapter(Context context, ArrayList<OrderItem> Items) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.Items = Items;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Items.size();
    }

    public void AddItem(Menu Item) {
        for(OrderItem I : Items)
            if(I.getItem().getId() == Item.getId())
            {
                I.setQuantuty(I.getQuantuty() + 1);
                notifyDataSetChanged();
                return;
            }
        OrderItem OI = new OrderItem();
        OI.setItem(Item);
        OI.setQuantuty(1);
        Items.add(OI);
        notifyDataSetChanged();
        return;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return Items.get(position);
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
            vi = inflater.inflate(R.layout.order_item_row, null);


        TextView text = (TextView) vi.findViewById(R.id.TVItemName);
        text.setText(Items.get(position).getItem().getName());

        text = (TextView) vi.findViewById(R.id.TVItemQuantity);
        text.setText(String.valueOf(Items.get(position).getQuantuty()));

        byte[] decodedString = Base64.decode(Items.get(position).getItem().getImageUrl(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        ImageView IV = (ImageView) vi.findViewById(R.id.IVItemImage);
        IV.setImageBitmap(decodedByte);

        ImageButton IMGAdd = (ImageButton) vi.findViewById(R.id.IMGBTNAddOne);
        IMGAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Items.get(position).setQuantuty(Items.get(position).getQuantuty() + 1);
                notifyDataSetChanged();
            }
        });

        ImageButton IMGRemove = (ImageButton) vi.findViewById(R.id.IMGBTNRemoveOne);
        IMGRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Items.get(position).getQuantuty() <= 1)
                    Items.remove(position);
                else
                    Items.get(position).setQuantuty(Items.get(position).getQuantuty() - 1);
                notifyDataSetChanged();
            }
        });

        return vi;
    }
}