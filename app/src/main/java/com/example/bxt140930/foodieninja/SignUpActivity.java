package com.example.bxt140930.Foodieninja;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bxt140930.Foodieninja.Entities.Credential;
import org.json.JSONObject;
import com.example.bxt140930.Foodieninja.Communication.CommunicationManager;
import com.example.bxt140930.Foodieninja.Other.SQLiteJDBCforCredential;

public class SignUpActivity extends AppCompatActivity {

    /*
    "login": "tester2",
    "firstName": "tester2",
    "lastName": "tester2",
    "email": "teste2r@localhost",
    "langKey": "en",
    "password": "1234"
     */
    Button _signupButton;
    EditText firstNameText;
    EditText lastNameText;
    EditText emailText;
    EditText idText;
    EditText passwordText;
    Context s;
    public SignUpActivity()
    {
        this.s = this;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        _signupButton = (Button) findViewById(R.id.BTNSignUp);
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }
    public void signup() {
//        Log.d(TAG, "Signup");

        _signupButton.setEnabled(false);

        String firstName = firstNameText.getText().toString();
        String lastName = lastNameText.getText().toString();
        String email = emailText.getText().toString();
        String id = idText.getText().toString();
        String password = passwordText.getText().toString();

        validate(firstName, lastName, email, id, password);
        ServerFacade JP = new ServerFacade(s);
        Credential CR = new Credential(id, firstName, lastName, email, password);

        String returnString = JP.SignUpRequest(CR);
        int returnCode = Integer.parseInt(returnString);

        if (returnCode != 201) {
            // Something went wrong so
            Toast.makeText(s, s.getString(R.string.error_signup_fail + returnCode), Toast.LENGTH_LONG).show();
            // Not quite sure this is the right approach
            Intent intent = new Intent(getBaseContext(), SignUpActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            SQLiteJDBCforCredential sqlite = new SQLiteJDBCforCredential(s);
            sqlite.addCredential(new Credential(id, password));
            Intent intent = new Intent(getBaseContext(), FoodJointControllerActivity.class);
            startActivity(intent);
            finish();
        }
    }
    public void validate(String firstName, String lastName, String email, String id, String password) {
        boolean valid = true;

        if (firstName.isEmpty()) {
            firstNameText.setError("cannot be empty");
            valid = false;
        }

        if (lastName.isEmpty()){
            lastNameText.setError("cannot be empty");
            valid = false;
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid email address");
            valid = false;
        }

        if (id.isEmpty() || id.length() < 2) {
            idText.setError("id cannot be empty nor it must be longer than a character");
            valid = false;
        } else if(id.length() > 100) {
            idText.setError("id cannot be longer than 100 characters");
            valid = false;
        }
        if (password.isEmpty() || password.length() < 5) {
            passwordText.setError("password must be longer than 4 characters");
            valid = false;
        } else if (password.length() > 60)
        {
            passwordText.setError("password cannot be longer than 60 characters");
            valid = false;
        }
        else {
          //  passwordText.setError(null);
        }
//        return valid;
    }
}
