package com.example.fittrackapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UserProfileActivity extends AppCompatActivity {

    private EditText etHeight, etWeight;
    private Button btnCalculateBMI;
    private TextView tvBMIResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        btnCalculateBMI = findViewById(R.id.btnCalculateBMI);
        tvBMIResult = findViewById(R.id.tvBMIResult);

        btnCalculateBMI.setOnClickListener(v -> calculateBMI());
    }

    private void calculateBMI() {
        String heightStr = etHeight.getText().toString().trim();
        String weightStr = etWeight.getText().toString().trim();

        // 1. Error Handling: Check for empty fields
        if (TextUtils.isEmpty(heightStr) || TextUtils.isEmpty(weightStr)) {
            Toast.makeText(this, "Please enter both height and weight", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float heightCm = Float.parseFloat(heightStr);
            float weightKg = Float.parseFloat(weightStr);

            // 2. Error Handling: Prevent invalid zero/negative inputs
            if (heightCm <= 0 || weightKg <= 0) {
                Toast.makeText(this, "Values must be greater than zero", Toast.LENGTH_SHORT).show();
                return;
            }

            // 3. BMI Math: Weight (kg) / (Height (m) * Height (m))
            float heightMeters = heightCm / 100;
            float bmi = weightKg / (heightMeters * heightMeters);

            displayBMIResult(bmi);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayBMIResult(float bmi) {
        String category;
        if (bmi < 18.5) {
            category = "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            category = "Normal Weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            category = "Overweight";
        } else {
            category = "Obese";
        }

        // Display formatted result
        String resultText = String.format("BMI: %.1f\nCategory: %s", bmi, category);
        tvBMIResult.setText(resultText);
    }
}