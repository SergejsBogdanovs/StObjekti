package lv.st.sbogdano.stobjekti.search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_stobjects_search.*
import lv.st.sbogdano.domain.model.StObject
import lv.st.sbogdano.stobjekti.R
import lv.st.sbogdano.stobjekti.databinding.ActivityStobjectsSearchBinding
import lv.st.sbogdano.stobjekti.internal.util.driveToStObject
import lv.st.sbogdano.stobjekti.internal.util.lazyThreadSafetyNone
import lv.st.sbogdano.stobjekti.navigation.Navigator
import lv.st.sbogdano.stobjekti.search.adapter.StObjectListAdapter
import lv.st.sbogdano.stobjekti.search.provider.StObjectsSuggestionProvider
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class StObjectsSearchActivity : AppCompatActivity(), StObjectListAdapter.Callbacks {

    private val viewModelStObjects: StObjectsSearchViewModel by viewModel()

    private val navigator: Navigator by inject()

    private val binder by lazyThreadSafetyNone<ActivityStobjectsSearchBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_stobjects_search)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder.viewModel = viewModelStObjects
        binder.stobjectCallbacks = this

        setupToolbar()

        handleSearch()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar_stobject_search)
        supportActionBar!!.apply {
            setDisplayHomeAsUpEnabled(true)
        }
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
        }
    }

    override fun onItemClick(view: View, item: StObject) =
        navigator.navigateToObjectDetails(this@StObjectsSearchActivity, item)

    override fun onDriveBtnClick(view: View, item: StObject) = view.driveToStObject(item)

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
