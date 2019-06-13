package com.example.noteapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*

class NoteListAdapter internal constructor(context: Context): RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notes = emptyList<Note>()
    private lateinit var mListener: NoteItemClickListener

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvTitle = itemView.tv_title
        var tvContent = itemView.tv_content
        var tvDayModified = itemView.tv_day_modified
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = inflater.inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val current = notes[position]
        holder.tvTitle.text = current.title
        holder.tvContent.text = current.content
        holder.tvDayModified.text = current.dateModified

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

    override fun getItemCount(): Int {
        return notes.size
    }
}