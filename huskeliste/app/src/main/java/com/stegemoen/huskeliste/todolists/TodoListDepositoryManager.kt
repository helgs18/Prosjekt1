package com.stegemoen.huskeliste.todolists

import com.stegemoen.huskeliste.todolists.data.TodoList

class TodoListDepositoryManager {
    private lateinit var todoListCollection:MutableList<TodoList>
    var onTodoList:((List<TodoList>)->Unit)? = null
    var onTodoListUpdate:((todoList:TodoList)->Unit)? = null

    private var handleliste: MutableList<String> = mutableListOf(
            "Egg", "Melk", "3 x Farris", "Avacado"
    )

    private var julegaver: MutableList<String> = mutableListOf(
            "Nintendo Switch", "Super Mario Odyssey", "Acer Nitro", "The Matrix DVD"
    )

    private var mustSeeMovies: MutableList<String> = mutableListOf(
            "Forrest Gump", "Aliens", "Terminator 2: Judgement Day", "Pulp Fiction", "Godfellas"
    )

    private var fjelltopper: MutableList<String> = mutableListOf(
            "Himmelbjerget", "Mount Everest", "Olympus Mons", "Jotunheimen"
    )

    private var bucketlist: MutableList<String> = mutableListOf()

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

    fun addTodoList(todoList:TodoList){
        todoListCollection.add(todoList)
        onTodoList?.invoke(todoListCollection)
    }

    // Bruker singleton patter, for vi trenger kun en TodoListDepositoryManager
    companion object {
        val instance = TodoListDepositoryManager()
    }
}