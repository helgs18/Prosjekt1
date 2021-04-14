package com.stegemoen.huskeliste.firebase

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.stegemoen.huskeliste.MainActivity
import com.stegemoen.huskeliste.todolists.TodoListDepositoryManager
import com.stegemoen.huskeliste.todolists.data.TodoList
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class FirebaseManager {
    private val TAG:String = "Huskeliste.SaveJson"

    // Can add path as parameter, but not without changing the unit test saveFileTest()
    fun saveToFile(listCollection: MutableList<TodoList>){//(notes: mutableListOf<note>()){
        var fileName = "huskeliste.json"

        val path = context.getExternalFilesDir(null)
        val jsonString = TodoListDepositoryManager.instance.getJson()

        val any = if (path != null) {
            val file = File(path, fileName)
            FileOutputStream(file, false).bufferedWriter().use { writer ->
                writer.write(jsonString)
                writer.close()
            }
            MainActivity.instance.onSave(file)
            //this.onSave?.invoke(file.toUri())
        } else {
            Log.e(TAG, "Could not get external path")
        }

    }

    fun loadFile() {
        var fileName = "handleliste.sjon"
        val path = context.getExternalFilesDir(null)
        val inFile = File(path, fileName)
        FileInputStream(inFile).bufferedReader()
        val noen = inFile
    }

    companion object {
        lateinit var sharedPreferences: SharedPreferences
        lateinit var context: Context
        val instance = FirebaseManager()

        fun init(con: Context) {
            // to prevent multiple initialization
            if (!Companion::sharedPreferences.isInitialized) {
                context = con
                sharedPreferences = con.getSharedPreferences("preference_name", Context.MODE_PRIVATE)
            }


        }
    }
}