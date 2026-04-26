package com.example.fittrackapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ExerciseTimerActivity extends AppCompatActivity {

    private TextView tvCategory, tvTimerDisplay;
    private Button btnStartResume, btnPause, btnReset;

    private Handler handler;
    private long startTime = 0L, timeInMilliseconds = 0L, timeSwapBuff = 0L, updateTime = 0L;
    private boolean isRunning = false;

    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updateTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updateTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;

            tvTimerDisplay.setText(String.format("%02d:%02d", mins, secs));
            handler.postDelayed(this, 0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_timer);

        tvCategory = findViewById(R.id.tvWorkoutCategory);
        tvTimerDisplay = findViewById(R.id.tvTimerDisplay);
        btnStartResume = findViewById(R.id.btnStartResume);
        btnPause = findViewById(R.id.btnPause);
        btnReset = findViewById(R.id.btnReset);

        handler = new Handler();

        // Get the category from the previous screen
        String category = getIntent().getStringExtra("WORKOUT_CATEGORY");
        if (category != null) {
            tvCategory.setText(category + " Workout");
        }

        btnStartResume.setOnClickListener(v -> {
            if (!isRunning) {
                startTime = SystemClock.uptimeMillis();
                handler.postDelayed(updateTimerThread, 0);
                isRunning = true;
                btnStartResume.setText("Resume");
            }
        });

        btnPause.setOnClickListener(v -> {
            if (isRunning) {
                timeSwapBuff += timeInMilliseconds;
                handler.removeCallbacks(updateTimerThread);
                isRunning = false;
            }
        });

        btnReset.setOnClickListener(v -> {
            startTime = 0L;
            timeInMilliseconds = 0L;
            timeSwapBuff = 0L;
            updateTime = 0L;
            isRunning = false;
            tvTimerDisplay.setText("00:00");
            handler.removeCallbacks(updateTimerThread);
            btnStartResume.setText("Start");
        });
    }
}