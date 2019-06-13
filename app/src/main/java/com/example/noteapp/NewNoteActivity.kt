package com.example.noteapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_note_editor.*

class NewNoteActivity :AppCompatActivity() {
    private var noteId :Long = LONG_DEFAULT_VALUE.toLong()
    private val replyIntent = Intent()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_editor)

        val data = intent.extras
        noteId = data.getLong(EXTRA_ID)

        if (noteId != LONG_DEFAULT_VALUE.toLong()) {
            ed_title.setText(data.getString(EXTRA_TITLE))
            ed_content.setText(data.getString(EXTRA_CONTENT))
        }

        bt_save.setOnClickListener {
            if (TextUtils.isEmpty(ed_title.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                replyIntent.putExtra(EXTRA_ID, noteId)
                replyIntent.putExtra(EXTRA_TYPE, TYPE_SAVE_NOTE)
                replyIntent.putExtra(EXTRA_TITLE, ed_title.text.toString())
                replyIntent.putExtra(EXTRA_CONTENT, ed_content.text.toString())
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

        bt_delete.setOnClickListener {
            replyIntent.putExtra(EXTRA_ID, noteId)
            replyIntent.putExtra(EXTRA_TYPE, TYPE_DELETE_NOTE)
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }
    }
}