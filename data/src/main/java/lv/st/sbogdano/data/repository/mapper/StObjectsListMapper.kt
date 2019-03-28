package lv.st.sbogdano.data.repository.mapper

import lv.st.sbogdano.data.local.model.StObjectLocalModel
import lv.st.sbogdano.domain.model.StObject

class StObjectsListMapper {

    private fun toStObject(stObject: StObject) = StObjectLocalModel(
            stObject.address,
            stObject.construction,
            stObject.year_of_manufacture,
            stObject.in_service_from,
            stObject.name,
            stObject.city_region,
            stObject.technical_object,
            stObject.type,
            stObject.x,
            stObject.y
    )

    fun toLocal(items: List<StObject>) = items.map { toStObject(it) }
}