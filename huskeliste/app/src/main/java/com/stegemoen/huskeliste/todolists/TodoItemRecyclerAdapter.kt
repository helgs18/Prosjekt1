package com.stegemoen.huskeliste.todolists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stegemoen.huskeliste.databinding.TodoitemLayoutBinding
import com.stegemoen.huskeliste.todolists.data.TodoItem


internal class TodoItemRecyclerAdapter(
        var todoItems: MutableList<TodoItem>)
        : RecyclerView.Adapter<TodoItemRecyclerAdapter.ViewHolder>() {



    // Hjelpeklasse
    internal inner class ViewHolder(val binding: TodoitemLayoutBinding):RecyclerView.ViewHolder(binding.root)
    {

        fun bind(todoItem: TodoItem) {
            binding.itemName.text = todoItem.itemName
            binding.checkbox.isChecked = todoItem.checked
            binding.deleteItemBtn.setOnClickListener {
                this@TodoItemRecyclerAdapter.updateItems(todoItem)
            }
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TodoitemLayoutBinding.inflate(LayoutInflater.from(parent.context),
                parent, false))
    }

    override fun getItemCount(): Int = todoItems.size

    // Display data at a certain position
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val todoItem = todoItems[position]
        holder.bind(todoItem)
    }

    fun updateItems(todoItem: TodoItem){
        //TodoListDepositoryManager.instance.deleteTodoItem(todoItems, todoItem)
        todoItems.remove(todoItem)
        notifyDataSetChanged()
    }

    // ToDo: Legg til onClickListener() funksjonalitet for checkboxes
    // for(i = 0; i < getListCount(); i++) e.l.
}