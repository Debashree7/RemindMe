package com.example.remindme;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ReminderConfigActivity extends AppCompatActivity {
    private int selectedYear, selectedMonth, selectedDay;
    private LinearLayout timeSelectionLayout;
    private Spinner scheduleTypeSpinner;
    private List<Button> timePickerButtons = new ArrayList<>();
    private int numberOfTimes = 1; // Default to once a day

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_config);

        EditText taskNameInput = findViewById(R.id.task_name_input);
        Button datePickerButton = findViewById(R.id.date_picker_button);
        scheduleTypeSpinner = findViewById(R.id.schedule_type);
        timeSelectionLayout = findViewById(R.id.time_selection_layout);
        Button saveButton = findViewById(R.id.save_task_button);

        String taskName = getIntent().getStringExtra("TASK_NAME");
        if (taskName != null) {
            taskNameInput.setText(taskName);
        }

        // Handle Date Picker
        datePickerButton.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    ReminderConfigActivity.this,
                    (view, year, month, dayOfMonth) -> {
                        selectedYear = year;
                        selectedMonth = month;
                        selectedDay = dayOfMonth;
                        datePickerButton.setText(String.format(Locale.getDefault(), "%02d/%02d/%d", dayOfMonth, month + 1, year));
                    },
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        });

        // Handle schedule selection
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.schedule_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        scheduleTypeSpinner.setAdapter(adapter);

        scheduleTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateTimePickers(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        saveButton.setOnClickListener(v -> {
            String taskNameText = taskNameInput.getText().toString();
            if (taskNameText.isEmpty()) {
                taskNameInput.setError("Task name cannot be empty");
                return;
            }

            // Save logic here
            MainActivity.sendReminderNotification(ReminderConfigActivity.this, taskNameText);
        });
    }

    private void updateTimePickers(int schedulePosition) {
        timeSelectionLayout.removeAllViews();
        timePickerButtons.clear();

        switch (schedulePosition) {
            case 0: // Once a day
                numberOfTimes = 1;
                break;
            case 1: // 2 times a day
                numberOfTimes = 2;
                break;
            case 2: // 4 times a day
                numberOfTimes = 4;
                break;
        }

        for (int i = 0; i < numberOfTimes; i++) {
            Button timeButton = new Button(this);
            timeButton.setText("Select Time " + (i + 1));
            timeButton.setOnClickListener(v -> showTimePicker(timeButton));

            timeSelectionLayout.addView(timeButton);
            timePickerButtons.add(timeButton);
        }
    }

    private void showTimePicker(Button button) {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                ReminderConfigActivity.this,
                (view, hourOfDay, minute) -> button.setText(String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute)),
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true
        );
        timePickerDialog.show();
    }
}
