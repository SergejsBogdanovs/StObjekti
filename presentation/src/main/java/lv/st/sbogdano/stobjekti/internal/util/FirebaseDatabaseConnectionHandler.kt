package lv.st.sbogdano.stobjekti.internal.util

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.os.Handler
import com.google.firebase.database.FirebaseDatabase

class FirebaseDatabaseConnectionHandler : Application.ActivityLifecycleCallbacks {

    private var count = 0
    private val delayedTimeMillis: Long = 5000 // change this if you want different timeout
    private val mHandler = Handler()

    override fun onActivityPaused(activity: Activity?) {}

    override fun onActivityResumed(activity: Activity?) {}

    override fun onActivityStarted(activity: Activity?) {
        count++
        if (count > 0)
            FirebaseDatabase.getInstance().goOnline()
    }

    override fun onActivityDestroyed(activity: Activity?) {}

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {}

    override fun onActivityStopped(activity: Activity?) {
        count--
        if (count == 0) {

            mHandler.postDelayed({
                // just make sure that in the defined seconds no other activity is brought to front
                if (count == 0) {
                    FirebaseDatabase.getInstance().goOffline()
                }
            }, delayedTimeMillis)
        }
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {}
}