package com.example.bxt140930.foodieninja;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.xml.sax.helpers.LocatorImpl;

import communication.HTTPPostJsonCommunication;

public class LoginActivity extends AppCompatActivity {
    String userNameForServer="";
    String passwordForServer="";
    private static final int REQUEST_SIGNUP = 0;

    Context c;
    public LoginActivity()
    {
        this.c = this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //to check whether the user already login
        setContentView(R.layout.activity_login);
        final EditText user = (EditText)findViewById(R.id.ETUser);
        final EditText password = (EditText)findViewById(R.id.ETPass);

        Button login = (Button)findViewById(R.id.BTNLogin);
        TextView signUp = (TextView)findViewById(R.id.TXTSignUp);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userNameForServer = user.getText().toString();
                passwordForServer = password.getText().toString();
                JsonParser JP = new JsonParser(c);
                Credential CR = new Credential();
                CR.setUsername(userNameForServer);
                CR.setPassword(passwordForServer);
                JP.CheckCredentials(CR);
                String returnString =  JP.CheckCredentials(CR);
                int returnCode = Integer.parseInt(returnString);

                if (returnCode != 200)
                {

                }
                else
                {
                    SQLiteJDBCforCredential sqlite = new SQLiteJDBCforCredential(c);
                    sqlite.addCredential(new Credential(userNameForServer,passwordForServer));
                    Intent intent = new Intent(getBaseContext(), FoodJointControllerActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
              Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }
}