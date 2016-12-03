package com.example.bxt140930.Foodieninja;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bxt140930.Foodieninja.Entities.Credential;
import com.example.bxt140930.Foodieninja.Other.DBAdapter;
import com.example.bxt140930.Foodieninja.Other.ServerFacade;

public class LoginControllerActivity extends AppCompatActivity {
    String userNameForServer="";
    String passwordForServer="";
    private static final int REQUEST_SIGNUP = 0;

    Context c;
    public LoginControllerActivity()
    {
        this.c = this;
    }
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int retryCount = 3;

            setContentView(R.layout.activity_login);
            final EditText user = (EditText) findViewById(R.id.ETUser);
            final EditText password = (EditText) findViewById(R.id.ETPass);

            final Button login = (Button) findViewById(R.id.BTNLogin);
            final TextView signUp = (TextView) findViewById(R.id.TXTSignUp);
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    userNameForServer = user.getText().toString();
                    passwordForServer = password.getText().toString();
                    ServerFacade JP = new ServerFacade(c);
                    Credential CR = new Credential();
                    CR.setUsername(userNameForServer);
                    CR.setPassword(passwordForServer);
//                JP.CheckCredentials(CR);
                    String returnString = JP.CheckCredentials(CR);
                    int returnCode = Integer.parseInt(returnString);
                    if (returnCode != 200) {
                        // Either id or password not valid to login.
                        Toast.makeText(getBaseContext(), c.getString(R.string.error_invalid_User_Password + returnCode), Toast.LENGTH_LONG).show();
                        // Not quite sure this is the right approach
                        Intent intent = new Intent(getBaseContext(), LoginControllerActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        DBAdapter sqlite = new DBAdapter(c);
                        sqlite.addCredential(new Credential(userNameForServer, passwordForServer));
                        login.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                userNameForServer = user.getText().toString();
                                passwordForServer = password.getText().toString();
                                ServerFacade JP = new ServerFacade(c);
                                Credential CR = new Credential();
                                CR.setUsername(userNameForServer);
                                CR.setPassword(passwordForServer);
                                JP.CheckCredentials(CR);
                                String returnString = JP.CheckCredentials(CR);
                                int returnCode = Integer.parseInt(returnString);

                                //TODO do i still need sqlite db?
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString("id", userNameForServer);
                                editor.putString("password", passwordForServer);
                                editor.commit();

                                Intent intent = new Intent(getBaseContext(), FoodJointControllerActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                }
            });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
            }
        });
    }
}