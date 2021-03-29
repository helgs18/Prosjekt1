package com.stegemoen.huskeliste

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stegemoen.huskeliste.databinding.FragmentTodoItemBinding
import com.stegemoen.huskeliste.todolists.data.TodoItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_todo_item.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TodoItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TodoItemFragment : Fragment() {
    private var _binding:FragmentTodoItemBinding? = null
    private val binding get() = _binding!!

    private val todoitems:MutableList<TodoItem> = mutableListOf<TodoItem>()
    private val todoitems2:MutableList<TodoItem> = mutableListOf<TodoItem>()
    private lateinit var itemname:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = arguments?.getString("itemName")
        val shame = arguments?.getString("fitemname")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodoItemBinding.inflate(layoutInflater)
        val view = binding.root

        // ToDo: Sjekk om jeg trenger disse, så lenge jeg ikke skal gjøre noe avansert?
        val fm = childFragmentManager
        val ft = fm.beginTransaction()

        // ToDo: Finn ut hva todoitems bør erstattes med
        todoitems.forEach(){
            // ToDo: Finn ut hvordan jeg legger til itemNames
            val fulltone:String = "hva skjer?"
        }

        ft.commit()

        return inflater.inflate(R.layout.fragment_todo_item, container, false)
        //return view
        //return super.onCreateView(inflater, container, savedInstanceState)
    }
}



/*
class TodoItemFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_todo_item, container, false);
        //rootView.itemName.text = todoListing.todoItem.

        return rootView
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_todo_item, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TodoItemFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                TodoItemFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
 */