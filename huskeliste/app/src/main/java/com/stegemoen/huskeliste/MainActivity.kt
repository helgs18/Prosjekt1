package com.stegemoen.huskeliste

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.stegemoen.huskeliste.databinding.ActivityMainBinding
import com.stegemoen.huskeliste.todolists.data.TodoItem
import com.stegemoen.huskeliste.todolists.data.TodoList
import com.stegemoen.huskeliste.todolists.TodoListRecyclerAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var handleliste: MutableList<TodoItem> = mutableListOf(
        TodoItem("Egg", false),
        TodoItem("Melk", false),
        TodoItem("3 x Farris", false),
        TodoItem("Avacado", false))
    private var julegaver:MutableList<TodoItem> = mutableListOf(
        TodoItem("Nintendo Switch", false),
        TodoItem("Super Mario Odyssey", false),
        TodoItem("Acer Nitro", false),
        TodoItem("CB partypack", false),
        TodoItem("The Matrix DVD", true))
    private var mustSeeMovies: MutableList<TodoItem> = mutableListOf(
        TodoItem("Forrest Gump", true),
        TodoItem("Aliens", true),
        TodoItem("Terminator 2 Judgement Day", false),
        TodoItem("Pulp Fiction", true),
        TodoItem("Godfellas", true))
    private var fjelltopper: MutableList<TodoItem> = mutableListOf(
        TodoItem("Himmelbjerget", false),
        TodoItem("Mount Everest", true),
        TodoItem("Olympus Mons", true),
        TodoItem("Jotunheimen", false))
    private var bucketlist: MutableList<TodoItem> = mutableListOf()

    private var todoListCollection:MutableList<TodoList> = mutableListOf(
        TodoList("Handleliste", handleliste),
        TodoList("Must See filmer", mustSeeMovies),
        TodoList("Julegaver", julegaver),
        TodoList("Fjelltopper å bestige", fjelltopper),
        TodoList("Bucket List", bucketlist)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.todoListing.layoutManager = LinearLayoutManager(this)
        // Legger til parameter for onListClicked funksjonen
        binding.todoListing.adapter = TodoListRecyclerAdapter(todoListCollection, this::onListClicked)

        binding.saveBt.setOnClickListener{
            // Kan dette gjøres bedre? Sjekk senere forelesning ...
            todoListCollection.add(TodoList(binding.listName.text.toString(), mutableListOf<TodoItem>()))
            (binding.todoListing.adapter as TodoListRecyclerAdapter).updateLists(todoListCollection)
        }
        // sparer denne til et senere tidspunkt
        /*
        todoListAdapter.updateLists(listOf(
                TodoList("Best Nintendo Games", mutableListOf<TodoItem>()),
                TodoList("Best XBox games", mutableListOf<TodoItem>()),
                TodoList("Best Playstation games", mutableListOf<TodoItem>())
        ))*/
    }

    private fun onListClicked(todoList: TodoList):Unit{

    }
}