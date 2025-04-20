package com.example.todolist.ui

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.Task
import com.example.todolist.TaskViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TodoListScreen(taskViewModel: TaskViewModel = viewModel()) {
    var taskName by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var dueDateMillis by remember { mutableStateOf(0L) }
    var showDatePicker by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) } // For dropdown menu state
    val tasks by taskViewModel.tasks.observeAsState(emptyList())

    Column(modifier = Modifier.padding(16.dp)) {

        // Task input field
        OutlinedTextField(
            value = taskName,
            onValueChange = { taskName = it },
            label = { Text("Enter Task Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Show date picker
        Button(onClick = { showDatePicker = true }) {
            Text("Choose Due Date")
        }

        if (selectedDate.isNotEmpty()) {
            Text("Selected Date: $selectedDate")
        }

        if (showDatePicker) {
            val context = LocalContext.current
            val calendar = Calendar.getInstance()

            DatePickerDialog(
                context,
                { _, year, month, day ->
                    // Set the date to midnight (00:00:00) to ignore the time part
                    selectedDate = "$day/${month + 1}/$year"
                    calendar.set(year, month, day, 0, 0, 0)  // Set the time to 00:00:00
                    calendar.set(Calendar.MILLISECOND, 0)  // Reset milliseconds to 0
                    dueDateMillis = calendar.timeInMillis // Store only the date as milliseconds
                    showDatePicker = false
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Add task button
        Button(
            onClick = {
                if (taskName.isNotEmpty() && dueDateMillis > 0) {
                    // Use dueDateMillis for the task (only date, no time info)
                    val task = Task(taskName, dueDateMillis)
                    taskViewModel.addTask(task)
                    taskName = "" // Clear task input
                    selectedDate = "" // Clear selected date
                    dueDateMillis = 0L // Reset due date timestamp
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Task")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Options menu for sorting
        OptionsMenu(
            onSortByName = {
                taskViewModel.sortTasksByName()
            },
            onSortByDate = {
                taskViewModel.sortTasksByDate()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display tasks
        Column {
            tasks.forEach { task ->
                Text("${task.taskName} - ${formatDate(task.dueDate)}")
            }
        }
    }
}

@Composable
fun OptionsMenu(onSortByName: () -> Unit, onSortByDate: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Default.MoreVert, contentDescription = "Options Menu")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Sort by Name") },
                onClick = {
                    onSortByName()
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Sort by Date") },
                onClick = {
                    onSortByDate()
                    expanded = false
                }
            )
        }
    }
}

fun formatDate(timestamp: Long): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())  // Only show date, no time
    return dateFormat.format(Date(timestamp))
}
