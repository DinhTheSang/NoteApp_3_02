package com.example.noteapp

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.note_item.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var noteViewModel: NoteViewModel

    private lateinit var yellowColor: String
    private lateinit var greenColor: String
    private lateinit var pinkColor: String
    private lateinit var purpleColor: String
    private lateinit var blueColor: String
    private lateinit var grayColor: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        yellowColor = ContextCompat.getColor(this, R.color.stroke_color_yellow).toString()
        greenColor = ContextCompat.getColor(this, R.color.stroke_color_green).toString()
        pinkColor = ContextCompat.getColor(this, R.color.stroke_color_pink).toString()
        purpleColor = ContextCompat.getColor(this, R.color.stroke_color_purple).toString()
        blueColor = ContextCompat.getColor(this, R.color.stroke_color_blue).toString()
        grayColor = ContextCompat.getColor(this, R.color.stroke_color_gray).toString()

        val adapter = NoteListAdapter(this)
        rv_list_note.adapter = adapter
        adapter.setListener(noteItemClickListener)
        adapter.setDisplayColor(displayColorNote)
        rv_list_note.layoutManager = LinearLayoutManager(this)

        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(getDrawable(R.drawable.recycler_line_divider))
        rv_list_note.addItemDecoration(itemDecoration)

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        noteViewModel.allNotes.observe(this, Observer { notes ->
            notes?.let {adapter.setNotes(it)}
        })

        fab_new_note.setOnClickListener {
            val intent = Intent(this@MainActivity, NewNoteActivity::class.java)
            intent.putExtra(EXTRA_ID, LONG_DEFAULT_VALUE.toLong())
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_main_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    private val noteItemClickListener = object :NoteItemClickListener {
        override fun onItemCLicked(position: Int) {
            val intent = Intent(this@MainActivity, NewNoteActivity::class.java)

            val titleGet = rv_list_note.get(position).tv_title.text.toString()
            val idGet: Long? = noteViewModel.getNoteIdWithTitle(titleGet)

            intent.putExtra(EXTRA_ID, idGet)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
        }

        override fun onItemLongCLicked(position: Int) {
            Toast.makeText(
                applicationContext,
                "Long Click Event",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private val displayColorNote = object :DisplayColorNote {
        override fun getLlDrawable(color: String) =
            when (color) {
                yellowColor -> getDrawable(R.drawable.note_item_yellow)
                greenColor -> getDrawable(R.drawable.note_item_green)
                pinkColor -> getDrawable(R.drawable.note_item_pink)
                purpleColor -> getDrawable(R.drawable.note_item_purple)
                blueColor -> getDrawable(R.drawable.note_item_blue)
                grayColor -> getDrawable(R.drawable.note_item_gray)
                else -> getDrawable(R.drawable.note_item_yellow)
            }

        override fun getBtDrawable(color: String) =
            when (color) {
                yellowColor -> getDrawable(R.drawable.top_line_yellow)
                greenColor -> getDrawable(R.drawable.top_line_green)
                pinkColor -> getDrawable(R.drawable.top_line_pink)
                purpleColor -> getDrawable(R.drawable.top_line_purple)
                blueColor -> getDrawable(R.drawable.top_line_blue)
                grayColor -> getDrawable(R.drawable.top_line_gray)
                else -> getDrawable(R.drawable.top_line_yellow)
            }
    }
}
