package com.example.taskmanager.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.Model.TaskModel
import com.example.taskmanager.R
import com.example.taskmanager.TaskActivity


class TaskAdapter( private val activity: Activity,private var taskList: List<TaskModel>) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    // ViewHolder class holds references to the views in each list item
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val task: TextView = itemView.findViewById(R.id.checkDone)
        val but : Button = itemView.findViewById(R.id.checkDoneButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.task_loyout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTask = taskList[position]

        // Set data to views in the ViewHolder
        holder.task.text = " ${currentTask.getTask()}"

        if (currentTask.getStatus() == 1) {
            holder.but.text = "Completed"
            holder.but.setBackgroundColor(ContextCompat.getColor(activity, R.color.green))
        }


        holder.itemView.setOnClickListener{
            // Handle item click here
            // You can access the currentTask or position as needed

            // Create an Intent to start another activity
            val intent = Intent(activity, TaskActivity::class.java)

            // Pass the currentTask object as an extra to the Intent
            intent.putExtra("TASK_EXTRA", currentTask)

            // Start the activity
            activity.startActivity(intent)
        }



    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun toBoolean(n:Int): Boolean {
        return n!=0
    }

    fun setTask(taskList:List<TaskModel>) {
        this.taskList = taskList
        notifyDataSetChanged()
    }
}
