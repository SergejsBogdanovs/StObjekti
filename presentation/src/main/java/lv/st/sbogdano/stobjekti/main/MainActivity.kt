package lv.st.sbogdano.stobjekti.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*
import lv.st.sbogdano.stobjekti.R
import lv.st.sbogdano.stobjekti.internal.util.databinding.ViewBindingAdapters
import lv.st.sbogdano.stobjekti.navigation.Navigator
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        search.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
        }
    }

    private fun nullOrBlankWarning() {
        val error = getString(R.string.empty_search_text_msg)
        ViewBindingAdapters.showLongMessage(window.decorView, error)
    }
}


