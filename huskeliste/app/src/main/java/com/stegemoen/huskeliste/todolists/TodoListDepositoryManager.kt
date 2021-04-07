package com.stegemoen.huskeliste.todolists

import com.stegemoen.huskeliste.todolists.data.TodoItem
import com.stegemoen.huskeliste.todolists.data.TodoList

class TodoListDepositoryManager {
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
    }

    fun updateTodoList(todoList:TodoList){
        // finn huskeliste i listen og erstatt med den ny listen
        onTodoListUpdate?.invoke(todoList)
    }

    fun deleteTodoList(todoList:TodoList){
        todoListCollection.remove(todoList)
        onTodoList?.invoke(todoListCollection)
    }

    fun addTodoList(todoList:TodoList){
        todoListCollection.add(todoList)
        onTodoList?.invoke(todoListCollection)
    }

    fun addTodoItem(todoList: TodoList, todoItem:TodoItem){
        todoList.listItems.add(todoItem)
    }

    // Bruker singleton patter, for vi trenger kun en TodoListDepositoryManager
    companion object {
        val instance = TodoListDepositoryManager()
    }
}