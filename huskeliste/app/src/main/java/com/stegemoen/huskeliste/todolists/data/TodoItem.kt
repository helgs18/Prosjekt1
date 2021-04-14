package com.stegemoen.huskeliste.todolists.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TodoItemCollection(val title:String):Parcelable {

    var  todoItems:MutableList<TodoItem> = mutableListOf<TodoItem>()
        get() { return field }
        private set

    constructor(title: String, todoItems:MutableList<TodoItem>) : this(title) {
        this.todoItems = todoItems.toMutableList()
    }

    fun addTodoItem(todoItem:TodoItem){
        if (!todoItems.contains(todoItem)){
            todoItems.add(todoItem)
        }
    }

    fun removeBook(book:TodoItem){
        todoItems.remove(book)
    }

}

@Parcelize
data class TodoItem(val itemName: String, var checked: Boolean): Parcelable