package com.example.parsegram;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.parsegram.fragments.ComposeFragment;
import com.example.parsegram.fragments.FeedFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
                        Toast.makeText(HostActivity.this, "Navigating Home", Toast.LENGTH_SHORT).show();
                        fragment = new FeedFragment();
                        break;
                    case R.id.action_compose:
                        Toast.makeText(HostActivity.this, "Navigating Create", Toast.LENGTH_SHORT).show();
                        fragment = new ComposeFragment();
                        break;
                    case R.id.action_profile:
                        // TODO: Update fragment to go to profile
                        Toast.makeText(HostActivity.this, "Navigating Profile", Toast.LENGTH_SHORT).show();
                        fragment = new ComposeFragment();
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
        // TODO: Change this to Feed Fragment
        bottomNavigationView.setSelectedItemId(R.id.action_home);


    }
}