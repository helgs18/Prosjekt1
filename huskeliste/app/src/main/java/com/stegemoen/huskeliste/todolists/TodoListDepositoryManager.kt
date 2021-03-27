package com.stegemoen.huskeliste.todolists

import com.stegemoen.huskeliste.todolists.data.TodoItem
import com.stegemoen.huskeliste.todolists.data.TodoList

class TodoListDepositoryManager {
    private lateinit var todoListCollection:MutableList<TodoList>
    var onTodoList:((List<TodoList>)->Unit)? = null
    var onTodoListUpdate:((todoList:TodoList)->Unit)? = null

    // ToDo: Flytt disse til en egen TodoItemDepository?
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

    fun load(){

        todoListCollection = mutableListOf(
            TodoList("Handleliste", handleliste),
            TodoList("Must See filmer", mustSeeMovies),
            TodoList("Julegaver", julegaver),
            TodoList("Fjelltopper å bestige", fjelltopper),
            TodoList("Bucket List", bucketlist)
            /*TodoList("Handleliste", handleliste),
            TodoList("Must See filmer", mustSeeMovies),
            TodoList("Julegaver", julegaver),
            TodoList("Fjelltopper å bestige", fjelltopper),
            TodoList("Bucket List", bucketlist)*/
        )
        onTodoList?.invoke(todoListCollection)
    }

    fun updateTodoList(todoList:TodoList){
        // finn huskeliste i listen og erstatt med den ny listen
        onTodoListUpdate?.invoke(todoList)
    }

    fun addTodoList(todoList:TodoList){
        todoListCollection.add(todoList)
        onTodoList?.invoke(todoListCollection)
    }

    // Bruker singleton patter, for vi trenger kun en TodoListDepositoryManager
    companion object {
        val instance = TodoListDepositoryManager()
    }
}