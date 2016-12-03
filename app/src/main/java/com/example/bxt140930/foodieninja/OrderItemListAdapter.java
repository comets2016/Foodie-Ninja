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
import android.widget.Toast;

import com.example.bxt140930.Foodieninja.Entities.FoodJoint;
import com.example.bxt140930.Foodieninja.Entities.Item;
import com.example.bxt140930.Foodieninja.Entities.Order;

class OrderItemListAdapter extends BaseAdapter {

    Context context;
    Order Order;
    private static LayoutInflater inflater = null;

    public OrderItemListAdapter(Context context, Order Order) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.Order = Order;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Order.getOrderItems().size();
    }

    public void AddItem(Item Item) {
        if (Order == null)
            Order = new Order(new FoodJoint());
        if (!Order.AddOrderItems(Item))
            Toast.makeText(context.getApplicationContext(), context.getString(R.string.exceed_items), Toast.LENGTH_LONG);
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return Order.getOrderItems().get(position);
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
        text.setText(Order.getOrderItems().get(position).getItem().getName());

        text = (TextView) vi.findViewById(R.id.TVItemQuantity);
        text.setText(String.valueOf(Order.getOrderItems().get(position).getQuantuty()));

        byte[] decodedString = Base64.decode(Order.getOrderItems().get(position).getItem().getImageUrl(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        ImageView IV = (ImageView) vi.findViewById(R.id.IVItemImage);
        IV.setImageBitmap(decodedByte);

        ImageButton IMGAdd = (ImageButton) vi.findViewById(R.id.IMGBTNAddOne);
        IMGAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddItem(Order.getOrderItems().get(position).getItem());
                notifyDataSetChanged();
            }
        });

        ImageButton IMGRemove = (ImageButton) vi.findViewById(R.id.IMGBTNRemoveOne);
        IMGRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Order.getOrderItems().get(position).getQuantuty() <= 1)
                    Order.getOrderItems().remove(position);
                else
                    Order.getOrderItems().get(position).setQuantuty(Order.getOrderItems().get(position).getQuantuty() - 1);
                notifyDataSetChanged();
            }
        });
        return vi;
    }
}