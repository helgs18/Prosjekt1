package com.stegemoen.huskeliste.todolists

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.stegemoen.huskeliste.EXTRA_TODOLIST_INFO
import com.stegemoen.huskeliste.R
import com.stegemoen.huskeliste.databinding.ActivityTodoListDetailsBinding
import com.stegemoen.huskeliste.todolists.data.TodoItem
import com.stegemoen.huskeliste.todolists.data.TodoList

class TodoListDetailsActivity: AppCompatActivity(){
    private lateinit var binding: ActivityTodoListDetailsBinding
    private lateinit var todoList:TodoList



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityTodoListDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedTodoList = intent.getParcelableExtra<TodoList>(EXTRA_TODOLIST_INFO)
        // for list
        val arrayAdapter: ArrayAdapter<*>
        val users = arrayOf(
                "Virat Kohli", "Rohit Sharma", "Steve Smith",
                "Kane Williamson", "Ross Taylor"
        )
        var listitems: MutableList<TodoItem>
        var itemNamesCollection: MutableList<String> = mutableListOf<String>()
        var itemCheckboxCollection: MutableList<String> = mutableListOf<String>()

        var index: Int = 0
        listitems = receivedTodoList?.listItems as MutableList<TodoItem>
        listitems.forEach {
            //itemNamesCollection.add(index, it.itemName)
            itemCheckboxCollection.add(index, it.checked.toString())
            itemNamesCollection.add(it.itemName)
            index++
        }

        if(receivedTodoList != null){
            todoList = receivedTodoList
            Log.i("Details view", receivedTodoList.toString())
        } else {
            finish()
        }
        binding.listName.text = todoList.listName + ":"
        Log.i("onCreate: listName =", todoList.listName.toString())

        // Code for listView from xml file
        var mListView = findViewById<ListView>(R.id.itemList)
        arrayAdapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, itemNamesCollection)
        mListView.adapter = arrayAdapter

        //binding.itemrecycler.adapter = TodoItemRecyclerAdapter(emptyList(), this::onItemClicked)
        //TodoItemRecyclerAdapter(todoItems: 5, onTodoItemClicked:5)
        /*
        var itemNames = mutableListOf<String>()
        var itemElements = mutableListOf<TodoItem>()
        todoList.listItems.forEach{
            Log.i("Item in list", it.ItemName.toString())
            itemNames.add(it.ItemName.toString())
            itemElements.add(it)
        }
        Log.i("tiems in list", "nadasvada")
        binding.todoItems.adapter = TodoItemRecyclerAdapter(emptyList<TodoItem>())*/

        // ToDo: Add itemsNames or itemElements to ListView or recycle View (class = TodoItem)
    }

    private fun onItemClicked(todoItem: TodoItem):Unit{
        /*val intent = Intent(this, TodoItemDetailsActivity::class.java).apply{
            putExtra(EXTRA_TODOLIST_INFO, todoList)
        }
        startActivity(intent)*/
        // ToDo: Do something with clickety klakk
    }
}