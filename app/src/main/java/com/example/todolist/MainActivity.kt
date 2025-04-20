package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.todolist.ui.TodoListScreen

class MainActivity : ComponentActivity() { // 👈 change from AppCompatActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListScreen()
        }
    }
}
