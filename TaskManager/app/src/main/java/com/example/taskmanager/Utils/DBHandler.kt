package com.example.taskmanager.Utils

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.taskmanager.Model.TaskModel

class DBHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "taskListDatabase"
        const val TABLE_NAME = "task"
        const val COLUMN_ID = "id"
        const val COLUMN_TASK = "task"
        const val COLUMN_START_DATE = "startDate"
        const val COLUMN_END_DATE = "endDate"
        const val COLUMN_STATUS = "status"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_TASK TEXT," +
                "$COLUMN_START_DATE TEXT," +
                "$COLUMN_END_DATE TEXT," +
                "$COLUMN_STATUS INTEGER)")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addTask(task: String, startDate: String, endDate: String, status: Int): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TASK, task)
            put(COLUMN_START_DATE, startDate)
            put(COLUMN_END_DATE, endDate)
            put(COLUMN_STATUS, status)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun getAllTasks(): List<TaskModel> {
        val taskList = mutableListOf<TaskModel>()
        val db = this.readableDatabase

        val columns = arrayOf(
            COLUMN_ID,
            COLUMN_TASK,
            COLUMN_START_DATE,
            COLUMN_END_DATE,
            COLUMN_STATUS
        )

        val cursor = db.query(
            TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null
        )

        if (cursor != null && cursor.moveToFirst()) {
            do {
                val id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID))
                val task = cursor.getString(cursor.getColumnIndex(COLUMN_TASK))
                val startDate = cursor.getString(cursor.getColumnIndex(COLUMN_START_DATE))
                val endDate = cursor.getString(cursor.getColumnIndex(COLUMN_END_DATE))
                val status = cursor.getInt(cursor.getColumnIndex(COLUMN_STATUS))
                taskList.add(TaskModel(id,status, task, startDate, endDate ))
            } while (cursor.moveToNext())
            cursor.close()
        }

        return taskList
    }

    fun updateStatus(id: Long, status: Int) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_STATUS, status)
        }

        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(id.toString())

        db.update(TABLE_NAME, values, whereClause, whereArgs)
    }



    // Add other database operations as needed
}
