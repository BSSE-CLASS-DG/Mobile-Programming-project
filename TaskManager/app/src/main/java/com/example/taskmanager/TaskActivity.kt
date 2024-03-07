package com.example.taskmanager

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.taskmanager.Model.TaskModel
import com.example.taskmanager.databinding.ActivityTaskBinding


class TaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Use getSerializable extension function to avoid deprecated method
        val receivedTask = intent.getSerializableExtra("TASK_EXTRA") as? TaskModel
        if (receivedTask != null) {
            binding.taskTitle.text = receivedTask.getTask()
            binding.startdate.text = binding.startdate.text.toString() + receivedTask.getStartDate()
            binding.endDate.text = binding.endDate.text.toString() + receivedTask.getEndDate()

        }

        // Set up "Up" or "Back" navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Override onOptionsItemSelected to handle "Up" or "Back" button click
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Handle "Up" or "Back" button click
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
