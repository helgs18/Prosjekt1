package com.stegemoen.huskeliste.todolists

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.stegemoen.huskeliste.EXTRA_TODOLIST_INFO
import com.stegemoen.huskeliste.databinding.ActivityTodoListDetailsBinding
import com.stegemoen.huskeliste.todolists.data.TodoList

class TodoListDetailsActivity: AppCompatActivity() {
    private lateinit var binding: ActivityTodoListDetailsBinding
    private lateinit var todoList:TodoList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoListDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedTodoList = intent.getParcelableExtra<TodoList>(EXTRA_TODOLIST_INFO)

        if(receivedTodoList != null){
            todoList = receivedTodoList
            Log.i("Details view", receivedTodoList.toString())
        } else {
            finish()
        }

        binding.listName.text = todoList.listName

    }
}