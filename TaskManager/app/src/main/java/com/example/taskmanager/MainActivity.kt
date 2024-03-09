package com.example.taskmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.Adapter.TaskAdapter
import com.example.taskmanager.Model.TaskModel
import com.example.taskmanager.databinding.ActivityMainBinding
    class MainActivity : AppCompatActivity() {
        private lateinit var binding: ActivityMainBinding
        private lateinit var taskAdapter: TaskAdapter
        private lateinit var taskList: List<TaskModel>
        private lateinit var tasksRecyclerView: RecyclerView

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            supportActionBar?.hide()

            // Initialize taskList with sample data or fetch from somewhere
            taskList = listOf(
                TaskModel(id = 1, status = 1, task = "Sample Task 1", startDate = "2024-03-05", endDate = "2024-10-20"),
                TaskModel(
                    id = 2, status = 0, task = "Sample Task 2",
                    startDate = "2024-02-05", endDate = "2024-11-23"),
                TaskModel(id = 3, status = 1, task = "Assignment submission", startDate = "2023-11-05", endDate = "2024-03-23"),
                TaskModel(id = 4, status = 1, task = "Assignment 2 submission", startDate = "2024-05-05", endDate = "2024-04-23"),
                TaskModel(id = 5, status = 1, task = "Assignment 3 submission", startDate = "2022-03-06", endDate = "2024-05-23"),
                TaskModel(id = 6, status = 0, task = "Assignment 4 submission", startDate = "2024-10-08", endDate = "2024-09-23"),
                TaskModel(id = 6, status = 0, task = "Assignment 4 submission",  startDate = "2024-03-05", endDate = "2024-04-30"),
                // Add more tasks as needed
            )

            tasksRecyclerView = binding.taskRecycleView
            tasksRecyclerView.layoutManager = LinearLayoutManager(this)

            // Initialize taskAdapter before using it
            taskAdapter = TaskAdapter(this, taskList)

            tasksRecyclerView.adapter = taskAdapter
        }
    }
