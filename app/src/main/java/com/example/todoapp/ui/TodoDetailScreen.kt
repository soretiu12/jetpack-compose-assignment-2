package com.example.todoapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todoapp.model.Todo
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(todo: Todo?, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Todo Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)) {
            if (todo == null) {
                Text("TODO item not found")
            } else {
                Column {
                    Text("Title: ${todo.title}", style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Status: ${if (todo.isCompleted) "Completed" else "Pending"}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("User ID: ${todo.userId}")
                    Text("ID: ${todo.id}")
                }
            }
        }
    }
}