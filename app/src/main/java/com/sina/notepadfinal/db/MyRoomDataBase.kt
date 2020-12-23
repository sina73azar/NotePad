package com.sina.notepadfinal.db

import android.content.Context
import android.util.Log
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.sina.notepadfinal.datamodel.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [Note::class],version = 1,exportSchema = false)
abstract class MyRoomDataBase : RoomDatabase() {
    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun clearAllTables() {
        TODO("Not yet implemented")
    }
    abstract fun noteDao():NoteDao

    companion object {
        private var INSTANCE: MyRoomDataBase? = null
        private const val DB_NAME = "notes.db"

        fun getDatabase(context: Context): MyRoomDataBase {
            if (INSTANCE == null) {
                synchronized(MyRoomDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext, MyRoomDataBase::class.java, DB_NAME).build()
                            //.allowMainThreadQueries() // Uncomment if you don't want to use RxJava or coroutines just yet (blocks UI thread)
//                            .addCallback(object : Callback() {
//                                override fun onCreate(db: SupportSQLiteDatabase) {
//                                    super.onCreate(db)
//                                    Log.d("MoviesDatabase", "populating with data...")
//                                    GlobalScope.launch(Dispatchers.IO) { rePopulateDb(INSTANCE) }
//                                }
//                            }).build()
                    }
                }
            }

            return INSTANCE!!
        }
}



}