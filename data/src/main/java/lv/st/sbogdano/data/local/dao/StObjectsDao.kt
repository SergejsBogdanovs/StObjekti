package lv.st.sbogdano.data.local.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import lv.st.sbogdano.data.local.model.StObjectLocalModel

@Dao
interface StObjectsDao {

    @Query("SELECT * FROM StObjects WHERE name = :name")
    fun getAll(name: String): Maybe<List<StObjectLocalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg stObjects: StObjectLocalModel)
}