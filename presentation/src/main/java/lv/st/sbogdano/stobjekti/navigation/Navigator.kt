package lv.st.sbogdano.stobjekti.navigation

import android.app.Activity
import android.content.Intent
import lv.st.sbogdano.domain.model.StObject
import lv.st.sbogdano.stobjekti.detail.StObjectDetailActivity
import lv.st.sbogdano.stobjekti.main.MainActivity
import lv.st.sbogdano.stobjekti.main.PrivacyPolicyActivity

class Navigator {

    companion object {
        private val EXTRA_ST_OBJECT = "${StObjectDetailActivity::class.java.`package`?.name}.extra.ST_OBJECT"
    }

    fun navigateToMainActivity(activity: Activity) {
        val intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)
    }

    fun navigateToObjectDetails(activity: Activity, stObject: StObject) {
        val intent = Intent(activity, StObjectDetailActivity::class.java)
        intent.putExtra(EXTRA_ST_OBJECT, stObject)
        activity.startActivity(intent)
    }

    fun navigateToPrivacyPolicy(activity: Activity) {
        val intent = Intent(activity, PrivacyPolicyActivity::class.java)
        activity.startActivity(intent)
    }

    fun getStObject(activity: Activity) = activity.intent.getSerializableExtra(EXTRA_ST_OBJECT)!!
}