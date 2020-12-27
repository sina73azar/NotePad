package com.sina.notepadfinal.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sina.notepadfinal.datamodel.Note
import com.sina.notepadfinal.databinding.NotesItemBinding


class MyRecyclerAdapter(
      var listNotes: MutableList<Note>,
    private val clickListener: ((note:Note,pos:Int, view: View) -> Unit),
    private val longClickListener: ((note:Note, view: View) -> Boolean),
) : RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: NotesItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NotesItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = listNotes[position]
        holder.binding.tvTitle.text = item.title
        holder.binding.tvShowNoteValue.text = item.noteValue
        holder.binding.tvDate.text = item.date.reformat()

        //handle on clicks

//        holder.binding.tvShowNoteValue.setOnClickListener(listener)
//        holder.binding.tvTitle.setOnClickListener(listener)
        holder.binding.imgDelete.setOnClickListener { clickListener(item,position, it) }
        holder.binding.imgDelete.setOnLongClickListener { longClickListener(item, it) }

        holder.binding.tvShowNoteValue.setOnClickListener { clickListener (item,position,it)}
        holder.binding.tvShowNoteValue.setOnLongClickListener { longClickListener(item, it) }

        holder.binding.tvTitle.setOnClickListener{clickListener(item,position,it)}
        holder.binding.tvTitle.setOnLongClickListener{longClickListener(item,it)}

        holder.binding.imgEdit.setOnLongClickListener{longClickListener(item,it)}
        holder.binding.imgEdit.setOnClickListener{clickListener(item,position,it)}





    }

    override fun getItemCount(): Int {
        return listNotes.size
    }

}