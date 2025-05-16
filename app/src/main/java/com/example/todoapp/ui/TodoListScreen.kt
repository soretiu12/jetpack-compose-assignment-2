package com.example.todoapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoapp.model.Todo
import com.example.todoapp.viewmodel.TodoListViewModel
import android.util.Log

@Composable
fun TodoListScreen(
    viewModel: TodoListViewModel,
    modifier: Modifier = Modifier,
    onItemClick: (Todo) -> Unit
) {
    val todos by viewModel.todos.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    Log.d("TodoListScreen", "Todos count: ${todos.size}")

    Box(modifier = modifier.fillMaxSize()) {
        when {
            isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            errorMessage != null -> {
                Text(
                    text = errorMessage ?: "Something went wrong",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            todos.isEmpty() -> {
                Text("No TODOs found", modifier = Modifier.align(Alignment.Center))
            }
            else -> {
                LazyColumn(modifier = Modifier.padding(16.dp)) {
                    items(todos) { todo ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable { onItemClick(todo) }
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(todo.title, style = MaterialTheme.typography.titleMedium)
                                Text(
                                    text = if (todo.isCompleted) "Completed" else "Pending",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}