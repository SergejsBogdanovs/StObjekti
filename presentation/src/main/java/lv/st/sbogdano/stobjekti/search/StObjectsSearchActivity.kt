package lv.st.sbogdano.stobjekti.search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import lv.st.sbogdano.domain.model.StObject
import lv.st.sbogdano.stobjekti.R
import lv.st.sbogdano.stobjekti.databinding.ActivityStObjectsSearchBinding
import lv.st.sbogdano.stobjekti.internal.util.driveToObject
import lv.st.sbogdano.stobjekti.internal.util.lazyThreadSafetyNone
import lv.st.sbogdano.stobjekti.navigation.Navigator
import lv.st.sbogdano.stobjekti.search.adapter.StObjectListAdapter
import lv.st.sbogdano.stobjekti.search.provider.StObjectsSuggestionProvider
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class StObjectsSearchActivity : AppCompatActivity(), StObjectListAdapter.Callbacks {

    private val viewModelStObjects: StObjectsSearchViewModel by viewModel()

    private val navigator: Navigator by inject()

    private val binder by lazyThreadSafetyNone<ActivityStObjectsSearchBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_st_objects_search)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder.viewModel = viewModelStObjects
        binder.stobjectCallbacks = this
        handleSearch()
    }

    private fun handleSearch() {
        val intent = intent
        when (intent.action) {
            Intent.ACTION_SEARCH -> {
                intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                    viewModelStObjects.searchStObject(query)

                    SearchRecentSuggestions(this,
                            StObjectsSuggestionProvider.AUTHORITY,
                            StObjectsSuggestionProvider.MODE).saveRecentQuery(query, null)
                }
            }
            Intent.ACTION_VIEW -> {
                val selectedSuggestionRowId = intent.dataString
            }
        }
    }

    override fun onItemClick(view: View, item: StObject) {
        navigator.navigateToObjectDetails(this@StObjectsSearchActivity, item)
        //viewModelStObjects.addToRecentObjects(item)
    }

    override fun onDriveBtnClick(view: View, item: StObject) {
        view.driveToObject(item)
    }
}
