package com.stegemoen.huskeliste.todolists.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TodoList(var listName: String, val listItems: MutableList<TodoItem>): Parcelable
// listName has to be var, so it can be changed if there is a naming conflict