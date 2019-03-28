package lv.st.sbogdano.stobjekti.search

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import lv.st.sbogdano.domain.interactor.GetObjectByNameUseCase
import lv.st.sbogdano.domain.model.StObject
import lv.st.sbogdano.stobjekti.R
import lv.st.sbogdano.stobjekti.internal.util.BaseAndroidViewModel
import timber.log.Timber

class StObjectsSearchViewModel(
    private val context: Context,
    private val getObjectByNameUseCase: GetObjectByNameUseCase
) : BaseAndroidViewModel(context.applicationContext as Application) {

    val loading = ObservableBoolean()
    val error = ObservableField<String>()
    val empty = ObservableBoolean()
    val result = ObservableArrayList<StObject>()

    fun searchStObject(name: String) = addDisposable(findStObject(name))

    private fun findStObject(name: String): Disposable {
        return getObjectByNameUseCase.execute(name)
            .subscribeWith(object : DisposableObserver<List<StObject>>() {

                override fun onStart() {
                    super.onStart()
                    loading.set(true)
                    empty.set(false)
                }

                override fun onNext(t: List<StObject>) {
                    loading.set(false)
                    result.clear()
                    result.addAll(t)
                    empty.set(t.isEmpty())
                }

                override fun onError(e: Throwable) {
                    loading.set(false)
                    error.set(e.localizedMessage ?: e.message ?: context.getString(R.string.unknown_error))
                }

                override fun onComplete() {
                }
            })
    }
}
