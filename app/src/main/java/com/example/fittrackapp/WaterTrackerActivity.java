package com.example.fittrackapp;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class WaterTrackerActivity extends AppCompatActivity {

    private ProgressBar progressBarWater;
    private TextView tvWaterCount;
    private Button btnAddWater, btnSetReminder;

    private int currentWater = 0;
    private final int DAILY_GOAL = 2000; // 2000ml goal
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_tracker);

        progressBarWater = findViewById(R.id.progressBarWater);
        tvWaterCount = findViewById(R.id.tvWaterCount);
        btnAddWater = findViewById(R.id.btnAddWater);
        btnSetReminder = findViewById(R.id.btnSetReminder);

        // Load saved water data
        sharedPreferences = getSharedPreferences("WaterPrefs", MODE_PRIVATE);
        currentWater = sharedPreferences.getInt("waterIntake", 0);
        updateUI();

        createNotificationChannel();

        btnAddWater.setOnClickListener(v -> {
            currentWater += 250;
            if (currentWater > DAILY_GOAL) currentWater = DAILY_GOAL;

            // Save data locally
            sharedPreferences.edit().putInt("waterIntake", currentWater).apply();
            updateUI();

            if(currentWater == DAILY_GOAL) {
                Toast.makeText(this, "Goal Reached! Great job!", Toast.LENGTH_SHORT).show();
            }
        });

        btnSetReminder.setOnClickListener(v -> setWaterReminder());
    }

    private void updateUI() {
        progressBarWater.setProgress(currentWater);
        tvWaterCount.setText(currentWater + " / " + DAILY_GOAL + " ml");
    }

    private void createNotificationChannel() {
        // Modern Android requires Notification Channels
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "WaterReminders";
            String description = "Channel for water tracking reminders";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("FitTrackWater", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void setWaterReminder() {
        // Schedule a push notification using Android's AlarmManager
        Intent intent = new Intent(this, WaterReminderReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        long timeAtButtonClick = System.currentTimeMillis();
        long twoHoursInMillis = 1000 * 60 * 60 * 2; // Remind every 2 hours

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeAtButtonClick + twoHoursInMillis,
                twoHoursInMillis, pendingIntent);

        Toast.makeText(this, "Reminders set for every 2 hours!", Toast.LENGTH_SHORT).show();
    }
}