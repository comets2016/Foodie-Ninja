package com.example.bxt140930.Foodieninja;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import static android.R.attr.x;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //RelativeLayout relativeLayoutMain = (RelativeLayout)  findViewById(R.id.RLWelcome);
        //relativeLayoutMain.animate().alpha(1).setDuration(3000);


        final Intent I = new Intent(this, FoodJointControllerActivity.class);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                startActivity(I);
                finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        }, 5000);
    }
}
