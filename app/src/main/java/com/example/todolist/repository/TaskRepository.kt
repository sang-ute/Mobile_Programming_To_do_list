package com.example.todolist.repository

import com.example.todolist.Task // this now works!

class TaskRepository {
    private val tasks = mutableListOf<Task>()

    fun getTasks(): List<Task> {
        return tasks
    }

    fun addTask(task: Task) {
        tasks.add(task)
    }
}
