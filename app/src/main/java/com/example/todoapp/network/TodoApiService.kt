package com.example.todoapp.network

import com.example.todoapp.model.Todo
import retrofit2.http.GET

interface TodoApiService {
    @GET("todos")
    suspend fun getTodos(): List<Todo>
}
