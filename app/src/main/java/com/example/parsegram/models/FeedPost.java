package com.example.parsegram.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.example.parsegram.Post;
import com.example.parsegram.R;

import java.io.File;
import java.text.ParseException;

public class FeedPost {

    private static final String TAG = FeedPost.class.getSimpleName();
    String user;
    String description;
    String createdAt;
    File imageFile;
    File profilePicture;

    public FeedPost(Post post) {
        // Can get all attributes of post from the Post data type from the parsegram server
        this.user = post.getUser().getUsername();
        this.description = post.getDescription();
        this.createdAt = post.getCreatedAt().toString();
        try {
            this.imageFile = post.getImage().getFile();
        } catch ( com.parse.ParseException e) {
            Log.e(TAG, "Parse error creating post: " + e);
        }
    }

    public String getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public File getImageFile() {
        return imageFile;
    }

    public File getProfilePicture() {
        return profilePicture;
    }
}
