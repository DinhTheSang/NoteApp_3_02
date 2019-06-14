package com.example.noteapp

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_note_editor.*
import java.text.SimpleDateFormat
import java.util.*

class NewNoteActivity :AppCompatActivity() {
    private var noteId :Long = LONG_DEFAULT_VALUE.toLong()
    private lateinit var menuEditor: Menu
    private lateinit var noteViewModel: NoteViewModel

    private lateinit var noteTitle: String
    private lateinit var noteContent: String
    private lateinit var noteColor: String
    private lateinit var noteCurrentColor: String
    private lateinit var noteDate: String
    private val dataFormatter = SimpleDateFormat("MMM dd yyyy HH:mma")


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_editor)

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteColor = ContextCompat.getColor(this, R.color.note_color_yellow).toString()
        noteCurrentColor = ContextCompat.getColor(this, R.color.note_color_yellow).toString()

        var date = Date();
        noteDate = dataFormatter.format(date)
        tv_day_editor.setText(noteDate)

        val data = intent.extras
        noteId = data.getLong(EXTRA_ID)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_new_note, menu)
        menuEditor = menu

        if (noteId != LONG_DEFAULT_VALUE.toLong()) {
            val noteGet: Note? = noteViewModel.getNoteWithId(noteId)
            noteTitle = noteGet?.title!!
            noteContent = noteGet?.content!!
            noteDate = noteGet?.dateModified!!
            noteColor = noteGet?.color!!
            noteCurrentColor = noteColor

            when (noteColor) {
                ContextCompat.getColor(this, R.color.stroke_color_yellow).toString() -> {
                    menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_yellow)
                    ll_editor.background = getDrawable(R.drawable.note_item_yellow)
                    bt_line_editor.background = getDrawable(R.drawable.top_line_yellow)
                }

                ContextCompat.getColor(this, R.color.stroke_color_green).toString() -> {
                    menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_green)
                    ll_editor.background = getDrawable(R.drawable.note_item_green)
                    bt_line_editor.background = getDrawable(R.drawable.top_line_green)
                }

                ContextCompat.getColor(this, R.color.stroke_color_pink).toString() -> {
                    menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_pink)
                    ll_editor.background = getDrawable(R.drawable.note_item_pink)
                    bt_line_editor.background = getDrawable(R.drawable.top_line_pink)
                }

                ContextCompat.getColor(this, R.color.stroke_color_purple).toString() -> {
                    menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_purple)
                    ll_editor.background = getDrawable(R.drawable.note_item_purple)
                    bt_line_editor.background = getDrawable(R.drawable.top_line_purple)
                }

                ContextCompat.getColor(this, R.color.stroke_color_blue).toString() -> {
                    menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_blue)
                    ll_editor.background = getDrawable(R.drawable.note_item_blue)
                    bt_line_editor.background = getDrawable(R.drawable.top_line_blue)
                }

                ContextCompat.getColor(this, R.color.stroke_color_gray).toString() -> {
                    menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_gray)
                    ll_editor.background = getDrawable(R.drawable.note_item_gray)
                    bt_line_editor.background = getDrawable(R.drawable.top_line_gray)
                }
            }

            ed_title.setText(noteTitle)
            ed_content.setText(noteTitle)
            tv_day_editor.setText(noteDate)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_color_yellow -> {
                noteCurrentColor = ContextCompat.getColor(this, R.color.stroke_color_yellow).toString()
                menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_yellow)
                ll_editor.background = getDrawable(R.drawable.note_item_yellow)
                bt_line_editor.background = getDrawable(R.drawable.top_line_yellow)
                return true
            }

            R.id.menu_color_green -> {
                noteCurrentColor = ContextCompat.getColor(this, R.color.stroke_color_green).toString()
                menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_green)
                ll_editor.background = getDrawable(R.drawable.note_item_green)
                bt_line_editor.background = getDrawable(R.drawable.top_line_green)
                return true
            }

            R.id.menu_color_pink -> {
                noteCurrentColor = ContextCompat.getColor(this, R.color.stroke_color_pink).toString()
                menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_pink)
                ll_editor.background = getDrawable(R.drawable.note_item_pink)
                bt_line_editor.background = getDrawable(R.drawable.top_line_pink)
                return true
            }

            R.id.menu_color_purple -> {
                noteCurrentColor = ContextCompat.getColor(this, R.color.stroke_color_purple).toString()
                menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_purple)
                ll_editor.background = getDrawable(R.drawable.note_item_purple)
                bt_line_editor.background = getDrawable(R.drawable.top_line_purple)
                return true
            }

            R.id.menu_color_blue -> {
                noteCurrentColor = ContextCompat.getColor(this, R.color.stroke_color_blue).toString()
                menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_blue)
                ll_editor.background = getDrawable(R.drawable.note_item_blue)
                bt_line_editor.background = getDrawable(R.drawable.top_line_blue)
                return true
            }

            R.id.menu_color_gray -> {
                noteCurrentColor = ContextCompat.getColor(this, R.color.stroke_color_gray).toString()
                menuEditor.findItem(R.id.menu_select_color).setIcon(R.drawable.note_color_gray)
                ll_editor.background = getDrawable(R.drawable.note_item_gray)
                bt_line_editor.background = getDrawable(R.drawable.top_line_gray)
                return true
            }

            R.id.menu_editor_delete -> {
                if (noteId != LONG_DEFAULT_VALUE.toLong()) {
                    noteViewModel.deleteNote(noteViewModel.getNoteWithId(noteId)!!)
                }
                finish()
            }

            R.id.menu_done -> {
                val titleGet = ed_title.text.toString()
                val contentGet = ed_content.text.toString()

                if (noteId != LONG_DEFAULT_VALUE.toLong()) {
                    if (titleGet != noteTitle || contentGet != noteContent || noteColor != noteCurrentColor) {
                        var date = Date()
                        noteColor = noteCurrentColor
                        noteDate = dataFormatter.format(date)
                    }

                    val note = Note(noteId, titleGet, contentGet, noteDate, noteColor)
                    noteViewModel.insertNote(note)
                } else {
                    var date = Date()
                    noteDate = dataFormatter.format(date)
                    val note = Note(null, titleGet, contentGet, noteDate, noteColor)
                    noteViewModel.insertNote(note)
                }
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}