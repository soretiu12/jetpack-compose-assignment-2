package com.example.todoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.repository.TodoRepository

class TodoViewModelFactory(private val repository: TodoRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TodoListViewModel::class.java) ->
                TodoListViewModel(repository) as T
            modelClass.isAssignableFrom(TodoDetailViewModel::class.java) ->
                TodoDetailViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}