package lv.st.sbogdano.stobjekti.internal.util.databinding

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import lv.st.sbogdano.stobjekti.internal.util.fade

object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("fadeView")
    fun fadeView(view: View, show: Boolean) {
        view.fade(show)
    }

    @JvmStatic
    @BindingAdapter("showLongMessage", "callback", requireAll = false)
    fun showLongMessage(view: View, text: String?, callback: BaseTransientBottomBar.BaseCallback<Snackbar>? = null) {
        text?.let {
            val snackbar = Snackbar.make(view, it, Snackbar.LENGTH_LONG)
            if (callback != null) snackbar.addCallback(callback)
            snackbar.show()
        }
    }

}