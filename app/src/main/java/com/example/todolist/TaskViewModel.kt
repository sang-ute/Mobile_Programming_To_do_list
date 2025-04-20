package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.repository.TaskRepository

data class Task(
    val taskName: String,
    val dueDate: Long
)

class TaskViewModel : ViewModel() {

    private val taskRepository = TaskRepository()

    // MutableLiveData to observe and update the list of tasks
    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> get() = _tasks // Expose only LiveData for external observers

    // Track sorting state for name and date
    private var isSortedByNameAscending = true
    private var isSortedByDateAscending = true

    init {
        // Load tasks when the ViewModel is created
        loadTasks()
    }

    // Load tasks from the repository
    private fun loadTasks() {
        _tasks.postValue(taskRepository.getTasks())
    }

    // Function to add a task
    fun addTask(task: Task) {
        taskRepository.addTask(task)
        loadTasks() // Refresh the list after adding a task
    }

    // Function to toggle sorting by name
    fun sortTasksByName() {
        isSortedByNameAscending = !isSortedByNameAscending
        val sortedTasks = if (isSortedByNameAscending) {
            taskRepository.getTasks().sortedBy { it.taskName } // Ascending order
        } else {
            taskRepository.getTasks().sortedByDescending { it.taskName } // Descending order
        }
        _tasks.postValue(sortedTasks)
    }

    // Function to toggle sorting by date
    fun sortTasksByDate() {
        isSortedByDateAscending = !isSortedByDateAscending
        val sortedTasks = if (isSortedByDateAscending) {
            taskRepository.getTasks().sortedBy { it.dueDate } // Ascending order
        } else {
            taskRepository.getTasks().sortedByDescending { it.dueDate } // Descending order
        }
        _tasks.postValue(sortedTasks)
    }
}
