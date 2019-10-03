package lv.st.sbogdano.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import lv.st.sbogdano.data.local.dao.StObjectsDao
import lv.st.sbogdano.data.local.model.StObjectLocalModel
import androidx.sqlite.db.SupportSQLiteDatabase
import android.icu.lang.UCharacter.GraphemeClusterBreak.V



@Database(entities = [StObjectLocalModel::class], version = 1, exportSchema = false)
abstract class StObjectsDatabase : RoomDatabase() {

    abstract fun recentObjectsDao(): StObjectsDao

    companion object {

//        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                // Since we didn't alter the table, there's nothing else to do here.
//            }
//        }

        fun newInstance(context: Context): StObjectsDatabase {
            return Room.databaseBuilder(context, StObjectsDatabase::class.java, "stobjects.db")
                //.addMigrations(MIGRATION_1_2)
                .build()
        }
    }
}