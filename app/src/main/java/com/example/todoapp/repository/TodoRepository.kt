package com.example.todoapp.repository

import com.example.todoapp.data.TodoDao
import com.example.todoapp.model.Todo
import com.example.todoapp.network.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import android.util.Log

class TodoRepository(private val dao: TodoDao) {
    val todos: Flow<List<Todo>> = dao.getAllTodos()

    suspend fun refreshTodos() {
        val response = RetrofitInstance.api.getTodos()
        Log.d("TodoRepository", "Fetched ${response.size} todos from API")
        dao.insertAll(response)
    }
    suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id)
    }
}
