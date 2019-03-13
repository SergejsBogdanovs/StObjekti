package lv.st.sbogdano.stobjekti

import android.app.Application
import lv.st.sbogdano.stobjekti.internal.injection.*
import org.koin.android.ext.android.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree



class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin(this, appComponent)

        Timber.plant(DebugTree())
    }
}

val appComponent = listOf(
    presentationModule,
    domainModule,
    dataModule
)