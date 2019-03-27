package lv.st.sbogdano.data.gateway.mapper

import lv.st.sbogdano.data.local.model.StObjectLocalModel
import lv.st.sbogdano.domain.model.StObject

class GatewayMapper {

    fun toDomainModel(stObjectLocalModel: StObjectLocalModel) =  StObject(
        stObjectLocalModel.address,
        stObjectLocalModel.construction,
        stObjectLocalModel.year_of_manufacture,
        stObjectLocalModel.in_service_from,
        stObjectLocalModel.name,
        stObjectLocalModel.city_region,
        stObjectLocalModel.technical_object,
        stObjectLocalModel.type,
        stObjectLocalModel.x,
        stObjectLocalModel.y
    )

    fun toLocalModel(stObjects: StObject): StObjectLocalModel = StObjectLocalModel(
        stObjects.address,
        stObjects.construction,
        stObjects.year_of_manufacture,
        stObjects.in_service_from,
        stObjects.name,
        stObjects.city_region,
        stObjects.technical_object,
        stObjects.type,
        stObjects.x,
        stObjects.y
    )
}