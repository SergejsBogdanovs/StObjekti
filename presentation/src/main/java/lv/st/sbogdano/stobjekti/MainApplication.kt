package lv.st.sbogdano.stobjekti

import android.app.Application
import com.facebook.stetho.Stetho
import leakcanary.AppWatcher
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

        // LeakCanary
        AppWatcher.config = AppWatcher.config.copy(watchFragmentViews = false)

        // Stetho
        Stetho.initializeWithDefaults(this)

        // Timber
        Timber.plant(Timber.DebugTree())
    }
}

val appModules = listOf(
    presentationModule,
    domainModule,
    dataModule
)