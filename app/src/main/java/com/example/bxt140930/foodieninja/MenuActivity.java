package com.example.bxt140930.foodieninja;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent I = getIntent();
        int ID = I.getIntExtra("ID", -1);

        if(ID < 0)
            finish();

        JsonParser JP = new JsonParser(this);
        ;

        ListView listview = (ListView) findViewById(R.id.MenuList);
        listview.setAdapter(new MenuAdapter(this, JP.GetMenu(ID)));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent appInfo = new Intent(MenuActivity.this, Restaurant.class);
                startActivity(appInfo);
                finish();
            }
        });

        Button Fin = (Button)findViewById(R.id.BTNFinalize);
        Fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Payment.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
