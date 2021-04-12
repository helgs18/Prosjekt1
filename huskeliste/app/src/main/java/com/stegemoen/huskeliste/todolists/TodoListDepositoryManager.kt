package com.stegemoen.huskeliste.todolists

import android.net.Uri
import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import com.stegemoen.huskeliste.todolists.data.TodoItem
import com.stegemoen.huskeliste.todolists.data.TodoList
import com.stegemoen.huskeliste.todolists.TodoItemRecyclerAdapter
import com.stegemoen.huskeliste.firebase.SaveJson


class TodoListDepositoryManager {
    // ToDo: Sjekk hvorfor oppretting av lister og items ikke vises i json-fil
    private val TAG:String = "Huskeliste.TodoListDepositoryManager"
    private lateinit var todoListCollection:MutableList<TodoList>
    var onTodoList:((List<TodoList>)->Unit)? = null
    var onTodoListUpdate:((todoList:TodoList)->Unit)? = null

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
        this.todoListCollection = todoListCollection
        SaveJson.instance.saveToFile(todoListCollection)
    }

    fun addTodoItem(todoList: TodoList, todoItem:TodoItem){
        todoListCollection.forEach {
            if(it == todoList){
                it.listItems.add(todoItem)
            }
        }
        todoList.listItems.add(todoItem)
        SaveJson.instance.saveToFile(todoListCollection)
    }

    // Bruker singleton patter, for vi trenger kun en TodoListDepositoryManager
    companion object {
        val instance = TodoListDepositoryManager()
    }

    fun getJson():String {
        var jsonString: String = "{\n\t\"todoListCollection\":[\n"
        todoListCollection.forEach{
            jsonString += "\t\t{\n"
            jsonString += "\t\t\t\"listName\":\"${it.listName}\",\n"
            jsonString += "\t\t\t\t\"ListItems\":[\n"
            "\t\t\t\t\t,\n\"listItems\":[\n\t\t\t\t\t\t{"
            if(it.listItems.isEmpty()) {
                jsonString += " ,\n"

            } else {
                it.listItems.forEach{
                    jsonString += "\t\t\t\t{\n"
                    jsonString += "\t\t\t\t\t\"itemName\": \"${it.itemName}\",\n" + // add itemName
                    "\t\t\t\t\t\"checked\": ${it.checked.toString()}\n\t\t\t\t},\n"        // add checkbox value
                }
            }

            jsonString = jsonString.dropLast(2)   // removes comma
            jsonString += "\n\t\t\t]"         // ends listItems
            jsonString += "\n\t\t},\n"        // ends current list
        }
        jsonString = jsonString.dropLast(2)      // removes comma
        jsonString += "\n\t]"             // ends todoLists
        jsonString += "\n}"             // ends todoListCollection

        return jsonString
    }
}