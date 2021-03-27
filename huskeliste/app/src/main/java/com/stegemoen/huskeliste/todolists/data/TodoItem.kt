package com.stegemoen.huskeliste.todolists.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TodoItem(val ItemName:String, val checked: Boolean): Parcelable

// Todo: repliker TodoListRecyclerAdapter og todoListLayout.xml for TodoItem