package com.example.remindme;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;

public class TaskSelectionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_selection);

        RecyclerView recyclerView = findViewById(R.id.task_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        ArrayList<Task> tasks = new ArrayList<>(Arrays.asList(

                new Task("Read a Book", R.drawable.ic_book, Color.parseColor("#FF6F61")),  // Bright Red-Orange
                new Task("Take Vitamins", R.drawable.ic_vitamins, Color.parseColor("#6B8E23")),  // Olive Green
                new Task("Learn a Language", R.drawable.ic_language, Color.parseColor("#1E90FF")),  // Dodger Blue
                new Task("Exercise for 60 minutes", R.drawable.ic_exercise, Color.parseColor("#FF1493")),  // Deep Pink
                new Task("Drink 8 Cups of Water", R.drawable.ic_water, Color.parseColor("#00CED1"))  // Dark Turquoise
        ));

        TaskAdapter adapter = new TaskAdapter(tasks, this);
        recyclerView.setAdapter(adapter);
    }
}
