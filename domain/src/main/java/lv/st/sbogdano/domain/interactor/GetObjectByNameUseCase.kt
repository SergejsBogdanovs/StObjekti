package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.usecases.ObservableUseCase
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.model.StObject

class GetObjectByNameUseCase(
        schedulers: Schedulers,
        private val gateway: Gateway
) : ObservableUseCase<String, List<StObject>>(schedulers) {

    override fun buildObservable(params: String?): Observable<List<StObject>> {
        return gateway.getStObjects(params)
    }
}