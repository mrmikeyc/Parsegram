package com.example.parsegram;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class CreatePostActivity extends AppCompatActivity {

    private static final String TAG = CreatePostActivity.class.getSimpleName();

    Button btnSubmit;
    Button btnTakePicture;
    EditText etDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        etDescription = findViewById(R.id.etCreateDescription);
        btnSubmit = findViewById(R.id.menuBtnFinishPost);
        btnTakePicture = findViewById(R.id.btnTakePicture);
    }


        private void savePost(String description, ParseUser currentUser) {
        Post post = new Post();
        post.setDescription(description);
        post.setUser(currentUser);
        // TODO: post.setImage();

        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.i(TAG, "Post was successfully saved!");
                    etDescription.setText("");
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Log.e(TAG, "Error while saving: " + e);
                    Toast.makeText(CreatePostActivity.this, "Unable to save post", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.creation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menuBtnFinishPost) {
            String description = etDescription.getText().toString();
            ParseUser user = ParseUser.getCurrentUser();
            savePost(description, user);
        }

        return super.onOptionsItemSelected(item);
    }
}