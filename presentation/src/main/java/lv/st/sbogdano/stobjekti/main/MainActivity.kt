package lv.st.sbogdano.stobjekti.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import lv.st.sbogdano.stobjekti.R
import lv.st.sbogdano.stobjekti.navigation.Navigator
import lv.st.sbogdano.stobjekti.search.provider.StObjectsSuggestionProvider
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        search.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when (item?.itemId) {
            R.id.action_privacy -> {
                showPrivacyPolicy()
                true
            }
            R.id.action_clear_search_history -> {
                clearSearchHistory()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showPrivacyPolicy() {
        navigator.navigateToPrivacyPolicy(this)
    }

    private fun clearSearchHistory() {
        SearchRecentSuggestions(this, StObjectsSuggestionProvider.AUTHORITY, StObjectsSuggestionProvider.MODE)
                .clearHistory()
    }
}
