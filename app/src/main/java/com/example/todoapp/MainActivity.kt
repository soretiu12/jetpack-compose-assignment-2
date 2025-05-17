package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.data.TodoDatabase
import com.example.todoapp.navigation.TodoNavGraph
import com.example.todoapp.repository.TodoRepository
import com.example.todoapp.ui.theme.TodoAppTheme
import com.example.todoapp.viewmodel.TodoDetailViewModel
import com.example.todoapp.viewmodel.TodoListViewModel
import com.example.todoapp.viewmodel.TodoViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme {
                val context = LocalContext.current
                val dao = TodoDatabase.getDatabase(context).todoDao()
                val repository = remember { TodoRepository(dao) }

                // Create factories for both ViewModels
                val listViewModelFactory = remember { TodoViewModelFactory(repository) }
                val detailViewModelFactory = remember { TodoViewModelFactory(repository) }

                // Create both ViewModels
                val todoListViewModel: TodoListViewModel = viewModel(factory = listViewModelFactory)
                val todoDetailViewModel: TodoDetailViewModel = viewModel(factory = detailViewModelFactory)

                val navController = rememberNavController()

                // Use our new NavGraph
                TodoNavGraph(
                    navController = navController,
                    todoListViewModel = todoListViewModel,
                    todoDetailViewModel = todoDetailViewModel
                )
            }
        }
    }
}