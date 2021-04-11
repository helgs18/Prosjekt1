package com.stegemoen.huskeliste.todolists

import android.net.Uri
import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import com.stegemoen.huskeliste.todolists.data.TodoItem
import com.stegemoen.huskeliste.todolists.data.TodoList
import com.stegemoen.huskeliste.todolists.TodoItemRecyclerAdapter
import com.stegemoen.huskeliste.firebase.SaveJson


class TodoListDepositoryManager {
    private val TAG:String = "Huskeliste.TodoListDepositoryManager"
    private lateinit var todoListCollection:MutableList<TodoList>
    var onTodoList:((List<TodoList>)->Unit)? = null
    var onTodoListUpdate:((todoList:TodoList)->Unit)? = null
    var onTodoItem:((List<TodoItem>)->Unit)? = null

    private var handleliste: MutableList<TodoItem> = mutableListOf(
            TodoItem("Egg", false),
            TodoItem("Melk", false),
            TodoItem("3 x Farris", false),
            TodoItem("Avacado", false)
    )

    private var julegaver: MutableList<TodoItem> = mutableListOf(
            TodoItem("Nintendo Switch", false),
            TodoItem("Super Mario Odyssey", false),
            TodoItem("Acer Nitro", false),
            TodoItem("The Matrix DVD", false)
    )

    private var mustSeeMovies: MutableList<TodoItem> = mutableListOf(
            TodoItem("Forrest Gump", false),
            TodoItem("Aliens", false),
            TodoItem("Terminator 2: Judgement Day", true),
            TodoItem("Pulp Fiction", false),
            TodoItem("Godfellas", false)
    )

    private var fjelltopper: MutableList<TodoItem> = mutableListOf(
            TodoItem("Himmelbjerget", false),
            TodoItem("Mount Everest", false),
            TodoItem("Olympus Mons", false),
            TodoItem("Jotunheimen", false)
    )

    private var bucketlist: MutableList<TodoItem> = mutableListOf()

    fun load(){

        todoListCollection = mutableListOf(
            TodoList("Handleliste", handleliste),
            TodoList("Must See filmer", mustSeeMovies),
            TodoList("Julegaver", julegaver),
            TodoList("Fjelltopper å bestige", fjelltopper),
            TodoList("Bucket List", bucketlist)
        )
        onTodoList?.invoke(todoListCollection)
        SaveJson.instance.saveToFile(todoListCollection)
    }

    fun updateTodoList(todoList:TodoList){
        // finn huskeliste i listen og erstatt med den ny listen
        onTodoListUpdate?.invoke(todoList)
        SaveJson.instance.saveToFile(todoListCollection)
    }

    fun deleteTodoList(todoList:TodoList){
        todoListCollection.remove(todoList)
        onTodoList?.invoke(todoListCollection)
        SaveJson.instance.saveToFile(todoListCollection)
    }

    fun deleteTodoItem(todoList: MutableList<TodoItem>, todoItem:TodoItem) {
        if(todoList.contains(todoItem)){
            var index = 0
            todoListCollection.forEach {
                if(it.listItems.contains(todoItem)){
                    todoList.remove(todoItem)
                    todoListCollection[index].listItems.remove(todoItem)
                }
                index++
            }
            todoList.remove(todoItem)
            SaveJson.instance.saveToFile(todoListCollection)
        }
    }

    fun addTodoList(todoList:TodoList){
        todoListCollection.add(todoList)
        onTodoList?.invoke(todoListCollection)
        SaveJson.instance.saveToFile(todoListCollection)
    }

    fun addTodoItem(todoList: TodoList, todoItem:TodoItem){
        todoList.listItems.add(todoItem)
        SaveJson.instance.saveToFile(todoListCollection)
    }

    // Bruker singleton patter, for vi trenger kun en TodoListDepositoryManager
    companion object {
        val instance = TodoListDepositoryManager()
    }
    /*
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
    }*/
}