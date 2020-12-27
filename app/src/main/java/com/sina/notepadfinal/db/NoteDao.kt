package com.sina.notepadfinal.db

import androidx.room.*
import com.sina.notepadfinal.datamodel.Note

@Dao
interface NoteDao {

    @Insert
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("SELECT * FROM note_table WHERE note_id = :id")
    fun getNote(id:Int): Note

    @Query("SELECT * FROM note_table ")
    fun getNoteList():MutableList<Note>
    @Query("Select Count (*) from note_table")
    fun getCount():Int
//    @Query("SELECT * FROM note_table WHERE note_title LIKE '%:keyword%'")
//    fun searchNote(keyword:String):List<Note>

//    @Query("SELECT * FROM note_table ORDER BY note_id LIMIT 1")
//    fun getLastNote(): Note

}