package com.example.bxt140930.foodieninja;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

class RestaurantAdapter extends BaseAdapter {

    Context context;
    String[] Names;
    String[] Hours;
    String[] WaitTime;
    private static LayoutInflater inflater = null;

    public RestaurantAdapter(Context context, String[] Name,  String[] Hours,  String[] WaitingTime) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.Names = Name;
        this.Hours = Hours;
        this.WaitTime = WaitingTime;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Names.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return Names[position];
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
            vi = inflater.inflate(R.layout.restaurant_row, null);
        TextView text = (TextView) vi.findViewById(R.id.text);
        text.setText(Names[position]);
        return vi;
    }
}