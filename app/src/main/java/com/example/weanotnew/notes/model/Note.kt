package com.example.weanotnew.notes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_tb")
data class Note(var title: String, var text: String, var createTime: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}