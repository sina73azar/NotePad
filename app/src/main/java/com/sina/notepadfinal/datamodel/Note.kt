package com.sina.notepadfinal.datamodel

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.sina.notepadfinal.datamodel.Note.CREATOR.TABLE_NAME
import com.sina.notepadfinal.datamodel.Note.Companion.TABLE_NAME
import kotlinx.android.parcel.Parcelize


@Entity(tableName =TABLE_NAME,
        indices =[Index(value = ["title","noteValue","date"],unique = true)] )
@Parcelize
data class Note(@PrimaryKey(autoGenerate = true)
                val did:Int,
                @ColumnInfo(name = "title")val title: String,
                @ColumnInfo(name = "noteValue")val noteValue: String = "",
                @ColumnInfo(name = "date")val date: Long):Parcelable{

    companion object{
        const val TABLE_NAME="note_table"
    }





}


