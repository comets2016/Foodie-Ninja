package com.example.bxt140930.foodieninja;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

class MenuAdapter extends BaseAdapter {

    Context context;
    ArrayList<Menu> Menu;
    private static LayoutInflater inflater = null;

    public MenuAdapter(Context context, ArrayList<Menu> Menu) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.Menu = Menu;
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
    public View getView(int position, View convertView, ViewGroup parent) {
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

        return vi;
    }
}