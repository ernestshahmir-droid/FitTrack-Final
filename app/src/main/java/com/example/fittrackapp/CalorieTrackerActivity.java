package com.example.fittrackapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class CalorieTrackerActivity extends AppCompatActivity {

    private EditText etFoodName, etCalories;
    private Button btnAddFood;
    private TextView tvTotalCalories;
    private ListView lvFoodHistory;

    private int totalCalories = 0;
    private ArrayList<String> foodList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_tracker);

        etFoodName = findViewById(R.id.etFoodName);
        etCalories = findViewById(R.id.etCalories);
        btnAddFood = findViewById(R.id.btnAddFood);
        tvTotalCalories = findViewById(R.id.tvTotalCalories);
        lvFoodHistory = findViewById(R.id.lvFoodHistory);

        foodList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, foodList);
        lvFoodHistory.setAdapter(adapter);

        btnAddFood.setOnClickListener(v -> addFoodItem());
    }

    private void addFoodItem() {
        String foodName = etFoodName.getText().toString().trim();
        String caloriesStr = etCalories.getText().toString().trim();

        if (TextUtils.isEmpty(foodName) || TextUtils.isEmpty(caloriesStr)) {
            Toast.makeText(this, "Please enter both food name and calories", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int calories = Integer.parseInt(caloriesStr);

            if (calories <= 0) {
                Toast.makeText(this, "Calories must be greater than zero", Toast.LENGTH_SHORT).show();
                return;
            }

            // Add math to total
            totalCalories += calories;
            tvTotalCalories.setText("Total Calories: " + totalCalories);

            // Add item to list visually
            String logEntry = foodName + " - " + calories + " kcal";
            foodList.add(0, logEntry); // Adds to the top of the list
            adapter.notifyDataSetChanged();

            // Clear inputs for the next entry
            etFoodName.setText("");
            etCalories.setText("");

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid calorie amount", Toast.LENGTH_SHORT).show();
        }
    }
}