package com.stegemoen.huskeliste.firebase

import android.app.Application
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.core.net.toUri
import com.stegemoen.huskeliste.MainActivity
import com.stegemoen.huskeliste.todolists.TodoListDepositoryManager
import com.stegemoen.huskeliste.todolists.data.TodoList
import java.io.File
import java.io.FileOutputStream

class SaveJson {
    private val TAG:String = "Huskeliste.SaveJson"
    var onSave:((file: Uri) -> Unit)? = null

    // Can add path as parameter, but not without changing the unit test saveFileTest()
    fun saveToFile(listCollection: MutableList<TodoList>){//(notes: mutableListOf<note>()){
        var fileName = "huskeliste.json"
        val path = context.getExternalFilesDir(null)

        // ToDo: initialize context
        //val context: Context
        //val path = context.getExternalFilesDir(null)
        val ingenting = context.getExternalFilesDir(null)


        if(path != null) {
            val file = File(path,fileName)
            FileOutputStream(file, true).bufferedWriter().use { writer ->
                listCollection.forEach {
                    writer.write("${it}\n")
                }
                writer.close()
            }
            this.onSave?.invoke(file.toUri())
        } else {
            Log.e(TAG, "Could not get external path")
        }
    }


    companion object {
        lateinit var sharedPreferences: SharedPreferences
        lateinit var context: Context
        val instance = SaveJson()

        fun init(con: Context) {
            // to prevent multiple initialization
            if (!Companion::sharedPreferences.isInitialized) {
                context = con
                sharedPreferences = con.getSharedPreferences("preference_name", Context.MODE_PRIVATE)
            }


        }
    }
}