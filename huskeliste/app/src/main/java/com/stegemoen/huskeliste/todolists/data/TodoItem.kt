package com.stegemoen.huskeliste.todolists.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TodoItem(val itemName: String, var checked: Boolean): Parcelable