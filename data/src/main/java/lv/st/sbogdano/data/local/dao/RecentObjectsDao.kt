package lv.st.sbogdano.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Maybe
import lv.st.sbogdano.data.local.model.StObjectLocalModel

@Dao
interface RecentObjectsDao {

    @Query("SELECT * FROM RecentStObjects")
    fun getAll(): Maybe<List<StObjectLocalModel>>

}