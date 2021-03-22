package com.example.parsegram.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.parsegram.models.Post;
import com.example.parsegram.PostAdapter;
import com.example.parsegram.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class FeedFragment extends Fragment {

    private static final String TAG = FeedFragment.class.getSimpleName();
    private static final String CREATED_AT_KEY = "createdAt";
    public static final int NUM_POSTS = 20;

    List<Post> allPosts;
    PostAdapter adapter;
    RecyclerView rvPosts;
    SwipeRefreshLayout swipeRefreshLayout;

    public FeedFragment() {
        // Required empty public constructor
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

        swipeRefreshLayout = view.findViewById(R.id.swiperefresh);
        rvPosts = view.findViewById(R.id.rvPosts);
        allPosts = new ArrayList<>();
        adapter = new PostAdapter(getContext(), allPosts);
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPosts.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "Swiped to refresh data - fetching new query");
                swipeRefreshLayout.setRefreshing(true);
                fetchPosts();
            }
        });

        fetchPosts();
    }

    protected void fetchPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);

        // Include a specific key so that we can narrow down our posts (i.e. by user)
        // query.include(Post.KEY_USER);
        query.setLimit(NUM_POSTS);
        query.addDescendingOrder(Post.KEY_CREATED_AT);

        // Launch actual post
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> queriedPosts, ParseException e) {
                // The list of all post objects that fit our query

                if (e == null) {
                    // Note that a Post is a db response item, and a FeedPost is a model
                    adapter.clear();
                    adapter.addAll(queriedPosts);
                    rvPosts.smoothScrollToPosition(0);

                    // Doesn't matter how we got here, just make sure the refresh symbol goes away!
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    Log.e(TAG, "Error querying post data: " + e);
                }
            }
        });
    }

    // FOR CUSTOM NAVIGATION BAR FOR REFRESH BUTTON IN NAV BAR //

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_feed, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuBtnRefreshFeed: {
                Log.i(TAG, "Refreshing feed via action bar");
                swipeRefreshLayout.setRefreshing(true);
                fetchPosts();
            }
        }
        return super.onOptionsItemSelected(item);
    }
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