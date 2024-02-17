package com.example.rm_jp_dd_workout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.SeekBar;

import com.example.rm_jp_dd_workout.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    ActivityMainBinding binding;

    int steps = 0;

    int height = 0;
    int weight = 0;
    float calories = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.seekBarStepsPerMinute.setMax(200);
        binding.seekBarStepsPerMinute.setProgress(1);
        binding.seekBarStepsPerMinute.setOnSeekBarChangeListener(this);

        height = Integer.parseInt(binding.editTextHeight.getText().toString());
        weight = Integer.parseInt(binding.editTextWeight.getText().toString());

        Workout workout = new Workout(steps,weight,height);
        calories = workout.calculateCalories();





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
}