package com.example.parsegram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = SignUpActivity.class.getSimpleName();

    EditText etNewUsername;
    EditText etNewPassword;
    Button btnSignup;
    ProgressBar signUpLoadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etNewUsername = findViewById(R.id.etSignupUsername);
        etNewPassword = findViewById(R.id.etSignupPassword);
        btnSignup = findViewById(R.id.btnSignup);
        signUpLoadingProgressBar = findViewById(R.id.pbSignupProgressBar);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When clicked, pull the info
                signUpLoadingProgressBar.setVisibility(View.VISIBLE);
                String username = etNewUsername.getText().toString();
                String password = etNewPassword.getText().toString();
                signUpUser(username, password);
            }
        });
    }

    private void signUpUser(String username, String password) {
        ParseUser newUser = new ParseUser();
        // Core properties
        newUser.setUsername(username);
        newUser.setPassword(password);
        // If we had any extra properties, put them in here.  But we don't.

        newUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.i(TAG, "Signup successful!  Returning to welcome to then move to Feed Screen");
                    Toast.makeText(SignUpActivity.this, "Sign up successful!", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Log.e(TAG, "Error signing up.  Reason: " + e);
                    Toast.makeText(SignUpActivity.this, "Sign up unsuccessful.", Toast.LENGTH_SHORT).show();
                }
                signUpLoadingProgressBar.setVisibility(View.INVISIBLE);
            }
        });

    }
}