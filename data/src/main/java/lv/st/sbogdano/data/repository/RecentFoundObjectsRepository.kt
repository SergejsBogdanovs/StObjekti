package lv.st.sbogdano.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import lv.st.sbogdano.data.local.dao.RecentObjectsDao
import lv.st.sbogdano.data.local.model.StObjectLocalModel
import kotlin.concurrent.thread

class RecentFoundObjectsRepository(
    private val recentObjectsDao: RecentObjectsDao
) {

    fun getAll(): Observable<List<StObjectLocalModel>> = recentObjectsDao.getAll().toObservable()

    fun add(stObjectLocalModel: StObjectLocalModel): Completable {
        return recentObjectsDao.addToRecentFoundObjects(stObjectLocalModel)
    }

}