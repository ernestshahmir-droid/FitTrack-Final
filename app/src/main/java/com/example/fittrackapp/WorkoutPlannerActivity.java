package com.example.fittrackapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class WorkoutPlannerActivity extends AppCompatActivity {

    private Button btnCardio, btnStrength, btnStretching;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_planner);

        btnCardio = findViewById(R.id.btnCardio);
        btnStrength = findViewById(R.id.btnStrength);
        btnStretching = findViewById(R.id.btnStretching);

        btnCardio.setOnClickListener(v -> openTimer("Cardio"));
        btnStrength.setOnClickListener(v -> openTimer("Strength"));
        btnStretching.setOnClickListener(v -> openTimer("Stretching"));
    }

    private void openTimer(String category) {
        Intent intent = new Intent(WorkoutPlannerActivity.this, ExerciseTimerActivity.class);
        intent.putExtra("WORKOUT_CATEGORY", category);
        startActivity(intent);
    }
}