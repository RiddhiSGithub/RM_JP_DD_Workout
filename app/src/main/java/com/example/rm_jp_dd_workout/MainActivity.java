package com.example.rm_jp_dd_workout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.rm_jp_dd_workout.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    ActivityMainBinding binding;

    // Variables to store user input data
    int steps = 0;
    int height = 0;
    int weight = 0;
    float calories = 0;

    // SharedPreferences variables for storing workout sessions
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate layout using view binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set max progress for seek bar and initialize listeners
        binding.seekBarStepsPerMinute.setMax(200);
        binding.seekBarStepsPerMinute.setProgress(0);
        setListeners(); // Method to set listeners for views
    }

    // Method to set listeners for views
    private void setListeners() {
        binding.seekBarStepsPerMinute.setOnSeekBarChangeListener(this);
        binding.buttonAddSession.setOnClickListener(this);
    }

    // SeekBarChangeListener method to update steps value
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        steps = progress;
        binding.txtViewSeekBarProgress.setText(String.valueOf(steps));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // Not used
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // Not used
    }

    // OnClickListener method for button clicks
    @Override
    public void onClick(View v) {
        if(v.getId() == binding.buttonAddSession.getId()) {
            // Retrieve user input for height and weight
            height = Integer.parseInt(binding.editTextHeight.getText().toString());
            weight = Integer.parseInt(binding.editTextWeight.getText().toString());

            // Create a Workout object and calculate calories
            Workout workout = new Workout(steps, weight, height);
            calories = workout.calculateCalories();

            // Save workout session data to SharedPreferences
            sharedPreferences = getSharedPreferences("Workout_Sessions", Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            int size = sharedPreferences.getAll().size();
            String key = "Session-" + (size + 1);
            String value = "Steps:" + steps + " Weight:" + weight + " Height:" + height + " Calories" + calories;
            editor.putString(key, value);
            editor.apply();

            // Clear input fields and seekbar progress
            clearAll();

            // Start PreviousSessionActivity to display saved sessions
            Intent intent = new Intent(MainActivity.this, PreviousSessionActivity.class);
            startActivity(intent);
        }
    }

    // Method to clear input fields and seekbar progress
    private void clearAll() {
        binding.editTextWeight.setText("");
        binding.editTextHeight.setText("");
        binding.seekBarStepsPerMinute.setProgress(0);
        binding.txtViewSeekBarProgress.setText("0");
    }
}
