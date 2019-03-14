package lv.st.sbogdano.stobjekti.navigation

import android.app.Activity
import android.content.Intent
import lv.st.sbogdano.stobjekti.search.SearchableActivity

class Navigator {

    companion object {
        private val EXTRA_QUERY = "${SearchableActivity::class.java.`package`.name}.extra.QUERY"
    }

    fun navigateToSearch(activity: Activity, query: String) {
        val intent = Intent(activity, SearchableActivity::class.java)
        intent.putExtra(EXTRA_QUERY, query)
        activity.startActivity(intent)
    }

    fun getQuery(activity: Activity) = activity.intent.getStringExtra(EXTRA_QUERY)!!
}