package lv.st.sbogdano.stobjekti.search

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
import lv.st.sbogdano.stobjekti.search.adapter.StObjectListAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity(), StObjectListAdapter.Callbacks {

    private val viewModel: SearchViewModel by viewModel()

    private val navigator: Navigator by inject()

    private val binder by lazyThreadSafetyNone<ActivityStartupBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_startup)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
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
        navigator.navigateToObjectDetails(this@SearchActivity, item)
        viewModel.addToRecentObjects(item)
    }
}

