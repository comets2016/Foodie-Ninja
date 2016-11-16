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

public class LoginActivity extends AppCompatActivity {
    String userNameForServer="";
    String passwordForServer="";
    private static final int REQUEST_SIGNUP = 0;

    Context c;
    LoginActivity()
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
                CommunicationManager cm = new CommunicationManager();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("j_username", userNameForServer);
                    jsonObject.put("j_password", passwordForServer);
                } catch(Exception e)
                {
                    //TODO: need different error message
                    Toast.makeText(c, c.getString(R.string.ErrorRetrvingData), Toast.LENGTH_LONG).show();
                }

                int returnCode = cm.sendJsonPOSTResuest(c, "api/authentication", jsonObject);


                // 200 is the good return code that indicates the server found the user name/password
                if (returnCode != 200)
                {
                    // throwing an error with some kind of message
                    finish();
                }
                else
                {
                    // successful case! Good user id and password
                    // save the credential to the sqlite
                    String tableName="credential";
                    SQLiteJDBCforCredential sqlite = new SQLiteJDBCforCredential(c, tableName);

                    // storing the credential to the credential table
                    sqlite.addCredential(new Credential(userNameForServer,passwordForServer));

//                    if((sqlite.createDB(tableName) && sqlite.createTable(tableName))!=false)
//                    {
//                         insert into DB
//                        sqlite.insertIntoTable(tableName, userNameForServer, passwordForServer);
//                    }
//                    else
//                    {
//                        finish();
//                    }

                }
                //moving to the next page.. not yet.s.
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
//                finish();
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