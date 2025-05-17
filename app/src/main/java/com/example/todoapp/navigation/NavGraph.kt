package com.example.todoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todoapp.ui.TodoDetailScreen
import com.example.todoapp.ui.TodoListScreen
import com.example.todoapp.viewmodel.TodoDetailViewModel
import com.example.todoapp.viewmodel.TodoListViewModel

@Composable
fun TodoNavGraph(
    navController: NavHostController,
    todoListViewModel: TodoListViewModel,
    todoDetailViewModel: TodoDetailViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "todo_list"
    ) {
        composable("todo_list") {
            TodoListScreen(
                viewModel = todoListViewModel,
                onTodoClick = { todoId ->
                    navController.navigate("todo_detail/$todoId")
                }
            )
        }

        composable(
            route = "todo_detail/{todoId}",
            arguments = listOf(navArgument("todoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val todoId = backStackEntry.arguments?.getInt("todoId") ?: return@composable
            TodoDetailScreen(
                viewModel = todoDetailViewModel,
                todoId = todoId,
                onNavigateBack = { navController.popBackStack() }  // Changed from onBackClick to onNavigateBack
            )
        }
    }
}