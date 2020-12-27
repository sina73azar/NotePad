package com.sina.notepadfinal.datamodel

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.sina.notepadfinal.datamodel.Note.Companion.TABLE_NAME
import kotlinx.android.parcel.Parcelize

@Entity(tableName = TABLE_NAME)
@Parcelize
data class Note(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "noteValue") val noteValue: String = "",
    @ColumnInfo(name = "date") val date: Long,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    val id: Int=0
) : Parcelable {

    companion object {
        const val TABLE_NAME = "note_table"
    }


}


