package com.example.todoapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey val id: Int,
    val userId: Int,
    val title: String,
    @SerializedName("completed") val isCompleted: Boolean
)
