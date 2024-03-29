package com.example.noteapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.File

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "content") var content: String,
    @ColumnInfo(name = "dateModified") var dateModified: String,
    @ColumnInfo(name = "color") var color: String,
    @ColumnInfo(name = "picture")var picture: String)

