package lv.st.sbogdano.data.repository

import com.google.firebase.database.DatabaseReference
import durdinapps.rxfirebase2.DataSnapshotMapper
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import lv.st.sbogdano.data.local.dao.StObjectsDao
import lv.st.sbogdano.data.local.model.StObjectLocalModel
import lv.st.sbogdano.data.utils.getFormattedName
import java.util.concurrent.TimeoutException

class StObjectsRepository(
    private val remoteStObjectsDatabase: DatabaseReference,
    private val localStObjectsDatabase: StObjectsDao
) {

    fun getObject(name: String): Observable<List<StObjectLocalModel>> {

        val local = localStObjectsDatabase.getAll(getFormattedName(name))

        val remote =
            RxFirebaseDatabase.observeSingleValueEvent(
                remoteStObjectsDatabase.orderByChild("name").equalTo(getFormattedName(name)),
                DataSnapshotMapper.listOf(StObjectLocalModel::class.java))
                .doOnComplete { throw TimeoutException() }
                .map { list ->
                    list.distinctBy { it.technical_object }.distinctBy { it.city_region }.distinctBy { it.address }
                }
                .observeOn(Schedulers.newThread())
                .doOnSuccess { localStObjectsDatabase.insert(*it.toTypedArray()) }

        return Maybe.concat(local, remote)
            .firstElement()
            .toObservable()
    }

}