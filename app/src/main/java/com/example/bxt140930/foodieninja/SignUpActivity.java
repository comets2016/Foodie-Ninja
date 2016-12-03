package com.example.bxt140930.Foodieninja;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bxt140930.Foodieninja.Entities.Credential;
import com.example.bxt140930.Foodieninja.Other.DBAdapter;
import com.example.bxt140930.Foodieninja.Other.ServerFacade;

/**
 * Created by jxj050100 on 11/15/2016.
 */

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

    public SignUpActivity() {
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
//                _signupButton.setEnabled(false);
                final EditText firstNameEditText = (EditText) findViewById(R.id.ETFirstName);
                final EditText lastNameEditText = (EditText) findViewById(R.id.ETLastName);
                final EditText emailEditText = (EditText) findViewById(R.id.ETEmail);
                final EditText idEditText = (EditText) findViewById(R.id.ETId);
                final EditText passwordEditText = (EditText) findViewById(R.id.ETPassword);

                if (firstNameEditText.getText().length() == 0) {
                    firstNameEditText.setError(s.getString(R.string.error_signup_firstName));
                }

                String firstName = firstNameEditText.getText().toString();

                if (lastNameEditText.getText().length() == 0) {
                    lastNameEditText.setError(s.getString(R.string.error_signup_lastName));
                }
                String lastName = lastNameEditText.getText().toString();

                String email = emailEditText.getText().toString();
                String id = idEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                ServerFacade JP = new ServerFacade(s);
                Credential CR = new Credential(id, firstName, lastName, email, password);


                String returnString = JP.SignUpRequest(CR);
                Context context = getApplicationContext();

                if (returnString == null) {
                    Toast.makeText(context, "Duplicated username or email address", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getBaseContext(), SignUpActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    DBAdapter sqlite = new DBAdapter(s);
                    sqlite.addCredential(new Credential(id, password));
                    Intent intent = new Intent(getBaseContext(), FoodJointControllerActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public void validate(String firstName, String lastName, String email, String id, String password) {
//        boolean validate = true;

        if (firstName.isEmpty() || firstName == null) {
            String errormessage = s.getString(R.string.error_signup_firstName);
        }

        if (lastName.isEmpty() || lastName == null) {
//            lastNameText.setError("cannot be empty");
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            emailText.setError("enter a valid email address");
        }

        if (id.isEmpty() || id.length() < 2) {
//            idText.setError("id cannot be empty nor it must be longer than a character");
        } else if (id.length() > 100) {
//            idText.setError("id cannot be longer than 100 characters");
        }
        if (password.isEmpty() || password.length() < 5) {
//            passwordText.setError("password must be longer than 4 characters");
        } else if (password.length() > 60) {
//            passwordText.setError("password cannot be longer than 60 characters");
        } else {
            //  passwordText.setError(null);
        }
    }
}
