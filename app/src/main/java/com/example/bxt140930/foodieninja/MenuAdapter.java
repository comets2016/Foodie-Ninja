package com.example.bxt140930.foodieninja;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class MenuAdapter extends BaseAdapter {

    Context context;
    String[] Names;
    String[] Prices;
    int [] ResIDs;
    private static LayoutInflater inflater = null;

    public MenuAdapter(Context context, String[] Name, String[] Hours, int [] Logo) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.Names = Name;
        this.Prices = Hours;
        ResIDs = Logo;
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
            vi = inflater.inflate(R.layout.menu_row, null);

        TextView text = (TextView) vi.findViewById(R.id.Itemname);
        text.setText(Names[position]);

        text = (TextView) vi.findViewById(R.id.ItemPrice);
        text.setText(Prices[position]);

        ImageView Logo = (ImageView) vi.findViewById(R.id.IVLogo);
        Logo.setImageResource(ResIDs[position]);
        return vi;
    }
}