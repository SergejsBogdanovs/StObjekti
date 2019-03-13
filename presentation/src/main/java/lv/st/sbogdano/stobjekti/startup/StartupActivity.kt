package lv.st.sbogdano.stobjekti.startup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import lv.st.sbogdano.stobjekti.databinding.ActivityStartupBinding
import lv.st.sbogdano.stobjekti.R
import lv.st.sbogdano.stobjekti.internal.util.lazyThreadSafetyNone
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartupActivity : AppCompatActivity() {

    private val viewModel: StartupViewModel by viewModel()

    private val binder by lazyThreadSafetyNone<ActivityStartupBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_startup)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binder.viewModel = viewModel
        viewModel.loadRecentObjects()

        binder.btnSearch.setOnClickListener {
            binder.inputSearch.text?.run {
                viewModel.searchStObject(binder.inputSearch.text.toString())
            }
        }
    }
}

