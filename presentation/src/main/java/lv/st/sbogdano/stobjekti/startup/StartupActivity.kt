package lv.st.sbogdano.stobjekti.startup

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_startup.*
import lv.st.sbogdano.domain.model.StObject
import lv.st.sbogdano.stobjekti.R
import lv.st.sbogdano.stobjekti.databinding.ActivityStartupBinding
import lv.st.sbogdano.stobjekti.internal.util.lazyThreadSafetyNone
import lv.st.sbogdano.stobjekti.navigation.Navigator
import lv.st.sbogdano.stobjekti.startup.adapter.StObjectListAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartupActivity : AppCompatActivity(), StObjectListAdapter.Callbacks {

    private val viewModel: StartupViewModel by viewModel()

    private val navigator: Navigator by inject()

    private val binder by lazyThreadSafetyNone<ActivityStartupBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_startup)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder.viewModel = viewModel
        binder.stobjectCallbacks= this

        viewModel.loadRecentObjects()

        search.apply {
            isActivated = true
            onActionViewExpanded()
            clearFocus()
        }

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchStObject(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onItemClick(view: View, item: StObject) {

    }
}

