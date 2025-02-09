package com.example.remindme;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateTaskActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        EditText taskNameInput = findViewById(R.id.task_name_input);
        Button saveButton = findViewById(R.id.save_task_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = taskNameInput.getText().toString();
                // Save the task logic (e.g., store in database or shared preferences)
            }
        });
    }
}
