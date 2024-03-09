package com.example.taskmanager.Model

import java.io.Serializable

class TaskModel(private var id: Long, private var status: Int, private var task: String, private var startDate:String, private var endDate:String) :Serializable {
    // Additional class members or functions can be added here

    // Getter methods are automatically generated in Kotlin
    fun getId(): Long {
        return id
    }

    fun getStatus(): Int {
        return status
    }
    fun getStartDate(): String {
        return startDate
    }

    fun getEndDate(): String {
        return endDate
    }

    fun getTask(): String {
        return task
    }

    // Setter methods
    fun setId(newId: Long) {
        id = newId
    }

    fun setStatus(newStatus: Int) {
        status = newStatus
    }
    fun setStartDate(newstartDate: String) {
        startDate = newstartDate
    }

    fun setEndDate(newendDate: String) {
        endDate = newendDate
    }

    fun setTask(newTask: String) {
        task = newTask
    }
}
