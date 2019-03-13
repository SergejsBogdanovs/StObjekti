package lv.st.sbogdano.data.gateway

import io.reactivex.Observable
import lv.st.sbogdano.data.gateway.mapper.GatewayMapper
import lv.st.sbogdano.data.repository.RecentFoundObjectsRepository
import lv.st.sbogdano.data.repository.StObjectsRepository
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.model.StObject

class GatewayImpl(
    private val recentFoundObjectsRepository: RecentFoundObjectsRepository,
    private val stObjectsRepository: StObjectsRepository
) : Gateway {

    private val mapper = GatewayMapper()

    override fun getRecentStObjects(): Observable<List<StObject>> =
        recentFoundObjectsRepository.getAll()
            .doOnError { println("Getting recent objects error") }
            .map { it.map { stObjectLocalModel -> mapper.toDomainModel(stObjectLocalModel) } }

    override fun getStObjects(params: String?): Observable<List<StObject>> =
        stObjectsRepository.getObject(params)
            .doOnError { throwable -> println(throwable.message.toString()) }
}