package lv.st.sbogdano.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import lv.st.sbogdano.data.local.dao.StObjectsDao
import lv.st.sbogdano.data.local.model.StObjectLocalModel

@Database(entities = [StObjectLocalModel::class], version = 1, exportSchema = false)
abstract class StObjectsDatabase : RoomDatabase() {

    abstract fun recentObjectsDao(): StObjectsDao

    companion object {
        fun newInstance(context: Context): StObjectsDatabase {
            return Room.databaseBuilder(context, StObjectsDatabase::class.java, "stobjects.db").build()
        }
    }
}