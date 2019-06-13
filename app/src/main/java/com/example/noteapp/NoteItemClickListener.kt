package com.example.noteapp

interface NoteItemClickListener {
    fun onItemCLicked(position: Int)
    fun onItemLongCLicked(position: Int)
}