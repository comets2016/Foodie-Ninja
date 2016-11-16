package com.example.bxt140930.foodieninja;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by jxj050100 on 11/15/2016.
 */

public class SignUpActivity extends AppCompatActivity {

    Button _signupButton;
    EditText firstNameText;
    EditText lastNameText;
    EditText emailText;
    EditText idText;
    EditText passwordText;

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

//        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
//                R.style.AppTheme_Dark_Dialog);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("Creating Account...");
//        progressDialog.show();

        String firstName = firstNameText.getText().toString();
        String lastName = lastNameText.getText().toString();
        String email = emailText.getText().toString();
        String id = idText.getText().toString();
        String password = passwordText.getText().toString();

        if (!validate(firstName, lastName, email, id, password)) {
//            onSignupFailed();
            return;
        }
        // TODO: Implement your own signup logic here.

//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                         On complete call either onSignupSuccess or onSignupFailed
//                         depending on success
//                        onSignupSuccess();
//                         onSignupFailed();
//                        progressDialog.dismiss();
//                    }
//                }, 3000);
    }
    public boolean validate(String firstName, String lastName, String email, String id, String password) {
        boolean valid = true;

        // todo more error checking here
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

        if (id.isEmpty() || id.length() < 4 ) {
            idText.setError("id must be longer than 4 characters");
            valid = false;
        } else {
            idText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }
        return valid;
    }
}
