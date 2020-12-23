package com.sina.notepadfinal.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sina.notepadfinal.datamodel.Note
import com.sina.notepadfinal.datamodel.Note.CREATOR.TABLE_NAME


@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insert(note: Note): Int
    @Update(onConflict = OnConflictStrategy.IGNORE)
     fun update(note: Note)
    @Query("Delete from $TABLE_NAME")
     fun deleteAll()



    @Query("DELETE FROM $TABLE_NAME WHERE did = :noteId")
    fun deleteById(noteId: Int)

    @Query("SELECT COUNT(*) FROM $TABLE_NAME")
    fun getCount(): LiveData<Int?>?
}