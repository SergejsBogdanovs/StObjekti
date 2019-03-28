package lv.st.sbogdano.domain.gateway

import io.reactivex.Observable
import lv.st.sbogdano.domain.model.StObject

interface Gateway {

    fun getStObjects(params: String): Observable<List<StObject>>
}