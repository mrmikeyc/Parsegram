package com.example.parsegram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class FeedActivity extends AppCompatActivity {

    private static final int CREATE_POST_REQUEST_CODE = 200;
    private static final String TAG = FeedActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menuBtnCreatePost) {
            // Go to create post activity
            Log.i(TAG, "Starting create activity!");
            Intent intent = new Intent(this, CreatePostActivity.class);
            startActivityForResult(intent, CREATE_POST_REQUEST_CODE);
        }

        return super.onOptionsItemSelected(item);
    }
}