package com.stegemoen.huskeliste.todoitem.data

import android.os.Parcelable
import com.stegemoen.huskeliste.todolists.data.TodoItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TodoItem(val itemName: String, val checked: Boolean): Parcelable