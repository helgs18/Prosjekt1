package com.stegemoen.huskeliste.todolists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stegemoen.huskeliste.R
import com.stegemoen.huskeliste.todolists.data.TodoItem

class newRecyclerAdapter(
        private val todoItems: MutableList<TodoItem>) //,
        //private val onItemClicked:(TodoItem)->Unit)
        : RecyclerView.Adapter<newRecyclerAdapter.ViewHolder>() {

    // Hjelpeklasse
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val itemViewTextView: TextView = itemView.findViewById(R.id.TodoItem_text)
        private val itemViewCheckbox: CheckBox = itemView.findViewById(R.id.checkbox)

        fun bind(word: String, isChecked: Boolean) { //fun bind(word: String, onItemClicked:(String) -> Unit) {
            //binding.itemName.text = todoItem.ItemName // todoItem.itemName
            itemViewTextView.text = word
            itemViewCheckbox.isChecked = isChecked
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
}