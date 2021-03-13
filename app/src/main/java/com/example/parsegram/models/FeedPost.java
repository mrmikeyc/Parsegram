package com.example.parsegram.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.example.parsegram.Post;
import com.example.parsegram.R;
import com.parse.ParseException;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedPost {

    private static final String TAG = FeedPost.class.getSimpleName();
    String user;
    String description;
    Date createdAt;
    File imageFile;
    File profilePicture;

    public FeedPost(Post post) throws ParseException {
        // Can get all attributes of post from the Post data type from the parsegram server
        this.user = post.getUser().fetchIfNeeded().getUsername();
        this.description = post.getDescription();
        this.createdAt = post.getCreatedAt();
        try {
            this.imageFile = post.getImage().getFile();
        } catch ( com.parse.ParseException e) {
            Log.e(TAG, "Parse error creating post: " + e);
        }
    }

    public static List<FeedPost> createFeedPostList(List<Post> queriedPosts) throws ParseException {
        List<FeedPost> feedPosts = new ArrayList<>();
        for (int i = 0; i < queriedPosts.size(); i++ ) {
            feedPosts.add(new FeedPost(queriedPosts.get(i)));
        }
        return feedPosts;
    }

    public String getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public File getImageFile() {
        return imageFile;
    }

    public File getProfilePicture() {
        return profilePicture;
    }
}
