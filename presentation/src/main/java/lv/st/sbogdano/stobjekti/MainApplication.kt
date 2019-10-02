package lv.st.sbogdano.stobjekti

import android.app.Application
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import lv.st.sbogdano.stobjekti.internal.injection.dataModule
import lv.st.sbogdano.stobjekti.internal.injection.domainModule
import lv.st.sbogdano.stobjekti.internal.injection.presentationModule
import lv.st.sbogdano.stobjekti.internal.util.FirebaseDatabaseConnectionHandler
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModules)
        }

        // Manage firebase database connection when in background and foreground
        registerActivityLifecycleCallbacks(FirebaseDatabaseConnectionHandler())

        initLeakCanary()
        initTimber()
        initStetho()
    }

    private fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}

val appModules = listOf(
    presentationModule,
    domainModule,
    dataModule
)