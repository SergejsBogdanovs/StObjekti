package lv.st.sbogdano.data.repository.mapper

import com.google.firebase.database.DataSnapshot
import lv.st.sbogdano.domain.model.StObject

class StObjectsListMapper {

    private fun toStObject(dataSnapshot: DataSnapshot) = dataSnapshot.getValue(StObject::class.java)!!

    fun toList(items: List<DataSnapshot>) = items.map { toStObject(it) }
}