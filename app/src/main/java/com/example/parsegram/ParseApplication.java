package com.example.parsegram;

import android.app.Application;

import com.example.parsegram.models.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Register the parse model
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)

                // These are my custom access keys for accessing my specific Parse dashboard
                .applicationId("4tkytOidkvUf6CUovwFR6q2hJnCcRBEV0rJtl5jj")
                .clientKey("kDGjrlNPQYYjywAlXN3IGDU9hRgAVqwsBJJrokQ9")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
