package com.example.todoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.model.Todo
import com.example.todoapp.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoListViewModel(private val repository: TodoRepository) : ViewModel() {
    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        viewModelScope.launch {
            repository.todos.collect {
                _todos.value = it
            }
        }

        refreshTodos()
    }


    fun refreshTodos() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _errorMessage.value = null // Clear any previous errors
                repository.refreshTodos()
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load todos: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}