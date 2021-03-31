package com.stegemoen.huskeliste.todolists

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.stegemoen.huskeliste.EXTRA_TODOLIST_INFO
import com.stegemoen.huskeliste.R
import com.stegemoen.huskeliste.databinding.ActivityTodoListDetailsBinding
import com.stegemoen.huskeliste.todolists.data.TodoList

class TodoListDetailsActivity: AppCompatActivity(){
    private lateinit var binding: ActivityTodoListDetailsBinding
    private lateinit var todoList:TodoList



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityTodoListDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedTodoList = intent.getParcelableExtra<TodoList>(EXTRA_TODOLIST_INFO)

        // For recyclerView.adapter
        var listitems: MutableList<String>
        listitems = receivedTodoList?.listItems as MutableList<String>

        todoList = receivedTodoList // Fjernet if-statement pga. always true

        binding.listName.text = todoList.listName + ":"

        // Adapter kode er tatt fra FlowerAdapter og RecyclerView koden under er basert på koden
        // fra RecyclerViewSimple/ (https://github.com/android/views-widgets-samples/tree/main/RecyclerViewSimple)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = TodoItemRecyclerAdapter(listitems)
    }

    /*
    private fun onItemClicked(todoItem: String):Unit{
        /*val intent = Intent(this, TodoItemDetailsActivity::class.java).apply{
            putExtra(EXTRA_TODOLIST_INFO, todoList)
        }
        startActivity(intent)*/
        // ToDo: Gjør noe med onItemClicked(), som enten endrer på visningen eller checked variablen (se TodoItemRecyclerAdapter)
    }*/
    // ToDo: Legge til knapp for nye huskelister, fiske layout og bruke Firebase backend.
}