package com.stegemoen.huskeliste

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.stegemoen.huskeliste.databinding.ActivityMainBinding
import com.stegemoen.huskeliste.todolists.TodoItem
import com.stegemoen.huskeliste.todolists.TodoList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var todoList:MutableList<TodoList> = mutableListOf(
        TodoList("Must See Movies", mutableListOf<TodoItem>()),
        TodoList("Must Play Games", mutableListOf<TodoItem>()),
        TodoList("Christmas Presents", mutableListOf<TodoItem>()),
        TodoList("Shopping List", mutableListOf<TodoItem>())
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}