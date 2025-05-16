package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.runtime.collectAsState

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.todoapp.data.TodoDatabase
import com.example.todoapp.repository.TodoRepository
import com.example.todoapp.ui.TodoDetailScreen
import com.example.todoapp.ui.TodoListScreen
import com.example.todoapp.ui.theme.TodoAppTheme
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
                val factory = remember { TodoViewModelFactory(repository) }
                val viewModel: TodoListViewModel = viewModel(factory = factory)

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "list") {
                    composable("list") {
                        TodoListScreen(
                            viewModel = viewModel,
                            onItemClick = { todo ->
                                navController.navigate("detail/${todo.id}")
                            }
                        )
                    }
                    composable("detail/{todoId}") { backStackEntry ->
                        val todoId = backStackEntry.arguments?.getString("todoId")?.toIntOrNull()
                        val todo = viewModel.todos.collectAsState().value.find { it.id == todoId }
                        TodoDetailScreen(todo = todo, navController = navController)
                    }
                }
            }
        }
    }
}
