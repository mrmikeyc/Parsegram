package com.example.parsegram;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)

                // These are my custom access keys for accessing my specific Parse dashboard
                .applicationId("4tkytOidkvUf6CUovwFR6q2hJnCcRBEV0rJtl5jj")
                .clientKey("kDGjrlNPQYYjywAlXN3IGDU9hRgAVqwsBJJrokQ9")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
