package com.example.parsegram;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int LOGIN_ACTIVITY_REQUEST_CODE = 100;
    private static final int SIGN_UP_ACTIVITY_REQUEST_CODE = 700;

    Button btnLogin;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if someone is already logged on.
        if (ParseUser.getCurrentUser() != null) {
            Log.i(TAG, String.format("User [%s] was already logged on - moving to feed!", ParseUser.getCurrentUser().getUsername()));
            goToFeedActivity();
        }

        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start intent to go to login activity
                Log.i(TAG, "Login button pressed");
                goToLoginActivity();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start intent to go to sign up activity
                Log.i(TAG, "Sign up button pressed");
                gotoSignupActivity();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == LOGIN_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Log.i(TAG, "Login successful, moving to feed screen");
            goToFeedActivity();
            finish();
        } else if (requestCode == SIGN_UP_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Log.i(TAG, "Sign up successful, moving to feed screen");
            goToFeedActivity();
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void goToFeedActivity() {
        Intent intent = new Intent(this, HostActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoSignupActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivityForResult(intent, SIGN_UP_ACTIVITY_REQUEST_CODE);
    }

    private void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, LOGIN_ACTIVITY_REQUEST_CODE);
    }
}