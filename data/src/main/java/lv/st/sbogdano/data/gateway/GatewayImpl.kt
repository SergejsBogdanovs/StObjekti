package lv.st.sbogdano.data.gateway

import io.reactivex.Observable
import lv.st.sbogdano.data.gateway.mapper.GatewayMapper
import lv.st.sbogdano.data.repository.RecentObjectsRepository
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.model.StObject

class GatewayImpl(
    private val recentObjectsRepository: RecentObjectsRepository
) : Gateway {

    private val mapper = GatewayMapper()

    override fun getRecentStObjects(): Observable<List<StObject>> =
        recentObjectsRepository.getAll()
            .doOnError{ println("Getting recent objects error") }
            .map{ it.map { stObjectLocalModel -> mapper.toDomainModel(stObjectLocalModel) } }
}