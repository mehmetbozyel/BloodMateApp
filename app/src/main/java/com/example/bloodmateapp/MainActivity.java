package com.example.bloodmateapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);

        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.replace(R.id.context, homeFragment, "");
        Log.d("Bloodmate", "Home Selected");
        ft1.commit();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Log.d("Bloddmate", "onNavigationItemSelected");
            switch (menuItem.getItemId()){
                case R.id.nav_home:
                    // home fragment transaction

                    HomeFragment homeFragment = new HomeFragment();
                    FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                    ft1.replace(R.id.context, homeFragment, "");
                    Log.d("Bloodmate", "Home Selected");
                    ft1.commit();
                    return true;
                case R.id.nav_announcement:

                    AnnouncementFragment announcementFragment = new AnnouncementFragment();
                    FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                    ft2.replace(R.id.context, announcementFragment, "");
                    Log.d("Bloodmate", "Announcement Selected");
                    ft2.commit();

                    return true;
                case R.id.nav_profile:
                    Log.d("Bloodmate", "Profile Selected");
                    ProfileFragment profileFragment = new ProfileFragment();
                    FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                    ft3.replace(R.id.context, profileFragment, "");
                    ft3.commit();

                    return true;
            }
            return false;
        }
    };


}
