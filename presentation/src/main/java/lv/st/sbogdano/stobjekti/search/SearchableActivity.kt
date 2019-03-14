package lv.st.sbogdano.stobjekti.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import lv.st.sbogdano.stobjekti.R
import lv.st.sbogdano.stobjekti.navigation.Navigator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchableActivity : AppCompatActivity() {

    private val viewModel: SearchViewModel by viewModel()

    private val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchable)

//        // Verify the action and get the query
//        if (Intent.ACTION_SEARCH == intent.action) {
//            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
//                doMySearch(query)
//            }
//        }

        doMySearch(navigator.getQuery(this))
    }

    private fun doMySearch(query: String) {
        viewModel.searchStObject(query)
    }
}
