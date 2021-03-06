package com.stegemoen.huskeliste.todolists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stegemoen.huskeliste.databinding.TodolistLayoutBinding
import com.stegemoen.huskeliste.todolists.data.TodoList

// legger med funksjon (lambda) med adapteren
class TodoListRecyclerAdapter(
        private var todoLists:List<TodoList>,
        private val onTodoListClicked:(TodoList)->Unit)
    : RecyclerView.Adapter<TodoListRecyclerAdapter.ViewHolder>() {

    // Dette er en hjelpeklasse, for å si dette er den type view som vi har lyst til å ha
    class ViewHolder(val binding:TodolistLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(todoList: TodoList,onTodoListClicked:(TodoList) -> Unit) {
            binding.listName.text = todoList.listName
            binding.deleteListBtn.setOnClickListener {
                onDeleteListBtnClicked(todoList)
            }
            binding.cardForLists.setOnClickListener {
                onTodoListClicked(todoList)
            }
        }

        private fun onDeleteListBtnClicked(todoList: TodoList) {
            TodoListDepositoryManager.instance.deleteTodoList(todoList)
        }
    }

    override fun getItemCount(): Int = todoLists.size

    override fun onBindViewHolder(holder:ViewHolder, position:Int) {
        val todoList = todoLists[position]
        holder.bind(todoList,onTodoListClicked)
    }

    /* Trenger ikke alltid returnere samme ViewHolder. / eller samme type View
        kan ha forskjellige type ting i poppe opp i recycleren vår.
        Betyr dette at jeg kan bruke samme recycler for TodoItem?
        Se ca: 1:00:00 i videoen IKT #9 (https://www.youtube.com/watch?v=i9jrx3-iKlo)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TodolistLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false))
    }

    public fun updateLists(newLists:List<TodoList>){
        todoLists = newLists
        notifyDataSetChanged()
    }

    // Slette en liste. Bruk knapp, og eventuelt legg til papirkurv bilde senere.
    // Basic funksjon. Kan legge til bekreftelse senere, om det blir nok tid.
    // Kan en funskjon liknende updateLists(...) brukes?
    fun deleteList(): Void?{
        // ToDo:
        return null
    }
}