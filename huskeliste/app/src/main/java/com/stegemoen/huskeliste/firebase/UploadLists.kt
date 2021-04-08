package com.stegemoen.huskeliste.firebase

import com.stegemoen.huskeliste.todolists.data.TodoList

class UploadLists {
    lateinit var todoListCollection: MutableList<TodoList>
    lateinit var jsonString: String

    fun upload(lists: MutableList<TodoList>){
        lists.forEach{

        }
    }
}