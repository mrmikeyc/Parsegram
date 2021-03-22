package com.example.parsegram;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.parsegram.models.Post;
import com.example.parsegram.models.TimeFormatter;
import com.parse.ParseException;
import com.parse.ParseFile;

import java.util.Date;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private static final String TAG = PostAdapter.class.getSimpleName();

    private List<Post> posts;
    Context context;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View postView = inflater.inflate(R.layout.post_item, parent, false);
        return new ViewHolder(postView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        try {
            holder.bind(post);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private String getTimeDifference(Date createdAt) {
        return TimeFormatter.getTimeDifference(createdAt.toString());
    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Post> postList) {
        posts.addAll(postList);
        notifyDataSetChanged();
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
            ivPostProfilePicture = itemView.findViewById(R.id.ivPostProfilePicture);
        }

        public void bind(Post post) throws ParseException {
            Log.d(TAG, "Post content: " + post.getObjectId());

            // Bind the date from the post object to the Viewholder
            tvPostDescription.setText(post.getDescription());
            tvPostUser.setText(post.getUser().fetchIfNeeded().getUsername());

            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(post.getImage().getUrl()).into(ivPostImage);
            }

            // Glide.with(context).load(post.getImageFile()).placeholder(R.drawable.ic_baseline_person_24).into(holder.ivPostProfilePicture);
            ivPostProfilePicture.setImageResource(R.drawable.ic_baseline_person_24);

            // Invoke the getTimeDifference class that we got from online >> TimeFormatter
            tvPostCreatedAt.setText(getTimeDifference(post.getCreatedAt()));
        }
    }
}
