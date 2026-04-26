package com.example.fittrackapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button logoutButton, btnGoToWorkouts, btnGoToProfile, btnGoToCalories, btnGoToSettings, btnGoToWater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // CHECK DARK MODE MEMORY BEFORE LOADING SCREEN
        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        boolean isDarkMode = sharedPreferences.getBoolean("isDarkMode", false);
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        setContentView(R.layout.activity_main);

        // Link buttons to XML
        logoutButton = findViewById(R.id.btnLogout);
        btnGoToWorkouts = findViewById(R.id.btnGoToWorkouts);
        btnGoToProfile = findViewById(R.id.btnGoToProfile);
        btnGoToCalories = findViewById(R.id.btnGoToCalories);
        btnGoToSettings = findViewById(R.id.btnGoToSettings);
        btnGoToWater = findViewById(R.id.btnGoToWater);

        // Sprint 7: Go to Water Tracker
        btnGoToWater.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, WaterTrackerActivity.class));
        });

        // Sprint 6: Go to Settings
        btnGoToSettings.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        });

        // Sprint 4: Go to Calories
        btnGoToCalories.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CalorieTrackerActivity.class));
        });

        // Sprint 3: Go to User Profile
        btnGoToProfile.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, UserProfileActivity.class));
        });

        // Sprint 2: Go to Workout Planner
        btnGoToWorkouts.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, WorkoutPlannerActivity.class));
        });

        // Sprint 1: Logout
        logoutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish(); // Closes MainActivity so user can't hit the back button
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Security Check: If the user is not logged in, force them to the Login Screen
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
    }
}