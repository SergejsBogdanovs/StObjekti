package lv.st.sbogdano.stobjekti

import android.app.Application
import androidx.multidex.MultiDex
import com.facebook.stetho.Stetho
import lv.st.sbogdano.stobjekti.internal.injection.dataModule
import lv.st.sbogdano.stobjekti.internal.injection.domainModule
import lv.st.sbogdano.stobjekti.internal.injection.presentationModule
import lv.st.sbogdano.stobjekti.internal.util.FirebaseDatabaseConnectionHandler
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)

        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModules)
        }

        // Manage firebase database connection when in background and foreground
        registerActivityLifecycleCallbacks(FirebaseDatabaseConnectionHandler())

        Timber.plant(DebugTree())

        Stetho.initializeWithDefaults(this)
    }
}

val appModules = listOf(
    presentationModule,
    domainModule,
    dataModule
)