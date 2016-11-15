package com.example.bxt140930.foodieninja;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONObject;
import org.xml.sax.helpers.LocatorImpl;

public class LoginActivity extends AppCompatActivity {
    String userNameForServer="";
    String passwordForServer="";
    Context C;
   LoginActivity()
    {
        this.C = this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LoginSinglton loginSinglton = LoginSinglton.getInstance();

        //to check whether the user already login

        loginSinglton.validateUser(userNameForServer, passwordForServer);

        setContentView(R.layout.activity_login);
        final EditText user = (EditText)findViewById(R.id.ETUser);
        final EditText password = (EditText)findViewById(R.id.ETPass);

        Button login = (Button)findViewById(R.id.BTNLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userNameForServer = user.getText().toString();
                passwordForServer = password.getText().toString();
                CommunicationManager cm = new CommunicationManager();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("j_username", userNameForServer);
                    jsonObject.put("j_password", passwordForServer);
                } catch(Exception e)
                {
                    //
                }

                String Result = cm.sendJsonPOSTResuest(C, "api/authentication", jsonObject);
                //moving to the next page.. not yet.s.
//                Intent intent = new Intent(getBaseContext(), MainActivity.class);
//                startActivity(intent);
//                finish();
            }
        });
    }
}
//    ListView listview = (ListView) findViewById(R.id.MenuList);
//listview.setAdapter(new MenuAdapter(this, JP.GetMenu(ID)));
//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//@Override
//public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
//        Intent appInfo = new Intent(MenuActivity.this, Restaurant.class);
//        startActivity(appInfo);
//        finish();
//        }
//        });