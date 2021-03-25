package com.stegemoen.huskeliste.todolists.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TodoList(val listName: String): Parcelable {
    val listItems: MutableList<TodoItem> = mutableListOf<TodoItem>()
}
// data class TodoList(val listName: String, val listItems: MutableList<TodoItem>): Parcelable