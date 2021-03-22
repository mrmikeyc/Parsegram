package com.example.parsegram;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.parsegram.fragments.ComposeFragment;
import com.example.parsegram.fragments.FeedFragment;
import com.example.parsegram.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

// TODO: Rename HostActivity to be more generic because now it has rotating fragments
public class HostActivity extends AppCompatActivity {

    private static final String TAG = HostActivity.class.getSimpleName();

    final FragmentManager fragmentManager = getSupportFragmentManager();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        // Navigation bar initialization
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // MenuItem is one of the item id's defined in the nav bar xml file
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        fragment = new FeedFragment();
                        break;
                    case R.id.action_compose:
                        fragment = new ComposeFragment();
                        break;
                    case R.id.action_profile:
                        fragment = new ProfileFragment();
                        break;
                    default:
                        // TODO: Find way to eliminate default case - or make it a reasonable default
                        fragment = new ComposeFragment();
                }

                // Whatever fragment we have reassigned to
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Default navigation selection (good for when the app starts up)
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }

    public void manuallyNavigateHome() {
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }

    // This method is called from ProfileFragment menuBtnSignOut
    public void backToLogin() {
        ParseUser.logOutInBackground(new LogOutCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Intent intent = new Intent(HostActivity.this, MainActivity.class);
                    Toast.makeText(HostActivity.this, "You have been signed out!", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                    finish();
                } else {
                    Log.e(TAG, "Error logging out: " + e);
                }
            }
        });
    }
}