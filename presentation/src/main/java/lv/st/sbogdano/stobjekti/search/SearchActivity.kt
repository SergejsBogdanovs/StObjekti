package lv.st.sbogdano.stobjekti.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_search.*
import lv.st.sbogdano.domain.model.StObject
import lv.st.sbogdano.stobjekti.R
import lv.st.sbogdano.stobjekti.databinding.ActivitySearchBinding
import lv.st.sbogdano.stobjekti.internal.util.databinding.ViewBindingAdapters
import lv.st.sbogdano.stobjekti.internal.util.driveToObject
import lv.st.sbogdano.stobjekti.internal.util.lazyThreadSafetyNone
import lv.st.sbogdano.stobjekti.navigation.Navigator
import lv.st.sbogdano.stobjekti.search.adapter.StObjectListAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity(), StObjectListAdapter.Callbacks {

    private val viewModel: SearchViewModel by viewModel()

    private val navigator: Navigator by inject()

    private val binder by lazyThreadSafetyNone<ActivitySearchBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_search)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binder.viewModel = viewModel
        binder.stobjectCallbacks = this

        viewModel.loadRecentObjects()

        search.apply {
            isActivated = true
            onActionViewExpanded()
            clearFocus()
        }

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) viewModel.searchStObject(query) else nullOrBlankWarning()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun nullOrBlankWarning() {
        val error = getString(R.string.empty_search_text_msg)
        ViewBindingAdapters.showLongMessage(window.decorView, error)
    }

    override fun onItemClick(view: View, item: StObject) {
        navigator.navigateToObjectDetails(this@SearchActivity, item)
        viewModel.addToRecentObjects(item)
    }

    override fun onDriveBtnClick(view: View, item: StObject) {
        view.driveToObject(item)
    }
}


