package com.example.rm_jp_dd_workout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.MyViewHolder> {

    private List<String> dataList; // List to hold data for each session

    // Constructor to initialize the adapter with the list of session data
    public SessionAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    // Create ViewHolder instances
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the item layout and create a new ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    // Bind data to the ViewHolder
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String data = dataList.get(position); // Get data for the current position
        holder.textView.setText(data); // Set data to the TextView in the ViewHolder
    }

    // Return the total number of items in the data list
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // ViewHolder class to hold the views for each item
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView; // TextView to display session data

        // Constructor to initialize the ViewHolder with the item view
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView_Sessions); // Initialize TextView from item layout
        }
    }
}
