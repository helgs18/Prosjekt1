package com.stegemoen.huskeliste.todolists.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TodoListCollection(val title: String): Parcelable {
    var todoLists: MutableList<TodoList> = mutableListOf<TodoList>()
    get() { return field }
    private set

    constructor(title: String, todoLists: List<TodoList>): this(title) {
        this.todoLists = todoLists.toMutableList()
    }

    fun addTodoList(todoList: TodoList) {
        todoLists.add(todoList)
    }
}

@Parcelize
data class TodoList(var listName: String, val listItems: MutableList<TodoItem>): Parcelable
// listName has to be var, so it can be changed if there is a naming conflict