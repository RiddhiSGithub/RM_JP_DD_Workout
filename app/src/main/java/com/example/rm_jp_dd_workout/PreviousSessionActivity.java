package com.example.rm_jp_dd_workout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class PreviousSessionActivity extends AppCompatActivity {

    private RecyclerView recyclerView; // RecyclerView to display previous workout sessions
    private SessionAdapter adapter; // Adapter for RecyclerView
    List<String> dataList; // List to hold data for RecyclerView

    SharedPreferences sharedPreferences; // SharedPreferences to store workout sessions
    SharedPreferences.Editor editor; // SharedPreferences editor

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_session); // Set the layout for the activity

        sharedPreferences = getSharedPreferences("Workout_Sessions", Context.MODE_PRIVATE); // Get SharedPreferences instance

        recyclerView = findViewById(R.id.recyclerView); // Initialize RecyclerView from layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Set LinearLayoutManager to RecyclerView

        dataList = new ArrayList<>(); // Initialize list to hold workout session data

        // Iterate over all saved workout sessions in SharedPreferences
        sharedPreferences.getAll().forEach((key, value) -> {
            dataList.add(value.toString()); // Add each session data to the dataList
        });

        adapter = new SessionAdapter(dataList); // Create a new adapter instance with dataList
        recyclerView.setAdapter(adapter); // Set adapter to RecyclerView to display workout sessions
    }
}
