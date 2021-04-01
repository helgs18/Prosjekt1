package com.stegemoen.huskeliste.todolists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stegemoen.huskeliste.R
import com.stegemoen.huskeliste.todolists.data.TodoItem

class TodoItemRecyclerAdapter(
        private val todoItems: MutableList<TodoItem>) //,
        //private val onItemClicked:(TodoItem)->Unit)
        : RecyclerView.Adapter<TodoItemRecyclerAdapter.ViewHolder>() {

    // Hjelpeklasse
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val itemViewTextView: TextView = itemView.findViewById(R.id.TodoItem_text)
        private val itemViewCheckbox: CheckBox = itemView.findViewById(R.id.checkbox)

        fun bind(word: String, isChecked: Boolean) { //fun bind(word: String, onItemClicked:(String) -> Unit) {
            itemViewTextView.text = word
            itemViewCheckbox.isChecked = isChecked
            // ToDo: Legg til setOnClickListener
            /*itemViewTextView.setOnClickListener {
                onItemClicked(String)
            }*/
        }

    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.todoitem_layout, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = todoItems.size

    // Display data at a certain position
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(todoItems[position].itemName, todoItems[position].checked)
    }

    // ToDo: Legg til onClickListener() funksjonalitet for checkboxes
    // for(i = 0; i < getListCount(); i++) e.l.

    // Slette et item. Bruk knapp, og eventuelt legg til papirkurv bilde senere.
    fun deleteItem(): Void?{
        // ToDo: for(i = 0; i < getItemCount(); i++) e.l.
        return null
    }
}