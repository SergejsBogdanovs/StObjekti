package lv.st.sbogdano.domain.interactor

import io.reactivex.Completable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.model.StObject
import lv.st.sbogdano.domain.usecases.CompletableUseCase

class AddToRecentFoundObjectsUseCase(
    schedulers: Schedulers,
    private val gateway: Gateway
) : CompletableUseCase<StObject, Unit>(schedulers) {

    override fun buildCompletable(params: StObject): Completable {
        return gateway.addToRecentFoundObjects(params)
    }
}