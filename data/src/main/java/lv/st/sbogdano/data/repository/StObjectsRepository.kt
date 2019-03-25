package lv.st.sbogdano.data.repository

import com.google.firebase.database.DatabaseReference
import durdinapps.rxfirebase2.DataSnapshotMapper
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Observable
import lv.st.sbogdano.data.utils.getFormattedName
import lv.st.sbogdano.domain.model.StObject

class StObjectsRepository(private val remoteStObjectsDatabase: DatabaseReference) {

    fun getObject(name: String): Observable<List<StObject>> {

        val result =
                RxFirebaseDatabase.observeSingleValueEvent(
                        remoteStObjectsDatabase.orderByChild("name").equalTo(getFormattedName(name)),
                        DataSnapshotMapper.listOf(StObject::class.java))
                        .toObservable()

        return result
    }

}