package com.example.parsegram.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parsegram.Post;
import com.example.parsegram.PostAdapter;
import com.example.parsegram.R;
import com.example.parsegram.models.FeedPost;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FeedFragment extends Fragment {

    private static final String TAG = FeedFragment.class.getSimpleName();

    List<FeedPost> posts;
    PostAdapter adapter;

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // TODO: Move RV to a fragment
        RecyclerView rvPosts = (RecyclerView) view.findViewById(R.id.rvPosts);
        posts = new ArrayList<>();
        adapter = new PostAdapter(getContext(), posts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
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

//    private void goToCreateActivity() {
//        Log.i(TAG, "Starting create activity");
//        Intent intent = new Intent(HostActivity.this, CreatePostActivity.class);
//        startActivityForResult(intent, CREATE_POST_REQUEST_CODE);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//
//        if (requestCode == CREATE_POST_REQUEST_CODE && resultCode == RESULT_OK) {
//            // TODO: Refresh the recyclerview to have the newest post
//        } else if (requestCode == USER_SETTINGS_REQUEST_CODE && resultCode == RESULT_OK) {
//            // This result implies they hit the logout button
//            backToLogin();
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    private void backToLogin() {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        finish();
//    }

}

// Query format
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