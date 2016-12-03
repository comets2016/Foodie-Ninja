package com.example.bxt140930.Foodieninja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.bxt140930.Foodieninja.Entities.FoodJoint;
import com.example.bxt140930.Foodieninja.Other.LoginSinglton;
import com.example.bxt140930.Foodieninja.Other.ServerFacade;

import java.util.ArrayList;

public class FoodJointControllerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginSinglton.getInstance().validateUser(this);

        Button Btn = (Button) findViewById(R.id.BTNLoginInfo);
        Btn.setText("Logged In As : " + LoginSinglton.getInstance().GetUserId(this));

        ServerFacade JP = new ServerFacade(this);
        ArrayList<FoodJoint> FoodJointList = JP.GetFoodJointsList();

        ListView listview = (ListView) findViewById(R.id.RestaurantList);
        listview.setAdapter(new FoodjointListAdapter(this, FoodJointList));

        Button BtnLogOut = (Button) findViewById(R.id.BTNLogout);
        BtnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginSinglton.getInstance().SignOut(FoodJointControllerActivity.this);
            }
        });
    }
}
