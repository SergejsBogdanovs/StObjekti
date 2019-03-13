package lv.st.sbogdano.data.repository

import io.reactivex.Observable
import lv.st.sbogdano.data.local.dao.RecentObjectsDao
import lv.st.sbogdano.data.local.model.StObjectLocalModel

class RecentFoundObjectsRepository(
    private val recentObjectsDao: RecentObjectsDao
) {

    fun getAll(): Observable<List<StObjectLocalModel>> = recentObjectsDao.getAll().toObservable()

}