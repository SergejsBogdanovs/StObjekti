package lv.st.sbogdano.stobjekti.startup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
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

        viewModel.loadRecentObjects()
    }
}
