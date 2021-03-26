package com.stegemoen.huskeliste.todoitem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stegemoen.huskeliste.todoitem.data.TodoItem
import com.stegemoen.huskeliste.databinding.TodoitemLayoutBinding

class TodoItemRecyclerAdapter (
    private val todoItems:MutableList<TodoItem>)
    : RecyclerView.Adapter<TodoItemRecyclerAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position:Int){
        val todoItem = todoItems[position]
        holder.bind(todoItem)
    }

    override fun getItemCount(): Int = todoItems.size

    // Hjelpeklasse
    class ViewHolder(val binding:TodoitemLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(todoItem: TodoItem) {
            binding.itemName.text = todoItem.itemName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TodoitemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }
}