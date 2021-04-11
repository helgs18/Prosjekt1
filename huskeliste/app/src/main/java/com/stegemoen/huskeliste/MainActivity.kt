package com.stegemoen.huskeliste

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.storage.FirebaseStorage
import com.stegemoen.huskeliste.databinding.ActivityMainBinding
import com.stegemoen.huskeliste.firebase.SaveJson
import com.stegemoen.huskeliste.todolists.TodoListDepositoryManager
import com.stegemoen.huskeliste.todolists.TodoListDetailsActivity
import com.stegemoen.huskeliste.todolists.data.TodoList
import com.stegemoen.huskeliste.todolists.TodoListRecyclerAdapter
import com.stegemoen.huskeliste.todolists.data.TodoItem
import org.json.JSONObject
import java.io.File

const val EXTRA_TODOLIST_INFO: String = "com.stegemoen.huskeliste.todolists.info"

class MainActivity : AppCompatActivity() {
    private val TAG:String = "Huskeliste.MainActivity"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        SaveJson.Companion.init(this)

        binding.todoListing.layoutManager = LinearLayoutManager(this)
        // Legger til parameter for onListClicked funksjonen
        binding.todoListing.adapter = TodoListRecyclerAdapter(emptyList<TodoList>(), this::onListClicked)

        TodoListDepositoryManager.instance.onTodoList = {
            (binding.todoListing.adapter as TodoListRecyclerAdapter).updateLists(it)
        }

        TodoListDepositoryManager.instance.load()

        binding.saveListBtn.setOnClickListener {
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

    fun onSave(file: File) {
        this.upload(file.toUri())
        print(file.toString())
    }

    private fun upload(file: Uri) {
        // Need to create a reference before uploading to storage
        val ref = FirebaseStorage.getInstance().reference.child(
            "melodies/${file.lastPathSegment}")
        var uploadTask = ref.putFile(file)

        uploadTask.addOnSuccessListener {
            Log.d(TAG, "Saved file to fb ${it.toString()}")
        }.addOnFailureListener {
            Log.e(TAG, "Error saving file to fb", it)
        }
    }

    companion object {
        val instance = MainActivity()
    }
}