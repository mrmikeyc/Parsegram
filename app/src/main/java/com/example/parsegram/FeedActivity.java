package com.example.parsegram;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parsegram.models.FeedPost;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    private static final int CREATE_POST_REQUEST_CODE = 200;
    private static final int USER_SETTINGS_REQUEST_CODE = 300;
    private static final String TAG = FeedActivity.class.getSimpleName();

    FloatingActionButton fabCreatePost;
    List<FeedPost> posts;
    PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        fabCreatePost = findViewById(R.id.fabCreatePost);

        fabCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCreateActivity();
            }
        });

        RecyclerView rvPosts = (RecyclerView) findViewById(R.id.rvPosts);
        posts = new ArrayList<>();
        adapter = new PostAdapter(this, posts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvPosts.setLayoutManager(linearLayoutManager);
        rvPosts.setAdapter(adapter);

        fetchPosts();
    }

    private void fetchPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);

        // Include a specific key so that we can narrow down our posts (i.e. by user)
        // query.include(Post.KEY_USER);

        // Launch actual post
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> queriedPosts, ParseException e) {
                // The list of all post objects that fit our query

                if (e == null) {
                    try {
                        // Note that a Post is a db response item, and a FeedPost is a model
                        posts.addAll(FeedPost.createFeedPostList(queriedPosts));
                        Collections.reverse(posts);
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e(TAG, "Error querying post data: " + e);
                }
            }
        });
    }

    private void goToCreateActivity() {
        Log.i(TAG, "Starting create activity");
        Intent intent = new Intent(FeedActivity.this, CreatePostActivity.class);
        startActivityForResult(intent, CREATE_POST_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == CREATE_POST_REQUEST_CODE && resultCode == RESULT_OK) {
            // TODO: Refresh the recyclerview to have the newest post
        } else if (requestCode == USER_SETTINGS_REQUEST_CODE && resultCode == RESULT_OK) {
            // This result implies they hit the logout button
            backToLogin();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void backToLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menuBtnUserDetails) {
            // Go to create user details
            Log.i(TAG, "Entering user details activity");
            Intent intent = new Intent(this, UserDetails.class);
            startActivityForResult(intent, USER_SETTINGS_REQUEST_CODE);
        }
        return super.onOptionsItemSelected(item);
    }

}


/*

    private void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);

        // Include a specific key so that we can narrow down our posts (i.e. by user)
        query.include(Post.KEY_USER);

        // Launch actual post
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                // The list of all post objects that fit our query

                if (e == null) {
                    for (Post post : posts) {
                        Log.i(TAG, "Post: " + post.getDescription());
                        Log.i(TAG, "User: " + post.getUser().getUsername());
                    }
                } else {
                    Log.e(TAG, "Error querying post data: " + e);
                }
            }
        });
    }

 */