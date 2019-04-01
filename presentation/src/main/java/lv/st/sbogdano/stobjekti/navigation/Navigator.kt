package lv.st.sbogdano.stobjekti.navigation

import android.app.Activity
import android.content.Intent
import lv.st.sbogdano.domain.model.StObject
import lv.st.sbogdano.stobjekti.detail.ObjectDetailActivity

class Navigator {

    companion object {
        private val EXTRA_ST_OBJECT = "${ObjectDetailActivity::class.java.`package`.name}.extra.ST_OBJECT"
    }

    fun navigateToObjectDetails(activity: Activity, stObject: StObject) {
        val intent = Intent(activity, ObjectDetailActivity::class.java)
        intent.putExtra(EXTRA_ST_OBJECT, stObject)
        activity.startActivity(intent)
    }

    fun getStObject(activity: Activity) = activity.intent.getSerializableExtra(EXTRA_ST_OBJECT)!!
}