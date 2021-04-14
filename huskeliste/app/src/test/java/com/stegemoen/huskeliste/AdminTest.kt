package com.stegemoen.huskeliste
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.stegemoen.huskeliste.todolists.TodoListDepositoryManager
import com.stegemoen.huskeliste.todolists.data.TodoItem
import com.stegemoen.huskeliste.todolists.data.TodoList
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AdminTest {
    @Test
    fun addTodoList(){
        var check = false
        val list: TodoList = (
                TodoList("Brustyper",
                    mutableListOf<TodoItem>(
                        TodoItem("Coca Cola", false),
                        TodoItem("Solo", true),
                        TodoItem("Pepsi Max", false)
                    )))
        TodoListDepositoryManager.instance.addTodoList(list)
        TodoListDepositoryManager.instance.getTodoListCollection().forEach {
            if(it.listName == "Brustyper") {
                check = true
            }
        }
        if(check != true){
            assertFalse("Could not find requested YourBusinessObject in List<YourBusinessObject>!", true)
        } else {
            assertTrue("Successfully added TodoList", true)
        }
    }
}