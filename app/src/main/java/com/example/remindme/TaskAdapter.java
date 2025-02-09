package com.example.remindme;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> tasks;
    private Context context;

    public TaskAdapter(List<Task> tasks, Context context) {
        this.tasks = tasks;
        this.context = context;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.taskName.setText(task.getName());
        holder.taskIcon.setImageResource(task.getIcon());
        holder.itemView.setBackgroundColor(task.getColor());

        // Fix: Ensure click listener works
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ReminderConfigActivity.class);
            intent.putExtra("TASK_NAME", task.getName()); // Pass task name
            v.getContext().startActivity(intent); // Fix: Use v.getContext() instead of 'context'
        });
    }



    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskName;
        ImageView taskIcon;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.task_name);
            taskIcon = itemView.findViewById(R.id.task_icon);
        }
    }
}