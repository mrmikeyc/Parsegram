package com.example.parsegram;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    ProgressBar loadingProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        loadingProgressBar = findViewById(R.id.loading);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                String user = usernameEditText.getText().toString();
                String pass = passwordEditText.getText().toString();
                loginUser(user, pass);
            }
        });
    }

    private void loginUser(String username, String password) {
        Log.i(TAG, String.format("Attempting to login user: %s", username));

        // Navigate to feed activity if successful sign in
        // Login on the background thread to not block the UI
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                // If we succeed, go to feed activity
                // otherwise, error

                if (e == null) {
                    // Provided we didn't fail, go to feed activity
                    goToFeedActivity();
                    Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(TAG, "Issue with login: " + e);
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }

                loadingProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void goToFeedActivity() {
        Intent intent = new Intent(this, FeedActivity.class);
        startActivity(intent);

        // Finish the activity is good because this will remove the activity from the stack, so
        // the back button won't bring us back to the login screen.
        finish();
    }
}