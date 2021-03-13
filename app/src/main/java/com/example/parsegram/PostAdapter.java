package com.example.parsegram;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.parsegram.models.FeedPost;
import com.example.parsegram.models.TimeFormatter;
import com.parse.ParseException;

import org.w3c.dom.Text;

import java.io.File;
import java.util.Date;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private static final String TAG = PostAdapter.class.getSimpleName();

    private List<FeedPost> posts;
    Context context;

    public PostAdapter(Context context, List<FeedPost> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View postView = inflater.inflate(R.layout.post_item, parent, false);
        return new ViewHolder(postView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeedPost post = posts.get(position);

        holder.tvPostUser.setText(post.getUser());
        holder.tvPostDescription.setText(post.getDescription());
        holder.tvPostCreatedAt.setText(getTimeDifference(post.getCreatedAt()));

//        Bitmap bmp = BitmapFactory.decodeByteArray(post.getImageFile(), 0, post.getImageFile().length);
//        holder.ivPostImage.setImageBitmap(bmp);
        Glide.with(context).load(post.getImageFile()).into(holder.ivPostImage);

    }

    private String getTimeDifference(Date createdAt) {
        return TimeFormatter.getTimeDifference(createdAt.toString());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Post attributes
        TextView tvPostUser;
        TextView tvPostDescription;
        TextView tvPostCreatedAt;
        ImageView ivPostImage;
        ImageView ivPostProfilePicture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPostUser = (TextView) itemView.findViewById(R.id.tvPostUsername);
            tvPostDescription = (TextView) itemView.findViewById(R.id.tvPostDescription);
            tvPostCreatedAt = (TextView) itemView.findViewById(R.id.tvPostCreatedAt);
            ivPostImage = (ImageView) itemView.findViewById(R.id.ivPostImage);
            // TODO: Create profile picture field and put it in here
        }
    }
}
