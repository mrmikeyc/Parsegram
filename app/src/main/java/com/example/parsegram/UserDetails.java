package com.example.parsegram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;

public class UserDetails extends AppCompatActivity {

    TextView tvUsernameDetails;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        btnLogout = findViewById(R.id.btnLogout);
        tvUsernameDetails = findViewById(R.id.tvUsernameDetails);

        tvUsernameDetails.setText(ParseUser.getCurrentUser().getUsername());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sign the user out
                ParseUser.logOut();
                // Return to the feed screen and go back to login
                Intent intent = new Intent();
                intent.putExtra("LOGOUTBOOL", true);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}