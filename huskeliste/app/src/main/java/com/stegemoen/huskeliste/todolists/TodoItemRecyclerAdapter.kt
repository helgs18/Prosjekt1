package com.stegemoen.huskeliste.todolists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stegemoen.huskeliste.databinding.TodoitemLayoutBinding
import com.stegemoen.huskeliste.todolists.data.TodoItem

class TodoItemRecyclerAdapter(
    private val todoItems: MutableList<TodoItem>,
    private val onItemClicked:(TodoItem)->Unit)
    : RecyclerView.Adapter<TodoItemRecyclerAdapter.ViewHolder>() {

    // Hjelpeklasse
    class ViewHolder(val binding:TodoitemLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(todoItem: TodoItem, onItemClicked:(TodoItem) -> Unit) {
            //binding.itemName.text = todoItem.ItemName // todoItem.itemName
            binding.listItemsName.text = todoItem.itemName.toString()
            binding.checkBox.text = todoItem.checked.toString()
            binding.cardForItems.setOnClickListener {
                onItemClicked(todoItem)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position:Int){
        val todoItem = todoItems[position]
        holder.bind(todoItem,onItemClicked)
    }

    override fun getItemCount(): Int = todoItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TodoitemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false))
    }
}