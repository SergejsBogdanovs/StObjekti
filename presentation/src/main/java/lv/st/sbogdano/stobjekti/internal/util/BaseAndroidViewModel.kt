package lv.st.sbogdano.stobjekti.internal.util

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

@SuppressLint("StaticFieldLeak")
abstract class BaseAndroidViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable += disposable
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}

private operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    this.add(disposable)
}
