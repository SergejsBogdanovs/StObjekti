package lv.st.sbogdano.stobjekti.search

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import lv.st.sbogdano.domain.interactor.GetObjectByNameUseCase
import lv.st.sbogdano.domain.interactor.RecentFoundObjectsGetAllUseCase
import lv.st.sbogdano.domain.model.StObject
import lv.st.sbogdano.stobjekti.R
import lv.st.sbogdano.stobjekti.internal.util.BaseAndroidViewModel
import timber.log.Timber

class SearchViewModel(
    private val context: Context,
    private val getObjectByNameUseCase: GetObjectByNameUseCase
) : BaseAndroidViewModel(context.applicationContext as Application) {

    val loading = ObservableBoolean()
    val error = ObservableField<String>()
    val empty = ObservableBoolean()
    val stObjects = ObservableArrayList<StObject>()

    fun searchStObject(name: String) = addDisposable(findStObject(name))

    private fun findStObject(name: String): Disposable {
        return getObjectByNameUseCase.execute(name)
            .subscribeWith(object : DisposableObserver<List<StObject>>() {

                override fun onNext(t: List<StObject>) {
                    Timber.v(t.toString())
                    stObjects.addAll(t)
                }

                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                }
            })
    }

}
