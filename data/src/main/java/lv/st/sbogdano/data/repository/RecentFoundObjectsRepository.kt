package lv.st.sbogdano.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import kotlinx.coroutines.*
import lv.st.sbogdano.data.local.dao.RecentObjectsDao
import lv.st.sbogdano.data.local.model.StObjectLocalModel
import kotlin.coroutines.CoroutineContext

class RecentFoundObjectsRepository(
    private val recentObjectsDao: RecentObjectsDao
) : CoroutineScope {

    private val pendingJob = Job()

    override val coroutineContext: CoroutineContext
        get() = (Dispatchers.Default + pendingJob)

    fun getAll(): Observable<List<StObjectLocalModel>> = recentObjectsDao.getAll().toObservable()

    fun add(stObjectLocalModel: StObjectLocalModel): Completable {
        if (recentObjectsDao.getCount() >= 2) {
            recentObjectsDao.delete(recentObjectsDao.getOldestRecord())
            return recentObjectsDao.addToRecentFoundObjects(stObjectLocalModel)
        } else {
            return recentObjectsDao.addToRecentFoundObjects(stObjectLocalModel)
        }
    }

}