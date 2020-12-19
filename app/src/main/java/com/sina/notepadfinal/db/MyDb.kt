package com.sina.notepadfinal.db

import com.sina.notepadfinal.datamodel.Note

class MyDb {
    val listOfNotes:MutableList<Note> = mutableListOf<Note>()

    fun getList(): MutableList<Note> {
        return listOfNotes
    }
    fun addToList(note: Note) {
        listOfNotes.add(note)
    }

}