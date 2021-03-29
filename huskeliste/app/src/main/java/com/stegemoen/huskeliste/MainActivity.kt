package com.stegemoen.huskeliste

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.stegemoen.huskeliste.databinding.ActivityMainBinding
import com.stegemoen.huskeliste.todolists.TodoListDepositoryManager
import com.stegemoen.huskeliste.todolists.TodoListDetailsActivity
import com.stegemoen.huskeliste.todolists.data.TodoList
import com.stegemoen.huskeliste.todolists.TodoListRecyclerAdapter
import com.stegemoen.huskeliste.todolists.data.TodoItem

const val EXTRA_TODOLIST_INFO: String = "com.stegemoen.huskeliste.todolists.info"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.todoListing.layoutManager = LinearLayoutManager(this)
        // Legger til parameter for onListClicked funksjonen
        binding.todoListing.adapter = TodoListRecyclerAdapter(emptyList<TodoList>(), this::onListClicked)

        TodoListDepositoryManager.instance.onTodoList = {
            (binding.todoListing.adapter as TodoListRecyclerAdapter).updateLists(it)
        }

        TodoListDepositoryManager.instance.load()

        binding.saveBt.setOnClickListener {
            // Todo: Sjekk om jeg rekker å gjøre denne koden bedre
            val listName = binding.listName.text.toString()

            binding.listName.setText("")
            addTodoList(listName)

            val ipm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            ipm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

    private fun addTodoList(listName:String){
        val todoList = TodoList(listName, mutableListOf<TodoItem>())
        // val todoList = TodoList(listName, mutableListOf<TodoItem>())
        TodoListDepositoryManager.instance.addTodoList(todoList)

    }
    private fun onListClicked(todoList: TodoList):Unit{
        val intent = Intent(this, TodoListDetailsActivity::class.java).apply{
            putExtra(EXTRA_TODOLIST_INFO, todoList)
        }

        startActivity(intent)
    }
}