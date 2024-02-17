package com.example.rm_jp_dd_workout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PreviousSessionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SessionAdapter adapter;
    List dataList;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_session);

        sharedPreferences = getSharedPreferences("Workout_Sessions", Context.MODE_PRIVATE);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataList = new ArrayList<>();

        sharedPreferences.getAll().forEach((key, value) -> {
            dataList.add(value);
        });


        adapter = new SessionAdapter(dataList);
        recyclerView.setAdapter(adapter);
    }
}