package lv.st.sbogdano.data.gateway.mapper

import lv.st.sbogdano.data.local.model.StObjectLocalModel
import lv.st.sbogdano.domain.model.StObject

class GatewayMapper {

    fun toDomainModel(stObjectLocalModel: StObjectLocalModel) =  StObject(
        stObjectLocalModel.address,
        stObjectLocalModel.construction,
        stObjectLocalModel.date_of_manufacture,
        stObjectLocalModel.in_service_from,
        stObjectLocalModel.name,
        stObjectLocalModel.region_city,
        stObjectLocalModel.technical_object,
        stObjectLocalModel.type,
        stObjectLocalModel.x,
        stObjectLocalModel.y
    )
}