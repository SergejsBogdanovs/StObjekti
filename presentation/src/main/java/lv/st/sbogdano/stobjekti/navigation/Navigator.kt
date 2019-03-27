package lv.st.sbogdano.stobjekti.navigation

import android.app.Activity
import android.content.Intent
import lv.st.sbogdano.domain.model.StObject
import lv.st.sbogdano.stobjekti.detail.ObjectDetailActivity
import lv.st.sbogdano.stobjekti.search.StObjectsSearchActivity

class Navigator {

    companion object {
        private val EXTRA_ST_OBJECT = "${ObjectDetailActivity::class.java.`package`.name}.extra.ST_OBJECT"
        private val EXTRA_QUERY = "${StObjectsSearchActivity::class.java.`package`.name}.extra.QUERY"
    }

    fun navigateToObjectDetails(activity: Activity, stObject: StObject) {
        val intent = Intent(activity, ObjectDetailActivity::class.java)
        intent.putExtra(EXTRA_ST_OBJECT, stObject)
        activity.startActivity(intent)
    }

    fun navigateToObjectSearchActivity(activity: Activity, query: String?) {
        val intent = Intent(activity, StObjectsSearchActivity::class.java)
        intent.action = Intent.ACTION_SEARCH
        intent.putExtra(EXTRA_QUERY, query)
        activity.startActivity(intent)
    }

    fun getStObject(activity: Activity) = activity.intent.getSerializableExtra(EXTRA_ST_OBJECT)!!
    fun getQuery(activity: Activity) = activity.intent.getStringExtra(EXTRA_QUERY)!!
}