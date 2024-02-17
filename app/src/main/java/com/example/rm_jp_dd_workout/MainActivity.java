package com.example.rm_jp_dd_workout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.rm_jp_dd_workout.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    ActivityMainBinding binding;

    int steps = 0;

    int height = 0;
    int weight = 0;
    float calories = 0;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.seekBarStepsPerMinute.setMax(200);
        binding.seekBarStepsPerMinute.setProgress(0);
        setListeners();


    }

    private void setListeners() {
        binding.seekBarStepsPerMinute.setOnSeekBarChangeListener(this);
        binding.buttonAddSession.setOnClickListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        steps = progress;
        binding.txtViewSeekBarProgress.setText(String.valueOf(steps));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.buttonAddSession.getId())
        {
            height = Integer.parseInt(binding.editTextHeight.getText().toString());
            weight = Integer.parseInt(binding.editTextWeight.getText().toString());

            Workout workout = new Workout(steps,weight,height);
            calories = workout.calculateCalories();
            sharedPreferences = getSharedPreferences("Workout_Sessions", Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            int size = sharedPreferences.getAll().size();
            String key = "Session-" + (size+1);
            String value = "Steps:" + steps + " Weight:" + weight + " Height:" + height + " Calories" + calories;
            editor.putString(key, value);
            editor.apply();
            clearAll();
            Intent intent = new Intent(MainActivity.this, PreviousSessionActivity.class);
            startActivity(intent);
        }
    }

    private void clearAll() {
        binding.editTextWeight.setText("");
        binding.editTextHeight.setText("");
        binding.seekBarStepsPerMinute.setProgress(0);
        binding.txtViewSeekBarProgress.setText("0");
    }
}