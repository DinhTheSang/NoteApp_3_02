package com.example.noteapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.note_item.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val adapter = NoteListAdapter(this)
        rv_list_note.adapter = adapter
        adapter.setListener(noteItemClickListener)
        rv_list_note.layoutManager = LinearLayoutManager(this)
        rv_list_note.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

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
            R.id.menu_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.let { data ->
                val type = data.getIntExtra(EXTRA_TYPE, LONG_DEFAULT_VALUE)
                if (type == TYPE_SAVE_NOTE) {
                    var id_reply: Long? = data.getLongExtra(EXTRA_ID, LONG_DEFAULT_VALUE.toLong())
                    val title_reply = data.getStringExtra(EXTRA_TITLE)
                    val content_reply = data.getStringExtra(EXTRA_CONTENT)
                    if (id_reply == LONG_DEFAULT_VALUE.toLong()) {
                        id_reply = null
                    }
                    val note = Note(id_reply, title_reply, content_reply, "13-06", "#FFFFFF")
                    noteViewModel.insertNote(note)
                }
                if (type == TYPE_DELETE_NOTE) {
                    val id = data.getLongExtra(EXTRA_ID, LONG_DEFAULT_VALUE.toLong())
                    if (id != LONG_DEFAULT_VALUE.toLong()) {
                        noteViewModel.deleteNote(noteViewModel.getNoteWithId(id)!!)
                    }
                }
            }
        }
    }

    private val noteItemClickListener = object :NoteItemClickListener {
        override fun onItemCLicked(position: Int) {
            val intent = Intent(this@MainActivity, NewNoteActivity::class.java)

            val titleGet = rv_list_note.get(position).tv_title.text.toString()
            val idGet: Long? = noteViewModel.getNoteIdWithTitle(titleGet)
            val noteGet: Note? = noteViewModel.getNoteWithId(idGet!!)
            val contentGet = noteGet?.content

            intent.putExtra(EXTRA_ID, idGet)
            intent.putExtra(EXTRA_TITLE, titleGet)
            intent.putExtra(EXTRA_CONTENT, contentGet)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
        }

        override fun onItemLongCLicked(position: Int) {
            Toast.makeText(
                applicationContext,
                "Long Clicked Event",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
