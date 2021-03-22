package com.example.parsegram.fragments;

import android.util.Log;
import android.view.Menu;

import com.example.parsegram.R;
import com.example.parsegram.models.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends FeedFragment {

    private static final String TAG = ProfileFragment.class.getSimpleName();

    @Override
    protected void fetchPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);

        // Include a specific key so that we can narrow down our posts (i.e. by user)
        // query.include(Post.KEY_USER);
        query.setLimit(FeedFragment.NUM_POSTS);
        query.addDescendingOrder(Post.KEY_CREATED_AT);

        // This is really the only change - we just filter against the current signed in user
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());

        // Launch actual post
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> queriedPosts, ParseException e) {
                // The list of all post objects that fit our query

                if (e == null) {
                    // Note that a Post is a db response item, and a FeedPost is a model
                    allPosts.addAll(queriedPosts);
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e(TAG, "Error querying post data: " + e);
                }
            }
        });
    }
}
