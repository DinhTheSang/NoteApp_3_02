package com.example.noteapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_note_editor.view.*
import kotlinx.android.synthetic.main.note_item.view.*
import java.io.File

class NoteListAdapter internal constructor(context: Context): RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notes = emptyList<Note>()
    private lateinit var mListener: NoteItemClickListener
    private lateinit var mDisplayColor: DisplayColorNote

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.tv_title
        var tvContent: TextView = itemView.tv_content
        var tvDayModified: TextView = itemView.tv_day_modified
        var llNoteElement: LinearLayout = itemView.ll_note_element
        var btLineNoteElement: Button = itemView.bt_line_note_element

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = inflater.inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val current = notes[position]
        holder.tvTitle.text = current.title
        holder.tvContent.text = current.content
        holder.tvDayModified.text = current.dateModified.substring(0, 11)
        holder.llNoteElement.background = mDisplayColor.getLlDrawable(current.color)
        holder.btLineNoteElement.background = mDisplayColor.getBtDrawable(current.color)

        holder.itemView.setOnClickListener {
            mListener.onItemCLicked(position)
        }
        holder.itemView.setOnLongClickListener {
            mListener.onItemLongCLicked(position)
            true
        }
    }

    internal fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    internal fun setListener(mListener: NoteItemClickListener) {
        this.mListener = mListener
    }

    internal fun setDisplayColor(mDisplayColor: DisplayColorNote) {
        this.mDisplayColor = mDisplayColor
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}