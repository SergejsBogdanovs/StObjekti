package lv.st.sbogdano.data.local.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import lv.st.sbogdano.data.local.model.StObjectLocalModel

@Dao
interface RecentObjectsDao {

    @Query("SELECT * FROM RecentStObjects")
    fun getAll(): Maybe<List<StObjectLocalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToRecentFoundObjects(stObjectLocalModel: StObjectLocalModel): Completable

    @Query("SELECT COUNT(*) FROM RecentStObjects")
    fun getCount(): Int

    @Delete
    fun delete(stObjectLocalModel: StObjectLocalModel): Completable

    @Query("SELECT * FROM RecentStObjects ORDER BY created_at ASC LIMIT 1")
    fun getOldestRecord(): StObjectLocalModel
}