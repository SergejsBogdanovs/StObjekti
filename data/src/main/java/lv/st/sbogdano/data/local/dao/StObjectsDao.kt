package lv.st.sbogdano.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import lv.st.sbogdano.data.local.model.StObjectLocalModel

@Dao
interface StObjectsDao {

    @Query("SELECT * FROM StObjects WHERE name = :name")
    fun getAll(name: String): Maybe<List<StObjectLocalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg stObjects: StObjectLocalModel)
}